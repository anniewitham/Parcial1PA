package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.ArchivoAleatorio;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * ControlPrincipal es la clase que maneja la lógica principal de la aplicación.
 * Esta clase actúa como un controlador en el patrón MVC, gestionando la interacción 
 * entre la vista y el modelo, así como el flujo de la aplicación. Proporciona 
 * funcionalidades para registrar, consultar, modificar, eliminar, y serializar 
 * razas de perros a través de una interfaz gráfica.
 * @author Juan, Ana, Samuel
 */
public class ControlPrincipal implements ActionListener {

    protected VentanaRegistrarRaza vistaRegistrarRaza;
    protected VentanaBuscarArchivo buscarArchivo;
    protected VentanaEmergente ventanaEmergente;
    protected VentanaActualizar ventanaCompletar;
    protected RazaDAO razaDAO;
    protected Conexion conexion;
    protected ControlRaza controlRaza;
    protected ArchivoAleatorio archivoAleatorio;

    /**
     * Constructor de ControlPrincipal que inicializa las vistas, la conexión a la 
     * base de datos, y carga las propiedades necesarias para el funcionamiento de la 
     * aplicación.
     * 
     * @throws SQLException Si hay un error al establecer la conexión a la base de datos.
     */
    public ControlPrincipal() throws SQLException {
        ventanaEmergente = new VentanaEmergente();
        buscarArchivo = new VentanaBuscarArchivo();
        archivoAleatorio = new ArchivoAleatorio();

        vistaRegistrarRaza = new VentanaRegistrarRaza(this);
        vistaRegistrarRaza.BotonConsultar.addActionListener(this);
        vistaRegistrarRaza.BotonInsertar.addActionListener(this);
        vistaRegistrarRaza.BotonLimpiar.addActionListener(this);
        vistaRegistrarRaza.BotonModificar.addActionListener(this);
        vistaRegistrarRaza.BotonSerializar.addActionListener(this);
        vistaRegistrarRaza.BotonEliminar.addActionListener(this);
        vistaRegistrarRaza.BotonEliminar.addActionListener(this);

        cargarPropiedades();
    }

    /**
     * Carga las propiedades de la base de datos desde un archivo seleccionado por el usuario.
     * Establece la conexión a la base de datos y crea instancias de los objetos necesarios 
     * para la gestión de razas. Si no hay razas registradas, se cargan razas desde las 
     * propiedades del archivo y se muestran ventanas de actualización para completar la información.
     */
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
                    controlRaza = new ControlRaza(razaDAO, this);

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

    
    /**
     * Maneja los eventos de acción de los botones en la interfaz gráfica.
     * Dependiendo del comando recibido, llama a los métodos apropiados para 
     * insertar, consultar, modificar, eliminar, serializar razas o actualizar información.
     * 
     * @param e El evento de acción que contiene información sobre la interacción del usuario.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Insertar":
                if (vistaRegistrarRaza.TextRaza.getText().isEmpty()
                        || vistaRegistrarRaza.TextPais.getText().isEmpty()
                        || vistaRegistrarRaza.TextApariencia.getText().isEmpty()
                        || vistaRegistrarRaza.TextPelo.getText().isEmpty()
                        || vistaRegistrarRaza.TextColor.getText().isEmpty()
                        || vistaRegistrarRaza.TextEspalda.getText().isEmpty()
                        || vistaRegistrarRaza.TextLomo.getText().isEmpty()
                        || vistaRegistrarRaza.TextCola.getText().isEmpty()
                        || vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Por favor, ingresa todos los datos para la insercion de la raza.");
                } else {
                    try {
                        if (controlRaza.validarRazaCreada(vistaRegistrarRaza.TextRaza.getText().toString())) {
                            ventanaEmergente.ventanaError("La raza " + vistaRegistrarRaza.TextRaza.getText() + " ya existe en la base de datos.");
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

                if (!vistaRegistrarRaza.TextRaza.getText().isEmpty()
                        && vistaRegistrarRaza.TextPais.getText().isEmpty()
                        && vistaRegistrarRaza.TextApariencia.getText().isEmpty()
                        && vistaRegistrarRaza.TextPelo.getText().isEmpty()
                        && vistaRegistrarRaza.TextColor.getText().isEmpty()
                        && vistaRegistrarRaza.TextEspalda.getText().isEmpty()
                        && vistaRegistrarRaza.TextLomo.getText().isEmpty()
                        && vistaRegistrarRaza.TextCola.getText().isEmpty()
                        && vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n"
                            + "- Nombre de raza\n"
                            + "- Grupo y sección FCI (predeterminado)\n"
                            + "- País de origen\n"
                            + "- Color de manto en común");
                    try {
                        List<RazaVO> lista = controlRaza.consultarRaza(1, vistaRegistrarRaza.TextRaza.getText());
                        agregarDatosATabla(lista);
                    } catch (SQLException ex) {
                        ventanaEmergente.ventanaError("Error al consultar la raza: " + ex.getMessage());
                    }
                } else if (vistaRegistrarRaza.TextRaza.getText().isEmpty()
                        && vistaRegistrarRaza.TextPais.getText().isEmpty()
                        && vistaRegistrarRaza.TextApariencia.getText().isEmpty()
                        && vistaRegistrarRaza.TextPelo.getText().isEmpty()
                        && vistaRegistrarRaza.TextColor.getText().isEmpty()
                        && vistaRegistrarRaza.TextEspalda.getText().isEmpty()
                        && vistaRegistrarRaza.TextLomo.getText().isEmpty()
                        && vistaRegistrarRaza.TextCola.getText().isEmpty()
                        && vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n"
                            + "- Nombre de raza\n"
                            + "- Grupo y sección FCI (predeterminado)\n"
                            + "- País de origen\n"
                            + "- Color de manto en común");
                    try {
                        List<RazaVO> lista = controlRaza.consultarRaza(2,
                                vistaRegistrarRaza.GrupoFCI.getSelectedItem().toString() + ","
                                + vistaRegistrarRaza.ComboBoxSeccion.getSelectedItem().toString());
                        agregarDatosATabla(lista);
                    } catch (SQLException ex) {
                        ventanaEmergente.ventanaError("Error al consultar la raza: " + ex.getMessage());
                    }
                } else if (vistaRegistrarRaza.TextRaza.getText().isEmpty()
                        && !vistaRegistrarRaza.TextPais.getText().isEmpty()
                        && vistaRegistrarRaza.TextApariencia.getText().isEmpty()
                        && vistaRegistrarRaza.TextPelo.getText().isEmpty()
                        && vistaRegistrarRaza.TextColor.getText().isEmpty()
                        && vistaRegistrarRaza.TextEspalda.getText().isEmpty()
                        && vistaRegistrarRaza.TextLomo.getText().isEmpty()
                        && vistaRegistrarRaza.TextCola.getText().isEmpty()
                        && vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n"
                            + "- Nombre de raza\n"
                            + "- Grupo y sección FCI (predeterminado)\n"
                            + "- País de origen\n"
                            + "- Color de manto en común");
                    try {
                        List<RazaVO> lista = controlRaza.consultarRaza(3, vistaRegistrarRaza.TextPais.getText());
                        agregarDatosATabla(lista);
                    } catch (SQLException ex) {
                        ventanaEmergente.ventanaError("Error al consultar la raza: " + ex.getMessage());
                    }
                } else if (vistaRegistrarRaza.TextRaza.getText().isEmpty()
                        && vistaRegistrarRaza.TextPais.getText().isEmpty()
                        && vistaRegistrarRaza.TextApariencia.getText().isEmpty()
                        && vistaRegistrarRaza.TextPelo.getText().isEmpty()
                        && !vistaRegistrarRaza.TextColor.getText().isEmpty()
                        && vistaRegistrarRaza.TextEspalda.getText().isEmpty()
                        && vistaRegistrarRaza.TextLomo.getText().isEmpty()
                        && vistaRegistrarRaza.TextCola.getText().isEmpty()
                        && vistaRegistrarRaza.TextPecho.getText().isEmpty()) {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n"
                            + "- Nombre de raza\n"
                            + "- Grupo y sección FCI (predeterminado)\n"
                            + "- País de origen\n"
                            + "- Color de manto en común");
                    try {
                        List<RazaVO> lista = controlRaza.consultarRaza(4, vistaRegistrarRaza.TextColor.getText());
                        agregarDatosATabla(lista);
                    } catch (SQLException ex) {
                        ventanaEmergente.ventanaError("Error al consultar la raza: " + ex.getMessage());
                    }
                } else {
                    ventanaEmergente.ventanaAtencion("Recuerde que se puede consultar por (SOLO UNO):\n"
                            + "- Nombre de raza\n"
                            + "- Grupo y sección FCI (predeterminado)\n"
                            + "- País de origen\n"
                            + "- Color de manto en común");
                }
                break;

            case "Modificar":
                int seleccionM = vistaRegistrarRaza.Tabla.getSelectedRow();
                if (seleccionM != -1) {
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
                if (seleccion != -1) {
                    String nombre = (String) vistaRegistrarRaza.Tabla.getValueAt(seleccion, 0);
                    try {
                        controlRaza.eliminarRaza(nombre);
                        ((DefaultTableModel) vistaRegistrarRaza.Tabla.getModel()).removeRow(seleccion);
                        ventanaEmergente.ventanaPlana("Se acaba de eliminar la raza " + nombre + " de la base de datos.");
                    } catch (SQLException ex) {
                    }
                } else {
                    ventanaEmergente.ventanaAtencion("Debes seleccionar un registro para ser eliminado.");
                }
                break;

            case "Serializar":
                try {
                    controlRaza.serializarRazas();
                } catch (IOException ex) {
                   ventanaEmergente.ventanaError("Error al serializar las razas: " + ex.getMessage());
                }
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

                List<RazaVO> razasIncompletas = null;
                try {
                    razasIncompletas = controlRaza.obtenerRazasIncompletas();
                } catch (SQLException ex) {
                    Logger.getLogger(ControlPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!razasIncompletas.isEmpty()) {
                    for (RazaVO raza : razasIncompletas) {
                        ventanaEmergente.ventanaAtencion("Vas a actualizar la raza: " + raza.getNombre());
                        
                        ventanaCompletar = new VentanaActualizar(this);
                        ventanaCompletar.TextCompletarNombre.setText(raza.getNombre());
                        ventanaCompletar.TextCompletarPais.setText(raza.getPaisOrigen());
                        ventanaCompletar.ComboBoxGrupo.setSelectedItem(raza.getGrupoFCI());
                        ventanaCompletar.ComboBoxSeccion.setSelectedItem(raza.getSeccionFCI());
                        
                        ventanaCompletar.BotonActualizar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ventanaCompletar.dispose();
                            }
                        });

                        ventanaCompletar.setVisible(true);

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
            case "Salir":
                try {

                    persistirRazas();
                } catch (IOException ex) {
                }
                System.exit(0);
                break;

        }
    }

    
    private void persistirRazas() throws IOException {
        archivoAleatorio.abrirArchivo();
        List<String> razasSerializadas = new ArrayList<>();
        // Agrega cadenas de texto a la lista
        archivoAleatorio.persistirRazas(razasSerializadas);
        archivoAleatorio.cerrarArchivo();
    }

    /**
    * Obtiene la instancia de la ventana emergente asociada a la clase.
    * 
    * @return ventanaEmergente La instancia de la clase VentanaEmergente.
    */
    public VentanaEmergente getVentanaEmergente() {
        return ventanaEmergente;
    }

    /**
    * Agrega los datos de una lista de objetos RazaVO a la tabla de la vista.
    * 
    * Este método actualiza el modelo de la tabla eliminando las filas actuales
    * y añadiendo nuevas filas con la información de cada RazaVO de la lista 
    * proporcionada.
    * 
    * @param lista Lista de objetos RazaVO que contiene la información a mostrar 
    *              en la tabla. Cada objeto RazaVO se utiliza para llenar 
    *              una fila de la tabla con sus atributos correspondientes.
    */
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
