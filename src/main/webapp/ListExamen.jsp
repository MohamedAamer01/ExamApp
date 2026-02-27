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
    <title>Examens disponibles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-success px-4">
    <span class="navbar-brand fw-bold">🎓 ExamApp</span>
    <span class="text-white">👤 <%= user.getEmail() %></span>
</nav>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h4 class="text-success fw-bold">📚 Examens disponibles</h4>
    </div>

    <% if(examens == null || examens.isEmpty()){ %>
        <div class="alert alert-info text-center">
            Aucun examen disponible pour le moment.
        </div>
    <% } else { %>

    <div class="row g-4">
        <% for(Examen e : examens){ %>
        <div class="col-md-6">
            <div class="card shadow border-0 h-100">
                <div class="card-body p-4">
                    <h5 class="card-title fw-bold"><%= e.getTitre() %></h5>
                    <p class="text-muted">⏱ Durée : <%= e.getTempsTotal() %> minutes</p>
                    <a href="PasserExamenServlet?id=<%= e.getId() %>"
                       class="btn btn-success w-100">
                        🚀 Passer l'examen
                    </a>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <% } %>

    <div class="mt-4">
        <a href="dashboardetd.jsp" class="btn btn-outline-secondary">
            ← Retour
        </a>
    </div>

</div>

</body>
</html>