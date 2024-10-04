package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.Conexion;
import edu.avanzada.parcial1.modelo.RazaDAO;
import edu.avanzada.parcial1.modelo.RazaVO;
import edu.avanzada.parcial1.vista.VentanaBuscarArchivo;
import edu.avanzada.parcial1.vista.VentanaActualizar;
import edu.avanzada.parcial1.vista.VentanaEmergente;
import edu.avanzada.parcial1.vista.VentanaRegistrarRaza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ControlPrincipal implements ActionListener {
    private VentanaRegistrarRaza vistaRegistrarRaza;
    private VentanaBuscarArchivo buscarArchivo;
    private VentanaEmergente ventanaEmergente;
    private VentanaActualizar ventanaCompletar;
    private RazaDAO razaDAO;
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

                    if (urlBD == null || usuario == null || contrasena == null) {
                        ventanaEmergente.ventanaError("Faltan propiedades en el archivo.");
                        continue;
                    }

                    conexion = new Conexion(urlBD, usuario, contrasena);
                    razaDAO = new RazaDAO(conexion.getConexion());
                    controlRaza = new ControlRaza(razaDAO);

                    if (!razaDAO.consultarExistencia()) {
                        controlRaza.cargarRazasDesdePropiedades(propiedades);
                        ventanaEmergente.ventanaAtencion("Datos precargados. Por favor, completa la información.");

                        List<RazaVO> razasIncompletas = controlRaza.obtenerRazasIncompletas();
                        if (!razasIncompletas.isEmpty()) {
                            for (RazaVO raza : razasIncompletas) {
                                ventanaEmergente.ventanaAtencion("Vas a actualizar la raza: " + raza.getNombre());

                                ventanaCompletar = new VentanaActualizar(this);
                                ventanaCompletar.TextCompletarNombre.setText(raza.getNombre());
                                ventanaCompletar.TextCompletarPais.setText(raza.getPaisOrigen());
                                ventanaCompletar.BotonActualizar.addActionListener(this);
                                ventanaCompletar.ComboBoxGrupo.setSelectedItem(raza.getGrupoFCI());
                                ventanaCompletar.ComboBoxSeccion.setSelectedItem(raza.getSeccionFCI());

                                ventanaCompletar.setVisible(true);
                                ventanaCompletar.addWindowListener(new WindowAdapter() {
                                    @Override
                                    public void windowClosing(WindowEvent e) {
                                        ventanaCompletar.dispose();
                                    }
                                });

                                while (ventanaCompletar.isShowing()) {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException ie) {
                                        Thread.currentThread().interrupt();
                                    }
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
            } catch (Exception e) {
                ventanaEmergente.ventanaError("Error inesperado: " + e.getMessage());
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Insertar":
                if (vistaRegistrarRaza.TextRaza.getText().isEmpty() ||
                        vistaRegistrarRaza.TextPais.getText().isEmpty() ||
                        vistaRegistrarRaza.TextApariencia.getText().isEmpty() ||
                        vistaRegistrarRaza.TextPelo.getText().isEmpty() ||
                        vistaRegistrarRaza.TextColor.getText().isEmpty() ||
                        vistaRegistrarRaza.TextEspalda.getText().isEmpty() ||
                        vistaRegistrarRaza.TextLomo.getText().isEmpty() ||
                        vistaRegistrarRaza.TextCola.getText().isEmpty() ||
                        vistaRegistrarRaza.TextPecho.getText().isEmpty()){
                    ventanaEmergente.ventanaAtencion("Por favor, ingresa todos los datos para la insercion de la raza.");
                } else {
                    try {
                        if(controlRaza.validarRazaCreada(vistaRegistrarRaza.TextRaza.getText().toString())){
                            ventanaEmergente.ventanaError("La raza "+vistaRegistrarRaza.TextRaza.getText()+" ya existe en la base de datos.");
                            break;
                        } else {
                            RazaVO raza = new RazaVO(
                                vistaRegistrarRaza.TextRaza.getText(),
                                vistaRegistrarRaza.TextPais.getText(),
                                vistaRegistrarRaza.GrupoFCI.getSelectedItem().toString(),
                                vistaRegistrarRaza.ComboBoxSeccion.getSelectedItem().toString(),
                                vistaRegistrarRaza.TextApariencia.getText(),
                                vistaRegistrarRaza.TextPelo.getText(),
                                vistaRegistrarRaza.TextColor.getText(),
                                vistaRegistrarRaza.TextEspalda.getText(),
                                vistaRegistrarRaza.TextLomo.getText(),
                                vistaRegistrarRaza.TextCola.getText(),
                                vistaRegistrarRaza.TextPecho.getText()
                            );
                        try {
                            controlRaza.insertarRaza(raza);
                            ventanaEmergente.ventanaPlana("Raza " + raza.getNombre() + " insertada correctamente");
                        } catch (SQLException ex) {
                            ventanaEmergente.ventanaError(ex.toString());
                        }
                    vistaRegistrarRaza.limpiar();
                    }
                } catch (SQLException ex) {
                }
            }
            break;

            case "Consultar":
                DefaultTableModel model = (DefaultTableModel) vistaRegistrarRaza.Tabla.getModel();
                model.setRowCount(0);

                if (!vistaRegistrarRaza.TextRaza.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPais.getText().isEmpty() &&
                        vistaRegistrarRaza.TextApariencia.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPelo.getText().isEmpty() &&
                        vistaRegistrarRaza.TextColor.getText().isEmpty() &&
                        vistaRegistrarRaza.TextEspalda.getText().isEmpty() &&
                        vistaRegistrarRaza.TextLomo.getText().isEmpty() &&
                        vistaRegistrarRaza.TextCola.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n" +
                            "- Nombre de raza\n" +
                            "- Grupo y sección FCI (predeterminado)\n" +
                            "- País de origen\n" +
                            "- Color de manto en común");
                        try {
                            List<RazaVO> lista = controlRaza.consultarRaza(1, vistaRegistrarRaza.TextRaza.getText());
                            agregarDatosATabla(lista);
                        } catch (SQLException ex) {
                            ventanaEmergente.ventanaError("Error al consultar la raza: " + ex.getMessage());
                        }
                } else if (vistaRegistrarRaza.TextRaza.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPais.getText().isEmpty() &&
                        vistaRegistrarRaza.TextApariencia.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPelo.getText().isEmpty() &&
                        vistaRegistrarRaza.TextColor.getText().isEmpty() &&
                        vistaRegistrarRaza.TextEspalda.getText().isEmpty() &&
                        vistaRegistrarRaza.TextLomo.getText().isEmpty() &&
                        vistaRegistrarRaza.TextCola.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n" +
                            "- Nombre de raza\n" +
                            "- Grupo y sección FCI (predeterminado)\n" +
                            "- País de origen\n" +
                            "- Color de manto en común");
                    try {
                        List<RazaVO> lista = controlRaza.consultarRaza(2,
                                vistaRegistrarRaza.GrupoFCI.getSelectedItem().toString() + "," +
                                vistaRegistrarRaza.ComboBoxSeccion.getSelectedItem().toString());
                        agregarDatosATabla(lista);
                    } catch (SQLException ex) {
                        ventanaEmergente.ventanaError("Error al consultar la raza: " + ex.getMessage());
                    }
                } else if (vistaRegistrarRaza.TextRaza.getText().isEmpty() &&
                        !vistaRegistrarRaza.TextPais.getText().isEmpty() &&
                        vistaRegistrarRaza.TextApariencia.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPelo.getText().isEmpty() &&
                        vistaRegistrarRaza.TextColor.getText().isEmpty() &&
                        vistaRegistrarRaza.TextEspalda.getText().isEmpty() &&
                        vistaRegistrarRaza.TextLomo.getText().isEmpty() &&
                        vistaRegistrarRaza.TextCola.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n" +
                            "- Nombre de raza\n" +
                            "- Grupo y sección FCI (predeterminado)\n" +
                            "- País de origen\n" +
                            "- Color de manto en común");
                    try {
                        List<RazaVO> lista = controlRaza.consultarRaza(3, vistaRegistrarRaza.TextPais.getText());
                        agregarDatosATabla(lista);
                    } catch (SQLException ex) {
                        ventanaEmergente.ventanaError("Error al consultar la raza: " + ex.getMessage());
                    }
                } else if (vistaRegistrarRaza.TextRaza.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPais.getText().isEmpty() &&
                        vistaRegistrarRaza.TextApariencia.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPelo.getText().isEmpty() &&
                        !vistaRegistrarRaza.TextColor.getText().isEmpty() &&
                        vistaRegistrarRaza.TextEspalda.getText().isEmpty() &&
                        vistaRegistrarRaza.TextLomo.getText().isEmpty() &&
                        vistaRegistrarRaza.TextCola.getText().isEmpty() &&
                        vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n" +
                            "- Nombre de raza\n" +
                            "- Grupo y sección FCI (predeterminado)\n" +
                            "- País de origen\n" +
                            "- Color de manto en común");
                    try {
                        List<RazaVO> lista = controlRaza.consultarRaza(4, vistaRegistrarRaza.TextColor.getText());
                        agregarDatosATabla(lista);
                    } catch (SQLException ex) {
                        ventanaEmergente.ventanaError("Error al consultar la raza: " + ex.getMessage());
                    }
                } else {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n" +
                            "- Nombre de raza\n" +
                            "- Grupo y sección FCI (predeterminado)\n" +
                            "- País de origen\n" +
                            "- Color de manto en común");
                }
                break;

            case "Modificar":
                int seleccionM = vistaRegistrarRaza.Tabla.getSelectedRow();
                if(seleccionM != -1){
                    try {
                        RazaVO raza = (RazaVO) controlRaza.consultarRaza(1, (String) vistaRegistrarRaza.Tabla.getValueAt(seleccionM, 0));
                        
                        ventanaCompletar = new VentanaActualizar(this);
                        ventanaCompletar.TextCompletarNombre.setText(raza.getNombre());
                        ventanaCompletar.TextCompletarPais.setText(raza.getPaisOrigen());
                        ventanaCompletar.ComboBoxGrupo.setSelectedItem(raza.getGrupoFCI());
                        ventanaCompletar.ComboBoxSeccion.setSelectedItem(raza.getSeccionFCI());
                        ventanaCompletar.TextCompletarApariencia.setText(raza.getApariencia());
                        ventanaCompletar.TextCompletarCola.setText(raza.getCola());
                        ventanaCompletar.TextCompletarColor.setText(raza.getColor());
                        ventanaCompletar.TextCompletarEspalda.setText(raza.getEspalda());
                        ventanaCompletar.TextCompletarLomo.setText(raza.getLomo());
                        ventanaCompletar.TextCompletarPecho.setText(raza.getPecho());
                        ventanaCompletar.TextCompletarPelo.setText(raza.getPelo());
                    } catch (SQLException ex) {
                    }
                    
                }
                break;

            case "Eliminar":
                int seleccion = vistaRegistrarRaza.Tabla.getSelectedRow();
                if(seleccion != -1) {
                    String nombre = (String) vistaRegistrarRaza.Tabla.getValueAt(seleccion, 0);
                    try {
                        controlRaza.eliminarRaza(nombre);
                        ((DefaultTableModel) vistaRegistrarRaza.Tabla.getModel()).removeRow(seleccion);
                        ventanaEmergente.ventanaPlana("Se acaba de eliminar la raza "+nombre+" de la base de datos.");
                    } catch (SQLException ex) {
                        }
                } else {
                    ventanaEmergente.ventanaAtencion("Debes seleccionar un registro para ser eliminado.");
                }
                break;

            case "Serializar":
                // Implementa la lógica de serialización aquí.
                ventanaEmergente.ventanaAtencion("Función de serializar no implementada.");
                break;

            case "Actualizar":
                    RazaVO razaa = new RazaVO(
                    ventanaCompletar.TextCompletarNombre.getText(),
                    ventanaCompletar.TextCompletarPais.getText(),
                    ventanaCompletar.ComboBoxGrupo.getSelectedItem().toString(),
                    ventanaCompletar.ComboBoxSeccion.getSelectedItem().toString(),
                    ventanaCompletar.TextCompletarApariencia.getText(),
                    ventanaCompletar.TextCompletarPelo.getText(),
                    ventanaCompletar.TextCompletarColor.getText(),
                    ventanaCompletar.TextCompletarEspalda.getText(),
                    ventanaCompletar.TextCompletarLomo.getText(),
                    ventanaCompletar.TextCompletarCola.getText(),
                    ventanaCompletar.TextCompletarPecho.getText()
                );
        
            try {
                controlRaza.actualizarRaza(razaa);
                ventanaCompletar.dispose();
                ventanaEmergente.ventanaPlana("Raza " + razaa.getNombre() + " actualizada correctamente");
            } catch (SQLException ex) {
                ventanaEmergente.ventanaError("Error al actualizar: " + ex.getMessage());
            }

        // Verifica si hay más razas incompletas después de la actualización.
        List<RazaVO> razasIncompletas = null;
            try {
                razasIncompletas = controlRaza.obtenerRazasIncompletas();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (!razasIncompletas.isEmpty()) {
            for (RazaVO raza : razasIncompletas) {
                ventanaEmergente.ventanaAtencion("Vas a actualizar la raza: " + raza.getNombre());

                // Crear nueva instancia de VentanaActualizar
                ventanaCompletar = new VentanaActualizar(this);
                ventanaCompletar.TextCompletarNombre.setText(raza.getNombre());
                ventanaCompletar.TextCompletarPais.setText(raza.getPaisOrigen());
                ventanaCompletar.ComboBoxGrupo.setSelectedItem(raza.getGrupoFCI());
                ventanaCompletar.ComboBoxSeccion.setSelectedItem(raza.getSeccionFCI());

                // Agregar ActionListener solo una vez
                ventanaCompletar.BotonActualizar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Implementar la lógica de actualización aquí
                        // Asegurarse de que no se abra otra ventana hasta que esta se cierre
                        ventanaCompletar.dispose(); // Cerrar la ventana actual
                    }
                });

                // Mostrar la ventana
                ventanaCompletar.setVisible(true);

                // Espera a que la ventana se cierre
                while (ventanaCompletar.isShowing()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    
    break;

        }
    }
    
    private void agregarDatosATabla(List<RazaVO> lista) {
        DefaultTableModel model = (DefaultTableModel) vistaRegistrarRaza.Tabla.getModel();
        model.setRowCount(0);

        for (RazaVO raza : lista) {
            Object[] rowData = {
                raza.getNombre(),
                raza.getPaisOrigen(),
                raza.getGrupoFCI(),
                raza.getSeccionFCI(),
                raza.getApariencia(),
                raza.getPelo(),
                raza.getColor(),
                raza.getEspalda(),
                raza.getLomo(),
                raza.getCola(),
                raza.getPecho()
            };
            model.addRow(rowData);
        }
    }
}