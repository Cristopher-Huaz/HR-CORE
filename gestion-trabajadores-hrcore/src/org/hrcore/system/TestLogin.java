package org.hrcore.system;

import org.hrcore.system.service.AuthenticationService;
import org.hrcore.system.service.AuthenticationStatus;

public class TestLogin {
    public static void main(String[] args) {
        AuthenticationService auth = new AuthenticationService();
        AuthenticationStatus status = auth.userLogin("clopez", "pass123");
        System.out.println("Resultado: " + status);
    }
}