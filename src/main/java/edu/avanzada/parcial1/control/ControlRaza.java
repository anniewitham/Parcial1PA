package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.RazaDAO;
import edu.avanzada.parcial1.modelo.RazaVO;
import edu.avanzada.parcial1.modelo.Serializacion;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 *  Clase ControlRaza que maneja la lógica de negocio relacionada con las razas de perros.
 * Esta clase se encarga de interactuar con la capa de acceso a datos a través de la clase RazaDAO
 * para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las razas.
 * @author Juan, Ana, Samuel
 */
public class ControlRaza {
    protected RazaDAO razaDAO;   // Objeto para acceder a los datos de las razas
    protected ControlPrincipal control; // Controlador principal de la aplicación

    /**
     * Constructor de la clase ControlRaza.
     *
     * @param razaDAO Instancia de RazaDAO para la manipulación de datos de razas.
     * @param control Instancia de ControlPrincipal para acceder a funcionalidades de la aplicación.
     */
    public ControlRaza(RazaDAO razaDAO, ControlPrincipal control) {
        this.razaDAO = razaDAO;
        this.control = control;
    }
    
    /**
     * Carga razas desde un archivo de propiedades y las inserta en la base de datos.
     *
     * @param propiedades Propiedades que contienen los datos de las razas a cargar.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public void cargarRazasDesdePropiedades(Properties propiedades) throws SQLException {
        List<RazaVO> razas = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String clave = "Raza" + i;
            String valorRaza = propiedades.getProperty(clave);
            if (valorRaza != null) {
                String[] razaData = valorRaza.split(",");

                String nombreRaza = razaData.length > 0 ? razaData[0].trim() : null;
                String paisOrigen = razaData.length > 1 ? razaData[1].trim() : null;

                // Creando un objeto RazaVO con los datos disponibles
                RazaVO raza = new RazaVO(nombreRaza, paisOrigen, null, null, null, null, null, null, null, null, null);
                razas.add(raza);
            }
        }

        insertarRazas(razas);
    }

    /**
     * Inserta una lista de razas en la base de datos.
     *
     * @param razas Lista de objetos RazaVO que representan las razas a insertar.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public void insertarRazas(List<RazaVO> razas) throws SQLException {
        for (RazaVO raza : razas) {
            razaDAO.insertarRaza(raza); // Delegar la inserción a RazaDAO
        }
    }

    /**
     * Inserta una raza en la base de datos.
     *
     * @param raza Objeto RazaVO que representa la raza a insertar.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public void insertarRazaP(RazaVO raza) throws SQLException {
        razaDAO.insertarRazaP(raza);
    }

    /**
     * Inserta una raza en la base de datos.
     *
     * @param raza Objeto RazaVO que representa la raza a insertar.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public void insertarRaza(RazaVO raza) throws SQLException {
        razaDAO.insertarRaza(raza);
    }

    /**
     * Elimina una raza de la base de datos.
     *
     * @param nombre Nombre de la raza a eliminar.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public void eliminarRaza(String nombre) throws SQLException {
        razaDAO.eliminarRaza(nombre);
    }

    /**
     * Consulta razas en la base de datos según el tipo y el criterio de búsqueda.
     *
     * @param tipoConsulta Tipo de consulta a realizar.
     * @param consulta Criterio de búsqueda para la consulta.
     * @return Lista de objetos RazaVO que cumplen con la consulta.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public List<RazaVO> consultarRaza(int tipoConsulta, String consulta) throws SQLException {
        return razaDAO.consultarRazas(tipoConsulta, consulta);
    }

    /**
     * Obtiene una lista de razas que tienen campos esenciales incompletos.
     *
     * @return Lista de objetos RazaVO que representan las razas incompletas.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public List<RazaVO> obtenerRazasIncompletas() throws SQLException {
        List<RazaVO> razasIncompletas = new ArrayList<>();
        List<RazaVO> todasLasRazas = razaDAO.consultarRazas();

        for (RazaVO raza : todasLasRazas) {
            if (raza.getApariencia() == null || raza.getPelo() == null || raza.getColor() == null
                    || raza.getEspalda() == null || raza.getLomo() == null || raza.getCola() == null
                    || raza.getPecho() == null) {
                razasIncompletas.add(raza);
            }
        }

        return razasIncompletas;
    }

    /**
     * Valida si una raza ha sido creada en la base de datos.
     *
     * @param nombre Nombre de la raza a validar.
     * @return true si la raza existe, false en caso contrario.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public boolean validarRazaCreada(String nombre) throws SQLException {
        return razaDAO.validarRazaCreada(nombre);
    }
    
    /**
     * Busca una raza en la base de datos por su nombre.
     *
     * @param nombre Nombre de la raza a buscar.
     * @return Objeto RazaVO que representa la raza encontrada, o null si no se encuentra.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    // Método para buscar una raza por nombre
    public RazaVO buscarRaza(String nombre) throws SQLException {
        return razaDAO.buscarRaza(nombre);
    }
    
    /**
     * Actualiza una raza en la base de datos.
     *
     * @param raza Objeto RazaVO que representa la raza a actualizar.
     * @throws SQLException si ocurre un error en la operación de base de datos.
     */
    public void actualizarRaza(RazaVO raza) throws SQLException {
        razaDAO.actualizarRaza(raza);
    }
    
    /**
     * Método que serializa una lista de objetos RazaVO en un archivo binario.
     *
     * @throws IOException si ocurre un error de entrada/salida durante la
     *                     serialización.
     */
    public void serializarRazas() throws IOException {
        Serializacion serializacion = new Serializacion();

        try {
            List<RazaVO> razas = razaDAO.consultarRazas();
            if (!razas.isEmpty()) {
                serializacion.escribirArchSerializado(new ArrayList<>(razas));
                control.getVentanaEmergente().ventanaPlana("Razas serializadas correctamente.");
            } else {
                control.getVentanaEmergente().ventanaError("No hay razas para serializar.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar las razas: " + e.getMessage());
        } finally {
            serializacion.cerrarArchSerializado();
        }

    }
}
