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
    <title>Dashboard Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4">
    <span class="navbar-brand fw-bold">🎓 ExamApp</span>
    <span class="text-white">👤 <%= user.getEmail() %></span>
</nav>

<div class="container mt-5">

    <div class="card shadow p-4 mb-4">
        <h4 class="text-primary fw-bold">Bienvenue, Admin 👋</h4>
        <p class="text-muted mb-0">Gérez vos examens et questions depuis ce tableau de bord.</p>
    </div>

    <div class="row g-4">

        <div class="col-md-6">
            <div class="card shadow h-100 border-0">
                <div class="card-body text-center p-4">
                    <div class="fs-1 mb-3">📝</div>
                    <h5 class="card-title fw-bold">Ajouter un Examen</h5>
                    <p class="card-text text-muted">Créez un nouvel examen pour vos étudiants.</p>
                    <a href="addExamen.jsp" class="btn btn-primary w-100">
                        Ajouter Examen
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card shadow h-100 border-0">
                <div class="card-body text-center p-4">
                    <div class="fs-1 mb-3">📋</div>
                    <h5 class="card-title fw-bold">Liste des Examens</h5>
                    <p class="card-text text-muted">Consultez et gérez tous vos examens.</p>
                    <a href="AdminListExamenServlet" class="btn btn-success w-100">
                        Voir Examens
                    </a>
                </div>
            </div>
        </div>

    </div>
    <div class="col-md-6">
    <div class="card shadow h-100 border-0">
        <div class="card-body text-center p-4">
            <div class="fs-1 mb-3">👥</div>
            <h5 class="card-title fw-bold">Importer Étudiants</h5>
            <p class="card-text text-muted">Importez la liste des étudiants via fichier Excel.</p>
            <a href="importEtudiants.jsp" class="btn btn-primary w-100">
                📥 Importer Excel
            </a>
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