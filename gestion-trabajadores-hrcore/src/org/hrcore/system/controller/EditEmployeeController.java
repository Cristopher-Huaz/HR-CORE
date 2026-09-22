/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hrcore.system.model.Person;
import org.hrcore.system.utils.SceneManager;
import org.hrcore.system.utils.ViewFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hrcore.system.dao.EditEmployeeDAO;

/**
 *
 * @author informatica
 */
public class EditEmployeeController implements Initializable {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private PasswordField pwdEditUser;

    @FXML
    private TableView<Person> tblEmployee;

    @FXML
    private TextField txtEditBaseSalary;

    @FXML
    private TextField txtEditDepartment;

    @FXML
    private TextField txtEditPosition;

    @FXML
    private TextField txtLastNames;

    @FXML
    private TextField txtNames;

    @FXML
    private TableColumn<Person, String> clmTableName;

    @FXML
    private TableColumn<Person, String> clmTableLastName;

    @FXML
    private TableColumn<Person, String> clmTablePosition;

    @FXML
    private TableColumn<Person, String> clmTableDepartment;

    @FXML
    private TableColumn<Person, Double> clmTableBaseSalary;

    private ViewFactory viewFactory = new ViewFactory();

    private EditEmployeeDAO employeeDAO = new EditEmployeeDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadEmployees();
        configureTable();
        buildActions();
    }

    public void buildActions() {
        btnClose.setOnMouseClicked(e -> {
            System.exit(0);
        });
        btnReturn.setOnMouseClicked(e -> {
            viewFactory.viewDashboard();
        });
    }

    private void configureTable() {

        clmTableName.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );

        clmTableLastName.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );

        clmTablePosition.setCellValueFactory(
                new PropertyValueFactory<>("role")
        );

        clmTableDepartment.setCellValueFactory(
                new PropertyValueFactory<>("department")
        );

        clmTableBaseSalary.setCellValueFactory(
                new PropertyValueFactory<>("monthlySalary")
        );
    }

    private void loadEmployees() {

        tblEmployee.setItems(
                FXCollections.observableArrayList(
                        employeeDAO.getAllEmployees()
                )
        );
    }
}




