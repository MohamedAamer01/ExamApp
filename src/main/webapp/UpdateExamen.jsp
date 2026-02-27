<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier Examen</title>

<style>
    * {
        box-sizing: border-box;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    body {
        margin: 0;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background: #f4f6f9;
    }

    .card {
        background: #ffffff;
        padding: 35px;
        width: 400px;
        border-radius: 12px;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
        transition: 0.3s ease;
    }

    .card:hover {
        box-shadow: 0 12px 28px rgba(0, 0, 0, 0.12);
    }

    h2 {
        text-align: center;
        margin-bottom: 25px;
        color: #333;
        font-weight: 600;
    }

    .form-group {
        margin-bottom: 18px;
    }

    label {
        display: block;
        margin-bottom: 6px;
        font-size: 14px;
        color: #555;
    }

    input {
        width: 100%;
        padding: 10px 12px;
        border-radius: 8px;
        border: 1px solid #ddd;
        font-size: 14px;
        transition: 0.3s;
    }

    input:focus {
        border-color: #4f46e5;
        outline: none;
        box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.15);
    }

    button {
        width: 100%;
        padding: 12px;
        border-radius: 8px;
        border: none;
        background: #4f46e5;
        color: white;
        font-size: 15px;
        font-weight: 500;
        cursor: pointer;
        transition: 0.3s;
    }

    button:hover {
        background: #4338ca;
    }
</style>

</head>
<body>

<div class="card">
    <h2>Modifier Examen</h2>

    <form action="UpdateExemenServlet" method="post">

        <input type="hidden" name="id" value="${examen.id}">

        <div class="form-group">
            <label>Titre</label>
            <input type="text" name="titre" value="${examen.titre}" required>
        </div>

        <div class="form-group">
            <label>Temps Total (minutes)</label>
            <input type="number" name="tempsTotal" value="${examen.tempsTotal}" required>
        </div>

        <button type="submit">Enregistrer les modifications</button>

    </form>
</div>

</body>
</html>