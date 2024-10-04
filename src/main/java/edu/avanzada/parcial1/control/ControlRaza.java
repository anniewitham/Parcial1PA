package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.RazaDAO;
import edu.avanzada.parcial1.modelo.RazaVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ControlRaza {
    private RazaDAO razaDAO;

    public ControlRaza(RazaDAO razaDAO) {
        this.razaDAO = razaDAO;
    }

    public void cargarRazasDesdePropiedades(Properties propiedades) throws SQLException {
        List<RazaVO> razas = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String clave = "Raza" + i;
            String valorRaza = propiedades.getProperty(clave);
            if (valorRaza != null) {
                String[] razaData = valorRaza.split(",");

                String nombreRaza = razaData.length > 0 ? razaData[0].trim() : null; // Nombre de la raza
                String paisOrigen = razaData.length > 1 ? razaData[1].trim() : null; // País de origen

                // Creando un objeto RazaVO con los datos disponibles
                RazaVO raza = new RazaVO(nombreRaza, paisOrigen, null, null, null, null, null, null, null, null, null);
                razas.add(raza);
            }
        }

        insertarRazas(razas);
    }

    public void insertarRazas(List<RazaVO> razas) throws SQLException {
        for (RazaVO raza : razas) {
            razaDAO.insertarRaza(raza); // Delegar la inserción a RazaDAO
        }
    }

    public void insertarRazaP(RazaVO raza) throws SQLException {
        razaDAO.insertarRazaP(raza);
    }

    public void insertarRaza(RazaVO raza) throws SQLException {
        razaDAO.insertarRaza(raza);
    }

    public void eliminarRaza(String nombre) throws SQLException {
        razaDAO.eliminarRaza(nombre);
    }

    public List<RazaVO> consultarRaza(int tipoConsulta, String consulta) throws SQLException {
        return razaDAO.consultarRazas(tipoConsulta, consulta);
    }

    // Método para obtener razas incompletas
    public List<RazaVO> obtenerRazasIncompletas() throws SQLException {
        List<RazaVO> razasIncompletas = new ArrayList<>();
        List<RazaVO> todasLasRazas = razaDAO.consultarRazas();

        for (RazaVO raza : todasLasRazas) {
            // Verifica si algún campo esencial está vacío
            if (raza.getApariencia() == null || raza.getPelo() == null || raza.getColor() == null
                    || raza.getEspalda() == null || raza.getLomo() == null || raza.getCola() == null
                    || raza.getPecho() == null) {
                razasIncompletas.add(raza);
            }
        }

        return razasIncompletas;
    }
    
    public boolean validarRazaCreada(String nombre) throws SQLException {
        return razaDAO.validarRazaCreada(nombre);
    }
    
    // Método para buscar una raza por nombre
    public RazaVO buscarRaza(String nombre) throws SQLException {
        return razaDAO.buscarRaza(nombre);
    }
    
    public void actualizarRaza(RazaVO raza) throws SQLException {
        razaDAO.actualizarRaza(raza);
    }
}
