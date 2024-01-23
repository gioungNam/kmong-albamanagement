package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnectionUtil;

public class MessageDao {
     /**
     * 메시지를 message 테이블에 저장
     * @param message 메시지 내용
     * @return 저장 성공 여부
     */
    public boolean insertMessage(String message) {
        String sql = "INSERT INTO message (content) VALUES (?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, message);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 최근 등록된 메시지 순으로 조회
     * @return
     */
    public List<Message> getRecentMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT id, content FROM message ORDER BY insert_date DESC";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message message = new Message(rs.getInt("id"), rs.getString("content"));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    /**
     * 메시지 삭제
     * @param messageId
     * @return
     */
    public boolean deleteMessage(int messageId) {
        String sql = "DELETE FROM message WHERE id = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, messageId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
