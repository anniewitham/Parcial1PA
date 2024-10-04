package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.Conexion;
import edu.avanzada.parcial1.modelo.RazaDAO;
import edu.avanzada.parcial1.vista.VentanaBuscarArchivo;
import edu.avanzada.parcial1.vista.VentanaEmergente;
import edu.avanzada.parcial1.vista.VentanaRegistrarRaza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlPrincipal implements ActionListener {
    public VentanaRegistrarRaza vistaRegistrarRaza;
    public VentanaBuscarArchivo buscarArchivo;
    public VentanaEmergente ventanaEmergente;
    public RazaDAO razaDAO;
    private Conexion conexion;
    private ControlRaza controlRaza;

    public ControlPrincipal() throws SQLException {
        ventanaEmergente = new VentanaEmergente();
        buscarArchivo = new VentanaBuscarArchivo();
        vistaRegistrarRaza = new VentanaRegistrarRaza(this);
        
        vistaRegistrarRaza.BotonConsultar.addActionListener(this);
        vistaRegistrarRaza.BotonInsertar.addActionListener(this);
        vistaRegistrarRaza.BotonLimpiar.addActionListener(this);
        vistaRegistrarRaza.BotonModificar.addActionListener(this);
        vistaRegistrarRaza.BotonSerializar.addActionListener(this);
        vistaRegistrarRaza.BotonEliminar.addActionListener(this);

        cargarPropiedades();
    }

    private void cargarPropiedades() {
        boolean archivoSeleccionado = false;

        while (!archivoSeleccionado) {
            try {
                String rutaArchivo = buscarArchivo.buscarArchivo();
                
                if (buscarArchivo.isArchivoSeleccionado()) {
                    Properties propiedades = new Properties();
                    propiedades.load(new FileInputStream(rutaArchivo));

                    String urlBD = propiedades.getProperty("URLBD");
                    String usuario = propiedades.getProperty("usuario");
                    String contrasena = propiedades.getProperty("contrasena");

                    conexion = new Conexion(urlBD, usuario, contrasena);
                    razaDAO = new RazaDAO(conexion.getConexion());
                    controlRaza = new ControlRaza(this, razaDAO);

                    if (!razaDAO.consultarExistencia()) {
                        controlRaza.cargarRazasDesdePropiedades(propiedades);
                        ventanaEmergente.ventanaAtencion("Datos precargados. Por favor, completa la información.");
                    }

                    archivoSeleccionado = true;
                } else {
                    ventanaEmergente.ventanaAtencion("Debes seleccionar un archivo de propiedades.");
                }
            } catch (IOException e) {
                ventanaEmergente.ventanaError("Error al cargar propiedades: " + e.getMessage());
                archivoSeleccionado = true;
            } catch (SQLException e) {
                ventanaEmergente.ventanaError("Error al acceder a la base de datos: " + e.getMessage());
                archivoSeleccionado = true;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Insertar":
                //controlRaza.insertarRaza(vistaRegistrarRaza.obtenerDatosRaza());
                break;
            case "Consultar":
                //List<RazaVO> razas = controlRaza.consultarRazas();
                //vistaRegistrarRaza.mostrarRazas(razas);
                break;
            case "Eliminar":
                //controlRaza.eliminarRaza(vistaRegistrarRaza.obtenerRazaSeleccionada());
                break;
            case "Modificar":
                //controlRaza.modificarRaza(vistaRegistrarRaza.obtenerDatosRaza());
                break;
            case "Serializar":
            {
                try {
                    controlRaza.serializarRazas();
                } catch (IOException ex) {
                   ventanaEmergente.ventanaError("Error al serializar las razas: " + ex.getMessage());
                }
            }
                
                break;

            case "Limpiar":
                //vistaRegistrarRaza.limpiarCampos();
                break;
            default:
                break;
        }
    }
}
