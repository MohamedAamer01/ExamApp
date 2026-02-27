package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.ExamenDAO;

/**
 * Servlet implementation class DeleteExamenServlet
 */
@WebServlet("/DeleteExamenServlet")
public class DeleteExamenServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        ExamenDAO dao = new ExamenDAO();
        dao.delete(id);

        response.sendRedirect("AdminListExamenServlet");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
