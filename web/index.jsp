<%-- 
    Document   : index
    Created on : 11-nov-2015, 8:31:58
    Author     : CristianFaune
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <title>Login</title>
    </head>
    <body onload="javascript:history.go(1)" id="page-top" class="index">
        <h1 align="center">Ingreso</h1>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <form class="form-horizontal" action="<c:url value="/ValidarIngreso"/>" method="post">
                        <c:if test="${empty mapMensajeRut}">
                            <div class="form-group has-success col-lg-8">
                                <label class="control-label" for="inputSuccess1">Rut:</label>
                                <input type="text" class="form-control" id="inputSuccess1" 
                                       name="rut" aria-describedby="helpBlock2" placeholder="Ingrese su Rut" autofocus="true">
                                <span id="helpBlock2" class="help-block"></span>
                            </div>
                        </c:if>
                        <c:if test="${not empty mapMensajeRut}">
                            <div class="form-group has-error col-lg-8">
                                <label class="control-label" for="inputError1"><c:out value="${mapMensajeRut['errorRut']}"/></label>
                                <input type="text" class="form-control" 
                                       id="inputError1" name="rut" placeholder="Ingrese su rut" autofocus="true">
                            </div>
                        </c:if>
                        <c:if test="${empty mapMensajePass}">
                            <div class="form-group has-success col-lg-8">
                                <label class="control-label" for="inputSuccess2">Password:</label>
                                <input type="password" class="form-control" id="inputSuccess1" 
                                       name="password" aria-describedby="helpBlock2" placeholder="Ingrese su Password">
                                <span id="helpBlock2" class="help-block"></span>
                            </div>
                        </c:if>
                        <c:if test="${not empty mapMensajePass}">
                            <div class="form-group has-error col-lg-8">
                                <label class="control-label" for="inputError2"><c:out value="${mapMensajePass['errorPass']}"/></label>
                                <input type="password" class="form-control" 
                                       id="inputError1" name="password" placeholder="Ingrese su Password">
                            </div>
                        </c:if>
                        <div class="form-group">
                            <div class="col-sm-offset-0 col-sm-10">
                                <input type="submit" class="btn btn-default" value="Iniciar Sesión"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-4">
                    <img src="imagenes/logo_vet.png" alt="logo" class="img-rounded" class="img-responsive" width="150px">
                </div>
            </div>
        </div>
    </div>
</body>
</html>
