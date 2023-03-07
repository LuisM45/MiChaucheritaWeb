<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Mi Chaucherita</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/general.css">
    <link rel="stylesheet" type="text/css" href="css/tablesstyle.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			
			<div class="row align-items-center">
				
					<div class="container navegacion" style="margin-bottom: 0px;">
						<ul class="nav nav-tabs custom-tabs ">
							<li class="nav-item">
								<a class="nav-link " aria-current="page" href="usuario">Perfil</a>
							</li>
							<li class="nav-item">
								<a class="nav-link active" href="cuentas">Mis Cuentas</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="estado_contable">Mis Movimientos </a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="estado_contable?action=query">Estado Contable</a>
							</li>
						</ul>
					</div>
				
				
					<div class="wrap-info">

                        <div class="title-container">
                            <span class="login100-form-title-tabs" style="padding-bottom: 0px; ">
                                Actualizar Cuenta 
                            </span>
                            <span class="login100-form-subtitle fs-15" style="padding-bottom: 2px;">
                                <br>Aqui podras cambiar el nombre a la cuenta seleccionada<br>
                            </span>
                            <div class="input-container">
                                Ingresa el nuevo nombre de la cuenta para Actualizar:
                                <div><br></div>
                                <form action="?action=update" method="POST">
                                <div class="row align-items-center">
                                    <div class="col">
                                        <span class="input-group-text">Nuevo nombre de la cuenta</span>
                                        <input hidden name="id" value="${cuenta.id}">
                                        <input type="text" class="form-control" id="name" name="name" >
                                    </div>
                                    <div class="col botton-control">
                                        <input type="submit" class="btn btn-primary btn-lg">
                                    </div>
                                    </div>
                                </form>
                            </div>  
                        </div>
                        
						
		
						<!--Tabla de Visualizacion-->

<div class="table100">
    <table class="table">
        <thead>
            <tr class="table100-head">
                <th class="column1-profile">Nombre</th>
                <th class="column2-profile">Balance</th>
                <th class="column3-profile">Actualizar</th>
                <th class="column4-profile">Acción</th>
                <th class="column5-profile">Ver Estado Contable </th>
                </tr>
        </thead>
            <tbody>
                <tr>
                    <td>Celda de la tabla</td>
                    <td>Celda de la tabla</td>
                    <td>Celda de la tabla</td>
                </tr>
            
            </tbody>
        </table>
</div>
	

	
<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>
