package com.paybuc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:mysql://thedriveshow.co.za:3306/driveshow_lotto";
    private static final String USERNAME = "driveshow_user";
    private static final String PASSWORD = "driveshow_user";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}