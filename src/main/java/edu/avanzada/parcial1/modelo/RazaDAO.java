package edu.avanzada.parcial1.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * La clase RazaDAO es responsable de manejar las operaciones de acceso a datos
 * relacionadas con las razas de perros en la base de datos.
 * 
 * @author Juan, Ana, Samuel
 */
public class RazaDAO {
    private Connection conexion; // Conexión a la base de datos

    /**
     * Constructor de la clase RazaDAO que inicializa la conexión.
     *
     * @param conexion La conexión a la base de datos que se usará para las operaciones.
     */
    public RazaDAO(Connection conexion) {
        this.conexion = conexion; // Se pasa la conexión al DAO
    }
    
    /**
     * Consulta si existen registros en la tabla de razas.
     *
     * @return true si hay al menos una raza registrada, false en caso contrario.
     * @throws SQLException si hay un error en la consulta.
     */
    public boolean consultarExistencia() throws SQLException {
        String consulta = "SELECT * FROM `raza`";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(consulta);
            return resultSet.next(); // Retorna true si hay al menos un resultado
        } catch (SQLException ex) {
            // Manejo del error: aquí puedes lanzar la excepción o manejarla según tu lógica
            throw new SQLException("Error al consultar la existencia de razas: " + ex.getMessage());
        } finally {
            // Cerrar los recursos en finally
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            // No desconectamos aquí porque la conexión puede ser utilizada en otras operaciones
        }
    }
}
