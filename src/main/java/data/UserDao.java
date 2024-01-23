package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBConnectionUtil;

/**
 * user 테이블 접근 객체
 */
public class UserDao {

    /**
     * 알바생 이름 조회
     * @return
     */
    public List<User> getWorkers() {
        List<User> workers = new ArrayList<>();
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT user_id, nickname FROM user");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String nickname = rs.getString("nickname");

                // admin은 worker에서 뺀다.
                if ("admin".equals(userId)) continue;

                workers.add(new User(userId, nickname));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workers;
    }


    /**
     * user id로 조회
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT user_id, nickname, password FROM user WHERE user_id = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String userId = rs.getString("user_id");
                    String nickname = rs.getString("nickname");
                    String password = rs.getString("password");
                    user = new User(userId, nickname, password);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 주간 스케줄 조회
     * @param startOfWeek
     * @param endOfWeek
     * @return
     */
    public Map<String, List<String>> getWeeklySchedule(LocalDate startOfWeek, LocalDate endOfWeek) {
        Map<String, List<String>> scheduleMap = new HashMap<>();
        String sql = "SELECT user_id, work_date, start_time, end_time FROM work_schedule WHERE work_date BETWEEN ? AND ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(startOfWeek));
            stmt.setDate(2, java.sql.Date.valueOf(endOfWeek));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String userId = rs.getString("user_id");
                    LocalDate workDate = rs.getDate("work_date").toLocalDate();
                    int startTime = rs.getInt("start_time");
                    int endTime = rs.getInt("end_time");
                    String timeSlot = startTime + " ~ " + endTime;

                    scheduleMap.computeIfAbsent(userId, k -> new ArrayList<>())
                               .add(workDate.format(DateTimeFormatter.ofPattern("MM/dd")) + ":" + timeSlot);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduleMap;
    }

    /**
     * 알바생 등록
     * @param user
     * @return
     */
    public boolean insertUser(User user) {
        String sql = "INSERT INTO user (user_id, nickname, password) VALUES (?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getNickname());
            stmt.setString(3, user.getPassword());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
