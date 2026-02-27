package servlet;
import dao.ExamenDAO;
import model.Examen;
import model.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AddExamanServlet
 */
@WebServlet("/AddExamenServlet")
public class AddExamenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws IOException, ServletException {

        String titre = request.getParameter("titre");
        int temps = Integer.parseInt(request.getParameter("temps"));

        // Récupérer l'admin connecté
        Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

        Examen examen = new Examen();
        examen.setTitre(titre);
        examen.setTempsTotal(temps);
        examen.setAdminId(user.getId()); // ← AJOUT

        ExamenDAO dao = new ExamenDAO();
        dao.save(examen);

        response.sendRedirect("AdminListExamenServlet");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
