package db;

import java.sql.*;

public class JDBCUtil {

    private final static String URL = "jdbc:mysql://localhost/weibo"
            + "?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true";
    private final static String USER = "root";
    private final static String PASSWORD = "newpass";

    static {//加载JDBC驱动程序
        try {
            String diverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(diverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {//创建数据库连接
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(ResultSet rs, Statement stmt, Connection con) {//关闭数据库连接
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
