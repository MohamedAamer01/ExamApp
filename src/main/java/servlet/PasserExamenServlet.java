package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Question;

import java.io.IOException;
import java.util.List;

import dao.QuestionDAO;

/**
 * Servlet implementation class PasserExamenServlet
 */

@WebServlet("/PasserExamenServlet")
public class PasserExamenServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

Long examenId = Long.parseLong(request.getParameter("id"));
System.out.println(">>> examenId reçu: " + examenId);

QuestionDAO dao = new QuestionDAO();
List<Question> questions = dao.findByExamen(examenId);
System.out.println(">>> nb questions: " + questions.size());

HttpSession session = request.getSession();
session.removeAttribute("questions");
session.removeAttribute("index");
session.removeAttribute("score");

session.setAttribute("questions", questions);
session.setAttribute("index", 0);
session.setAttribute("score", 0);

request.setAttribute("examenId", examenId);
request.getRequestDispatcher("question.jsp").forward(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
