package servlet;
import dao.ExamenDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;

import java.io.IOException;

/**
 * Servlet implementation class AdminListExamenServlet
 */
@WebServlet("/AdminListExamenServlet")
public class AdminListExamenServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException{
    	
    	Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
    	
    	if(user == null){
            response.sendRedirect("login.jsp");
            return;
        }
        ExamenDAO dao = new ExamenDAO();
        //request.setAttribute("examens", dao.findAll());
        request.setAttribute("examens", dao.findByAdmin(user.getId()));
        request.getRequestDispatcher("adminListExamens.jsp")
               .forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
