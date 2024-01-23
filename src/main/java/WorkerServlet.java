import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MessageDao;
import data.User;
import data.UserDao;

public class WorkerServlet extends HttpServlet {

    private UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");

        // 세션의 "user"가 "admin"이 아니면 메인 페이지로 리디렉션
        if (!"admin".equals(user)) {
            response.sendRedirect("/");
            return;
        }

        request.getRequestDispatcher("/worker.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); 
        
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        // 중복 id 불가
        if (userDao.getUserByUsername(userId) != null) {
            response.getWriter().println("<script>alert('duplicate user id'); location.href='/worker';</script>");
            return;
        }

        boolean success = userDao.insertUser(new User(userId, name, password));

        if (success) {
            response.sendRedirect("/finishi.jsp");
        } else {
            response.getWriter().println("<script>alert('error!'); location.href='/worker';</script>");
        }
    }
}
