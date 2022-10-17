package peaksoft.util;

import org.postgresql.Driver;

import javax.security.auth.login.CredentialException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String username = "postgres";
    private static final String password = "1234";
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    // реализуйте настройку соеденения с БД

    public  static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
