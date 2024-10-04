package edu.avanzada.parcial1.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            throw new SQLException("Error al consultar la existencia de razas: " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Inserta una nueva raza en la base de datos.
     *
     * @param raza El objeto RazaVO que contiene los datos de la raza.
     * @throws SQLException Si ocurre un error al insertar la raza.
     */
    public void insertarRaza(RazaVO raza) throws SQLException {
        String consulta = "INSERT INTO raza (nombre, pais_origen, grupo_fci, seccion_fci, apariencia_general, pelo, color, espalda, lomo, cola, pecho) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, raza.getNombre());
            statement.setString(2, raza.getPaisOrigen());
            statement.setString(3, raza.getGrupoFCI());
            statement.setString(4, raza.getSeccionFCI());
            statement.setString(5, raza.getApariencia());
            statement.setString(6, raza.getPelo());
            statement.setString(7, raza.getColor());
            statement.setString(8, raza.getEspalda());
            statement.setString(9, raza.getLomo());
            statement.setString(10, raza.getCola());
            statement.setString(11, raza.getPecho());
            statement.executeUpdate(); // Ejecuta la inserción
        } catch (SQLException ex) {
            throw new SQLException("Error al insertar la raza: " + ex.getMessage());
        }
    }

    /**
     * Inserta una lista de razas en la base de datos.
     *
     * @param razas Lista de RazaVO que se desean insertar.
     * @throws SQLException Si ocurre un error al insertar las razas.
     */
    public void insertarRazas(List<RazaVO> razas) throws SQLException {
        for (RazaVO raza : razas) {
            insertarRaza(raza); // Usa el método de inserción existente
        }
    }

    /**
     * Actualiza una raza en la base de datos.
     *
     * @param raza El objeto RazaVO que contiene los datos actualizados de la raza.
     * @throws SQLException Si ocurre un error al actualizar la raza.
     */
    public void actualizarRaza(RazaVO raza) throws SQLException {
        String consulta = "UPDATE raza SET nombre=?, pais_origen=?, grupo_fci=?, seccion_fci=?, apariencia_general=?, pelo=?, color=?, espalda=?, lomo=?, cola=?, pecho=? WHERE id=?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, raza.getNombre());
            statement.setString(2, raza.getPaisOrigen());
            statement.setString(3, raza.getGrupoFCI());
            statement.setString(4, raza.getSeccionFCI());
            statement.setString(5, raza.getApariencia());
            statement.setString(6, raza.getPelo());
            statement.setString(7, raza.getColor());
            statement.setString(8, raza.getEspalda());
            statement.setString(9, raza.getLomo());
            statement.setString(10, raza.getCola());
            statement.setString(11, raza.getPecho());
            statement.executeUpdate();
        }
    }

    /**
     * Elimina una raza de la base de datos.
     *
     * @param id El ID de la raza que se desea eliminar.
     * @throws SQLException Si ocurre un error al eliminar la raza.
     */
    public void eliminarRaza(int id) throws SQLException {
        String consulta = "DELETE FROM raza WHERE id=?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    /**
     * Consulta todas las razas en la base de datos.
     *
     * @return Lista de RazaVO que representa las razas.
     * @throws SQLException Si ocurre un error al consultar.
     */
    public List<RazaVO> consultarRazas() throws SQLException {
        List<RazaVO> razas = new ArrayList<>();
        String consulta = "SELECT * FROM raza";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(consulta);

            while (resultSet.next()) {
                // Aquí debes crear el objeto RazaVO con los datos obtenidos
                RazaVO raza = new RazaVO(
                        resultSet.getString("nombre"),
                        resultSet.getString("pais_origen"),
                        resultSet.getString("grupo_fci"),
                        resultSet.getString("seccion_fci"),
                        resultSet.getString("apariencia_general"),
                        resultSet.getString("pelo"),
                        resultSet.getString("color"),
                        resultSet.getString("espalda"),
                        resultSet.getString("lomo"),
                        resultSet.getString("cola"),
                        resultSet.getString("pecho")
                );
                razas.add(raza); // Agregar la raza a la lista
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al consultar las razas: " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return razas; // Retorna la lista de razas
    }
}
