package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;

import java.io.IOException;

import dao.ExamenDAO;

/**
 * Servlet implementation class StudentExamensServlet
 */
@WebServlet("/StudentExamensServlet")
public class StudentExamensServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        Utilisateur user =
            (Utilisateur) request.getSession().getAttribute("user");

        if(user == null){
            response.sendRedirect("login.jsp");
            return;
        }

        ExamenDAO dao = new ExamenDAO();
        request.setAttribute("examens", dao.findAll());

        request.getRequestDispatcher("studentExamens.jsp")
               .forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
