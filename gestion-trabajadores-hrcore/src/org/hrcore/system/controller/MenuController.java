package org.hrcore.system.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.hrcore.system.utils.AlertInformation;
import org.hrcore.system.utils.ViewFactory;
import javafx.application.Platform;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {
    @FXML private Button btnClose;
    @FXML private Button btnGoManage;
    @FXML private Button btnGoRegister;
    @FXML private Button btnGoRegisterNewUser;
    @FXML private Button btnGoPayment;
    @FXML private Button btnLogOut;

    private ViewFactory viewFactory = new ViewFactory();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(">>> MENU CONTROLLER INICIALIZADO");
        buildActions();
        applyRolePermissions();
    }


    public void buildActions() {

        btnGoManage.setOnAction((ActionEvent e) -> viewFactory.viewEditEmployee());

        btnGoRegister.setOnAction((ActionEvent e) -> viewFactory.viewEmployeeRegistration());

        btnGoRegisterNewUser.setOnAction((ActionEvent e) ->
                AlertInformation.showAlert("info", "Pendiente de implementar", "Registrar usuario")
        );

        btnGoPayment.setOnAction((ActionEvent e) -> viewFactory.viewPayment());

        btnLogOut.setOnAction((ActionEvent e) -> {
            SessionController.clear();
            viewFactory.viewLogin();
        });

        btnClose.setOnAction((ActionEvent e) -> {
            Platform.exit();
            System.exit(0);
        });

    }


    private void applyRolePermissions() {

        boolean esDirector      = SessionController.isDirector();
        boolean esGestorTalento = SessionController.isGestorTalento();
        boolean esAnalista      = SessionController.isAnalista();

        boolean verNuevoUsuario = esDirector;
        btnGoRegisterNewUser.setVisible(verNuevoUsuario);
        btnGoRegisterNewUser.setManaged(verNuevoUsuario);

        boolean verGestionar = esDirector || esGestorTalento;
        btnGoManage.setVisible(verGestionar);
        btnGoManage.setManaged(verGestionar);

        boolean verRegistrar = esDirector || esGestorTalento;
        btnGoRegister.setVisible(verRegistrar);
        btnGoRegister.setManaged(verRegistrar);

        boolean verBoletas = esDirector || esAnalista;
        btnGoPayment.setVisible(verBoletas);
        btnGoPayment.setManaged(verBoletas);

        btnLogOut.setVisible(true);
        btnLogOut.setManaged(true);
        btnClose.setVisible(true);
        btnClose.setManaged(true);
    }
}

