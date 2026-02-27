<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Question, model.Choix, dao.ChoixDAO, model.Utilisateur" %>
<%
    List<Question> questions = (List<Question>) session.getAttribute("questions");
    Integer index = (Integer) session.getAttribute("index");

    if(questions == null || index == null){
        response.sendRedirect("StudentExamensServlet");
        return;
    }

    if(index >= questions.size()){
        request.setAttribute("score", session.getAttribute("score"));
        request.setAttribute("total", questions.size());
        request.getRequestDispatcher("resultat.jsp").forward(request, response);
        return;
    }

    Question q = questions.get(index);
    Utilisateur user = (Utilisateur) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Question <%= index+1 %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .choix-label {
            display: block;
            padding: 12px 16px;
            border: 2px solid #dee2e6;
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.2s;
        }
        .choix-label:hover {
            border-color: #0d6efd;
            background-color: #f0f4ff;
        }
        input[type="radio"]:checked + .choix-label {
            border-color: #0d6efd;
            background-color: #e7efff;
            font-weight: 600;
        }
        #timer-bar {
            transition: width 1s linear;
        }
    </style>
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-success px-4">
    <span class="navbar-brand fw-bold">🎓 ExamApp</span>
    <% if(user != null){ %>
        <span class="text-white">👤 <%= user.getEmail() %></span>
    <% } %>
</nav>

<div class="container mt-5">
    <div class="card shadow border-0 p-4" style="max-width:650px; margin:auto;">

        <!-- Header -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <span class="badge bg-success fs-6">
                Question <%= index+1 %> / <%= questions.size() %>
            </span>
            <span class="badge bg-warning text-dark fs-6">
                ⏱ <span id="timer">30</span> sec
            </span>
        </div>

        <!-- Barre de progression temps -->
        <div class="progress mb-4" style="height:6px;">
            <div id="timer-bar" class="progress-bar bg-warning"
                 style="width:100%"></div>
        </div>

        <!-- Énoncé -->
        <h5 class="fw-bold mb-4">📌 <%= q.getEnonce() %></h5>

        <!-- Formulaire -->
        <form action="ReponseServlet" method="post">
            <input type="hidden" name="questionId" value="<%= q.getId() %>"/>

            <% if(q.getType() != null && q.getType().trim().equalsIgnoreCase("QCM")){ %>
                <%
                ChoixDAO choixDAO = new ChoixDAO();
                List<Choix> choix = choixDAO.findByQuestion(q.getId());
                for(Choix c : choix){
                %>
                <div class="mb-2">
                    <input type="radio" name="reponse"
                           value="<%= c.getId() %>"
                           id="choix_<%= c.getId() %>"
                           class="d-none">
                    <label for="choix_<%= c.getId() %>" class="choix-label">
                        <%= c.getContenu() %>
                    </label>
                </div>
                <% } %>
            <% } else { %>
                <div class="mb-3">
                    <label class="form-label fw-semibold">Votre réponse :</label>
                    <input type="text" name="reponse"
                           class="form-control"
                           placeholder="Écrivez votre réponse ici..."
                           required>
                </div>
            <% } %>

            <button type="submit" class="btn btn-success w-100 mt-3">
                Suivant ➡️
            </button>
        </form>

    </div>
</div>

<script>
let temps = 30;
let timerEl = document.getElementById("timer");
let timerBar = document.getElementById("timer-bar");

let interval = setInterval(function(){
    temps--;
    timerEl.innerText = temps;
    timerBar.style.width = (temps / 30 * 100) + "%";

    if(temps <= 10){
        timerBar.classList.remove("bg-warning");
        timerBar.classList.add("bg-danger");
        timerEl.parentElement.classList.remove("bg-warning", "text-dark");
        timerEl.parentElement.classList.add("bg-danger");
    }

    if(temps <= 0){
        clearInterval(interval);
        document.forms[0].submit();
    }
}, 1000);
</script>

</body>
</html>