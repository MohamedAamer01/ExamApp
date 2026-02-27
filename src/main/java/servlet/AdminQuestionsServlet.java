package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.QuestionDAO;

@WebServlet("/AdminQuestionsServlet")
public class AdminQuestionsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        Long examenId =
        Long.parseLong(request.getParameter("id"));

        QuestionDAO dao = new QuestionDAO();
        request.setAttribute("questions",
            dao.findByExamen(examenId));

        request.setAttribute("examenId", examenId);

        request.getRequestDispatcher("adminQuestions.jsp")
               .forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
