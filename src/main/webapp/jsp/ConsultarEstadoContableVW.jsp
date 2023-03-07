<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
								<a class="nav-link " aria-current="page" href="perfil.html">Perfil</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="EstadosContables.html">Mis Cuentas</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="movimientos.html">Mis Movimientos </a>
							</li>
							<li class="nav-item">
								<a class="nav-link active" href="perfil.html">Estado Contable</a>
							</li>
						</ul>
					</div>
				
				
					<div class="wrap-info">

                        <div class="title-container">
                            <span class="login100-form-title-tabs" style="padding-bottom: 0px; ">
                                Estado Contable
                            </span>
                            <span class="login100-form-subtitle fs-15" style="padding-bottom: 2px;">
                                <br>Aqui podrás visualizar tu estado contable de las fechas  <br>
                            </span>
                            <h4 class="card-header" style="Color:#188c8a">${estado.fechaInicio} - ${estado.fechaFin} </h4>
                            <div class="input-container">
                                <a href="?action=delete&id=${estado.id}">Limpiar Estado? Referencia</a>
                                <div><br></div>
                                <div><br></div>
                                <div class="table100">
                                    <table class="table">
                                        <thead>
                                            <tr class="table100-head">
                                                <th class="column1-profile">Fecha de realización</th>
                                                <th class="column2-profile">Concepto</th>
                                                <th class="column3-profile">Valor</th>
                                                <th class="column6-profile">Cuenta de Origen</th>
                                                <th class="column6-profile">Cuenta de Destino</th>
                                                </tr>
                                        </thead>
                                            <tbody>
                                                <c:forEach items="${movimientos}" var="movimiento">
                                                    <tr>                    
                                                        <td>${movimiento.fecha}</td>
                                                        <td>${movimiento.concepto}</td>
                                                        <td>${movimiento.valor}</td>
                                                        <td>${movimiento.cuentaGeneradora.nombre}</td>
                                                        <td>${movimiento.cuentaReceptora.nombre}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
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