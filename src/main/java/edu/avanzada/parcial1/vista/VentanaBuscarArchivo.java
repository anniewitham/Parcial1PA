package edu.avanzada.parcial1.vista;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * Clase para buscar archivos a través de una ventana de selección.
 * @author avila
 */
public class VentanaBuscarArchivo {
    private JFileChooser buscarArchivo;
    
    

    /**
     * Abre un cuadro de diálogo para buscar un archivo y retorna la ruta del archivo seleccionado.
     * 
     * @return La ruta del archivo seleccionado como String, o null si no se seleccionó un archivo.
     */
    public String buscarArchivo() {
        buscarArchivo = new JFileChooser();
        
        // Mostrar el cuadro de diálogo y guardar el resultado
        int resultado = buscarArchivo.showOpenDialog(null);
        
        // Comprobar si se seleccionó un archivo
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = buscarArchivo.getSelectedFile(); // Obtener el archivo seleccionado
            return archivo.getAbsolutePath(); // Retornar la ruta absoluta del archivo
        } else {
            return null; // Retornar null si no se seleccionó ningún archivo
        }
    }
}

