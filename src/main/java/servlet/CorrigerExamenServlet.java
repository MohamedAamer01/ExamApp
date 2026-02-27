package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import dao.ChoixDAO;

/**
 * Servlet implementation class CorrigerExamenServlet
 */
@WebServlet("/CorrigerExamenServlet")
public class CorrigerExamenServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws IOException {

        Enumeration<String> params =
            request.getParameterNames();

        int score = 0;

        while(params.hasMoreElements()){

            String param = params.nextElement();

            if(param.startsWith("q_")){

                String valeur =
                    request.getParameter(param);

                if(valeur != null){

                    Long choixId =
                        Long.parseLong(valeur);

                    ChoixDAO dao =
                        new ChoixDAO();

                    if(dao.isCorrect(choixId)){
                        score++;
                    }
                }
            }
        }

        response.getWriter()
                .println("Votre note est : " + score);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
