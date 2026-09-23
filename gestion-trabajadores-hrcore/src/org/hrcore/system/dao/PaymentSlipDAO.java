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

/**
 *
 * @author informatica
 */

public class PaymentSlipDAO {

    private Connection connection;

    public PaymentSlipDAO() {

        connection = ConnectionDB
                .getInstanciaConexionDB()
                .getConnection();
    }

    public List<Person> getAllEmployees() {

        List<Person> employees = new ArrayList<>();

        String sql = "{CALL query_workers()}";

        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                Person person = new Person();

                person.setId(
                        result.getInt("id")
                );

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
}
