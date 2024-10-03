package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.Conexion;
import edu.avanzada.parcial1.modelo.RazaDAO;
import edu.avanzada.parcial1.vista.VentanaBuscarArchivo;
import edu.avanzada.parcial1.vista.VentanaEmergente;
import edu.avanzada.parcial1.vista.VentanaRegistrarRaza;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * ControlPrincipal sirve como clase fachada para hacer las conexiones entre las
 * vistas y los controles.
 * 
 * @author Juan, Ana, Samuel
 */
public class ControlPrincipal implements ActionListener {
    public VentanaRegistrarRaza vistaRegistrarRaza;
    public VentanaBuscarArchivo buscarArchivo;
    public VentanaEmergente ventanaEmergente;
    public RazaDAO razaDAO;
    private Conexion conexion; // Aquí defines la conexión

    public ControlPrincipal() {
        ventanaEmergente = new VentanaEmergente();
        buscarArchivo = new VentanaBuscarArchivo();
        vistaRegistrarRaza = new VentanaRegistrarRaza(this);
        
        // Añadir listeners a los botones
        vistaRegistrarRaza.BotonConsultar.addActionListener(this);
        vistaRegistrarRaza.BotonInsertar.addActionListener(this);
        vistaRegistrarRaza.BotonLimpiar.addActionListener(this);
        vistaRegistrarRaza.BotonModificar.addActionListener(this);
        vistaRegistrarRaza.BotonSerializar.addActionListener(this);
        vistaRegistrarRaza.BotonEliminar.addActionListener(this);

        // Abrir la ventana para buscar el archivo de propiedades
        abrirBuscadorSiNoHayDatos();
    }

    private void abrirBuscadorSiNoHayDatos() {
        boolean archivoSeleccionado = false;

        while (!archivoSeleccionado) {
            try {
                // Abrir la ventana de búsqueda para obtener el archivo de propiedades
                String rutaArchivo = buscarArchivo.buscarArchivo();
                
                if (buscarArchivo.isArchivoSeleccionado()) { // Verifica si se seleccionó un archivo
                    ControlProperties propiedades = new ControlProperties();
                    propiedades.cargarPropiedades(rutaArchivo); // Cargar el archivo seleccionado
                    System.out.println(rutaArchivo);
                    
                    // Inicializar la conexión con propiedades
                    conexion = new Conexion(propiedades);
                    System.out.println("1");
                    razaDAO = new RazaDAO(conexion.getConexion());
                    System.out.println("2");
                    
                    insercionInicial(); // Llama a inserción inicial
                    archivoSeleccionado = true; // Marca que se ha seleccionado un archivo
                } else {
                    // Mensaje al usuario
                    ventanaEmergente.ventanaAtencion("Debes seleccionar un archivo de propiedades.");
                    // La ventana se volverá a abrir en la siguiente iteración del bucle
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace(); // Esto te dará más información sobre el error
                ventanaEmergente.ventanaError("Error al cargar propiedades: " + e.getMessage());
                archivoSeleccionado = true; // Termina el bucle si hay un error
            }
        }
    }

    public void insercionInicial() throws SQLException {
        boolean existe = razaDAO.consultarExistencia();
        if (!existe) {
            ventanaEmergente.ventanaAtencion("¡Debes precargar los datos de tu properties!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Insertar":
                // Lógica para insertar
                break;
            case "Consultar":
                // Lógica para consultar
                break;
            case "Eliminar":
                // Lógica para eliminar
                break;
            case "Modificar":
                // Lógica para modificar
                break;
            case "Serializar":
                // Lógica para serializar
                break;
            case "Limpiar":
                // Lógica para limpiar
                break;
            default:
                break;
        }
    }
}
