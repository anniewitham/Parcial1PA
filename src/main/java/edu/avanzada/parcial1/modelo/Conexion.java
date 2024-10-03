package edu.avanzada.parcial1.modelo;

import edu.avanzada.parcial1.control.ControlProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase Conexion es responsable de establecer y gestionar la conexión
 * con la base de datos. Utiliza las credenciales almacenadas en un objeto
 * ControlProperties para conectarse a la base de datos y permite la
 * desconexión.
 * 
 * Esta clase implementa el patrón de diseño Singleton, asegurando que
 * solo haya una instancia de conexión a la base de datos en toda la aplicación.
 * 
 * @author Juan, Ana, Samuel
 */
public class Conexion {
    private static Connection conexion = null; // Instancia de conexión a la base de datos
    private String URLBD; // URL de la base de datos
    private String usuario; // Usuario para la conexión
    private String contrasena; // Contraseña para la conexión

    /**
     * Constructor de la clase Conexion. Inicializa las credenciales de conexión.
     *
     * @param propiedades Objeto ControlProperties que contiene las credenciales de conexión.
     */
    public Conexion(ControlProperties propiedades) throws SQLException {
        this.URLBD = propiedades.obtenerCredencial("URLBD");
        this.usuario = propiedades.obtenerCredencial("usuario");
        this.contrasena = propiedades.obtenerCredencial("contrasena");

        // Verificar credenciales antes de la conexión
        System.out.println("URLBD: " + URLBD);
        System.out.println("Usuario: " + usuario);
        System.out.println("Contraseña: " + contrasena);
    }


    /**
     * Obtiene la conexión a la base de datos. Si la conexión no existe,
     * se crea una nueva utilizando las credenciales cargadas.
     *
     * @return La conexión a la base de datos.
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
    public Connection getConexion() throws SQLException {
        if (conexion == null) {
            conexion = DriverManager.getConnection(URLBD, usuario, contrasena);
        }
        return conexion; // Retorna la conexión
    }

    /**
     * Desconecta la conexión a la base de datos establecida, estableciendo
     * la instancia de conexión a null.
     */
    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close(); // Desconectar la conexión
            conexion = null; // Restablecer a null
        }
    }
}
