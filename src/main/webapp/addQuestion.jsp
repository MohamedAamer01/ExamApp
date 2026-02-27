<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Utilisateur" %>

<%
    Utilisateur user = (Utilisateur) session.getAttribute("user");

    if(user == null || user.getRole() == null || !user.getRole().equals("ADMIN")){
        response.sendRedirect("login.jsp");
        return;
    }

    Long examenId = Long.parseLong(request.getParameter("id"));
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ajouter Question</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <script>
        function toggleFields() {
            let type = document.getElementById("type").value;
            let qcmDiv = document.getElementById("qcmFields");
            let courteDiv = document.getElementById("courteFields");

            if(type === "QCM"){
                qcmDiv.style.display = "block";
                courteDiv.style.display = "none";
            } else {
                qcmDiv.style.display = "none";
                courteDiv.style.display = "block";
            }
        }
    </script>
</head>

<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">

        <h3 class="mb-4 text-primary">Ajouter une Question</h3>

        <form action="AjouterQuestionServlet" method="post">

            <input type="hidden" name="examenId" value="<%= examenId %>"/>

            <!-- Énoncé -->
            <div class="mb-3">
                <label class="form-label">Énoncé</label>
                <textarea name="enonce"
                          class="form-control"
                          rows="4"
                          required></textarea>
            </div>

            <!-- Type -->
            <div class="mb-3">
                <label class="form-label">Type</label>
                <select name="type"
                        id="type"
                        class="form-select"
                        onchange="toggleFields()"
                        required>
                    <option value="QCM">QCM</option>
                    <option value="COURTE">Courte Réponse</option>
                </select>
            </div>

            <!-- Champs QCM -->
            <div id="qcmFields" style="display:block;">

                <label class="form-label">Choix</label>

                <input type="text" name="choix"
                       class="form-control mb-2"
                       placeholder="Choix 1">

                <input type="text" name="choix"
                       class="form-control mb-2"
                       placeholder="Choix 2">

                <input type="text" name="choix"
                       class="form-control mb-2"
                       placeholder="Choix 3">

                <input type="text" name="choix"
                       class="form-control mb-2"
                       placeholder="Choix 4">

                <div class="mb-3 mt-2">
                    <label class="form-label">
                        Index bonne réponse (0 à 3)
                    </label>
                    <input type="number"
                           name="bonne"
                           min="0"
                           max="3"
                           class="form-control"
                           style="width: 100px;">
                </div>
            </div>

            <!-- Champs Réponse Courte -->
            <div id="courteFields" style="display:none;">
                <div class="mb-3">
                    <label class="form-label">Réponse correcte</label>
                    <input type="text"
                           name="reponseCorrecte"
                           class="form-control"
                           placeholder="Entrez la réponse attendue">
                </div>
            </div>

            <button type="submit" class="btn btn-success mt-3">
                Ajouter
            </button>

            <a href="dashboardadmin.jsp" class="btn btn-secondary mt-3">
                Retour
            </a>

        </form>
    </div>
</div>

</body>
</html>