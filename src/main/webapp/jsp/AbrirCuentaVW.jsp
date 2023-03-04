<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Abrir Cuenta</title>
    </head>
    <body>
        <h1>Ingrese la información de las cuentas a crear</h1>
        <form action="?action=create" method="POST">
            <label for="name">Nombre</label>
            <input id="name" type="text" name="name">
            <label for="type">Tipo de Cuenta</label>
            <select id="type" name="type">
                <option value="EGRESO">Egreso</option>
                <option value="INGRESO">Ingreso</option>
                <option value="INGRESO_EGRESO">Ingreso y Egreso</option>
            </select>
            <input type="submit">
        </form>
    </body>
</html>
