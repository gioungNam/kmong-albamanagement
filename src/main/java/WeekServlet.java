import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Schedule;
import data.ScheduleDao;
import data.User;
import data.UserDao;
import util.DateUtils;

public class WeekServlet extends HttpServlet {

    private ScheduleDao scheduleDao = new ScheduleDao();
    private UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 쿼리 파라미터 추출
        String date = request.getParameter("date");

        // 날짜 형식 확인 및 리디렉션
        if (date == null || !DateUtils.isValidDate(date)) {
            response.sendRedirect("/");
            return;
        }

        List<User> allUsers = userDao.getWorkers();
        Map<String, Schedule> schedulesByUser = new HashMap<>();
        for (User user : allUsers) {
            Schedule schedule = scheduleDao.getScheduleByUserAndDate(user.getUserId(), date);
            schedulesByUser.put(user.getUserId(), schedule);
        }

        request.setAttribute("schedulesByUser", schedulesByUser);
        request.setAttribute("allUsers", allUsers);
        request.setAttribute("date", date);

        // JSP 페이지로 요청 전달
        request.getRequestDispatcher("/week.jsp").forward(request, response);
    }


}