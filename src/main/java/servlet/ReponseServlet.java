package servlet;

import dao.ChoixDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Question;
import java.io.IOException;
import java.util.List;

@WebServlet("/ReponseServlet")
public class ReponseServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

HttpSession session = request.getSession();

// Vérification que la session est valide
Integer index = (Integer) session.getAttribute("index");
Integer score = (Integer) session.getAttribute("score");
List<Question> questions = (List<Question>) session.getAttribute("questions");

if(index == null || score == null || questions == null){
response.sendRedirect("StudentExamensServlet");
return;
}

// Vérification que l'index est valide
if(index >= questions.size()){
request.setAttribute("score", score);
request.setAttribute("total", questions.size());
request.getRequestDispatcher("resultat.jsp").forward(request, response);
return;
}

Question q = questions.get(index);
String reponse = request.getParameter("reponse");

if(reponse != null && !reponse.trim().isEmpty()) {
if("QCM".equalsIgnoreCase(q.getType().trim())) {
  Long choixId = Long.parseLong(reponse);
  ChoixDAO choixDAO = new ChoixDAO();
  if(choixDAO.isCorrect(choixId)) {
      score++;
      session.setAttribute("score", score);
  }
} else {
  if(reponse.trim().equalsIgnoreCase(q.getReponseCorrecte())) {
      score++;
      session.setAttribute("score", score);
  }
}
}

index++;
session.setAttribute("index", index);

if(index >= questions.size()) {
request.setAttribute("score", score);
request.setAttribute("total", questions.size());
request.getRequestDispatcher("resultat.jsp").forward(request, response);
} else {
response.sendRedirect("question.jsp");
}
}

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {
        doPost(request, response);
    }
}