/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.desafio1dwf1.dao;

/**
 *
 * @author ludwi
 */
import Utils.SQLConnection;
import com.mycompany.desafio1dwf1.model.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {
    public DepartamentoDAO(){}
    public List<Departamento> getAll() {
        Connection conn = SQLConnection.getConnection();
        List<Departamento> list = new ArrayList<>(List.of());
        try {
            String sql = "SELECT * FROM departamentos";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Departamento departamento = new Departamento(
                        resultSet.getInt("idDepartamento"),
                        resultSet.getString("nombreDepartamento"),
                        resultSet.getString("descripcionDepartamento")
                );
                list.add(departamento);
            }
        } catch(Exception e) {
            
        }
        return list;
    }
    public Departamento getById(int id) {
        Connection conn = SQLConnection.getConnection();
        Departamento departamento = null;
        try {
            String sql = "SELECT * FROM departamentos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                departamento = new Departamento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        } catch(Exception e) {
            
        }
        return departamento;
    }
}
