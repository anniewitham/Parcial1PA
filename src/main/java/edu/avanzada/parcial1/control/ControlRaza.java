package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.RazaDAO;
import edu.avanzada.parcial1.modelo.RazaVO;
import edu.avanzada.parcial1.modelo.Serializacion;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ControlRaza {
    private RazaDAO razaDAO;
    private ControlPrincipal control;

    public ControlRaza(RazaDAO razaDAO, ControlPrincipal control) {
        this.razaDAO = razaDAO;
        this.control = control;
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
     /**
     * Método que serializa una lista de objetos RazaVO en un archivo binario.
     *
     * @throws IOException si ocurre un error de entrada/salida durante la
     * serialización
     */
    public void serializarRazas() throws IOException {
        Serializacion serializacion = new Serializacion();

        try {
            // Consultamos las razas desde la base de datos
            List<RazaVO> razas = razaDAO.consultarRazas();

            // Verificamos si la lista tiene al menos un registro
            if (!razas.isEmpty()) {
                // Si tiene al menos un registro, serializamos la lista de razas
                serializacion.escribirArchSerializado(new ArrayList<>(razas));
                control.getVentanaEmergente().ventanaPlana("Razas serializadas correctamente.");
            } else {
                // Si la lista está vacía, mostramos un mensaje de advertencia
                control.getVentanaEmergente().ventanaError("No hay razas para serializar.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar las razas: " + e.getMessage());
        } finally {
            serializacion.cerrarArchSerializado();
        }

    }
}
