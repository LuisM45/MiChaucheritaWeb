<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8000/mi_chaucherita.css">
        <link rel="stylesheet" href="css/stylsheet.css">
        <title>Registrar Ingreso</title>
    </head>
    <body>
        <h1>Ingrese información sobre el ingreso de la cuenta ${sourceCuenta.nombre}</h1>
        <form method="POST">
            <input required hidden name="id_source" value="${sourceCuenta.id}">
            
            <label for="concept">Concepto</label>
            <input required id="concept" name="concept" type="text">
            
            <label for="value">Valor</label>
            <input required id="value" name="value" type="number" step="0.01">
            
            <label for="value">Fecha</label>
            <input required id="value" name="date" type="date">
            
            <label for="recipient">Cuenta receptora</label>
            <select required id="recipient" name="id_recipient">
                <c:forEach items="${potentialRecipients}" var="recipient">
                    <option value="${recipient.id}">${recipient.nombre}</option>
                </c:forEach>
            </select>
            <input type="submit">
        </form>
    </body>
</html>
