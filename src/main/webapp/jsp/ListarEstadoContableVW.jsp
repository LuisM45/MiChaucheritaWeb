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
								<a class="nav-link " aria-current="page" href="perfil.html">Perfil</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="EstadosContables.html">Mis Cuentas</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="movimientos.html">Mis Movimientos </a>
							</li>
							<li class="nav-item">
								<a class="nav-link active" href="EstadosContables.html">Estado Contable</a>
							</li>
						</ul>
					</div>
				
				
					<div class="wrap-info" style="height: fit-content;">

                        <div class="title-container" style="padding-top: 40px;">
                            <span class="login100-form-title-tabs" style="padding-bottom: 0px; ">
                                Estado Contable
                            </span>
                            <span class="login100-form-subtitle fs-15" style="padding-bottom: 2px;">
                                Escoje un balance para ver más detalles
								<a href="?action=generate">Generar estado contable</a>
								<c:forEach items="${valorByCuentaByEstadoContable}" var="valorByCuentaByEstadoContableEntry">
									<c:set var="estado" value="${valorByCuentaByEstadoContableEntry.key}" scope="request"/>
									<c:set var="valorByCuenta" value="${valorByCuentaByEstadoContableEntry.value}" scope="request"/>
                            </span>
							<h4 class="card-header" style="Color:#188c8a">Estado del ${estado.fechaInicio} al ${estado.fechaFin} </h4>
							<a href="?action=query&id=${estado.id}">Mas detalle 👁</a>

                            <div class="input-container">
                                <div><br></div>
                                <div class="row align-items-center">
                                    <div class="col">
										<div class="card BIE">
										<div class="container-pic" >
											<div class="img-cont">
												<img src="images/Cangu.png" alt="Avatar" class="image">
											</div>
										</div>
											<div><br></div>
											<h4 class="card-header">Balance de Ingresos</h4>
											<ul class="list-group list-group-flush">
												<li class="list-group-item positive"  onclick="mostrarTabla1()">Mostrar Estado Contable</li>
												<li class="list-group-item negative"  onclick="mostrarTabla1()">Ocultar Estado Contable</li>
											</ul>
										</div>
										
									</div>
                                    <div class="col">
											<div class="card BIE">
											<div class="container-pic" >
												<div class="img-cont">
													<img src="images/Zorri.png" alt="Avatar" class="image">
												</div>
											</div>
												<div><br></div>
												<h4 class="card-header">Balance Ingresos y Egresos</h4>
												<ul class="list-group list-group-flush">
													<li class="list-group-item positive"  onclick="mostrarTabla3()">Mostrar Estado Contable</li>
													<li class="list-group-item negative"  onclick="mostrarTabla3()">Ocultar Estado Contable</li>
												</ul>
											</div>
											
                                    </div>
									<div class="col">
										<div class="card BIE">
										<div class="container-pic" >
											<div class="img-cont">
												<img src="images/dino.png" alt="Avatar" class="image">
											</div>
										</div>
											<div><br></div>
											<h4 class="card-header">Balance de Egresos</h4>
										<ul class="list-group list-group-flush">
											<li class="list-group-item positive"  onclick="mostrarTabla2()">Mostrar Estado Contable</li>
											<li class="list-group-item negative"  onclick="mostrarTabla2()">Ocultar Estado Contable</li>
										</ul>
										</div>
										
									</div>
                                    
                                    
									<div class="estado-cuentas" style=" place-items: center;"> 
										<div class="table100 tabla tabla-oculta"  id="mi-tabla-ingreso">
											<table>
											<thead>
											<tr class="table100-head">
											<th class="column1">Nombre</th>
											<th class="column2">total</th>
											</tr>
											</thead>
											<tbody>
												<c:forEach items="${cuentasByTipo.get('INGRESO')}" var="cuenta">
                									<c:set var="cuenta" value="${valorByCuentaEntry.key}" scope="request"/>
                									<c:set var="valor" value="${valorByCuentaEntry.value}" scope="request"/>
                									<tr>                    
                    								<td>${cuenta.nombre}</td>
                    								<td>${valorByCuenta.getOrDefault(cuenta,0)}</td>
													</tr>
            								</c:forEach>
											</tbody>
											</table>
										</div>
									</div>

									<!--Tabla 2 -->
									<div class="estado-cuentas" style=" place-items: center;"> 
										<div class="table100 tabla tabla-oculta"  id="mi-tabla-egreso">
											<table>
											<thead>
											<tr class="table100-head">
											<th class="column1">Nombre</th>
											<th class="column2">total</th>
											</tr>
											</thead>
											<tbody>
												<c:forEach items="${cuentasByTipo.get('EGRESO')}" var="cuenta">
                									<c:set var="cuenta" value="${valorByCuentaEntry.key}" scope="request"/>
                									<c:set var="valor" value="${valorByCuentaEntry.value}" scope="request"/>
                									<tr>                    
                    								<td>${cuenta.nombre}</td>
                    								<td>${valorByCuenta.getOrDefault(cuenta,0)}</td>
													</tr>
            								</c:forEach>
											</tbody>
											</table>
										</div>
									</div>

									<!--Tabla 3 -->
									<div class="estado-cuentas" style=" place-items: center;"> 
										<div class="table100 tabla tabla-oculta"  id="mi-tabla-egreso-ingreso">
											<table>
											<thead>
											<tr class="table100-head">
											<th class="column1">Nombre</th>
											<th class="column2">total</th>
											</tr>
											</thead>
											<tbody>
												<c:forEach items="${cuentasByTipo.get('INGRESO_EGRESO')}" var="cuenta">
                									<c:set var="cuenta" value="${valorByCuentaEntry.key}" scope="request"/>
                									<c:set var="valor" value="${valorByCuentaEntry.value}" scope="request"/>
                									<tr>                    
                    								<td>${cuenta.nombre}</td>
                    								<td>${valorByCuenta.getOrDefault(cuenta,0)}</td>
													</tr>
            								</c:forEach>
											</tbody>
											</table>
										</div>
									</div>

                                </div>
                            </div>  
                            
                        </div>
                        
						
		
						<!--Tabla de Visualizacion-->


	

	
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

	<script>
	function mostrarTabla1() {
      var tabla = document.getElementById("mi-tabla-ingreso");
      var card = document.getElementsByClassName("card")[0];
      
      if (tabla.className === "tabla tabla-oculta") {
        tabla.className = "tabla tabla-visible";
        card.style.backgroundColor = "#f2f2f2";
      } else {
        tabla.className = "tabla tabla-oculta";
        card.style.backgroundColor = "";
      }
    }


	function mostrarTabla2() {
      var tabla = document.getElementById("mi-tabla-egreso");
      var card = document.getElementsByClassName("card")[1];
      
      if (tabla.className === "tabla tabla-oculta") {
        tabla.className = "tabla tabla-visible";
        card.style.backgroundColor = "#f2f2f2";
      } else {
        tabla.className = "tabla tabla-oculta";
        card.style.backgroundColor = "";
      }
    }


	function mostrarTabla3() {
      var tabla = document.getElementById("mi-tabla-egreso-ingreso");
      var card = document.getElementsByClassName("card")[2];
      
      if (tabla.className === "tabla tabla-oculta") {
        tabla.className = "tabla tabla-visible";
        card.style.backgroundColor = "#f2f2f2";
      } else {
        tabla.className = "tabla tabla-oculta";
        card.style.backgroundColor = "";
      }
    }
	</script>

</body>
</html>