package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import dao.ExamenDAO;
import model.Examen;
import model.Utilisateur;

@WebServlet("/ListExamenServlet")
public class ListExamenServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,HttpServletResponse response)
                         throws ServletException, IOException {

        //  Vérifier si utilisateur connecté
        Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        //  Récupérer examens
        ExamenDAO dao = new ExamenDAO();
        List<Examen> examens = dao.findAll();

        // Envoyer vers JSP
        request.setAttribute("examens", examens);
        request.getRequestDispatcher("ListExamen.jsp")
               .forward(request, response);
    }
}