package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.Conexion;
import edu.avanzada.parcial1.modelo.RazaDAO;
import edu.avanzada.parcial1.modelo.RazaVO;
import edu.avanzada.parcial1.vista.VentanaBuscarArchivo;
import edu.avanzada.parcial1.vista.VentanaCompletar;
import edu.avanzada.parcial1.vista.VentanaEmergente;
import edu.avanzada.parcial1.vista.VentanaRegistrarRaza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ControlPrincipal implements ActionListener {
    protected VentanaRegistrarRaza vistaRegistrarRaza;
    protected VentanaBuscarArchivo buscarArchivo;
    protected VentanaEmergente ventanaEmergente;
    protected RazaDAO razaDAO;
    protected Conexion conexion;
    protected ControlRaza controlRaza;
    protected VentanaCompletar ventanaCompletar;
  

    public ControlPrincipal() throws SQLException {
        ventanaEmergente = new VentanaEmergente();
        buscarArchivo = new VentanaBuscarArchivo();
        vistaRegistrarRaza = new VentanaRegistrarRaza(this);


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
                controlRaza = new ControlRaza(razaDAO);

                if (!razaDAO.consultarExistencia()) {
                    controlRaza.cargarRazasDesdePropiedades(propiedades);
                    ventanaEmergente.ventanaAtencion("Datos precargados. Por favor, completa la información.");

                    // Verificar si hay razas incompletas
                    List<RazaVO> razasIncompletas = controlRaza.obtenerRazasIncompletas();
                    if (!razasIncompletas.isEmpty()) {
                        // Mostrar ventana de completar una por una
                        for (RazaVO raza : razasIncompletas) {
                            ventanaCompletar = new VentanaCompletar(this); // Crear la ventana
                            ventanaCompletar.BotonActualizar.addActionListener(this);
                            ventanaCompletar.ID.setText(Integer.toString(raza.getID())); // Cargar la ID
                            ventanaCompletar.ComboBoxGrupo.setSelectedItem(raza.getGrupoFCI()); // Cargar los valores
                            ventanaCompletar.ComboBoxSeccion.setSelectedItem(raza.getSeccionFCI());

                            // Mostrar ventana y esperar hasta que se cierre
                            ventanaCompletar.setVisible(true);

                            // Esperar hasta que la ventana se cierre para continuar
                            while (ventanaCompletar.isVisible()) {
                                Thread.sleep(100); // Esperar un momento antes de verificar de nuevo
                            }
                        }
                    }
                } else {
                    ventanaEmergente.ventanaAtencion("Los datos ya fueron precargados en una ocasión anterior.");
                    vistaRegistrarRaza.setVisible(true);
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
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Insertar":
                    RazaVO raza = new RazaVO(
                            vistaRegistrarRaza.TextRaza.getText(),
                            vistaRegistrarRaza.TextPais.getText(),
                            vistaRegistrarRaza.GrupoFCI.getSelectedItem().toString(),
                            vistaRegistrarRaza.SeccionFCI.getSelectedItem().toString(),
                            vistaRegistrarRaza.TextApariencia.getText(),
                            vistaRegistrarRaza.TextPelo.getText(),
                            vistaRegistrarRaza.TextColor.getText(),
                            vistaRegistrarRaza.TextEspalda.getText(),
                            vistaRegistrarRaza.TextLomo.getText(),
                            vistaRegistrarRaza.TextCola.getText(),
                            vistaRegistrarRaza.TextPecho.getText()
                    );
            {
                try {
                    controlRaza.insertarRaza(raza);
                    ventanaEmergente.ventanaPlana("Raza "+raza.getNombre()+" insertada correctamenrte");
                } catch (SQLException ex) {
                    ventanaEmergente.ventanaError(ex.toString());
                }
            }
                vistaRegistrarRaza.limpiar();
                break;

            case "Consultar":
                // Aquí va la lógica para consultar razas.
                break;
            case "Eliminar":
                // Aquí va la lógica para eliminar una raza.
                break;
            case "Modificar":
                // Aquí va la lógica para modificar una raza.
                break;
            case "Serializar":
                // Aquí va la lógica para serializar los datos.
                break;
            case "Limpiar":
                // Aquí va la lógica para limpiar los campos de la interfaz.
                break;
            case "Actualizar":
                // Aquí va la lógica para actualizar las razas desde VentanaCompletar.
                try {
                    controlRaza.completarRaza(
                            ventanaCompletar.ComboBoxGrupo.getSelectedItem().toString(),
                            ventanaCompletar.ComboBoxSeccion.getSelectedItem().toString(),
                            ventanaCompletar.TextCompletarApariencia.getText(),
                            ventanaCompletar.TextCompletarPelo.getText(),
                            ventanaCompletar.TextCompletarColor.getText(),
                            ventanaCompletar.TextCompletarEspalda.getText(),
                            ventanaCompletar.TextCompletarLomo.getText(),
                            ventanaCompletar.TextCompletarCola.getText(),
                            ventanaCompletar.TextCompletarPecho.getText(),
                            Integer.parseInt(ventanaCompletar.ID.getText())
                    );
                } catch (SQLException ex) {
                    ventanaEmergente.ventanaError("Error al actualizar la raza: " + ex.getMessage());
                }
                break;
            default:
                break;
        }
    }
}
