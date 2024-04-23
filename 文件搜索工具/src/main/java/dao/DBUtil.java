package dao;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DBUtil {
    // 使用 单例模式 来提供 DataSource
    private static volatile DataSource dataSource = null;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new SQLiteDataSource();
                    ((SQLiteDataSource)dataSource).setUrl("jdbc:sqlite://d:/project_tmp/everything/fileSearcher.db");
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
