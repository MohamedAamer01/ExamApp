<%@ page import="java.util.*, model.Question, model.Choix, dao.ChoixDAO" %>

<h2>Passer Examen</h2>

<form action="CorrigerExamenServlet" method="post">

<input type="hidden" name="examenId"
       value="<%= request.getAttribute("examenId") %>"/>

<%
List<Question> questions = (List<Question>) session.getAttribute("questions");
ChoixDAO choixDAO = new ChoixDAO(); // ← instancier UNE FOIS en dehors de la boucle

for (Question q : questions) {
%>
<div style="margin-bottom:20px;">
    <p><b><%= q.getEnonce() %></b></p>

    <% if ("QCM".equalsIgnoreCase(q.getType())) { %>
        <%
        List<Choix> choix = choixDAO.findByQuestion(q.getId());
        System.out.println("Choix pour question " + q.getId() + " : " + choix.size()); // DEBUG
        for (Choix c : choix) {
        %>
        <input type="radio"
               name="q_<%= q.getId() %>"
               value="<%= c.getId() %>">
        <%= c.getContenu() %><br>
        <% } %>
    <% } else { %>
        <input type="text" name="q_<%= q.getId() %>">
    <% } %>
</div>
<% } %>

<button type="submit">Soumettre</button>
</form>