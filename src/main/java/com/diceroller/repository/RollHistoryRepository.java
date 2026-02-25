package com.diceroller.repository;

import com.diceroller.domain.RollHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RollHistoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(RollHistoryRepository.class);

    public void saveRoll(int numDice, int faces, int totalResult, String breakdown) {
        String sql = "INSERT INTO roll_history (num_dice, faces, total_result, breakdown) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numDice);
            pstmt.setInt(2, faces);
            pstmt.setInt(3, totalResult);
            pstmt.setString(4, breakdown);
            pstmt.executeUpdate();
            logger.info("Roll saved to history: {}d{} = {}", numDice, faces, totalResult);
        } catch (SQLException e) {
            logger.error("Failed to save roll history", e);
        }
    }

    public List<RollHistory> getRecentHistory(int limit) {
        String sql = "SELECT * FROM roll_history ORDER BY timestamp DESC LIMIT ?";
        List<RollHistory> historyList = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LocalDateTime ts;
                try {
                    String tsStr = rs.getString("timestamp").replace(" ", "T");
                    ts = LocalDateTime.parse(tsStr);
                } catch (Exception e) {
                    logger.warn("Could not parse timestamp, resorting to now()");
                    ts = LocalDateTime.now();
                }

                historyList.add(new RollHistory(
                        rs.getInt("id"),
                        ts,
                        rs.getInt("num_dice"),
                        rs.getInt("faces"),
                        rs.getInt("total_result"),
                        rs.getString("breakdown")));
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch roll history", e);
        }
        return historyList;
    }
}
