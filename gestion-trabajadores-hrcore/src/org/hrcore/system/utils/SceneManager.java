/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.utils;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneManager {
    
    private static SceneManager instanciaSceneManager;
    private Stage stagePrincipal;
    
    private SceneManager(){
        
    }

    public void changeScene(Scene scene){
        try{
            if(stagePrincipal != null && scene != null) {
                scene.setFill(Color.TRANSPARENT);
                stagePrincipal.setScene(scene);
                // stagePrincipal.initStyle(StageStyle.TRANSPARENT);  // 👈 ELIMINA ESTA LÍNEA
                stagePrincipal.sizeToScene();
                stagePrincipal.show();
            } else {
                System.out.println("Error: La ventana principal o la escena son nulas.");
            }
        } catch (NullPointerException objetoNulo) {
            System.out.println("Error: Objeto nulo al cambiar de escena.");
            objetoNulo.printStackTrace();
        } catch (Exception errorPadre) {
            System.out.println("Error general al cambiar de escena: " + errorPadre.getMessage());
            errorPadre.printStackTrace();
        }
    }
    
        public static SceneManager getInstanciaSceneManager() {
        if (instanciaSceneManager == null) {
            instanciaSceneManager = new SceneManager();
        }
        return instanciaSceneManager;
    }
    
    // Getters y Setters para la ventana principal
    public Stage getStagePrincipal() {
        return stagePrincipal;
    }
    
    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }
}

