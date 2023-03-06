<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8000/mi_chaucherita.css">
        <link rel="stylesheet" href="css/stylsheet.css">
        <title>Mis cuentas</title>
    </head>
    <body>
        <h1>Bienvenido ${user.nombre}</h1>
        <a href="?action=create">Crear cuenta</a>
        <a href="?action=delete">Elimnar cuentas</a>
        <a href="estado_contable">Ver estados contables</a>
        <form method="POST">
            <label for="start_date">Fecha inicial</label>
            <input type="date" id="start_date" name="start_date">
            <label for="end_date">Fecha final</label>
            <input type="date" id="end_date" name="end_date">
            <input type="submit">
        </form>
    </body>
</html>
