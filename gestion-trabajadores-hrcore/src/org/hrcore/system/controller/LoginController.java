package org.hrcore.system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hrcore.system.service.AuthenticationService;
import org.hrcore.system.service.AuthenticationStatus;
import org.hrcore.system.utils.AlertInformation;
import org.hrcore.system.utils.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;

    private AuthenticationService authService = new AuthenticationService();
    private ViewFactory viewFactory = new ViewFactory();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // vacío
    }

    @FXML
    private void onLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            AlertInformation.showAlert(
                    "warning",
                    "Ingresa tu usuario y contraseña",
                    "Campos vacíos"
            );
            return;
        }

        AuthenticationStatus status = authService.userLogin(username, password);

        switch (status) {
            case LOGIN_SUCCESS:
                viewFactory.viewDashboard();
                break;

            case ERROR_USER_NOT_FOUND:
                AlertInformation.showAlert("error",
                        "El usuario ingresado no existe",
                        "Usuario no encontrado");
                break;

            case ERROR_CREDENTIALS:
                AlertInformation.showAlert("error",
                        "El usuario o la contraseña no coinciden",
                        "Credenciales incorrectas");
                break;

            case ERROR_USER_SEARCH:
                AlertInformation.showAlert("error",
                        "No se pudo verificar el usuario en la base de datos",
                        "Error de búsqueda");
                break;

            case ERROR_LOGIN:
                AlertInformation.showAlert("error",
                        authService.getMessageError(),
                        "Error al iniciar sesión");
                break;

            default:
                AlertInformation.showAlert("error",
                        "No se pudo iniciar sesión",
                        "Error desconocido");
        }
    }

    @FXML
    private void onClose(ActionEvent event) {
        System.exit(0);
    }
}