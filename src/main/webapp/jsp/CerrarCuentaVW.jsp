<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="http://localhost:8000/mi_chaucherita.css">
        <link rel="stylesheet" href="css/stylsheet.css">
    </head>
    <body>
        <h1>Seleccione la cuenta a eliminar ${user.nombre}</h1>
        <a href="?action=create">Crear cuenta</a>
        <a href="?action=create">Elimnar cuentas</a>
        <table>
            <tr>
                <th>Nombre</th>
                <th>Accion</th>
            </tr>
            <c:forEach items="${cuentas}" var="cuenta">
                <tr>
                    <td>${cuenta.nombre}</td>
                    <td><a href="?action=delete&id=${cuenta.id}">❌</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
