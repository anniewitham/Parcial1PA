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
    private Connection connection;

    public RazaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarRazaP(RazaVO raza) throws SQLException {
        String sql = "INSERT INTO raza (nombre, pais_origen) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, raza.getNombre());
            stmt.setString(2, raza.getPaisOrigen());
            stmt.executeUpdate();
        }
    }
    
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
            stmt.setString(12, raza.getPelo());
            stmt.executeUpdate();
        }
    }

    public void actualizarRaza(RazaVO raza, int id) throws SQLException {
        String sql = "UPDATE raza SET grupo_fci = ?, seccion_fci = ?, apariencia_general = ?, "
                   + "pelo = ?, color = ?, espalda = ?, lomo = ?, cola = ?, pecho = ? WHERE id = ?";
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
            stmt.setInt(10, id);
            stmt.executeUpdate();
        }
    }

    public void eliminarRaza(String nombre) throws SQLException {
        String sql = "DELETE FROM raza WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
    }
}


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
    
    public boolean validarRazaCreada(String nombre) throws SQLException {
        String sql = "SELECT * FROM raza WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
