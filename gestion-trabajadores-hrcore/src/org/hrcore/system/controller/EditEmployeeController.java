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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author informatica
 */
public class EditEmployeeController implements Initializable{
    
        @FXML
    private Button btnClose;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private PasswordField pwdEditUser;

    @FXML
    private TableView<?> tblEmployee;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        buildActions();
    }
    
    public void buildActions(){
        
    }
}
