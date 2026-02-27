package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Examen;

import java.io.IOException;

import dao.ExamenDAO;

/**
 * Servlet implementation class UpdateExemenServlet
 */
@WebServlet("/UpdateExemenServlet")
public class UpdateExemenServlet extends HttpServlet {

    // GET → afficher le formulaire
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        ExamenDAO dao = new ExamenDAO();
        Examen examen = dao.findById(id);
        request.setAttribute("examen", examen);
        request.getRequestDispatcher("UpdateExamen.jsp").forward(request, response);
    }

    // POST → traiter la modification
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String titre = request.getParameter("titre");
            int tempsTotal = Integer.parseInt(request.getParameter("tempsTotal"));

            ExamenDAO dao = new ExamenDAO();
            boolean success = dao.updateExamen(id, titre, tempsTotal);

            if(success) {
                response.sendRedirect("AdminListExamenServlet?success=1");
            } else {
                response.sendRedirect("AdminListExamenServlet?error=1");
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("AdminListExamenServlet?error=1");
        }
    }
}