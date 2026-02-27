<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Utilisateur" %>
<%
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    if(user == null || !user.getRole().equals("ADMIN")){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Importer Étudiants</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4">
    <span class="navbar-brand fw-bold">🎓 ExamApp</span>
    <span class="text-white">👤 <%= user.getEmail() %></span>
</nav>

<div class="container mt-5">
    <div class="card shadow p-4 border-0" style="max-width:500px; margin:auto;">

        <h4 class="text-primary fw-bold mb-4">📥 Importer des Étudiants</h4>

        <% if(request.getParameter("success") != null){ %>
            <div class="alert alert-success">
                ✅ <%= request.getParameter("success") %> étudiant(s) importé(s) avec succès !
            </div>
        <% } %>
        <% if(request.getParameter("error") != null){ %>
            <div class="alert alert-danger">
                ❌ Erreur lors de l'import.
            </div>
        <% } %>

        <p class="text-muted">Format attendu du fichier Excel :</p>
        <table class="table table-bordered table-sm mb-4">
            <thead class="table-light">
                <tr>
                    <th>email</th>
                    <th>motDePasse</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>etudiant@test.com</td>
                    <td>1234</td>
                </tr>
            </tbody>
        </table>

        <form action="ImportEtudiantsServlet" method="post"
              enctype="multipart/form-data">

            <div class="mb-3">
                <label class="form-label fw-semibold">Fichier Excel (.xlsx)</label>
                <input type="file" name="fichier"
                       class="form-control"
                       accept=".xlsx" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">
                📤 Importer
            </button>

            <a href="dashboardadmin.jsp" class="btn btn-outline-secondary w-100 mt-2">
                ← Retour
            </a>

        </form>
    </div>
</div>
</body>
</html>