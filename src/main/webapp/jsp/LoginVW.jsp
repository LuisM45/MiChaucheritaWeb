<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://raw.githubusercontent.com/LuisM45/MiChaucheritaWeb/heressy/styles.css">
        <title>Login</title>
    </head>
    <body>
        <h1>Bienvenido a Mi Chaucherita!</h1>
        <h2>Porfavor, ingrese sus credenciales</h2>
        <!-- Podria, potencialmente mostrarse un mensaje de error -->
        <form method="POST">
            <label for="username">Usuario</label>
            <input type="text" id="username" name="username"/>
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password"/>
            <input type="submit" value="Ingresar"/>
        </form>
    </body>
</html>
