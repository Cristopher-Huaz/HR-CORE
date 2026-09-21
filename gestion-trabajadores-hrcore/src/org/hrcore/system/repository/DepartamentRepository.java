/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.repository;

import org.hrcore.system.config.ConnectionDB;
import org.hrcore.system.model.Department;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DepartamentRepository {
    private final ConnectionDB connectionDB = ConnectionDB.getInstanciaConexionDB();
    public void create(Department department) {
        String sql = "{call sp_create_department(?,?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setString(1, department.getName());
            callSP.setString(2, department.getDescription());
            callSP.execute();
        } catch (SQLException errorSQL) {
            System.out.println("Error al crear departamento");
            errorSQL.printStackTrace();
        }
    }
    public Department findById(int id) {
        Department department = null;
        String sql = "{call sp_find_department_by_id(?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setInt(1, id);
            try (ResultSet resultado = callSP.executeQuery()) {
                if (resultado.next()) {
                    department = mapResultSetToDepartment(resultado);
                }
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al buscar departamento");
            errorSQL.printStackTrace();
        }
        return department;
    }
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        String sql = "{call sp_list_departments()}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql);
             ResultSet resultado = callSP.executeQuery()) {
            while (resultado.next()) {
                departments.add(mapResultSetToDepartment(resultado));
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al listar departamentos");
            errorSQL.printStackTrace();
        }
        return departments;
    }
    private Department mapResultSetToDepartment(ResultSet resultado) throws SQLException {
        Department department = new Department();
        department.setId(resultado.getInt("id"));
        department.setName(resultado.getString("name"));
        department.setDescription(resultado.getString("description"));
        return department;
    }
}
 