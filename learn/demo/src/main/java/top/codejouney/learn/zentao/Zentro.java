package top.codejouney.learn.zentao;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * @author Antvictor
 * @date 2025/5/24
 **/
public class Zentro {
    public static void main(String[] args) {


        String url = "";
        String username = "zentao";
        String password = "Zentao@2025";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Map<String, List<JSONObject>> taskData = readDaily("/Users/exccedy/Downloads/日报测试.xlsx");
            // 1. 加载驱动（JDK8 以后可以省略这一步）

            // 2. 建立连接
            Connection conn = DriverManager.getConnection(url, username, password);

            insertTask(taskData, conn);
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void insertTask(Map<String, List<JSONObject>> taskData, Connection conn) throws SQLException {
        taskData.forEach((task, taskEstimate) -> {
            // 根据key创建任务
            // 先根据真实姓名查用户
            try {
                String query = "select account from zt_user where realname = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                String name = task.substring(task.lastIndexOf("-") + 1);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    System.err.println(task + ":" + name + "不存在");
                    return;
                }
                String account = resultSet.getString(1);

                // 3. 创建并执行 SQL
                String sql = "INSERT INTO zt_task (project, parent, module, story, storyVersion, fromBug,\n" +
                        "                     name, type, pri, estimate, consumed, `left`, deadline, status, subStatus, color, mailto, `desc`,\n" +
                        "                     openedBy, openedDate,\n" +
                        "                     assignedTo, assignedDate, estStarted, realStarted, finishedBy, finishedDate, finishedList,\n" +
                        "                     canceledBy, canceledDate, closedBy, closedDate, closedReason,\n" +
                        "                     lastEditedBy, lastEditedDate, deleted)\n" +
                        "VALUES (4, 0, 0, 1123, 1, 0, ?, 'devel', 3, ?, ?, ?, ?, 'done', '', '', '', ?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
                        "        '', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '', ?, ?, '0');";
                // 设置 RETURN_GENERATED_KEYS 表示我们想获取插入后的自增主键
                PreparedStatement ps = null;

                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                // 任务名称
                ps.setString(1, task);
                // 预计工时
                int estimate = taskEstimate.stream().mapToInt(j -> j.getInteger("time")).sum();
                ps.setFloat(2, estimate);
                // 消耗工时
                ps.setFloat(3, estimate);
                // 剩余工时
                ps.setFloat(4, 0);
                // 截止日期
                Date dealLine = taskEstimate.stream().map(j -> j.getDate("date")).max(Date::compareTo).get();
                
                ps.setDate(5, new java.sql.Date( dealLine.getTime()));
                // 描述（等于任务名称）
                ps.setString(6, task);
                // 账号（通过user查account 条件realName）
                ps.setString(7, account);
                // 开启时间(带时分秒)
                Date startTime = taskEstimate.stream().map(j -> j.getDate("date")).min(Date::compareTo).get();
                ps.setTimestamp(8, new Timestamp(startTime.getTime()));
                // 指派人
                ps.setString(9, account);
                // 指派时间（等于开始时间)
                ps.setTimestamp(10, new Timestamp(startTime.getTime()));
                // 任务开始日期
                ps.setDate(11, new java.sql.Date( startTime.getTime()));
                // 真实开始时间
                ps.setTimestamp(12, new Timestamp(startTime.getTime()));
                // 结束人
                ps.setString(13, account);
                // 结束时间
                ps.setTimestamp(14, new Timestamp(dealLine.getTime()));
                // 最后操作人
                ps.setString(15, account);
                // 最后操作时间
                ps.setTimestamp(16, new Timestamp(dealLine.getTime()));

                ps.executeUpdate();

                // 获取返回的主键
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("插入成功，ID为：" + id);
                    // 再循环插入任务工时
                    String insertEsSql = "INSERT INTO zt_taskestimate ( task, date, `left`, consumed, account, work) " +
                            "VALUES (?, ?, ?, ?, ?, ?);";
                    PreparedStatement esPs = conn.prepareStatement(insertEsSql);
                    for (int i = 0; i < taskEstimate.size(); i++) {
                        esPs.setInt(1, id);
                        esPs.setDate(2, new java.sql.Date( taskEstimate.get(i).getDate("date").getTime()));
                        esPs.setFloat(3, estimate -= taskEstimate.get(i).getInteger("time"));
                        esPs.setFloat(4, taskEstimate.get(i).getInteger("time"));
                        esPs.setString(5, account);
                        esPs.setString(6, taskEstimate.get(i).getString("content"));

                        esPs.addBatch();
                    }


                    esPs.executeBatch();
                    esPs.close();
                }

                // 5. 关闭连接
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Map<String, List<JSONObject>> readDaily(String file) throws IOException {
        Map<String, List<JSONObject>> result = new HashMap<>();

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fis);
        for (Sheet sheet : workbook) {
//            Sheet sheet = sheetIterator.next();
            String dailyName = sheet.getSheetName();
            for (Row row : sheet) {
                System.out.print("任务：" + dailyName);
                if (row != null && row.getRowNum() != 0) {
                    System.out.print(row.getCell(0));
                    if (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                        continue;
                    }
                    JSONObject jsonObject = new JSONObject();
                    // 日期
                    Date date = row.getCell(0).getDateCellValue();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formatDate = simpleDateFormat.format(date);
                    System.out.println("Date: " + formatDate);
                    // 名称
                    String name = row.getCell(1).getStringCellValue();
                    // 工作内容
                    String content = row.getCell(2).getStringCellValue();
                    // 工时
                    double time = 8;

                    if (row.getCell(3) != null && CellType.BLANK != row.getCell(3).getCellType()) {
                        time = row.getCell(3).getNumericCellValue();
                    }



                    jsonObject.put("date", formatDate);
                    jsonObject.put("name", name);
                    jsonObject.put("content", content);
                    jsonObject.put("time", time);


                    result.computeIfAbsent(dailyName + "-" + name, k -> new ArrayList<>()).add(jsonObject);
                }

            }


        }
        return result;
    }
}
