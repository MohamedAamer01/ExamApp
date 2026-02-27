package dao;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;
//import basedonnees.DBConnection;
// import basedonnes.Connexion;
import model.Examen;

public class ExamenDAO {

    // Récupérer tous les examens
    public List<Examen> findAll() {
        List<Examen> liste = new ArrayList<>();

        try {
         String chemin="jdbc:mysql://localhost:3306/BD";
       	 String pilote="com.mysql.cj.jdbc.Driver";
       	 String user="appuser";
       	 String pwd="apppass123";
       	 Connection con;
       	Class.forName(pilote);
		con=DriverManager.getConnection(chemin,user,pwd);
            String sql = "SELECT * FROM examen";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Examen e = new Examen();
                e.setId(rs.getLong("id"));
                e.setTitre(rs.getString("titre"));
                e.setTempsTotal(rs.getInt("tempsTotal"));
                liste.add(e);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Ajouter un examen
    public void save(Examen examen) {
    	    try {
    	        String chemin="jdbc:mysql://localhost:3306/BD";
    	        String pilote="com.mysql.cj.jdbc.Driver";
    	        String user="appuser";
    	        String pwd="apppass123";
    	        Class.forName(pilote);
    	        Connection con = DriverManager.getConnection(chemin, user, pwd);

    	        // AJOUT de admin_id dans le INSERT
    	        String sql = "INSERT INTO examen (titre, tempsTotal, admin_id) VALUES (?, ?, ?)";
    	        PreparedStatement ps = con.prepareStatement(sql);
    	        ps.setString(1, examen.getTitre());
    	        ps.setInt(2, examen.getTempsTotal());
    	        ps.setLong(3, examen.getAdminId()); // ← AJOUT
    	        ps.executeUpdate();
    	        con.close();
    	    } catch(Exception e){
    	        e.printStackTrace();
    	    }
    	}
    // Supprimer un examen
    public void delete(Long id) {
        try {
            String chemin="jdbc:mysql://localhost:3306/BD";
            String pilote="com.mysql.cj.jdbc.Driver";
            String user="appuser";
            String pwd="apppass123";
            Class.forName(pilote);
            Connection con = DriverManager.getConnection(chemin, user, pwd);

            // 1. Supprimer les choix liés aux questions de cet examen
            String sql1 = "DELETE FROM qcm_choix WHERE question_id IN (SELECT id FROM question WHERE examen_id=?)";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setLong(1, id);
            ps1.executeUpdate();

            // 2. Supprimer les questions de cet examen
            String sql2 = "DELETE FROM question WHERE examen_id=?";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setLong(1, id);
            ps2.executeUpdate();

            // 3. Supprimer l'examen
            String sql3 = "DELETE FROM examen WHERE id=?";
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ps3.setLong(1, id);
            ps3.executeUpdate();

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    // Mofier un examen 
    public void modifier(Long id,String titre) {
    	try {
    		String chemin="jdbc:mysql://localhost:3306/BD";
         	 String pilote="com.mysql.cj.jdbc.Driver";
         	 String user="appuser";
         	 String pwd="apppass123";
         	 Connection con;
         	Class.forName(pilote);
  		    con=DriverManager.getConnection(chemin,user,pwd);
  		    String sql= "Update Table examen set titre=? where id=?";
  		    PreparedStatement ps = con.prepareStatement(sql); 
  		    ps.setLong(2,id);
  		    ps.setString(1,"test");
    	}
    catch(Exception e ) {
    	e.printStackTrace();
    }
}

    public boolean updateExamen(Long id, String titre, int tempsTotal) {

        boolean updated = false;

        try {
            String chemin = "jdbc:mysql://localhost:3306/BD";
            String pilote = "com.mysql.cj.jdbc.Driver";
            String userr = "appuser";
            String pwd = "apppass123";

            Class.forName(pilote);
            Connection con = DriverManager.getConnection(chemin, userr, pwd);

            String sql = "UPDATE examen SET titre=?, tempsTotal=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, titre);
            ps.setInt(2, tempsTotal);
            ps.setLong(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                updated = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }

    public List<Examen> findByAdmin(Long adminId) {
        List<Examen> liste = new ArrayList<>();
        try {
            String chemin="jdbc:mysql://localhost:3306/BD";
            String pilote="com.mysql.cj.jdbc.Driver";
            String user="appuser";
            String pwd="apppass123";
            Class.forName(pilote);
            Connection con = DriverManager.getConnection(chemin, user, pwd);

            String sql = "SELECT * FROM examen WHERE admin_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, adminId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Examen e = new Examen();
                e.setId(rs.getLong("id"));
                e.setTitre(rs.getString("titre"));
                e.setTempsTotal(rs.getInt("tempsTotal"));
                e.setAdminId(rs.getLong("admin_id"));
                liste.add(e);
            }
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return liste;
    }

    public Examen findById(Long id) {
        Examen examen = null;
        try {
            String chemin="jdbc:mysql://localhost:3306/BD";
            String pilote="com.mysql.cj.jdbc.Driver";
            String user="appuser";
            String pwd="apppass123";
            Class.forName(pilote);
            Connection con = DriverManager.getConnection(chemin, user, pwd);

            String sql = "SELECT * FROM examen WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                examen = new Examen();
                examen.setId(rs.getLong("id"));
                examen.setTitre(rs.getString("titre"));
                examen.setTempsTotal(rs.getInt("tempsTotal"));
                examen.setAdminId(rs.getLong("admin_id"));
            }
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return examen;
    }

}