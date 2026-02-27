<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Question, model.Utilisateur" %>
<%
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
        return;
    }
    List<Question> questions = (List<Question>) request.getAttribute("questions");
    Long examenId = (Long) request.getAttribute("examenId");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Questions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-4">
    <span class="navbar-brand fw-bold">🎓 ExamApp</span>
    <span class="text-white">👤 <%= user.getEmail() %></span>
</nav>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h4 class="text-primary fw-bold">❓ Liste des Questions</h4>
        <a href="addQuestion.jsp?id=<%= examenId %>" class="btn btn-success">
            ➕ Ajouter Question
        </a>
    </div>

    <% if(questions == null || questions.isEmpty()){ %>
        <div class="alert alert-info text-center">
            Aucune question pour cet examen.
        </div>
    <% } else { %>

    <div class="card shadow border-0">
        <table class="table table-hover mb-0">
            <thead class="table-primary">
                <tr>
                    <th>#</th>
                    <th>Énoncé</th>
                    <th>Type</th>
                    <th class="text-center">Action</th>
                </tr>
            </thead>
            <tbody>
            <%
            int i = 1;
            for(Question q : questions){ %>
                <tr>
                    <td class="text-muted"><%= i++ %></td>
                    <td class="fw-semibold"><%= q.getEnonce() %></td>
                    <td>
                        <% if("QCM".equalsIgnoreCase(q.getType())){ %>
                            <span class="badge bg-primary">QCM</span>
                        <% } else { %>
                            <span class="badge bg-secondary">Réponse courte</span>
                        <% } %>
                    </td>
                    <td class="text-center">
                        <a href="DeleteQuestionServlet?id=<%= q.getId() %>&ex=<%= examenId %>"
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Supprimer cette question ?')">
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
        <a href="AdminListExamenServlet" class="btn btn-outline-secondary">
            ← Retour aux examens
        </a>
    </div>

</div>

</body>
</html>