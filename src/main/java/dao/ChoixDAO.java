package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Choix;


public class ChoixDAO {

    public void save(Long questionId, String contenu, boolean estCorrect){

        String sql ="INSERT INTO qcm_choix (question_id, contenu, estCorrect) VALUES (?, ?, ?)";

        try {
        		String chemin="jdbc:mysql://localhost:3306/BD";
              	 String pilote="com.mysql.cj.jdbc.Driver";
              	 String user="appuser";
              	 String pwd="apppass123";
              	 Connection con;
              	Class.forName(pilote);
       		con=DriverManager.getConnection(chemin,user,pwd);
       	 PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, questionId);
            ps.setString(2, contenu);
            ps.setBoolean(3, estCorrect);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Choix> findByQuestion(Long questionId){

        List<Choix> liste = new ArrayList<>();

        String sql = "SELECT * FROM qcm_choix WHERE question_id=?";

        try
        {

    		String chemin="jdbc:mysql://localhost:3306/BD";
          	 String pilote="com.mysql.cj.jdbc.Driver";
          	 String user="appuser";
          	 String pwd="apppass123";
          	 Connection con;
          	Class.forName(pilote);
   		    con=DriverManager.getConnection(chemin,user,pwd);
   		    PreparedStatement ps= con.prepareStatement(sql);
            ps.setLong(1, questionId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Choix c = new Choix();
                c.setId(rs.getLong("id"));
                c.setQuestionId(rs.getLong("question_id"));
                c.setContenu(rs.getString("contenu"));
                c.setEstCorrect(rs.getBoolean("estCorrect"));

                liste.add(c);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return liste;
    }
    public boolean isCorrect(Long choixId){

        boolean correct = false;

        String sql = "SELECT estCorrect FROM qcm_choix WHERE id=?";

        try{
        	String chemin="jdbc:mysql://localhost:3306/BD";
         	 String pilote="com.mysql.cj.jdbc.Driver";
         	 String user="appuser";
         	 String pwd="apppass123";
         	 Connection con;
         	Class.forName(pilote);
  		    con=DriverManager.getConnection(chemin,user,pwd);
  		    PreparedStatement ps= con.prepareStatement(sql);
            ps.setLong(1, choixId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                correct = rs.getBoolean("estCorrect");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return correct;
    }
}