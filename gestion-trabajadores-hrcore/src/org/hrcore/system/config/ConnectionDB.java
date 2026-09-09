/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hrcore.system.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static  ConnectionDB instanciaConnectionDB;
    private Connection connection;


    private ConnectionDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+Enviroment.LOCATION_SERVICE+"/"+Enviroment.DATA_BASE,
                    Enviroment.USER_KINAL,
                    Enviroment.PASSWORD_KINAL);

        } catch(ClassNotFoundException classNotFound){
            System.out.println("Error de clase no encontrada");

        }catch (SQLException sqlException) {

            System.out.println("Error de conexion SQL");
        }catch(Exception e){
            System.out.println("Error parde : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public static ConnectionDB getInstanciaConexionDB() {
        if(instanciaConnectionDB == null)
            instanciaConnectionDB = new ConnectionDB();
        return instanciaConnectionDB;
    }
}
