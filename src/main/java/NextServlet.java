import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.User;
import data.UserDao;
import util.DateUtils;

public class NextServlet extends HttpServlet{
     private UserDao userDao = new UserDao();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDate today = LocalDate.now();
        LocalDate startOfNextWeek = today.plusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDate endOfNextWeek = today.plusWeeks(1).with(DayOfWeek.SUNDAY);

        List<String> fullWeekDays = new ArrayList<>();
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        List<String> weekDays = new ArrayList<>();
        List<String> weekDaysJapanese = new ArrayList<>();

        for (LocalDate date = startOfNextWeek; !date.isAfter(endOfNextWeek); date = date.plusDays(1)) {
            int dayOfWeek = date.getDayOfWeek().getValue();
            String dayInJapanese = DateUtils.dayOfWeekJapanese(dayOfWeek);
            String formattedDate = date.format(formatter);
            weekDays.add(formattedDate);
            weekDaysJapanese.add(formattedDate + "(" + dayInJapanese + ")");
            // 전체 날짜 형식 추가
            fullWeekDays.add(date.format(fullFormatter));
        }


        // 결과 데이터를 request 속성에 설정
        request.setAttribute("weekDays", weekDays);
        request.setAttribute("weekDaysJapanese", weekDaysJapanese);
        request.setAttribute("fullWeekDays", fullWeekDays);
        
        // 알바생 이름 조회
        List<User> workers = userDao.getWorkers();
        request.setAttribute("workers", workers);

        // 주간 스케줄 조회
        Map<String, List<String>> weeklySchedule = userDao.getWeeklySchedule(startOfNextWeek, endOfNextWeek);
        request.setAttribute("weeklySchedule", weeklySchedule);

        request.getRequestDispatcher("/next.jsp").forward(request, response);
    }
}
