package edu.avanzada.parcial1.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase Conexion es responsable de establecer y gestionar la conexión
 * con la base de datos. Permite la desconexión.
 * 
 * Esta clase implementa el patrón de diseño Singleton, asegurando que
 * solo haya una instancia de conexión a la base de datos en toda la aplicación.
 * 
 * @author Juan, Ana, Samuel
 */
public class Conexion {
    private static Conexion instancia; // Instancia Singleton
    private Connection conexion; // Instancia de conexión a la base de datos
    private String URLBD; // URL de la base de datos
    private String usuario; // Usuario para la conexión
    private String contrasena; // Contraseña para la conexión

    /**
     * Constructor de la clase Conexion. Inicializa las credenciales de conexión.
     *
     * @param urlBD La URL de la base de datos.
     * @param usuario El usuario para la conexión.
     * @param contrasena La contraseña para la conexión.
     */
    public Conexion(String urlBD, String usuario, String contrasena) {
        this.URLBD = urlBD;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    /**
     * Obtiene la instancia Singleton de la clase Conexion. Si la instancia no existe,
     * se crea una nueva utilizando las credenciales proporcionadas.
     *
     * @param urlBD La URL de la base de datos.
     * @param usuario El usuario para la conexión.
     * @param contrasena La contraseña para la conexión.
     * @return La instancia de Conexion.
     */
    public static Conexion getInstancia(String urlBD, String usuario, String contrasena) throws SQLException {
        if (instancia == null) {
            instancia = new Conexion(urlBD, usuario, contrasena);
        }
        return instancia;
    }

    /**
     * Obtiene la conexión a la base de datos. Si la conexión no existe,
     * se crea una nueva utilizando las credenciales cargadas.
     *
     * @return La conexión a la base de datos.
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
    public Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URLBD, usuario, contrasena);
        }
        return conexion; // Retorna la conexión
    }

    /**
     * Desconecta la conexión a la base de datos establecida, estableciendo
     * la instancia de conexión a null.
     */
    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close(); // Desconectar la conexión
            conexion = null; // Restablecer a null
        }
    }
}
