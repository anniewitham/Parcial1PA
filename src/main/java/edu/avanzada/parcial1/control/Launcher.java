package edu.avanzada.parcial1.control;

import java.sql.SQLException;

/**
 * Launcher es la clase principal del programa encargada de darle inicio
 * 
 * @author Juan, Ana, Samuel
 */
public class Launcher {

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) throws SQLException {
        new ControlPrincipal();
    }
}