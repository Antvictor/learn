//package larry.study.interceptor;
//
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//import larry.study.config.DataSourceConfig;
//
//@Aspect
//@Component
//public class DBAspect {
//
//
//    @Before("execution(* yangchao..server..*.get*(..))")
//    public void setReadDataSource() {
//        DataSourceConfig.DBUtil.setDB(DataSourceConfig.DBUtil.slave);
//        System.out.println("读");
//    }
//
//    @Before("execution(* yangchao..server..*.add*(..))")
//    public void setWriteDataSource() {
//        DataSourceConfig.DBUtil.setDB(DataSourceConfig.DBUtil.master);
//        System.out.println("写");
//    }
//
//    @After("execution(* yangchao.study.server..*.*(..))")
//    public void removeDataSource() {
//        DataSourceConfig.DBUtil.remove();
//        System.out.println("用完要删");
//    }
//}
