package edu.avanzada.parcial1.modelo;

import edu.avanzada.parcial1.control.ControlProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexion es la clase encargada de generar la conexion con la base de datos
 * @author avila
 */
public class Conexion {
    private ControlProperties propiedades;
    private static Connection conexion = null;
    private static String URLBD;
    private static String usuario;
    private static String contrasena;

    public Conexion() {
        propiedades = new ControlProperties();
        this.URLBD = propiedades.obtenerCredencial(URLBD);
        this.usuario = propiedades.obtenerCredencial(usuario);
        this.contrasena = propiedades.obtenerCredencial(contrasena);
    }
    
    public static Connection getConexion(){
        try {
            conexion = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            
        }
        return conexion;
    }
    
    public static void desconectar() {
        conexion = null;
    }
}