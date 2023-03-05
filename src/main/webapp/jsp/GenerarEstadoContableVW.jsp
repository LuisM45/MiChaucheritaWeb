<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
        <table>
            <tr>
                <th>Nombre</th>
                <th>Balance</th>
                <th>Actualizar</th>
                <th>Accion</th>
                <th>VerEstadoContable</th>
            </tr>
            <c:forEach items="${user.cuentasView}" var="cuenta">
                <tr>
                    <td>${cuenta.nombre}</td>
                    <td>${cuenta.valorTotal}</td>
                    <td>
                        <a href="?action=update&id=${cuenta.id}">Actualizar</a>
                    </td>
                    <td>
                        <c:if test="${cuenta.tipoCuenta.nombre == 'INGRESO'}">
                            <a href="pagos?action=earning&id_source=${cuenta.id}">Registrar ingreso</a>
                        </c:if>
                        <c:if test="${cuenta.tipoCuenta.nombre == 'INGRESO_EGRESO'}">
                            <a href="pagos?action=transfer&id_source=${cuenta.id}">Transferir valor</a>
                        </c:if>
                        <c:if test="${cuenta.tipoCuenta.nombre == 'EGRESO'}">
                            <a href="pagos?action=spending&id_source=${cuenta.id}">Registrar egreso</a>
                        </c:if>
                    </td>
                    <td>
                        <a href="">👁</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
