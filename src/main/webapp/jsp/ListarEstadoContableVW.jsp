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
        <c:forEach items="${valorByCuentaByEstadoContable}" var="valorByCuentaByEstadoContableEntry">
            <c:set var="estado" value="${valorByCuentaByEstadoContableEntry.key}" scope="request"/>
            <c:set var="valorByCuenta" value="${valorByCuentaByEstadoContableEntry.value}" scope="request"/>
            <h2>Estado del ${estado.fechaInicio} al ${estado.fechaFin}</h2>
            <a href="?action=query&id=${estado.id}">Mas detalle 👁</a>
            
            <h3>Ingreso</h3>
            <table>
            <tr>
                <th>Nombre</th>
                <th>Total</th>
            </tr>
            <c:forEach items="${cuentasByTipo.get('INGRESO')}" var="cuenta">
                <c:set var="cuenta" value="${valorByCuentaEntry.key}" scope="request"/>
                <c:set var="valor" value="${valorByCuentaEntry.value}" scope="request"/>
                <tr>                    
                    <td>${cuenta.nombre}</td>
                    <td>${valorByCuenta.getOrDefault(cuenta,0)}</td>
                </tr>
            </c:forEach>
            </table>
                
            <h3>Egreso</h3>
            <table>
            <tr>
                <th>Nombre</th>
                <th>Total</th>
            </tr>
            <c:forEach items="${cuentasByTipo.get('EGRESO')}" var="cuenta">
                <c:set var="cuenta" value="${valorByCuentaEntry.key}" scope="request"/>
                <c:set var="valor" value="${valorByCuentaEntry.value}" scope="request"/>
                <tr>                    
                    <td>${cuenta.nombre}</td>
                    <td>${valorByCuenta.getOrDefault(cuenta,0)}</td>
                </tr>
            </c:forEach>
            </table>
            
            <h3>Ingreso y egreso</h3>
            <table>
            <tr>
                <th>Nombre</th>
                <th>Total</th>
            </tr>
            <c:forEach items="${cuentasByTipo.get('INGRESO_EGRESO')}" var="cuenta">
                <c:set var="cuenta" value="${valorByCuentaEntry.key}" scope="request"/>
                <c:set var="valor" value="${valorByCuentaEntry.value}" scope="request"/>
                <tr>                    
                    <td>${cuenta.nombre}</td>
                    <td>${valorByCuenta.getOrDefault(cuenta,0)}</td>
                </tr>
            </c:forEach>
            </table>
        </c:forEach>
    </body>
</html>
