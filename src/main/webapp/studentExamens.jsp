<%@ page import="java.util.List, model.Examen" %>

<h2>Liste des Examens</h2>

<%
List<Examen> examens =  (List<Examen>) request.getAttribute("examens");

for(Examen e : examens){
%>
    <p>
        <b><%= e.getTitre() %></b>
        <a href="PasserExamenServlet?id=<%= e.getId() %>">
            Passer
        </a>
    </p>
<%
}
%>