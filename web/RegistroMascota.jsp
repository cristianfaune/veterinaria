<%-- 
    Document   : RegistroMascota
    Created on : 22-nov-2015, 12:44:15
    Author     : CristianFaune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

        <title>Registro Mascota</title>
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
                        <li role="presentation" class="active"><a href="<c:url value="/RegistroMascotaServlet"/>">Nueva Mascota</a></li>
                        <li role="presentation"><a href="<c:url value="/BuscarModificarServlet"/>">Modificar</a></li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-8 col-sm-offset-3">
                    <h3>Registro de nueva Mascota</h3>
                    <br/>
                    <p class="text-success"><c:out value="${mapMensaje['exito']}"/></p>
                    <br/>
                    <form class="form-horizontal" action="<c:url value="/RegistroMascotaServlet"/>" method="post">
                        <div class="form-group">
                            <label for="inputRut" class="col-sm-2 control-label">Rut Dueño:</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="rut" name="rutDueno" 
                                       value="<c:out value="${param.rutDueno}"/>" placeholder="Ingrese rut del dueño" autofocus="true">
                                <p class="text-danger"><c:out value="${mapMensaje['errorRut']}"/></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputNombre" class="col-sm-2 control-label">Nombre: </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" id="nombre" name="nombreMascota" 
                                       value="<c:out value="${param.nombreMascota}"/>" placeholder="Ingrese nombre mascota">
                                <p class="text-danger"><c:out value="${mapMensaje['errorNombre']}"/></p>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <label class="control-label">Especie:</label>
                            <select name="especies" class="form-control">
                                <option value="0">--seleccione--</option>
                                <c:forEach var="l" items="${listaEspecies}">
                                    <option value="${l.idEspecie}">
                                        <c:out value="${l.descripcion}"></c:out>
                                        </option>
                                </c:forEach>
                            </select>
                            <p class="text-danger"><c:out value="${mapMensaje['errorEspecies']}"/></p>
                        </div>
                        <div class="form-group col-lg-offset-0">
                            <label class="control-label col-xs-offset-0">  Raza:</label>
                            <br/>
                            <div class="col-md-4 col-xs-offset-0">
                                <input type="text" class="form-control" id="nombre" name="raza" 
                                       value="<c:out value="${param.raza}"/>" placeholder="Ingrese la raza">
                                <p class="text-danger"><c:out value="${mapMensaje['errorRaza']}"/></p>
                            </div>
                        </div>
                        <div class="form-group col-lg-12">
                            <label for="inputDireccion" class="col-sm-2 control-label">Fecha Nacimiento: </label>
                            <div class="col-md-6">
                                <input type="date" class="form-control" id="direccion" name="fechaNacimiento" 
                                       value="<c:out value="${param.fechaNacimiento}"/>" placeholder="Ingrese fecha">
                                <p class="text-danger"><c:out value="${mapMensaje['errorFecha']}"/></p>
                            </div>
                        </div>
                        <label>Esterilizado: </label>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="esterilizado" id="optionsRadioseste1" value="1">
                                Si
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="esterilizado" id="optionsRadioseste2" value="2" checked="">
                                No
                            </label>
                        </div>
                        <br/>
                        <br/>
                        <label>Sexo: </label>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="sexo" id="optionssex1" value="1" checked>
                                Macho
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="sexo" id="optionssex2" value="2">
                                Hembra
                            </label>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label for="inputDescripcion" class="col-sm-2 control-label">Descripción: </label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="nombre" name="descripcion" 
                                       value="<c:out value="${param.descripcion}"/>" placeholder="(Opcional)">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Registrar</button>
                            </div>
                        </div>
                    </form>
                </div>
                <%--<div class="col-xs-6 col-md-4"></div>--%>
            </div>
        </div>
    </body>
</html>
