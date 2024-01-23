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
import javax.servlet.http.HttpSession;

import data.MessageDao;
import data.User;
import data.Message;
import data.UserDao;
import util.DateUtils;

public class IndexServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private MessageDao messageDao = new MessageDao();

    public IndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        List<String> fullWeekDays = new ArrayList<>();
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        String formattedToday = today.format(formatter);

        List<String> weekDays = new ArrayList<>();
        List<String> weekDaysJapanese = new ArrayList<>();

        for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
            int dayOfWeek = date.getDayOfWeek().getValue();
            String dayInJapanese = DateUtils.dayOfWeekJapanese(dayOfWeek);
            String formattedDate = date.format(formatter);
            weekDays.add(formattedDate);
            weekDaysJapanese.add(formattedDate + "(" + dayInJapanese + ")");
            // 전체 날짜 형식 추가
            fullWeekDays.add(date.format(fullFormatter));
        }

        request.setAttribute("weekDays", weekDays);
        request.setAttribute("weekDaysJapanese", weekDaysJapanese);
        request.setAttribute("formattedToday", formattedToday);
        request.setAttribute("fullWeekDays", fullWeekDays);


        // 알바생 이름 조회
        List<User> workers = userDao.getWorkers();
        request.setAttribute("workers", workers);

        // 세션 유저 닉네임 정보
        HttpSession session = request.getSession();
        String userNickname = (String) session.getAttribute("userNickname");
        request.setAttribute("userNickname", userNickname);

        // 주간 스케줄 조회
        Map<String, List<String>> weeklySchedule = userDao.getWeeklySchedule(startOfWeek, endOfWeek);
        request.setAttribute("weeklySchedule", weeklySchedule);

        // 공지사항 메시지
        List<Message> recentMessages = messageDao.getRecentMessages();
        request.setAttribute("recentMessages", recentMessages);
    

        request.getRequestDispatcher("/main.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
