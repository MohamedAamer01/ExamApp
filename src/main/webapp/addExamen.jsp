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
    <title>Ajouter un Examen</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4">
    <span class="navbar-brand fw-bold">🎓 ExamApp</span>
    <span class="text-white">👤 <%= user.getEmail() %></span>
</nav>

<div class="container mt-5">
    <div class="card shadow p-4 border-0" style="max-width:500px; margin:auto;">

        <h4 class="text-primary fw-bold mb-4">📝 Ajouter un Examen</h4>

        <form action="AddExamenServlet" method="post">

            <div class="mb-3">
                <label class="form-label fw-semibold">Titre</label>
                <input type="text" name="titre"
                       class="form-control"
                       placeholder="Ex: Examen de Mathématiques"
                       required>
            </div>

            <div class="mb-3">
                <label class="form-label fw-semibold">Durée (minutes)</label>
                <input type="number" name="temps"
                       class="form-control"
                       placeholder="Ex: 60"
                       min="1"
                       required>
            </div>

            <div class="d-flex gap-2 mt-4">
                <button type="submit" class="btn btn-primary w-100">
                    ✅ Ajouter
                </button>
                <a href="dashboardadmin.jsp" class="btn btn-outline-secondary w-100">
                    ← Retour
                </a>
            </div>

        </form>
    </div>
</div>

</body>
</html>