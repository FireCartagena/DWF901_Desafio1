package com.mycompany.desafio1dwf1.dao;

import Utils.SQLConnection;
import static Utils.SQLConnection.getConnection;
import com.mycompany.desafio1dwf1.model.Empleado;
import com.mycompany.desafio1dwf1.model.Departamento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public EmpleadoDAO() {
        // Constructor vacío
    }

    public List<Empleado> getAll() {
        Connection conn = SQLConnection.getConnection();
        List<Empleado> empleados = new ArrayList<>();
        // query para obtener informacion extra de un empleado
        //String query = "SELECT e.idEmpleado, e.numeroDui, e.nombrePersona, e.usuario, e.numeroTelefono, e.correoInstitucional, e.fechaNacimiento, d.nombreDepartamento, car.cargo, tp.tipoContratacion FROM empleados AS e INNER JOIN contrataciones AS con ON con.idEmpleado = e.idEmpleado INNER JOIN departamentos AS d ON d.idDepartamento = con.idDepartamento INNER JOIN cargos AS car ON car.idCargo =  con.idCargo INNER JOIN tipoContratacion AS tp ON tp.idTipoContratacion = con.idTipoContratacion";
        String query  = "SELECT * FROM empleados";
        try(PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Empleado empleado = new Empleado(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        LocalDate.parse(resultSet.getString(7))
                );
                empleados.add(empleado);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public void agregarEmpleado(Empleado empleado) throws SQLException {
        String query = "INSERT INTO empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = SQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, empleado.getNumeroDui());
            preparedStatement.setString(2, empleado.getNombrePersona());
            preparedStatement.setString(3, empleado.getUsuario());
            preparedStatement.setString(4, empleado.getNumeroTelefono());
            preparedStatement.setString(5, empleado.getCorreoInstitucional());
            preparedStatement.setDate(6, Date.valueOf(empleado.getFechaNacimiento()));
            preparedStatement.executeUpdate();
        }
    }

    public Empleado getById(int id) throws SQLException {
        Empleado empleado = null;
        // query para obtener m'as informacion del empleado
        //String query = "SELECT e.idEmpleado, e.numeroDui, e.nombrePersona, e.usuario, "
//                + "e.numeroTelefono, e.correoInstitucional, e.fechaNacimiento, d.nombreDepartamento, car.cargo, "
//                + "tp.tipoContratacion FROM empleados AS e INNER JOIN contrataciones AS con ON con.idEmpleado = e.idEmpleado "
//                + "INNER JOIN departamentos AS d ON d.idDepartamento = con.idDepartamento "
//                + "INNER JOIN cargos AS car ON car.idCargo =  con.idCargo INNER JOIN tipoContratacion AS tp "
//                + "ON tp.idTipoContratacion = con.idTipoContratacion WHERE e.idEmpleado = ?";
        String query  = "SELECT * FROM empleados WHERE idEmpleado = ?";

        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    empleado = new Empleado(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            LocalDate.parse(resultSet.getString(7))
                    );
                }
            }
        }

        return empleado;
    }

    public void actualizarEmpleado(Empleado empleado) throws SQLException {
        String query = "UPDATE empleados SET numeroDui = ?, nombrePersona = ?, usuario = ?,"
                + " numeroTelefono = ?, correoInstitucional = ?, fechaNacimiento = ? WHERE idEmpleado = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, empleado.getNumeroDui());
            preparedStatement.setString(2, empleado.getNombrePersona());
            preparedStatement.setString(3, empleado.getUsuario());
            preparedStatement.setString(4, empleado.getNumeroTelefono());
            preparedStatement.setString(5, empleado.getCorreoInstitucional());
            preparedStatement.setDate(6, Date.valueOf(empleado.getFechaNacimiento()));
            preparedStatement.setInt(7, empleado.getIdEmpleado());

            preparedStatement.executeUpdate();
        }
    }

    public void eliminarEmpleado(int id) throws SQLException {
        String query = "DELETE FROM empleados WHERE idEmpleado = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
