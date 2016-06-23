<%-- 
    Document   : ModificarDatos
    Created on : 22-nov-2015, 23:57:40
    Author     : CristianFaune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Datos</title>
    </head>
    <c:if test="${empty doctor}">
        <c:redirect url="/ValidarIngreso"/>
    </c:if>
    <body>
        <%@include file="Opciones.jsp" %>
        <div class="container col-md-8 col-sm-offset-2">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-8 col-sm-offset-2">
                    <ul class="nav nav-pills nav-justified">
                        <li role="presentation"><a href="<c:url value="/BuscarDuenoServlet"/>">Inicio</a></li></a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroDuenoServlet"/>">Nuevo Dueño</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroMascotaServlet"/>">Nueva Mascota</a></li>
                        <li role="presentation" class="active"><a href="<c:url value="/BuscarModificarServlet"/>">Modificar</a></li>
                    </ul>
                </div>
            </div>
            <form class="form-inline" action="<c:url value="/BuscarModificarServlet"/>" method="post">
                <div class="form-group col-sm-offset-2">
                    <label for="exampleInputName2"></label>
                    <input type="text" class="form-control" 
                           id="exampleInputName2" placeholder="Rut del dueño"
                           name="rutBuscar"  autofocus="true"/>
                </div>
                <input type="submit" class="btn btn-default" value="Buscar"/>
            </form>
            <p class="text-danger col-sm-offset-2"><c:out value="${mapMensajeRut['errorRut']}"/></p>
            <br/>
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-sm-offset-1">
                        <h3>Modificar Datos Dueño</h3>
                        <br/>
                        <p class="text-success"><c:out value="${mapMensaje['exito']}"/></p>
                        <br/>
                        <form class="form-horizontal" action="<c:url value="/ModificarDatosServlet"/>" method="post">

                            <div class="form-group">
                                <label for="inputRut" class="col-sm-2 control-label">Rut:</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" id="rut" name="rut" 
                                           value="<c:out value="${dueno.rut}"/>" placeholder="Ingrese su Rut">
                                    <p class="text-danger"><c:out value="${mapMensaje['errorRut']}"/></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputNombre" class="col-sm-2 control-label">Nombre: </label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" id="nombre" name="nombre" 
                                           value="<c:out value="${dueno.nombre}"/>" placeholder="Ingrese su nombre">
                                    <p class="text-danger"><c:out value="${mapMensaje['errorNombre']}"/></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputApellidos" class="col-sm-2 control-label">Apellidos: </label>
                                <div class="col-xs-6">
                                    <input type="text" class="form-control" id="apellidos" name="apellidos" 
                                           value="<c:out value="${dueno.apellidos}"/>" placeholder="Ingrese sus apellidos">
                                    <p class="text-danger"><c:out value="${mapMensaje['errorApellidos']}"/></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputDireccion" class="col-sm-2 control-label">Dirección: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="direccion" name="direccion" 
                                           value="<c:out value="${dueno.direccion}"/>" placeholder="Ingrese su dirección">
                                    <p class="text-danger"><c:out value="${mapMensaje['errorDireccion']}"/></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputTelefono" class="col-sm-2 control-label">Teléfono: </label>
                                <div class="col-md-4">
                                    <input type="number" class="form-control" id="telefono" name="telefono" 
                                           value="<c:out value="${dueno.telefono}"/>" placeholder="Ingrese su teléfono">
                                    <p class="text-danger"><c:out value="${mapMensaje['errorTelefono']}"/></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="col-sm-2 control-label">Email: </label>
                                <div class="col-xs-6">
                                    <input type="email" class="form-control" id="mail" name="mail" 
                                           value="<c:out value="${dueno.mail}"/>" placeholder="Ingrese su email (Opcional)">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">Actualizar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <%--<div class="col-xs-6 col-md-4"></div>--%>
                </div>
            </div>
            <br/>
    </body>
</html>
