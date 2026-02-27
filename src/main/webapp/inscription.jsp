<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow p-4" style="max-width:450px; margin:auto;">
        <h3 class="mb-4 text-primary">Créer un compte</h3>

        <% if(request.getAttribute("erreur") != null){ %>
            <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
        <% } %>

        <form action="InscriptionServlet" method="post">

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mot de passe</label>
                <input type="password" name="motDePasse" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Rôle</label>
                <select name="role" class="form-select" required>
                    <option value="ETUDIANT">Étudiant</option>
                    <option value="ADMIN">Admin</option>
                </select>
            </div>

            <button type="submit" class="btn btn-success w-100">S'inscrire</button>

            <div class="mt-3 text-center">
                <a href="login.jsp">Déjà un compte ? Se connecter</a>
            </div>

        </form>
    </div>
</div>
</body>
</html>