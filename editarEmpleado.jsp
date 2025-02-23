<%-- 
    Document   : editarEmpleado
    Created on : 18 ago. 2024, 07:29:44
    Author     : ludwi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Empleado</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Editar Empleado</h1>
        <form action="empleados?action=guardar" method="post">
            <input type="hidden" name="id" value="${empleado.idEmpleado}">

            <div class="form-group">
                <label for="nombre">Numero de DUI:</label>
                <input type="text" class="form-control" id="numeroDui" name="dui" value="${empleado.numeroDui}" required>
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${empleado.nombrePersona}" required>
            </div>
            <div class="form-group">
                <label for="apellido">Usuario:</label>
                <input type="text" class="form-control" id="usuario" name="usuario" value="${empleado.usuario}" required>
            </div>
            <div class="form-group">
                <label for="telefono">Teléfono:</label>
                <input type="text" class="form-control" id="telefono" name="telefono" value="${empleado.numeroTelefono}" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${empleado.correoInstitucional}" required>
            </div>
            <div class="form-group">
                <label for="fechaNacimiento">Fecha de nacimiento:</label>
                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="${empleado.fechaNacimiento}"  required>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
        </form>
    </div>
</body>
</html>
