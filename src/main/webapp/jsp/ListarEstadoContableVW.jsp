<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8000/mi_chaucherita.css">
        <link rel="stylesheet" href="css/stylsheet.css">
        <title>Estados Contables</title>
    </head>
    <body>
        <h1>Estados contables</h1>
        <a href="?action=generate">Generar estado contable</a>
        <c:forEach items="${estadosContables}" var="estado">
            <h2>Estado de ${estado.fechaInicio} al ${estado.fechaFin}</h2>
            <h3>Ingreso</h3>
            <table>
            <tr>
                <th>Nombre</th>
                <th>Valor</th>
            </tr>
                <tr>                    
                    <td>${estado.fechaInicio}</td>
                    <td>${estado.fechaFin}</td>
                    <td>${estado.ingresosTotales}</td>
                    <td>${estado.egresosTotales}</td>
                    <td><a href="?action=query&id=${estado.id}">👁</a></td>
                </tr>
            </table>
        </c:forEach>
    </body>
</html>
