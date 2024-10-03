package edu.avanzada.parcial1.modelo;

import edu.avanzada.parcial1.control.ControlProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase Conexion es responsable de establecer y gestionar la conexión
 * con la base de datos. Utiliza las credenciales almacenadas en un archivo
 * de propiedades para conectarse a la base de datos y permite la
 * desconexión.
 * 
 * Esta clase implementa el patrón de diseño Singleton, asegurando que
 * solo haya una instancia de conexión a la base de datos en toda la aplicación.
 * 
 * @author Juan, Ana, Samuel
 */
public class Conexion {
    private ControlProperties propiedades; // Objeto para manejar las propiedades de conexión
    private static Connection conexion = null; // Instancia de conexión a la base de datos
    private static String URLBD; // URL de la base de datos
    private static String usuario; // Usuario para la conexión
    private static String contrasena; // Contraseña para la conexión

    /**
     * Constructor de la clase Conexion. Inicializa el objeto ControlProperties
     * y carga las credenciales de la base de datos desde el archivo de propiedades.
     */
    public Conexion() {
        propiedades = new ControlProperties();
        this.URLBD = propiedades.obtenerCredencial(URLBD);
        this.usuario = propiedades.obtenerCredencial(usuario);
        this.contrasena = propiedades.obtenerCredencial(contrasena);
    }

    /**
     * Obtiene la conexión a la base de datos. Si la conexión no existe,
     * se crea una nueva utilizando las credenciales cargadas.
     *
     * @return La conexión a la base de datos, o null si ocurrió un error.
     */
    public static Connection getConexion() {
        try {
            // Intenta establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            // Se puede manejar la excepción aquí (p.ej., registrar el error)
            ex.printStackTrace(); // Imprime el stack trace en caso de error
        }
        return conexion; // Retorna la conexión (puede ser null si hubo un error)
    }

    /**
     * Desconecta la conexión a la base de datos establecida, estableciendo
     * la instancia de conexión a null.
     */
    public static void desconectar() {
        conexion = null; // Desconectar la conexión
    }
}