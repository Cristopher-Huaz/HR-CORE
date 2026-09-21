package org.hrcore.system.service;


import org.hrcore.system.controller.SessionController;
import org.hrcore.system.model.Person;
import org.hrcore.system.repository.UserRepository;
import org.hrcore.system.utils.ToolBCrypt;

/**
 * Servicio encargado de la logica de autenticacion.
 */
public class AuthenticationService {

    private UserRepository userRepo = new UserRepository();
    private String messageError;

    public AuthenticationService() {
    }

    /**
     * Autentica las credenciales del usuario.
     *
     * Flujo:
     *  1. Busca al usuario por username.
     *  2. Evalua el typeEncrypt y valida la contrasena.
     *  3. Si todo esta bien, guarda al usuario en la sesion.
     */
    public AuthenticationStatus userLogin(String username, String password) {
        Person searchUser;

        // ---- Paso 1: buscar al usuario por username ----
        searchUser = userRepo.searchByUsername(username);

        if (searchUser == null) {
            return AuthenticationStatus.ERROR_USER_NOT_FOUND;
        }

        // ---- Paso 2: validar segun el tipo de encriptacion ----
        Person userLogued;
        try {
            switch (searchUser.getTypeEncrypt()) {
                case 1:
                    // Texto plano: buscar de nuevo con username + password
                    userLogued = userRepo.login(username, password);
                    break;

                case 2:
                    // MD5: el SP authenticate_user ya aplica md5() en la comparacion
                    userLogued = userRepo.login(username, password);
                    break;

                case 3:
                    // BCrypt: validar en Java
                    ToolBCrypt tool = new ToolBCrypt();
                    String savedPassword = searchUser.getPassword();
                    boolean passwordValid = tool.validatePassword(password, savedPassword);

                    if (passwordValid) {
                        userLogued = searchUser;
                    } else {
                        userLogued = null;
                    }
                    break;

                default:
                    userLogued = null;
            }
        } catch (Exception e) {
            messageError = e.getMessage();
            return AuthenticationStatus.ERROR_LOGIN;
        }

        // ---- Paso 3: verificar resultado ----
        if (userLogued == null) {
            return AuthenticationStatus.ERROR_CREDENTIALS;
        }

        // ---- Paso 4: guardar sesion y retornar exito ----
        SessionController.setUserLogued(userLogued);
        return AuthenticationStatus.LOGIN_SUCCESS;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}