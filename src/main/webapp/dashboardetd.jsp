<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Utilisateur" %>
<%
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard Étudiant</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-success px-4">
    <span class="navbar-brand fw-bold">🎓 ExamApp</span>
    <span class="text-white">👤 <%= user.getEmail() %></span>
</nav>

<div class="container mt-5">

    <div class="card shadow p-4 mb-4 border-0">
        <h4 class="text-success fw-bold">Bienvenue, <%= user.getEmail() %> 👋</h4>
        <p class="text-muted mb-0">Consultez les examens disponibles et passez vos tests.</p>
    </div>

    <div class="row g-4">

        <div class="col-md-6">
            <div class="card shadow h-100 border-0">
                <div class="card-body text-center p-4">
                    <div class="fs-1 mb-3">📚</div>
                    <h5 class="card-title fw-bold">Examens disponibles</h5>
                    <p class="card-text text-muted">Consultez la liste des examens et commencez.</p>
                    <a href="ListExamenServlet" class="btn btn-success w-100">
                        Voir les examens
                    </a>
                </div>
            </div>
        </div>

    </div>

    <div class="text-center mt-5">
        <a href="LogoutServlet" class="btn btn-outline-danger">
            🚪 Déconnexion
        </a>
    </div>

</div>

</body>
</html>