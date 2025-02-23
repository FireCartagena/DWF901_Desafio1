/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.desafio1dwf1.controllers;

import com.mycompany.desafio1dwf1.dao.DepartamentoDAO;
import com.mycompany.desafio1dwf1.dao.EmpleadoDAO;
import com.mycompany.desafio1dwf1.model.Departamento;
import com.mycompany.desafio1dwf1.model.Empleado;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author pyload
 */
@WebServlet(name = "EmpleadoController", urlPatterns = {"/empleados"})
public class EmpleadoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private EmpleadoDAO empleadoDAO;
    private DepartamentoDAO departamentoDAO;

    @Override
    public void init() {
        empleadoDAO = new EmpleadoDAO();
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            action = "listar"; // Asignar un valor por defecto si action es nulo
        }

        try {
            switch (action) {
                case "listar":
                    listarEmpleados(request, response);
                    break;
                case "crear":
                    mostrarFormularioCrear(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "guardar":
                    guardarEmpleado(request, response);
                    break;
                case "eliminar":
                    eliminarEmpleado(request, response);
                    break;
                default:
                    listarEmpleados(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Error al procesar la solicitud", e);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Empleado> empleados = empleadoDAO.getAll();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void mostrarFormularioCrear(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Departamento> departamentos = departamentoDAO.getAll();
        request.setAttribute("departamentos", departamentos);
        request.getRequestDispatcher("crearEmpleado.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado empleado = empleadoDAO.getById(id);
        System.out.println(empleado);
        request.setAttribute("empleado", empleado);
        request.getRequestDispatcher("editarEmpleado.jsp").forward(request, response);
    }

    private void guardarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String dui = request.getParameter("numeroDui");
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        Empleado empleado = new Empleado(
                id,
                dui,
                nombre,
                usuario,
                telefono,
                email,
                LocalDate.parse(fechaNacimiento)
        );

        if (id > 0) {
            empleadoDAO.actualizarEmpleado(empleado);
        } else {
            empleadoDAO.agregarEmpleado(empleado);
        }
        response.sendRedirect("empleados?action=listar");
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        empleadoDAO.eliminarEmpleado(id);
        response.sendRedirect("empleados?action=listar");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para la gestión de empleados";
    }

}
