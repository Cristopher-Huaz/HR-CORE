package org.hrcore.system.utils;

import javafx.scene.control.Alert;

public class AlertInformation {

    /**
     * Muestra una alerta en pantalla.
     *
     * @param typeAlert "error" | "confirm" | "info" | "warning"
     * @param message   Texto del cuerpo
     * @param title     Titulo de la ventana
     */
    public static void showAlert(String typeAlert, String message, String title) {
        Alert.AlertType type = switch (typeAlert.toLowerCase()) {
            case "error"   -> Alert.AlertType.ERROR;
            case "confirm" -> Alert.AlertType.CONFIRMATION;
            case "info"    -> Alert.AlertType.INFORMATION;
            case "warning" -> Alert.AlertType.WARNING;
            default        -> Alert.AlertType.NONE;
        };

        Alert objectAlert = new Alert(type);
        objectAlert.setTitle(title);
        objectAlert.setHeaderText(title);
        objectAlert.setContentText(message);
        objectAlert.showAndWait();
    }
}