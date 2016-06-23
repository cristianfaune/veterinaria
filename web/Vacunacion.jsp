<%-- 
    Document   : Vacunacion
    Created on : 23-nov-2015, 20:47:43
    Author     : CristianFaune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vacunación</title>
    </head>
    <c:if test="${empty doctor}">
        <c:redirect url="/ValidarIngreso"/>
    </c:if>
    <body>
        <%@include file="Opciones.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-8 col-sm-offset-2">
                    <ul class="nav nav-pills nav-justified">
                        <li role="presentation"><a href="<c:url value="/SalaAtencionServlet"/>">Ficha Médica</a></li>
                        <li role="presentation" class="active"><a href="<c:url value="/VacunacionServlet"/>">Vacunación</a></li>
                        <li role="presentation"><a href="<c:url value="/ProcedimientoServlet"/>">Procedimiento</a></li>
                    </ul>
                </div>
            </div>
            <br/>
            <br/>
            <form action="<c:url value="/VacunacionServlet"/>" method="post">
                <div class="form-group">
                    <div class="container col-sm-4 col-sm-offset-2">
                        <label>Tipo de Vacuna: </label>
                        <select class="form-control" name="descripcion">
                            <option value="0">--Seleccione vacuna--</option>
                            <c:forEach var="l" items="${lstVacunas}">
                                <option value="${l.descripcion}">
                                    <c:out value="${l.descripcion}"/>
                                </option>
                            </c:forEach>
                        </select>
                        <p class="text-danger"><c:out value="${mapMensaje['errorDescripcion']}"/></p>
                    </div>
                    <div class="container col-sm-2 col-sm-offset-0">
                        <label>Código: </label>
                        <input type="text" name="codigo" class="form-control text-uppercase" placeholder="Nº serie único"/>
                        <p class="text-danger"><c:out value="${mapMensaje['errorCodigo']}"/></p>
                    </div>
                    <br/>
                </div>
        </div>
        <br/>
        <br/>
        <input type="submit" value="Registrar" class="btn btn-default col-sm-offset-7"/>
        <p class="text-success text-uppercase col-sm-offset-3"><c:out value="${mapMensaje['exito']}"/></p>
    </form>
</body>
</html>
