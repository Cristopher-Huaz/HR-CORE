/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author informatica
 */
public class LoginController implements Initializable{

    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField pwdUser;

    @FXML
    private TextField txtUser;

//    private AlertInformation  alert = new AlertInformation();
//    private ChangeView view = new ChangeView();
//    private AuthenticationService authenticationService = new AlertInformation();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        buildActions();
    }
    
    public void  buildActions(){
        btnLogin.setOnMouseClicked(
        e ->{
            checkLogin();
        });
        btnClose.setOnMouseClicked(
        e ->{
           System.exit(0);
        });
    }
    private void checkLogin() {
        String username = txtUser.getText().trim();
        String password = pwdUser.getText().trim();

//        if (username.isEmpty() || password.isEmpty()) {
//            alert.mostrarAlertaWithImage("warning", "No deje campos vacios", "Error de Campo", "gumi.gif");
//            return;
    } 
}
