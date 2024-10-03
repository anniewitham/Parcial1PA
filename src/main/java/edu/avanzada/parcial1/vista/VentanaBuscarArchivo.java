package edu.avanzada.parcial1.vista;

import javax.swing.*;
import java.io.File;

/**
 * Clase para buscar archivos a través de una ventana de selección.
 * Esta clase permite al usuario seleccionar un archivo de propiedades
 * y gestiona el cierre de la ventana para asegurar que se ha realizado
 * una selección.
 * 
 * @author avila
 */
public class VentanaBuscarArchivo {
    private JFileChooser buscarArchivo;
    private File archivoSeleccionado; // Almacena el archivo seleccionado

    /**
     * Constructor de la clase VentanaBuscarArchivo.
     */
    public VentanaBuscarArchivo() {
        buscarArchivo = new JFileChooser();
    }

    /**
     * Abre un cuadro de diálogo para buscar un archivo y retorna la ruta del archivo seleccionado.
     * 
     * @return La ruta del archivo seleccionado como String, o null si no se seleccionó un archivo.
     */
    public String buscarArchivo() {
        buscarArchivo.setDialogTitle("Seleccionar archivo de propiedades"); // Título del cuadro de diálogo

        // Mostrar el cuadro de diálogo y guardar el resultado
        int resultado = buscarArchivo.showOpenDialog(null);
        
        // Comprobar si se seleccionó un archivo
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = buscarArchivo.getSelectedFile(); // Obtener el archivo seleccionado
            return archivoSeleccionado.getAbsolutePath(); // Retornar la ruta absoluta del archivo
        } else {
            archivoSeleccionado = null; // Restablecer archivo seleccionado
            return null; // Retornar null si no se seleccionó ningún archivo
        }
    }

    /**
     * Verifica si se ha seleccionado un archivo.
     *
     * @return true si se ha seleccionado un archivo, false en caso contrario.
     */
    public boolean isArchivoSeleccionado() {
        return archivoSeleccionado != null;
    }
}
