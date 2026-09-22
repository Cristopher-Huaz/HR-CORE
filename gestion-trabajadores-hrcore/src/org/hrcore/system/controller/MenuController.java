/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.controller;

import org.hrcore.system.utils.SceneManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.hrcore.system.utils.ViewFactory;

/**
 *
 * @author informatica
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnGoManage;

    @FXML
    private Button btnGoRegister;

    @FXML
    private Button btnGoRegisterNewUser;

    @FXML
    private Button btnLogOut;

    private ViewFactory viewFactory = new ViewFactory();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildActions();
    }

    public void buildActions() {
        btnClose.setOnMouseClicked(e->{
            SceneManager.getInstanciaSceneManager().exitApplication();
        });
        btnLogOut.setOnMouseClicked(e->{
            viewFactory.viewLogin();
        });
        btnGoRegisterNewUser.setOnMouseClicked(e->{
            
        });
        
    }
    
}
