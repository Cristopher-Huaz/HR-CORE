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
import javafx.scene.control.Button;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    private AlertInformation alert = new AlertInformation();
    private AuthenticationService authService = new AuthenticationService();
    private ViewFactory viewFactory = new ViewFactory();

    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildAccions();
    }

    public void buildAccions() {

    }

    @FXML
    private void onLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            alert.showAlert("Campos vacíos", "Ingresa usuario y contraseña", "WARN");
            return;
        }

        AuthenticationStatus status = authService.userLogin(username, password);

        switch (status) {
            case LOGIN_SUCCESS:
                viewFactory.viewDashboard();   // 👈 navegación
                break;

            case ERROR_USER_NOT_FOUND:
                alert.showAlert("Usuario no encontrado", "El usuario no existe", "ERROR");
                break;

            case ERROR_CREDENTIALS:
                alert.showAlert("Credenciales incorrectas", "Verifica tus datos", "ERROR");
                break;

            case ERROR_USER_SEARCH:
            case ERROR_LOGIN:
                alert.showAlert("Error del sistema", authService.getMessageError(), "ERROR");
                break;

            default:
                alert.showAlert("Error", "No se pudo iniciar sesión", "ERROR");
        }
    }

    @FXML
    private void onRegisterUser(ActionEvent event) {
        viewFactory.viewEmployeeRegistration();
    }
}
