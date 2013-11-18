

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcHelper implements JdbcConfig {
    // 获得数据库的连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);// 加载数据库驱动
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 获得数据库连接
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public static String query(Connection conn, String sql) {
        Statement stat = null;
        ResultSet rs = null;
        String resultstr = "";
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            resultstr = ResultSetToJson.ResultSetToJsonString(rs);             
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {// 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultstr;
    }
    
    public static String update(Connection conn, String sql) {
        Statement stat = null;
        int rs = 0;
        try {
            stat = conn.createStatement();
            rs = stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {// 释放资源
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return String.valueOf(rs);
    }
    
    
    
}
