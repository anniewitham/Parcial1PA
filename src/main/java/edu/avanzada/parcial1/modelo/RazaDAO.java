package edu.avanzada.parcial1.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase RazaDAO es responsable de manejar las operaciones de acceso a datos
 * relacionadas con las razas de perros en la base de datos.
 * 
 * Esta clase proporciona métodos para realizar operaciones CRUD (Crear, Leer, 
 * Actualizar, Eliminar) sobre las razas, así como consultas específicas para 
 * obtener información sobre ellas. Utiliza una conexión a la base de datos proporcionada 
 * en su constructor para ejecutar las consultas SQL.
 *
 * @author Juan, Ana, Samuel
 */
public class RazaDAO {
    private Connection connection;

    /**
     * Constructor de la clase RazaDAO.
     *
     * @param connection La conexión a la base de datos que se utilizará para 
     * realizar las operaciones de acceso a datos.
     */
    public RazaDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserta una nueva raza en la base de datos con nombre y país de origen.
     *
     * @param raza Objeto RazaVO que contiene la información de la raza a insertar.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public void insertarRazaP(RazaVO raza) throws SQLException {
        String sql = "INSERT INTO raza (nombre, pais_origen) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, raza.getNombre());
            stmt.setString(2, raza.getPaisOrigen());
            stmt.executeUpdate();
        }
    }

    /**
     * Inserta una nueva raza en la base de datos con todos sus atributos.
     *
     * @param raza Objeto RazaVO que contiene la información de la raza a insertar.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public void insertarRaza(RazaVO raza) throws SQLException {
        String sql = "INSERT INTO `raza`(`id`, `nombre`, `pais_origen`, `grupo_fci`, `seccion_fci`, `apariencia_general`, `pelo`, `color`, `espalda`, `lomo`, `cola`, `pecho`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "0");
            stmt.setString(2, raza.getNombre());
            stmt.setString(3, raza.getPaisOrigen());
            stmt.setString(4, raza.getGrupoFCI());
            stmt.setString(5, raza.getSeccionFCI());
            stmt.setString(6, raza.getApariencia());
            stmt.setString(7, raza.getPelo());
            stmt.setString(8, raza.getColor());
            stmt.setString(9, raza.getEspalda());
            stmt.setString(10, raza.getLomo());
            stmt.setString(11, raza.getCola());
            stmt.setString(12, raza.getPecho());
            stmt.executeUpdate();
        }
    }

    /**
     * Actualiza los atributos de una raza existente en la base de datos.
     *
     * @param raza Objeto RazaVO que contiene la información actualizada de la raza.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public void actualizarRaza(RazaVO raza) throws SQLException {
        String sql = "UPDATE raza SET grupo_fci = ?, seccion_fci = ?, apariencia_general = ?, "
                   + "pelo = ?, color = ?, espalda = ?, lomo = ?, cola = ?, pecho = ? WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, raza.getGrupoFCI());
            stmt.setString(2, raza.getSeccionFCI());
            stmt.setString(3, raza.getApariencia());
            stmt.setString(4, raza.getPelo());
            stmt.setString(5, raza.getColor());
            stmt.setString(6, raza.getEspalda());
            stmt.setString(7, raza.getLomo());
            stmt.setString(8, raza.getCola());
            stmt.setString(9, raza.getPecho());
            stmt.setString(10, raza.getNombre());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina una raza de la base de datos según su nombre.
     *
     * @param nombre El nombre de la raza a eliminar.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public void eliminarRaza(String nombre) throws SQLException {
        String sql = "DELETE FROM raza WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        }
    }

    /**
     * Consulta todas las razas en la base de datos.
     *
     * @return Una lista de objetos RazaVO que representan todas las razas.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public List<RazaVO> consultarRazas() throws SQLException {
        List<RazaVO> razas = new ArrayList<>();
        String sql = "SELECT * FROM raza";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                RazaVO raza = new RazaVO(
                        rs.getString("nombre"),
                        rs.getString("pais_origen"),
                        rs.getString("grupo_fci"),
                        rs.getString("seccion_fci"),
                        rs.getString("apariencia_general"),
                        rs.getString("pelo"),
                        rs.getString("color"),
                        rs.getString("espalda"),
                        rs.getString("lomo"),
                        rs.getString("cola"),
                        rs.getString("pecho")
                );
                razas.add(raza);
            }
        }

        return razas;
    }
    
    /**
     * Consulta razas en la base de datos según un tipo de consulta específico.
     *
     * @param _tipoConsulta Tipo de consulta (1: por nombre, 2: por grupo y sección, 
     *                      3: por país de origen, 4: por color).
     * @param consulta Cadena de consulta que se usará para filtrar los resultados.
     * @return Una lista de objetos RazaVO que cumplen con los criterios de consulta.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public List<RazaVO> consultarRazas(int _tipoConsulta, String consulta) throws SQLException {
        List<RazaVO> razas = new ArrayList<>();
        String sql = null;

        if (_tipoConsulta == 1) {
            sql = "SELECT * FROM `raza` WHERE nombre = ?";
        } else if (_tipoConsulta == 2) {
            String[] partes = consulta.split(",");
            String grupoFCI = partes[0].trim();
            String seccionFCI = partes[1].trim();
        
            // Comparación exacta con "=" en lugar de "LIKE"
            sql = "SELECT * FROM `raza` WHERE grupo_fci = ? AND seccion_fci = ?";
        } else if (_tipoConsulta == 3) {
            sql = "SELECT * FROM `raza` WHERE pais_origen = ?";
        } else if (_tipoConsulta == 4) {
            sql = "SELECT * FROM `raza` WHERE color = ?";
        }

        if (sql != null) {
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                if (_tipoConsulta == 2) {
                    String[] partes = consulta.split(",");
                    pstmt.setString(1, partes[0].trim());
                    pstmt.setString(2, partes[1].trim());
                } else {
                    pstmt.setString(1, consulta.trim());
                }

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        RazaVO raza = new RazaVO(
                            rs.getString("nombre"),
                            rs.getString("pais_origen"),
                            rs.getString("grupo_fci"),
                            rs.getString("seccion_fci"),
                            rs.getString("apariencia_general"),
                            rs.getString("pelo"),
                            rs.getString("color"),
                            rs.getString("espalda"),
                            rs.getString("lomo"),
                            rs.getString("cola"),
                            rs.getString("pecho")
                        );
                        razas.add(raza);
                    }
                }
            }
        }   
        return razas;
    }

    /**
     * Consulta si existen razas en la base de datos.
     *
     * @return true si hay razas en la base de datos, false de lo contrario.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public boolean consultarExistencia() throws SQLException {
        String sql = "SELECT COUNT(*) FROM raza";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    
    /**
     * Valida si una raza con el nombre especificado ya está creada en la base de datos.
     *
     * @param nombre El nombre de la raza a validar.
     * @return true si la raza ya existe, false de lo contrario.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public boolean validarRazaCreada(String nombre) throws SQLException {
        String sql = "SELECT * FROM raza WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Busca una raza en la base de datos según su nombre.
     *
     * @param nombre El nombre de la raza a buscar.
     * @return Un objeto RazaVO que representa la raza encontrada, o null si no se encuentra.
     * @throws SQLException Si ocurre un error al realizar la operación en la base de datos.
     */
    public RazaVO buscarRaza(String nombre) throws SQLException {
        String sql = "SELECT * FROM raza WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RazaVO(
                        rs.getString("nombre"),
                        rs.getString("pais_origen"),
                        rs.getString("grupo_fci"),
                        rs.getString("seccion_fci"),
                        rs.getString("apariencia_general"),
                        rs.getString("pelo"),
                        rs.getString("color"),
                        rs.getString("espalda"),
                        rs.getString("lomo"),
                        rs.getString("cola"),
                        rs.getString("pecho")
                    );
                }
            }
        }
        return null;
    }
}
