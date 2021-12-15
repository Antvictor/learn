package yangchao.study.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    /**
     * 注入master数据源
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean
    public DataSource master() {
        return new HikariDataSource();
    }

    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Bean
    public DataSource slave() {
        return new HikariDataSource();
    }

    @Bean
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DBUtil.master,master());
        targetDataSource.put(DBUtil.slave,slave());

        dynamicDataSource.setTargetDataSources(targetDataSource);
        // 设置默认
        dynamicDataSource.setDefaultTargetDataSource(master());
        return  dynamicDataSource;
    }


    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dynamicDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
        return sqlSessionTemplate;
    }


    public class DynamicDataSource extends AbstractRoutingDataSource{

        @Nullable
        @Override
        protected Object determineCurrentLookupKey() {
            return DBUtil.getDB();
        }
    }

    public  static class DBUtil{
        public static final String master = "master";
        public static final String slave = "slave";


        private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

        public static void setDB(String db) {
            threadLocal.set(db);
        }

        public static String getDB() {
            return threadLocal.get();
        }

        public static void remove() {
            threadLocal.remove();
        }
    }
}
