package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UtilisateurDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Utilisateur;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null ||
            email.trim().isEmpty() ||
            password.trim().isEmpty()) {

            response.sendRedirect("login.jsp?error=1");
            return;
        }

        email = email.trim();
        password = password.trim();
        try {
        // 🔥 Utiliser le DAO
        UtilisateurDAO dao = new UtilisateurDAO();
        Utilisateur user = dao.login(email, password);
        System.out.println("ROLE = [" + user.getRole() + "]");
        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user.getRole() != null && 
            	    user.getRole().trim().equalsIgnoreCase("ADMIN")) {

            	    response.sendRedirect("dashboardadmin.jsp");

            	} else {
            	    response.sendRedirect("dashboardetd.jsp");
            	}

        } else {
            response.sendRedirect("login.jsp?error=1");
        }
        }catch(Exception e ) {
        	e.printStackTrace();
        }
    }

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}