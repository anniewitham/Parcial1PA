package edu.avanzada.parcial1.control;

import java.sql.SQLException;

/**
 * Launcher es la clase principal del programa encargada de iniciar la aplicación.
 * 
 * Esta clase contiene el método main, que es el punto de entrada de la aplicación. 
 * Su responsabilidad principal es crear una instancia de ControlPrincipal, 
 * que gestiona el flujo y la lógica de la aplicación.
 *
 * @author Juan, Ana, Samuel
 */
public class Launcher {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     * @throws SQLException si ocurre un error relacionado con la base de datos 
     * durante la inicialización de la aplicación.
     */
    public static void main(String[] args) throws SQLException {
        new ControlPrincipal();
    }
}