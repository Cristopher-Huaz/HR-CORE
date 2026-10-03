/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.repository;

import org.hrcore.system.config.ConnectionDB;
import org.hrcore.system.model.Person;
import org.hrcore.system.utils.ToolBCrypt;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio encargado del acceso a datos de las personas (workers).
 *
 * @author informatica
 */
public class UserRepository {

    private final ConnectionDB connectionDB = ConnectionDB.getInstanciaConexionDB();

    /**
     * Inserta un nuevo trabajador.
     * Si typeEncrypt == 3, hashea la contrasena con BCrypt antes de enviarla.
     */
    public void create(Person person) {
        String sql = "{call insert_worker(?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            String passwordFinal = person.getPassword();

            if (person.getTypeEncrypt() == 3) {
                ToolBCrypt tool = new ToolBCrypt();
                passwordFinal = tool.encryptToString(person.getPassword());
            }

            callSP.setString(1, person.getFirstName());
            callSP.setString(2, person.getLastName());
            callSP.setString(3, person.getUsername());
            callSP.setDouble(4, person.getMonthlySalary());
            callSP.setString(5, person.getHireDate());
            callSP.setString(6, passwordFinal);
            callSP.setInt(7, Integer.parseInt(person.getDepartment()));
            callSP.setInt(8, Integer.parseInt(person.getRole()));
            callSP.setInt(9, person.getTypeEncrypt());
            callSP.execute();
        } catch (SQLException errorSQL) {
            System.out.println("Error al crear trabajador");
            errorSQL.printStackTrace();
        }
    }

    /**
     * Actualiza un trabajador existente.
     * Si typeEncrypt == 3 y la contrasena no viene vacia, la hashea con BCrypt.
     */
    public void update(Person person) {
        String sql = "{call update_worker(?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            String passwordFinal = person.getPassword();

            if (person.getTypeEncrypt() == 3
                    && passwordFinal != null && !passwordFinal.isEmpty()) {
                ToolBCrypt tool = new ToolBCrypt();
                passwordFinal = tool.encryptToString(person.getPassword());
            }

            callSP.setInt(1, person.getId());
            callSP.setString(2, person.getFirstName());
            callSP.setString(3, person.getLastName());
            callSP.setString(4, person.getUsername());
            callSP.setDouble(5, person.getMonthlySalary());
            callSP.setString(6, person.getHireDate());
            callSP.setString(7, passwordFinal);
            callSP.setInt(8, Integer.parseInt(person.getDepartment()));
            callSP.setInt(9, Integer.parseInt(person.getRole()));
            callSP.setInt(10, person.getTypeEncrypt());
            callSP.execute();
        } catch (SQLException errorSQL) {
            System.out.println("Error al actualizar trabajador");
            errorSQL.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "{call delete_worker(?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setInt(1, id);
            callSP.execute();
        } catch (SQLException errorSQL) {
            System.out.println("Error al eliminar trabajador");
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
            System.out.println("Error al buscar trabajador");
            errorSQL.printStackTrace();
        }
        return person;
    }

    public List<Person> read() {
        List<Person> persons = new ArrayList<>();
        String sql = "{call query_workers()}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql);
             ResultSet resultado = callSP.executeQuery()) {
            while (resultado.next()) {
                persons.add(mapResultSetToPerson(resultado));
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al listar trabajadores");
            errorSQL.printStackTrace();
        }
        return persons;
    }

    /**
     * Autentica un trabajador por username + password.
     * Para type 3 (bcrypt), la validacion se hace en Java.
     */
    public Person login(String username, String password) {
        Person person = null;
        String sql = "{call authenticate_user(?,?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setString(1, username);
            callSP.setString(2, password);
            try (ResultSet resultado = callSP.executeQuery()) {
                if (resultado.next()) {
                    int typeEncrypt = resultado.getInt("type_encrypt");

                    if (typeEncrypt == 3) {
                        String hashGuardado = resultado.getString("password");
                        ToolBCrypt tool = new ToolBCrypt();
                        if (!tool.validatePassword(password, hashGuardado)) {
                            return null;
                        }
                    }

                    person = new Person();
                    person.setId(resultado.getInt("id"));
                    person.setFirstName(resultado.getString("first_names"));
                    person.setLastName(resultado.getString("last_names"));
                    person.setUsername(resultado.getString("username"));
                    person.setTypeEncrypt(typeEncrypt);
                    person.setDepartment(resultado.getString("department"));
                    person.setRole(resultado.getString("role"));
                }
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al autenticar trabajador");
            errorSQL.printStackTrace();
        }
        return person;
    }

    public Person searchByUsername(String username) {
        Person person = null;
        String sql = "{call sp_search_worker_by_username(?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setString(1, username);
            try (ResultSet resultado = callSP.executeQuery()) {
                if (resultado.next()) {
                    person = mapResultSetToPerson(resultado);
                }
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al buscar trabajador por username");
            errorSQL.printStackTrace();
        }
        return person;
    }

    public boolean existsByUsername(String username) {
        String sql = "{call sp_exists_worker_by_username(?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setString(1, username);
            try (ResultSet resultado = callSP.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt(1) > 0;
                }
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al verificar trabajador por username");
            errorSQL.printStackTrace();
        }
        return false;
    }

    public boolean existsByUsernameAndPassword(String username, String password) {
        String sql = "{call sp_exists_worker_by_username_and_password(?,?)}";
        try (CallableStatement callSP = connectionDB.getConnection().prepareCall(sql)) {
            callSP.setString(1, username);
            callSP.setString(2, password);
            try (ResultSet resultado = callSP.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt(1) > 0;
                }
            }
        } catch (SQLException errorSQL) {
            System.out.println("Error al verificar trabajador por credenciales");
            errorSQL.printStackTrace();
        }
        return false;
    }

    private Person mapResultSetToPerson(ResultSet resultado) throws SQLException {
        Person person = new Person();
        person.setId(resultado.getInt("id"));
        person.setFirstName(resultado.getString("first_names"));
        person.setLastName(resultado.getString("last_names"));
        person.setUsername(resultado.getString("username"));
        person.setMonthlySalary(resultado.getDouble("monthly_salary"));
        person.setHireDate(resultado.getString("hire_date"));
        person.setTypeEncrypt(resultado.getInt("type_encrypt"));
        person.setDepartment(resultado.getString("department"));
        person.setRole(resultado.getString("role"));
        return person;
    }
}