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

    public Scene loadFileFXML(String nameFXML, int width, int height){
        String pathOfFile = PATH_VIEW + nameFXML;
        try {
            //FXML LOADER
            FXMLLoader loaderFXML = new FXMLLoader();
            //leer url y llamar al main
            URL urlFile = Main.class.getResource(pathOfFile);
            //Que va a construir el loader
            loaderFXML.setBuilderFactory(new JavaFXBuilderFactory());
            //especificar donde esta el archivogit
            loaderFXML.setLocation(urlFile);

            return new Scene(loaderFXML.load(), width, height);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void loadScene(String nameFXml){
        Scene scene = null;
        try{
            switch (nameFXml){
                case "EditEmployee" -> scene = loadFileFXML("EditEmployeeView.fxml", 750,500);
                case "RegistrationEmployee" -> scene = loadFileFXML("EmployeeRegistratonView.fxml", 750,500);
                case "Login" -> scene = loadFileFXML("LoginView.fxml", 750, 500);

                case "EditEmployee" -> scene = loadFileFXML("EditEmployeeView.fxml", 750,500);
                case "EmployeeRegistration" -> scene = loadFileFXML("EmployeeRegistratonView.fxml", 750,500);
                case "PaymentSlipView" -> scene = loadFileFXML("PaymentSlipView.fxml", 750,500);
                default -> scene = loadFileFXML("LoginView.fxml", 750,500);

            }
            SceneManager.getInstanciaSceneManager().changeScene(scene);
        }
        catch (NullPointerException e){

            //aqui va un alert equipo
            System.out.println("Load scene");
        }
    }
    public void viewEditEmployee(){
        loadScene("EditEmployee");
    }
    public void viewEmployeeRegistration(){
        loadScene("RegistrationEmployee");
    }
    public void viewLogin(){
        loadScene("Login");
    }
    public void viewPayment(){
        loadScene("Payment");

    }
}