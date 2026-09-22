/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hrcore.system.config.ConnectionDB;
import org.hrcore.system.model.Person;

public class EditEmployeeDAO {

    private Connection connection;

    public EditEmployeeDAO() {
        connection = ConnectionDB
                .getInstanciaConexionDB()
                .getConnection();
    }

    public List<Person> getAllEmployees() {

        List<Person> employees = new ArrayList<>();

        String sql = "{CALL query_workers()}";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Person person = new Person();

                person.setId(result.getInt("id"));
                person.setFirstName(
                        result.getString("first_names")
                );
                person.setLastName(
                        result.getString("last_names")
                );
                person.setUsername(
                        result.getString("username")
                );
                person.setMonthlySalary(
                        result.getDouble("monthly_salary")
                );
                person.setHireDate(
                        result.getString("hire_date")
                );
                person.setTypeEncrypt(
                        result.getInt("type_encrypt")
                );
                person.setDepartment(
                        result.getString("department")
                );
                person.setRole(
                        result.getString("role")
                );

                employees.add(person);
            }

        } catch (SQLException e) {
            System.out.println(
                    ">>> ERROR AL OBTENER EMPLEADOS: "
                    + e.getMessage()
            );
            e.printStackTrace();
        }

        return employees;
    }
    
    public boolean updateEmployee(
        int id,
        String firstName,
        String lastName,
        String username,
        double salary,
        String hireDate,
        String password,
        int typeEncrypt,
        String department,
        String role) {

    String sql = "{CALL update_worker(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

    try (PreparedStatement statement =
                 connection.prepareCall(sql)) {

        int departmentId = getDepartmentId(department);
        int roleId = getRoleId(role);

        if (departmentId == -1 || roleId == -1) {
            System.out.println(
                    ">>> ERROR: Departamento o puesto no encontrado."
            );
            return false;
        }

        statement.setInt(1, id);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, username);
        statement.setDouble(5, salary);
        statement.setString(6, hireDate);
        statement.setString(7, password);
        statement.setInt(8, departmentId);
        statement.setInt(9, roleId);
        statement.setInt(10, typeEncrypt);

        statement.executeUpdate();

        return true;

    } catch (SQLException e) {
        System.out.println(
                ">>> ERROR AL ACTUALIZAR EMPLEADO: "
                + e.getMessage()
        );
        e.printStackTrace();
        return false;
    }
}
    private int getDepartmentId(String department) {

    String sql = "SELECT id FROM Department WHERE name = ?";

    try (PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setString(1, department);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt("id");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return -1;
}
    private int getRoleId(String role) {

    String sql = "SELECT id FROM `role` WHERE role_type = ?";

    try (PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setString(1, role);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt("id");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return -1;
}
}
