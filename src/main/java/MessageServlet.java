import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MessageDao;

public class MessageServlet extends HttpServlet {

    private MessageDao messageDao = new MessageDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); 

        String action = request.getParameter("action");

        // 삭제 요청
        if ("delete".equals(action)) {
            int messageId = Integer.parseInt(request.getParameter("messageId"));

            boolean success = messageDao.deleteMessage(messageId);

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            return;
        }

        // 메시지 추가
        String message = request.getParameter("message");

        System.out.println("message = "+message);

        // MessageDao를 이용하여 메시지 저장
        boolean success = messageDao.insertMessage(message);

        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}