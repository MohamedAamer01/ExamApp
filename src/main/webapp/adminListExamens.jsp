<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Examen, model.Utilisateur" %>
<%
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
        return;
    }
    List<Examen> examens = (List<Examen>) request.getAttribute("examens");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Examens</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4">
    <span class="navbar-brand fw-bold">🎓 ExamApp</span>
    <span class="text-white">👤 <%= user.getEmail() %></span>
</nav>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h4 class="text-primary fw-bold">📋 Liste des Examens</h4>
        <a href="addExamen.jsp" class="btn btn-primary">+ Ajouter Examen</a>
    </div>

    <% if(examens == null || examens.isEmpty()){ %>
        <div class="alert alert-info text-center">
            Aucun examen disponible pour le moment.
        </div>
    <% } else { %>

    <div class="card shadow border-0">
        <table class="table table-hover mb-0">
            <thead class="table-primary">
                <tr>
                    <th>#</th>
                    <th>Titre</th>
                    <th>Durée</th>
                    <th class="text-center">Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
            int i = 1;
            for(Examen e : examens){ %>
                <tr>
                    <td class="text-muted"><%= i++ %></td>
                    <td class="fw-semibold"><%= e.getTitre() %></td>
                    <td>⏱ <%= e.getTempsTotal() %> min</td>
                    <td class="text-center">
                        <a href="addQuestion.jsp?id=<%= e.getId() %>"
                           class="btn btn-sm btn-success me-1">
                            ➕ Question
                        </a>
                        <a href="UpdateExemenServlet?id=<%= e.getId() %>"
                           class="btn btn-sm btn-warning me-1">
                          ✏️ Modifier
                        </a>
                        <a href="DeleteExamenServlet?id=<%= e.getId() %>"
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Supprimer cet examen ?')">
                            🗑 Supprimer
                        </a> 
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <% } %>

    <div class="mt-4">
        <a href="dashboardadmin.jsp" class="btn btn-outline-secondary">
            ← Retour
        </a>
    </div>

</div>

</body>
</html>