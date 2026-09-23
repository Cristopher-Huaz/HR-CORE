/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hrcore.system.dao.EmployeeRegistrationDAO;
import org.hrcore.system.utils.SceneManager;
import org.hrcore.system.utils.ViewFactory;

public class RegistrationController implements Initializable {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnEmployeeRegistration;

    @FXML
    private Button btnReturn;

    @FXML
    private PasswordField pwdUser;

    @FXML
    private TextField txtBaseSalary;

    @FXML
    private TextField txtDepartment;

    @FXML
    private TextField txtLastNames;

    @FXML
    private TextField txtNames;

    @FXML
    private TextField txtPosition;

    private ViewFactory viewFactory = new ViewFactory();

    private EmployeeRegistrationDAO employeeRegistrationDAO
            = new EmployeeRegistrationDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        buildActions();
    }

    public void buildActions() {

        btnClose.setOnMouseClicked(e -> {

            SceneManager
                    .getInstanciaSceneManager()
                    .exitApplication();
        });

        btnReturn.setOnMouseClicked(e -> {

            viewFactory.viewDashboard();
        });

        btnEmployeeRegistration.setOnMouseClicked(e -> {

            registerEmployee();
        });
    }

    private void registerEmployee() {

        String firstName
                = txtNames.getText().trim();

        String lastName
                = txtLastNames.getText().trim();

        String department
                = txtDepartment.getText().trim();

        String role
                = txtPosition.getText().trim();

        String password
                = pwdUser.getText().trim();

        String salaryText
                = txtBaseSalary.getText().trim();

        // Validar campos vacíos
        if (firstName.isEmpty()
                || lastName.isEmpty()
                || department.isEmpty()
                || role.isEmpty()
                || password.isEmpty()
                || salaryText.isEmpty()) {

            System.out.println(">>> ERROR: Todos los campos son obligatorios."
            );

            return;
        }

        double salary;

        try {

            salary = Double.parseDouble(salaryText);

        } catch (NumberFormatException e) {

            System.out.println(">>> ERROR: El salario debe ser numérico.");

            return;
        }

        /*
         * Generar username automáticamente.
         *
         * Ejemplo:
         * Juan Pérez
         * ->
         * juan.perez
         */
        String username
                = generateUsername(firstName, lastName);

        // Fecha actual
        String hireDate
                = LocalDate.now().toString();

        // Verificar si el username ya existe
        if (employeeRegistrationDAO.existsUsername(username)) {

            System.out.println(">>> ERROR: El usuario " + username + " ya existe.");
            return;
        }

        boolean registered
                = employeeRegistrationDAO.registerEmployee(
                firstName,
                lastName,
                username,
                salary,
                hireDate,
                password,
                department,
                role
        );

        if (registered) {

            System.out.println(">>> EMPLEADO REGISTRADO CORRECTAMENTE");
            System.out.println(">>> Usuario generado: " + username);
            clearFields();
        }
    }

    private String generateUsername(
            String firstName,
            String lastName) {

        String username = firstName.toLowerCase().trim() + "." + lastName.toLowerCase().trim();

        return username
                .replace(" ", "")
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ñ", "n");
    }

    private void clearFields() {
        txtNames.clear();
        txtLastNames.clear();
        txtDepartment.clear();
        txtPosition.clear();
        txtBaseSalary.clear();
        pwdUser.clear();
    }
}

