package servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Question;
import model.Utilisateur;
import dao.QuestionDAO;
import dao.ChoixDAO;

import java.io.IOException;

@WebServlet("/AjouterQuestionServlet")
public class AjouterQuestionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        // Vérification session ADMIN
        Utilisateur user =   (Utilisateur) request.getSession().getAttribute("user");

        if( !user.getRole().equals("ADMIN")){

            response.sendRedirect("login.jsp");
            return;
        }

        try {

            String enonce = request.getParameter("enonce");
            String type = request.getParameter("type");
            Long examenId =
                Long.parseLong(request.getParameter("examenId"));

            if(enonce == null || enonce.trim().isEmpty()){
                response.sendRedirect("addQuestion.jsp?id=" + examenId);
                return;
            }

            //  Sauvegarde Question
            Question q = new Question();
            q.setEnonce(enonce);
            q.setType(type);
            q.setExamenId(examenId);

            QuestionDAO questionDAO = new QuestionDAO();
            Long questionId = questionDAO.saveAndReturnId(q);

            // 🔹 Si QCM → Sauvegarde choix
            if(type.equals("QCM")){

                String[] choix = request.getParameterValues("choix");
                int bonne = Integer.parseInt(request.getParameter("bonne"));
                ChoixDAO choixDAO = new ChoixDAO();

                for(int i=0; i<choix.length; i++){
                    if(choix[i] != null && !choix[i].trim().isEmpty()){
                        boolean estCorrect = (i == bonne);
                        choixDAO.save(questionId, choix[i], estCorrect);
                    }
                }

            } else if(type.equals("COURTE")){

                String reponseCorrecte = request.getParameter("reponseCorrecte");
                q.setReponseCorrecte(reponseCorrecte);
                // Mettre à jour la question avec la réponse correcte
                questionDAO.updateReponseCorrecte(questionId, reponseCorrecte);
            }

            response.sendRedirect(
                "AdminQuestionsServlet?id=" + examenId);

        } catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("dashboardadmin.jsp");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws IOException {

        //  On ne traite pas en GET
        response.sendRedirect("dashboardadmin.jsp");
    }
}