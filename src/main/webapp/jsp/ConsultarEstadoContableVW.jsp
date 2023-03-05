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
        <h1>Estado contable ${estado.fechaInicio} - ${estado.fechaFin} - </h1>
        <a href="?action=generate">Generar estado contable</a>
        <table>
            <tr>
                <th>Fecha de realización</th>
                <th>Concepto</th>
                <th>Valor</th>
                <th>Cuenta de origen</th>
                <th>Cuenta de destino</th>
                <th>Más</th>
            </tr>
            <c:forEach items="${MovimientosByCuentas}" var="MovimientosByCuenta">
                <tr>
                    <th>Fecha de realización</th>
                    <th>Concepto</th>
                    <th>Valor</th>
                    <th>Cuenta de origen</th>
                    <th>Cuenta de destino</th>
                    <th>Más</th>
                </tr>
                <tr>                    
                    <td>${estado.fechaInicio}</td>
                    <td>${estado.fechaFin}</td>
                    <td>${estado.ingresosTotales}</td>
                    <td>${estado.egresosTotales}</td>
                    <td><a href="?action=query&id=${estado.id}">👁</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
