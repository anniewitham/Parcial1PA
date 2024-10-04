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

    private ControlPrincipal control;
    private RazaDAO razaDAO;

    public ControlRaza(ControlPrincipal control, RazaDAO razaDAO) {
        this.control = control;
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

    public void actualizarRaza(RazaVO raza) throws SQLException {
        // Delegar la actualización a RazaDAO
        razaDAO.actualizarRaza(raza);
    }

    public void eliminarRaza(int id) throws SQLException {
        // Delegar la eliminación a RazaDAO
        razaDAO.eliminarRaza(id);
    }

    public List<RazaVO> consultarRazas() throws SQLException {
        // Delegar la consulta a RazaDAO
        return razaDAO.consultarRazas();
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
                control.ventanaEmergente.ventanaPlana("Razas serializadas correctamente.");
            } else {
                // Si la lista está vacía, mostramos un mensaje de advertencia
                control.ventanaEmergente.ventanaError("No hay razas para serializar.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar las razas: " + e.getMessage());
        } finally {
            serializacion.cerrarArchSerializado();
        }

    }
}
