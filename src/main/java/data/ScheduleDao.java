package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnectionUtil;

public class ScheduleDao {

    /**
     * 근무 스케줄 시간 update
     * @param userId
     * @param workDate
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean updateSchedule(String userId, String workDate, int startTime, int endTime) {
        
        String sql = "UPDATE work_schedule SET start_time = ?, end_time = ? WHERE user_id = ? AND work_date = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, startTime);
            stmt.setInt(2, endTime);
            stmt.setString(3, userId);
            stmt.setDate(4, java.sql.Date.valueOf(workDate));

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 근무 스케줄 시간 insert
     * @param userId
     * @param workDate
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean insertSchedule(String userId, String workDate, int startTime, int endTime) {
        String sql = "INSERT INTO work_schedule (user_id, work_date, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setDate(2, java.sql.Date.valueOf(workDate));
            stmt.setInt(3, startTime);
            stmt.setInt(4, endTime);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 특정 유저의 특정 일자 스케줄 조회
     * @param userId
     * @param date
     * @return
     */
    public Schedule getScheduleByUserAndDate(String userId, String date) {
        String sql = "SELECT start_time, end_time FROM work_schedule WHERE user_id = ? AND work_date = ?";
        Schedule schedule = null;
    
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                int startTime = rs.getInt("start_time");
                int endTime = rs.getInt("end_time");
                schedule = new Schedule(userId, startTime, endTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }
}
