package top.codejouney.learn.zentao;

import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author Antvictor
 * @date 2025/7/29
 **/
public class SyncZentro {
    public static void main(String[] args) {
        //
        String toProject = "4";
        String toAccount = "";
        String toAccountName = "";
        String toTaskName = "";
        String fromTaskId = "8653";

        String toUrl = "";
        String toUsername = "";
        String toPassword = "";

        String fromUrl = "";
        String fromUsername = "root";
        String fromPassword = "123456";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 建立连接
            Connection conn = DriverManager.getConnection(fromUrl, fromUsername, fromPassword);
            Connection toConn = DriverManager.getConnection(toUrl, toUsername, toPassword);
            ResultSet resultSet = queryTask(toProject, toAccount,
                    toTaskName, fromTaskId,toAccountName, conn );
            String toTaskId = insertTask(resultSet, toConn);
            ResultSet taskEstimateList = queryTaskEstimate(toTaskId, fromTaskId, toAccount, conn);
            insertTaskEstimate(taskEstimateList, toConn);
            toConn.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertTaskEstimate(ResultSet taskEstimateList, Connection conn) {
        String insertEsSql = "INSERT INTO zt_taskestimate ( task, date, `left`,  account, work,consumed) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement esPs = null;
        try {
            esPs = conn.prepareStatement(insertEsSql);
            int columnCount = taskEstimateList.getMetaData().getColumnCount();
            while (taskEstimateList.next()) {
                for (int i = 1; i <= columnCount ; i++) {
                    esPs.setString(i, taskEstimateList.getString(i));
                }
                esPs.addBatch();
            }
            esPs.executeBatch();
            esPs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static String insertTask(ResultSet resultSet, Connection conn) {
        String insertSql = "INSERT INTO zt_task(parent, project, module, story, storyVersion, fromBug, name, type, pri, estimate, consumed, `left`,\n" +
                "                    deadline, status, subStatus, color, mailto, `desc`, openedBy, openedDate, assignedTo, assignedDate,\n" +
                "                    estStarted, realStarted, finishedBy, finishedDate, finishedList, canceledBy, canceledDate, closedBy,\n" +
                "                    closedDate, closedReason, lastEditedBy, lastEditedDate, deleted)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
                "        ?, ?, ?, ?, ?, ?,\n" +
                "        ?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
                "        ?, ?)";
        PreparedStatement ps = null;
        String id = "";
        try {
            ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            // 获取列总量
            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    ps.setString(i, resultSet.getString(i));
                }
            }
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }
    private static ResultSet queryTask(String project, String account,
                                       String name, String taskId,String accountName,
                                              Connection conn) {

        String query = "select parent\n,'" +
                project +
                "'      as project\n" +
                "     , module\n" +
                "     , story\n" +
                "     , storyVersion\n" +
                "     , fromBug\n, '" +
                name + "-" + accountName +
                "'      name\n" +
                "     , type\n" +
                "     , pri\n" +
                "     , estimate\n" +
                "     , consumed\n" +
                "     , `left`\n" +
                "     , deadline\n" +
                "     , status\n" +
                "     , subStatus\n" +
                "     , color\n" +
                "     , mailto\n" +
                "     , `desc`\n,'" +
                account +
                "'      openedBy\n" +
                "      ,openedDate\n,'" +
                account +
                "'      assignedTo\n" +
                "     , assignedDate\n" +
                "     , estStarted\n" +
                "     , realStarted\n ,'" +
                account +
                "'      finishedBy\n" +
                "     , finishedDate\n" +
                "     , finishedList\n" +
                "     , canceledBy\n" +
                "     , canceledDate\n" +
                "     , closedBy\n" +
                "     , closedDate\n" +
                "     , closedReason\n,'" +
                account +
                "'      lastEditedBy\n" +
                "     , lastEditedDate\n" +
                "     , deleted\n" +
                "from zt_task\n" +
                "where id in (?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, taskId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ResultSet queryTaskEstimate(String toId, String fromId, String toAccount,
                                                      Connection conn) {

        String query = "select '" + toId +"' task, date, `left`, '" +
                toAccount +
                "' account, work,consumed " +
                "from zt_taskestimate where task = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, fromId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
