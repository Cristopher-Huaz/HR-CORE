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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author informatica
 */
public class PaymentSlipController implements Initializable {

    @FXML
    private Button btnGenerateSlip;

    @FXML
    private Button btnReturn;

    @FXML
    private TableView<?> tblEmployeeEdit;

    @FXML
    private TextField txtModifications;

    @FXML
    private TextField txtObservations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildActions();
    }

    public void buildActions() {

    }
}
