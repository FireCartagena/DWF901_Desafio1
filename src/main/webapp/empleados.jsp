<%-- 
    Document   : empleado
    Created on : 18 ago. 2024, 07:25:03
    Author     : ludwi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Empleados</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Lista de Empleados</h1>
        <a href="empleados?action=crear" class="btn btn-primary mb-3">Agregar Empleado</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>DUI</th>
                    <th>Nombre</th>
                    <th>Usuario</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>Fecha de nacimiento</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="empleado" items="${empleados}">
                    <tr>
                        <td><c:out value="${empleado.idEmpleado}"/></td>
                        <td><c:out value="${empleado.numeroDui}"/></td>
                        <td><c:out value="${empleado.nombrePersona}"/></td>
                        <td><c:out value="${empleado.usuario}"/></td>
                        <td><c:out value="${empleado.numeroTelefono}"/></td>
                        <td><c:out value="${empleado.correoInstitucional}"/></td>
                        <td><c:out value="${empleado.fechaNacimiento}"/></td>
                        <td>
                            <a href="empleados?action=editar&id=${empleado.idEmpleado}" class="btn btn-warning btn-sm">Editar</a>
                            <a href="empleados?action=eliminar&id=${empleado.idEmpleado}" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
