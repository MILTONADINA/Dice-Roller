package com.diceroller.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    private static final String DB_URL = "jdbc:sqlite:dice_history.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS roll_history (\n" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                " timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                " num_dice INTEGER NOT NULL,\n" +
                " faces INTEGER NOT NULL,\n" +
                " total_result INTEGER NOT NULL,\n" +
                " breakdown TEXT\n" +
                ");";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            logger.info("Database initialized successfully.");
        } catch (SQLException e) {
            logger.error("Failed to initialize database", e);
            throw new RuntimeException("Database initialization failed", e);
        }
    }
}
