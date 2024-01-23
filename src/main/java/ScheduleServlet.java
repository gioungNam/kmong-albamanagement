import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ScheduleDao;
import org.json.JSONObject;


public class ScheduleServlet extends HttpServlet {

    private ScheduleDao scheduleDao = new ScheduleDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String workDate = request.getParameter("workDate");
        int startTime = Integer.parseInt(request.getParameter("startTime"));
        int endTime = Integer.parseInt(request.getParameter("endTime"));

        JSONObject jsonResponse = new JSONObject();
        try {
            boolean updated = scheduleDao.updateSchedule(userId, workDate, startTime, endTime);
            if (updated) {
                jsonResponse.put("status", "success");
            } else {
                // Update가 실패한 경우, 새로운 스케줄을 삽입
            boolean inserted = scheduleDao.insertSchedule(userId, workDate, startTime, endTime);
                if (inserted) {
                    jsonResponse.put("status", "success");
                } else {
                    jsonResponse.put("status", "fail");
                    jsonResponse.put("message", "Failed to update or insert schedule."); 
                } 
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Server error occurred"); 
            
        }

        response.getWriter().write(jsonResponse.toString());


        
    }
}
