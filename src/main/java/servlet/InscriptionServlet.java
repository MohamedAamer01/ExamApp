package servlet;

import dao.UtilisateurDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Utilisateur;
import java.io.IOException;

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String role = request.getParameter("role");

        // Validation basique
        if(email == null || email.trim().isEmpty() ||
           motDePasse == null || motDePasse.trim().isEmpty()){
            request.setAttribute("erreur", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("inscription.jsp").forward(request, response);
            return;
        }

        UtilisateurDAO dao = new UtilisateurDAO();

        // Vérifier si l'email existe déjà
        if(dao.existeParEmail(email)){
            request.setAttribute("erreur", "Cet email est déjà utilisé.");
            request.getRequestDispatcher("inscription.jsp").forward(request, response);
            return;
        }

        // Créer l'utilisateur
        Utilisateur u = new Utilisateur();
        u.setEmail(email);
        u.setMotDePasse(motDePasse);
        u.setRole(role);

        dao.save(u);

        // Rediriger vers login avec message succès
        response.sendRedirect("login.jsp?inscription=success");
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {
        request.getRequestDispatcher("inscription.jsp").forward(request, response);
    }
}