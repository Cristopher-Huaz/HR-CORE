/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hrcore.system.config.ConnectionDB;

public class EmployeeRegistrationDAO {

    private Connection connection;

    public EmployeeRegistrationDAO() {
        connection = ConnectionDB
                .getInstanciaConexionDB()
                .getConnection();
    }

    public boolean registerEmployee(
            String firstName,
            String lastName,
            String username,
            double salary,
            String hireDate,
            String password,
            String department,
            String role) {

        String sql = "{CALL insert_worker(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (PreparedStatement statement = connection.prepareCall(sql)) {

            int departmentId = getDepartmentId(department);
            int roleId = getRoleId(role);

            if (departmentId == -1) {
                System.out.println(">>> ERROR: Departamento no encontrado.");
                return false;
            }

            if (roleId == -1) {
                System.out.println( ">>> ERROR: Puesto no encontrado.");
                return false;
            }

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setDouble(4, salary);
            statement.setString(5, hireDate);
            statement.setString(6, password);
            statement.setInt(7, departmentId);
            statement.setInt(8, roleId);

            // 1 = texto plano
            // 2 = MD5
            statement.setInt(9, 2);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println( ">>> ERROR AL REGISTRAR EMPLEADO: " + e.getMessage());

            e.printStackTrace();

            return false;
        }
    }

    private int getDepartmentId(String department) {

        String sql =
                "SELECT id FROM Department WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, department);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt("id");
            }

        } catch (SQLException e) {

            System.out.println( ">>> ERROR AL BUSCAR DEPARTAMENTO: " + e.getMessage());
        }
        return -1;
    }

    private int getRoleId(String role) {

        String sql =  "SELECT id FROM `role` WHERE role_type = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, role);
            ResultSet result = statement.executeQuery();

            if (result.next()) 
                return result.getInt("id");
            

        } catch (SQLException e) {

            System.out.println( ">>> ERROR AL BUSCAR PUESTO: " + e.getMessage());
        }

        return -1;
    }

    public boolean existsUsername(String username) {

        String sql = "SELECT id FROM users WHERE username = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            return result.next();

        } catch (SQLException e) {

            System.out.println(">>> ERROR AL VERIFICAR USUARIO: " + e.getMessage());

            return false;
        }
    }
}
