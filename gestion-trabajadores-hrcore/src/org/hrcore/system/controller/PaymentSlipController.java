/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import org.hrcore.system.dao.PaymentSlipDAO;
import org.hrcore.system.model.Person;
import org.hrcore.system.utils.SceneManager;
import org.hrcore.system.utils.ViewFactory;

public class PaymentSlipController implements Initializable {

    @FXML
    private Button btnGenerateSlip;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReturn;

    @FXML
    private TableView<Person> tblEmployeeEdit;

    @FXML
    private TextField txtModifications;

    @FXML
    private TextField txtObservations;

    @FXML
    private TableColumn<Person, Double> clmPaymentBaseSalary;

    @FXML
    private TableColumn<Person, String> clmPaymentDepartment;

    @FXML
    private TableColumn<Person, String> clmPaymentLastName;

    @FXML
    private TableColumn<Person, String> clmPaymentName;

    @FXML
    private TableColumn<Person, String> clmPaymentPosition;

    private ViewFactory viewFactory = new ViewFactory();

    private PaymentSlipDAO paymentSlipDAO = new PaymentSlipDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureTable();
        loadEmployees();
        buildActions();
    }

    private void configureTable() {

        clmPaymentName.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );

        clmPaymentLastName.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );

        clmPaymentPosition.setCellValueFactory(
                new PropertyValueFactory<>("role")
        );

        clmPaymentDepartment.setCellValueFactory(
                new PropertyValueFactory<>("department")
        );

        clmPaymentBaseSalary.setCellValueFactory(
                new PropertyValueFactory<>("monthlySalary")
        );
    }

    private void loadEmployees() {

        tblEmployeeEdit.setItems(
                FXCollections.observableArrayList(
                        paymentSlipDAO.getAllEmployees()
                )
        );
    }

    public void buildActions() {

        btnClose.setOnMouseClicked(e -> {
            SceneManager.getInstanciaSceneManager().exitApplication();
        });

        btnReturn.setOnMouseClicked(e -> {
            viewFactory.viewMenu();
        });
    }
}
