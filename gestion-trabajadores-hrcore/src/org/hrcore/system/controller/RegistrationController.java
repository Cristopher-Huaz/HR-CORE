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
import javafx.scene.control.TextField;
import org.hrcore.system.utils.SceneManager;
import org.hrcore.system.utils.ViewFactory;

/**
 *
 * @author informatica
 */
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildActions();
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
