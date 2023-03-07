<!--desglose de todos los movimientos en un margen de tiempo.-->
<!--da un resumen de todos los estados contables por cuenta.-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Mi Chaucherita</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css" href="css/general.css">
        <link rel="stylesheet" type="text/css" href="css/tablesstyle.css">
        <link rel="stylesheet" type="text/css" href="css/perfil.css">
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="row align-items-center">
                    <div class="container navegacion" style="margin-bottom: 0px;">
                        <ul class="nav nav-tabs custom-tabs ">
                            <li class="nav-item">
                                <a class="nav-link active " aria-current="page" href="usuario">Perfil</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="cuentas">Mis Cuentas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="estado_contable">Mis Movimientos </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="estado_contable?action=query">Estado Contable</a>
                            </li>
                        </ul>
                    </div>


                    <div class="wrap-info" style="height: 1200px">
                        <div class="wrap-login100" style="padding: 0px; width: 1260px; height: 1200px;">
                            <div class="login100-pic"> 
                                <div class="row align-items-start" style="width: 1260px;height: 1200px;">
                                    <div class="col-4">
                                        <div class="card" style="width: 18rem;">
                                            <img src="https://picsum.photos/200" class="card-img-top" alt="Pepe_Profile">
                                            <div class="card-body">
                                                <h5 class="card-title">@${user.nombre}</h5>
                                                <p class="card-text">Mis Finanzas</p>
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">
                                                    <a href="cuentas?action=create" class="card-link">Crear Cuenta</a>
                                                </li>
                                                <li class="list-group-item">
                                                    <a href="cuentas?action=delete" class="card-link">Eliminar Cuenta</a>
                                                </li>
                                                <li class="list-group-item">
                                                    <a href="estado_contable?action=query" class="card-link">Ver Estados Contables</a>
                                                </li>
                                            </ul>
                                            <div class="card-body">
                                                <a href="login?action=logout" class="btn btn-primary">Cerrar Sesión</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-7">
                                        <div>
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
                                                                        <th class="column4-profile">Acción</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${user.cuentasIngreso}" var="cuenta">
                                                                        <tr>
                                                                            <td>${cuenta.nombre}</td>
                                                                            <td>${cuenta.valorTotal}</td>
                                                                            <td><a href="cuentas?action=update&id=${cuenta.id}">Actualizar</a></td>
                                                                            <td><a href="pagos?action=earning&id_source=${cuenta.id}">Registrar ingreso</a></td>
                                                                        </tr>
                                                                    </c:forEach>    
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card text-bg-secondary mb-3" style="max-width: 50rem;">
                                                <div class="card-header">Cuentas Egreso</div>
                                                <div class="card-body">
                                                    <div class="table100-profile">
                                                        <div class="table-responsive">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr class="table100-head">
                                                                        <th class="column1-profile">Nombre</th>
                                                                        <th class="column2-profile">Balance</th>
                                                                        <th class="column3-profile">Actualizar</th>
                                                                        <th class="column4-profile">Acción</th>
                                                                    </tr>
                                                                </thead>
                                                                <c:forEach items="${user.getCuentasEgreso()}" var="cuenta">
                                                                    <tr>
                                                                        <td>${cuenta.nombre}</td>
                                                                        <td>${cuenta.valorTotal}</td>
                                                                        <td><a href="cuentas?action=update&id=${cuenta.id}">Actualizar</a></td>
                                                                        <td><a href="pagos?action=spending&id_source=${cuenta.id}">Registrar egreso</a></td>
                                                                    </tr>
                                                                </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card text-bg-success mb-3" style="max-width: 50rem;">
                                                <div class="card-header">Cuentas Ingreso y Egreso</div>
                                                <div class="card-body">
                                                    <div class="table100-profile">
                                                        <div class="table-responsive">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr class="table100-head">
                                                                        <th class="column1-profile">Nombre</th>
                                                                        <th class="column2-profile">Balance</th>
                                                                        <th class="column3-profile">Actualizar</th>
                                                                        <th class="column4-profile">Acción</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${user.getCuentasIngresoEgreso()}" var="cuenta">
                                                                        <tr>
                                                                            <td>${cuenta.nombre}</td>
                                                                            <td>${cuenta.valorTotal}</td>
                                                                            <td><a href="cuentas?action=update&id=${cuenta.id}">Actualizar</a></td>
                                                                            <td><a href="pagos?action=transfer&id_source=${cuenta.id}">Registrar transferencia</a></td>
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
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        </div>








        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/tilt/tilt.jquery.min.js"></script>
        <script >
            $('.js-tilt').tilt({
                scale: 1.1
            })
        </script>
        <script src="js/main.js"></script>
    </body>
</html>