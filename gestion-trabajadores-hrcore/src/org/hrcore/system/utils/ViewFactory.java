package org.hrcore.system.utils;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import org.hrcore.system.Main;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;

public class ViewFactory {

    private final String PATH_VIEW = "/org/hrcore/system/view/";

    public Scene loadFileFXML(String nameFXML, int width, int height) {
        String pathOfFile = PATH_VIEW + nameFXML;
        try {
            FXMLLoader loaderFXML = new FXMLLoader();
            URL urlFile = Main.class.getResource(pathOfFile);
            loaderFXML.setBuilderFactory(new JavaFXBuilderFactory());
            loaderFXML.setLocation(urlFile);
            return new Scene(loaderFXML.load(), width, height);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void loadScene(String nameFXml) {
        Scene scene = null;
        switch (nameFXml) {
            case "Login"                -> scene = loadFileFXML("LoginView.fxml", 750, 500);
            case "Dashboard"            -> scene = loadFileFXML("MenuView.fxml", 900, 600);
            case "EditEmployee"         -> scene = loadFileFXML("EditEmployeeView.fxml", 750, 500);
            case "EmployeeRegistration" -> scene = loadFileFXML("EmployeeRegistratonView.fxml", 750, 500);
            case "Payment"              -> scene = loadFileFXML("PaymentSlipView.fxml", 750, 500);
            default                     -> scene = loadFileFXML("LoginView.fxml", 750, 500);
        }
        SceneManager.getInstanciaSceneManager().changeScene(scene);
    }

    // -------------------- Métodos de conveniencia --------------------

    public void viewLogin() {
        loadScene("Login");
    }

    public void viewDashboard() {
        loadScene("Dashboard");
    }

    public void viewEditEmployee() {
        loadScene("EditEmployee");
    }

    public void viewEmployeeRegistration() {
        loadScene("EmployeeRegistration");
    }

    public void viewPayment() {
        loadScene("Payment");
    }
}