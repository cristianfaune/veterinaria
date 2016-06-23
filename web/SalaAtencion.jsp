<%-- 
    Document   : SalaAtencion
    Created on : 23-nov-2015, 20:24:25
    Author     : CristianFaune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sala Atención</title>
    </head>
    <c:if test="${empty doctor}">
        <c:redirect url="/ValidarIngreso"/>
    </c:if>
    <body>
        <c:set var="now" value="<%=new java.util.Date()%>" />
        <%@include file="Opciones.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-8 col-sm-offset-2">
                    <ul class="nav nav-pills nav-justified">
                        <li role="presentation" class="active"><a href="<c:url value="/SalaAtencionServlet"/>">Ficha Médica</a></li>
                        <li role="presentation"><a href="<c:url value="/VacunacionServlet"/>">Vacunación</a></li>
                        <li role="presentation"><a href="<c:url value="/ProcedimientoServlet"/>">Procedimiento</a></li>
                    </ul>
                </div>
            </div>
            <div class="container col-md-8 col-sm-offset-2">
                <h5 class="text-right"><fmt:formatDate value="${now}" type="date"></fmt:formatDate></h5>
                    <table class="table">
                        <tbody>
                            <tr>
                                <td>Paciente: <h1><c:out value="${datos.mascota.nombre}"/></h1></td>
                            <td>Especie: <h3><em><c:out value="${datos.especie.descripcion}"/></h3></em></td>
                            <td>Fecha Nacimiento: <h4><em><fmt:formatDate value="${datos.mascota.fechaNacimiento}" pattern="dd/MM/yyyy" /></em></h4></td>
                            <td>
                                <form action="<c:url value="/SalaAtencionServlet"/>" method="post">
                                    <input class="btn btn-default col-sm-offset-7" type="submit" value="Descargar PDF"/>
                                </form>

                            </td>
                        </tr>
                        <tr>
                            <td>Sexo: <h4><em><c:out value="${datos.sexo.descripcion}"/></em></h4></td>
                            <td>Raza: <h4><em><c:out value="${datos.mascota.raza}"/></em></h4></td>
                            <td>Esterilizado: <h4><em><c:out value="${datos.mascota.esterilizado}"/></em></h4></td>
                        </tr>
                    </tbody>
                </table>
                <br/>
                <h3>Historial Vacunas:</h3>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Descripción</th>
                                <th>Fecha</th>
                                <th>Rut Doctor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="v" items="${vacunas}">
                                <tr>
                                    <td><strong><c:out value="${v.codigo}"/></strong></td>
                                    <td><c:out value="${v.descripcion}"/></td>
                                    <td><fmt:formatDate value="${v.fecha}" pattern="dd/MM/yyyy" /></td>
                                    <td><c:out value="${v.doctorRut}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <br/>
                <h3>Historial Procedimientos:</h3>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Descripción</th>
                                <th>Observaciones</th>
                                <th>Fecha</th>
                                <th>Rut Doctor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${procedimientos}">
                                <tr>
                                    <td><c:out value="${p.idProcedimiento}"/></td>
                                    <td><c:out value="${p.descripcion}"/></td>
                                    <td><c:out value="${p.observaciones}"/></td>
                                    <td><fmt:formatDate value="${p.fecha}" pattern="dd/MM/yyyy"/></td>
                                    <td><c:out value="${p.doctorRut}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
