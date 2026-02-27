package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.QuestionDAO;

@WebServlet("/DeleteQuestionServlet")
public class DeleteQuestionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws IOException {

        Long id =
        Long.parseLong(request.getParameter("id"));

        Long examenId =
        Long.parseLong(request.getParameter("ex"));

        QuestionDAO dao = new QuestionDAO();
        dao.delete(id);

        response.sendRedirect(
        "AdminQuestionsServlet?id=" + examenId);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
