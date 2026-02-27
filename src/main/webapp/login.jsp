<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4" style="max-width:450px; margin:auto;">

        <div class="text-center mb-4">
            <h3 class="text-primary fw-bold">🎓 ExamApp</h3>
            <p class="text-muted">Connectez-vous à votre compte</p>
        </div>

        <% if(request.getParameter("error") != null){ %>
            <div class="alert alert-danger">
                ❌ Email ou mot de passe incorrect.
            </div>
        <% } %>

        <% if("success".equals(request.getParameter("inscription"))){ %>
            <div class="alert alert-success">
                ✅ Compte créé avec succès ! Connectez-vous.
            </div>
        <% } %>

        <form action="loginServlet" method="post">

            <div class="mb-3">
                <label class="form-label fw-semibold">Email</label>
                <input type="email" name="email"
                       class="form-control"
                       placeholder="exemple@email.com"
                       required>
            </div>

            <div class="mb-3">
                <label class="form-label fw-semibold">Mot de passe</label>
                <input type="password" name="password"
                       class="form-control"
                       placeholder="••••••••"
                       required>
            </div>

            <button type="submit" class="btn btn-primary w-100 mt-2">
                Se connecter
            </button>

            <hr class="my-3">

            <div class="text-center">
                <span class="text-muted">Pas encore de compte ?</span>
                <a href="inscription.jsp" class="ms-1 fw-semibold">S'inscrire</a>
            </div>

        </form>
    </div>
</div>

</body>
</html>