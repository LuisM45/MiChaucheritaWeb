<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://localhost:8000/mi_chaucherita.css">
        <link rel="stylesheet" href="css/stylsheet.css">
        <title>Estado contable</title>
    </head>
    <body>
        <h1>Estado contable ${estado.fechaInicio} - ${estado.fechaFin} </h1>
        <a href="?action=delete&id=${estado.id}">Eliminar</a>
        <table>
            <tr>
                <th>Fecha de realización</th>
                <th>Concepto</th>
                <th>Valor</th>
                <th>Cuenta de origen</th>
                <th>Cuenta de destino</th>
            </tr>
            <c:forEach items="${movimientos}" var="movimiento">
                <tr>                    
                    <td>${movimiento.fecha}</td>
                    <td>${movimiento.concepto}</td>
                    <td>${movimiento.valor}</td>
                    <td>${movimiento.cuentaGeneradora.nombre}</td>
                    <td>${movimiento.cuentaReceptora.nombre}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
