package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// import basedonnees.DBConnection;
import model.Utilisateur;

public class UtilisateurDAO {
	public UtilisateurDAO() {}
    public Utilisateur login(String email, String password) {
        Utilisateur user = null;

        try {
        	 String chemin="jdbc:mysql://localhost:3306/BD";
          	 String pilote="com.mysql.cj.jdbc.Driver";
          	 String userr="appuser";
          	 String pwd="apppass123";
          	 Connection con;
          	Class.forName(pilote);
    		con=DriverManager.getConnection(chemin,userr,pwd);
            String sql = "SELECT * FROM utilisateur WHERE email=? AND motDePasse=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Utilisateur();
                user.setId(rs.getLong("id"));
               // user.setNom(rs.getString("nom"));
                //user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Erreur login : " + e.getMessage());
        }

        return user;
    }
    public boolean existeParEmail(String email) {
        String sql = "SELECT id FROM utilisateur WHERE email = ?";
        try {
        	String chemin="jdbc:mysql://localhost:3306/BD";
         	 String pilote="com.mysql.cj.jdbc.Driver";
         	 String userr="appuser";
         	 String pwd="apppass123";
         	 Connection con;
         	Class.forName(pilote);
   		    con=DriverManager.getConnection(chemin,userr,pwd);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true si email déjà pris
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void save(Utilisateur u) {
        String sql = "INSERT INTO utilisateur (email, motDePasse, role) VALUES (?, ?, ?)";
        try {
        	String chemin="jdbc:mysql://localhost:3306/BD";
        	 String pilote="com.mysql.cj.jdbc.Driver";
        	 String userr="appuser";
        	 String pwd="apppass123";
        	 Connection con;
        	Class.forName(pilote);
  		    con=DriverManager.getConnection(chemin,userr,pwd);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getMotDePasse());
            ps.setString(3, u.getRole());
            ps.executeUpdate();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}