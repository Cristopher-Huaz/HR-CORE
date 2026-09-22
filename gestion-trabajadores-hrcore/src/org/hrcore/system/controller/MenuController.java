package org.hrcore.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.hrcore.system.utils.AlertInformation;
import org.hrcore.system.utils.ViewFactory;

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
        buildActions();
        applyRolePermissions();
    }

    /**
     * Asigna las acciones a cada boton usando lambdas.
     */
    public void buildActions() {

        btnGoManage.setOnAction(e -> viewFactory.viewEditEmployee());

        btnGoRegister.setOnAction(e -> viewFactory.viewEmployeeRegistration());

        btnGoRegisterNewUser.setOnAction(e ->
                AlertInformation.showAlert("info", "Pendiente de implementar", "Registrar usuario")
        );

        btnGoPayment.setOnAction(e -> viewFactory.viewPayment());

        btnLogOut.setOnAction(e -> {
            SessionController.clear();
            viewFactory.viewLogin();
        });

        btnClose.setOnAction(e -> {
            SessionController.clear();
            viewFactory.viewLogin();
        });
    }

    /**
     * Segun el rol, muestra u oculta los botones del menu.
     *  - Director:           TODOS los botones
     *  - Gestor de Talento:  Gestionar + Registrar empleados
     *  - Analista:           Solo Boletas
     */
    private void applyRolePermissions() {

        boolean esDirector      = SessionController.isDirector();
        boolean esGestorTalento = SessionController.isGestorTalento();
        boolean esAnalista      = SessionController.isAnalista();

        // ---- btnGoRegisterNewUser: solo Director ----
        boolean verNuevoUsuario = esDirector;
        btnGoRegisterNewUser.setVisible(verNuevoUsuario);
        btnGoRegisterNewUser.setManaged(verNuevoUsuario);

        // ---- btnGoManage: Director + Gestor de Talento ----
        boolean verGestionar = esDirector || esGestorTalento;
        btnGoManage.setVisible(verGestionar);
        btnGoManage.setManaged(verGestionar);

        // ---- btnGoRegister: Director + Gestor de Talento ----
        boolean verRegistrar = esDirector || esGestorTalento;
        btnGoRegister.setVisible(verRegistrar);
        btnGoRegister.setManaged(verRegistrar);

        // ---- btnGoPayment: Director + Analista ----
        boolean verBoletas = esDirector || esAnalista;
        btnGoPayment.setVisible(verBoletas);
        btnGoPayment.setManaged(verBoletas);

        // ---- btnLogOut y btnClose: siempre visibles ----
        btnLogOut.setVisible(true);
        btnLogOut.setManaged(true);
        btnClose.setVisible(true);
        btnClose.setManaged(true);
    }
}