/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.repository;

import org.hrcore.system.config.ConnectionDB;
import org.hrcore.system.model.Person;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserRepository {
    private final ConnectionDB connectionDB = ConnectionDB.getInstanciaConexionDB();
    public void create(Person person) {
        String sql = "{call sp_create_person(?,?,?,?,?,?,?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setString(1, person.getFirstName());
            callSP.setString(2, person.getLastName());
            callSP.setDouble(3, person.getMonthlySalary());
            callSP.setString(4, person.getHireDate());
            callSP.setString(5, person.getPassword());
            callSP.setString(6, person.getDepartment());
            callSP.setString(7, person.getRole());
            callSP.execute();
        } catch (SQLException errorSQL) {
            System.out.println("Error al crear usuario");
            errorSQL.printStackTrace();
        }
    }
    public Person findById(int id) {
        Person person = null;
        String sql = "{call sp_find_person_by_id(?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setInt(1, id);
            try (ResultSet resultado = callSP.executeQuery()) {
                if (resultado.next()) {
                    person = mapResultSetToPerson(resultado);
                }
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al buscar usuario");
            errorSQL.printStackTrace();
        }
        return person;
    }
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        String sql = "{call sp_list_persons()}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql);
             ResultSet resultado = callSP.executeQuery()) {
            while (resultado.next()) {
                persons.add(mapResultSetToPerson(resultado));
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al listar usuarios");
            errorSQL.printStackTrace();
        }
        return persons;
    }
    private Person mapResultSetToPerson(ResultSet resultado) throws SQLException {
        Person person = new Person();
        person.setId(resultado.getInt("id"));
        person.setFirstName(resultado.getString("first_name"));
        person.setLastName(resultado.getString("last_name"));
        person.setMonthlySalary(resultado.getDouble("monthly_salary"));
        person.setHireDate(resultado.getString("hire_date"));
        person.setPassword(resultado.getString("password"));
        person.setDepartment(resultado.getString("department"));
        person.setRole(resultado.getString("role"));
        return person;
    }
}