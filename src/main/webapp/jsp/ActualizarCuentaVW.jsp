<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Cuenta</title>
        <link rel="stylesheet" href="http://localhost:8000/mi_chaucherita.css">
        <link rel="stylesheet" href="css/stylsheet.css">
    </head>
    <body>
        <h1>Ingrese la nueva información de la cuenta a actualizar</h1>
        <form action="?action=update" method="POST">
            <label for="name">Nombre</label>
            <input hidden name="id" value="${cuenta.id}">
            <input id="name" type="text" name="name">
            <input type="submit">
        </form>
    </body>
</html>
