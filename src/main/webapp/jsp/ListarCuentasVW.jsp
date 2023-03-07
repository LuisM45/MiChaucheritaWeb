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
				
				
					<div class="wrap-info" style="height: fit-content;">

                        <div class="title-container">
                            <span class="login100-form-title-tabs" style="padding-bottom: 0px; padding-top: 40px;">
                                Mis Cuentas
                            </span>
                            <span class="login100-form-subtitle fs-15" style="padding-bottom: 2px;">
                                <br>Aqui podras ver todas las cuentas que existen <br>
                            </span>
                            <div class="input-container" style="place-items:center;">
                                Ingresa la información de la cuenta que deseas crear:
                                <div><br></div>
                                <div class="card text-bg-primary mb-3" style="max-width: 50rem;">
                                                    
                                    <div class="card-header">Cuentas Ingresos</div>
                                    <div class="card-body">
                                        <div class="table100-profile">
                                            <div class="table-responsive">
                                                <table class="table">
                                                <thead>
                                                    <tr class="table100-head">
                                                        <th class="column1-profile">Nombre</th>
                                                        <th class="column2-profile">Balance</th>
                                                        <th class="column3-profile">Actualizar</th>
                                                        <th class="column3-profile">Accion</th>
                                                        <th class="column3-profile">Ver Estado Contable</th>
                                                        </tr>
                                                </thead>
                                                    <tbody>
                                                <c:forEach items="${cuentasIngreso}" var="cuenta">
                                                    <tr>
                                                        <td>${cuenta.nombre}</td>
                                                        <td>${cuenta.valorTotal}</td>
                                                        <td>
                                                        <a href="?action=update&id=${cuenta.id}">Actualizar</a>
                                                        </td>
                                                        <td>
                                                        <a href="pagos?action=earning&id_source=${cuenta.id}">Registrar ingreso</a>
                                                        </td>
                                                        <td>
                                                        <a href="">👁</a>
                                                        </td>
                                                        </tr>
                                                </c:forEach>
                                                    </tbody>
                                                </table>
                                        </div>
                                        </div>
                                    </div>
                                </div>

                                <!---Cueenta de Egresos -->
                                <div class="card text-bg-primary mb-3" style="max-width: 50rem;">
                                                    
                                    <div class="card-header">Cuentas Egresos</div>
                                    <div class="card-body">
                                        <div class="table100-profile">
                                            <div class="table-responsive">
                                                <table class="table">
                                                <thead>
                                                    <tr class="table100-head">
                                                        <th class="column1-profile">Nombre</th>
                                                        <th class="column2-profile">Balance</th>
                                                        <th class="column3-profile">Actualizar</th>
                                                        <th class="column3-profile">Accion</th>
                                                        <th class="column3-profile">Ver Estado Contable</th>
                                                        </tr>
                                                </thead>
                                                    <tbody>
                                                    <c:forEach items="${cuentasEgreso}" var="cuenta">
                                                    <tr>
                                                        <td>${cuenta.nombre}</td>
                                                        <td>${cuenta.valorTotal}</td>
                                                        <td><a href="cuentas?action=update&id=${cuenta.id}">Actualizar</a></td>
                                                        <td><a href="pagos?action=spending&id_source=${cuenta.id}">Registrar egreso</a></td>
                                                        <td><a href="">👁</a></td>
                                                        </tr>
                                                </c:forEach>
                                                    </tbody>
                                                </table>
                                        </div>
                                        </div>
                                    </div>
                                </div>


                                <!---Cuenta de Egresos e Ingresos -->

                                <div class="card text-bg-primary mb-3" style="max-width: 50rem;">                 
                                    <div class="card-header">Cuentas Ingreso y Egresos</div>
                                    <div class="card-body">
                                        <div class="table100-profile">
                                            <div class="table-responsive">
                                                <table class="table">
                                                <thead>
                                                    <tr class="table100-head">
                                                        <th class="column1-profile">Nombre</th>
                                                        <th class="column2-profile">Balance</th>
                                                        <th class="column3-profile">Actualizar</th>
                                                        <th class="column3-profile">Accion</th>
                                                        <th class="column3-profile">Ver Estado Contable</th>
                                                        </tr>
                                                </thead>
                                                    <tbody>
                                                        <c:forEach items="${cuentasIngresoEgreso}" var="cuenta">
                                                    <tr>
                                                        <td>${cuenta.nombre}</td>
                                                        <td>${cuenta.valorTotal}</td>
                                                        <td><a href="cuentas?action=update&id=${cuenta.id}">Actualizar</a></td>
                                                        <td><a href="pagos?action=transfer&id_source=${cuenta.id}">Registrar transferencia</a></td>
                                                        <td><a href="">👁</a></td>
                                                    </tr>
                                                </c:forEach>
                                                    </tbody>
                                                </table>
                                        </div>
                                        </div>
                                    </div>
                                </div>


                            </div>  
                        </div>
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
