package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionDAO {
	public List<Question> findByExamen(Long examenId){

	    List<Question> liste = new ArrayList<>();

	    try{
	    	String chemin="jdbc:mysql://localhost:3306/BD";
	       	 String pilote="com.mysql.cj.jdbc.Driver";
	       	 String user="appuser";
	       	 String pwd="apppass123";
	       	 Connection con;
	       	Class.forName(pilote);
			con=DriverManager.getConnection(chemin,user,pwd);
	        String sql = "SELECT * FROM question WHERE examen_id=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setLong(1, examenId);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()){
	            Question q = new Question();
	            q.setId(rs.getLong("id"));
	            q.setEnonce(rs.getString("enonce"));
	            q.setType(rs.getString("type"));
	            q.setExamenId(rs.getLong("examen_id"));
	           // q.setReponseCorrecte(rs.getString("reponse_correcte"));
	            q.setReponseCorrecte(rs.getString("reponse_correcte"));
	            liste.add(q);
	        }

	        con.close();

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return liste;
	}
	public void save(Question question){

	    String sql = "INSERT INTO question (enonce, type, examen_id) VALUES (?, ?, ?)";

	    try{
	    	String chemin="jdbc:mysql://localhost:3306/BD";
	       	 String pilote="com.mysql.cj.jdbc.Driver";
	       	 String user="appuser";
	       	 String pwd="apppass123";
	       	 Connection con;
	       	Class.forName(pilote);
			con=DriverManager.getConnection(chemin,user,pwd);
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, question.getEnonce());
	        ps.setString(2, question.getType());
	        ps.setLong(3, question.getExamenId());

	        ps.executeUpdate();

	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	public void delete(Long id){

    String sql = "DELETE FROM question WHERE id=?";

    try{
    	String chemin="jdbc:mysql://localhost:3306/BD";
       	 String pilote="com.mysql.cj.jdbc.Driver";
       	 String user="appuser";
       	 String pwd="apppass123";
       	 Connection con;
       	Class.forName(pilote);
		con=DriverManager.getConnection(chemin,user,pwd);
		PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
    }catch(Exception e){
        e.printStackTrace();
    }
}
	public Long saveAndReturnId(Question question){

	    Long generatedId = null;

	    String sql =
	    "INSERT INTO question (enonce, type, examen_id) VALUES (?, ?, ?)";

	    try {
	    	String chemin="jdbc:mysql://localhost:3306/BD";
	       	 String pilote="com.mysql.cj.jdbc.Driver";
	       	 String user="appuser";
	       	 String pwd="apppass123";
	       	 Connection con;
	       	Class.forName(pilote);
			con=DriverManager.getConnection(chemin,user,pwd);
	        PreparedStatement ps =
	        con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, question.getEnonce());
	        ps.setString(2, question.getType());
	        ps.setLong(3, question.getExamenId());

	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();

	        if(rs.next()){
	            generatedId = rs.getLong(1);
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return generatedId;
	}
	public void updateReponseCorrecte(Long id, String reponse){
	    String sql = "UPDATE question SET reponse_correcte=? WHERE id=?";
	    try {
	    	String chemin="jdbc:mysql://localhost:3306/BD";
	       	 String pilote="com.mysql.cj.jdbc.Driver";
	       	 String user="appuser";
	       	 String pwd="apppass123";
	       	 Connection con;
	       	Class.forName(pilote);
			con=DriverManager.getConnection(chemin,user,pwd);
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, reponse);
	        ps.setLong(2, id);
	        ps.executeUpdate();
	        con.close();
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
}
