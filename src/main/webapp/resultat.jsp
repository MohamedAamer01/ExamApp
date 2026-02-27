<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultat Examen</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-5">

    <div class="card shadow p-5 text-center">

        <h2 class="mb-4 text-primary">Résultat de votre Examen</h2>

        <%
            Integer score = (Integer) request.getAttribute("score");
            Integer total = (Integer) request.getAttribute("total");

            if(score == null) score = 0;
            if(total == null) total = 0;

            double noteSur20 = 0;

            if(total > 0){
                noteSur20 = (score * 20.0) / total;
            }
        %>

        <h4>
            Score : 
            <span class="badge bg-success fs-4">
                <%= score %> / <%= total %>
            </span>
        </h4>

        <h4 class="mt-3">
            Note sur 20 :
            <span class="badge bg-info fs-4">
                <%= String.format("%.2f", noteSur20) %>
            </span>
        </h4>

        <div class="mt-4">
            <% if(noteSur20 >= 10){ %>
                <div class="alert alert-success">
                    🎉 Félicitations, vous avez réussi !
                </div>
            <% } else { %>
                <div class="alert alert-danger">
                    ❌ Vous devez améliorer vos performances.
                </div>
            <% } %>
        </div>

        <a href="StudentExamensServlet"
           class="btn btn-primary mt-4">
            Retour aux examens
        </a>

    </div>

</div>

</body>
</html>