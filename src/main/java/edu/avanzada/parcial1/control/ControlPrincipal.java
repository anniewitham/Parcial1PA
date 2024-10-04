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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

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

                        List<RazaVO> razasIncompletas = controlRaza.obtenerRazasIncompletas();
                        if (!razasIncompletas.isEmpty()) {
                            for (RazaVO raza : razasIncompletas) {
                                ventanaCompletar = new VentanaCompletar(this);
                                ventanaCompletar.BotonActualizar.addActionListener(this);
                                ventanaCompletar.ID.setText(Integer.toString(raza.getID()));
                                ventanaCompletar.ComboBoxGrupo.setSelectedItem(raza.getGrupoFCI());
                                ventanaCompletar.ComboBoxSeccion.setSelectedItem(raza.getSeccionFCI());

                                ventanaCompletar.setVisible(true);
                                while (ventanaCompletar.isVisible()) {
                                    Thread.sleep(100);
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
                try {
                    controlRaza.insertarRaza(raza);
                    ventanaEmergente.ventanaPlana("Raza " + raza.getNombre() + " insertada correctamente");
                } catch (SQLException ex) {
                    ventanaEmergente.ventanaError(ex.toString());
                }
                vistaRegistrarRaza.limpiar();
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
                    System.out.println("a");
                        try {
                            System.out.println("a");
                            List<RazaVO> lista = controlRaza.consultarRaza(1, vistaRegistrarRaza.TextRaza.getText());
                            System.out.println("Lista de razas consultadas: " + lista.size());
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
                                vistaRegistrarRaza.SeccionFCI.getSelectedItem().toString());
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
                // Implementa la lógica de modificación aquí.
                ventanaEmergente.ventanaAtencion("Función de modificar no implementada.");
                break;

            case "Eliminar":
                // Implementa la lógica de eliminación aquí.
                ventanaEmergente.ventanaAtencion("Función de eliminar no implementada.");
                break;

            case "Serializar":
                // Implementa la lógica de serialización aquí.
                ventanaEmergente.ventanaAtencion("Función de serializar no implementada.");
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
            case "Limpiar":
                vistaRegistrarRaza.limpiar();
                break;
            default:
                ventanaEmergente.ventanaAtencion("Acción no reconocida.");
                break;
        }
    }
    
    private void agregarDatosATabla(List<RazaVO> lista) {
        System.out.println("Lista de razas consultadas: " + lista.size());
        DefaultTableModel model = (DefaultTableModel) vistaRegistrarRaza.Tabla.getModel();
        model.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos

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
