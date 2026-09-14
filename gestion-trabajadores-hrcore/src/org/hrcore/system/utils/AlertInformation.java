/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.utils;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlertInformation {
    
    public AlertInformation(){
        
    }
    
    public void showAlert(String typeAlert, String message, String title) {
        Alert.AlertType type = switch(typeAlert.toLowerCase()) {
            case "error" -> Alert.AlertType.ERROR;
            case "confirm" -> Alert.AlertType.CONFIRMATION;
            case "info" -> Alert.AlertType.INFORMATION;
            case "warning" -> Alert.AlertType.WARNING;
            default -> Alert.AlertType.NONE;
        };
        
       Alert objectAlert = new Alert(type);
        objectAlert.setTitle(title);
        objectAlert.setHeaderText(title);
        objectAlert.setContentText(message);
        objectAlert.showAndWait();
    }
    
    public void showAlertWithImage(String typeAlert, String message, String title, String imageName) {
        Alert.AlertType type = switch(typeAlert.toLowerCase()) {
            case "error" -> Alert.AlertType.ERROR;
            case "confirm" -> Alert.AlertType.CONFIRMATION;
            case "info" -> Alert.AlertType.INFORMATION;
            case "warning" -> Alert.AlertType.WARNING;
            default -> Alert.AlertType.NONE;
        };

        Alert objectAlert = new Alert(type);
        objectAlert.setTitle(title);
        objectAlert.setHeaderText(title);
        objectAlert.setContentText(message);

        String imagePath = "/org/hrcore/system/resources/images/" + imageName;
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        
        if (!image.isError()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);
            objectAlert.setGraphic(imageView);
        }

        objectAlert.showAndWait();
    }
}

