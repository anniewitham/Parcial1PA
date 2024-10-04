
package edu.avanzada.parcial1.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * Clase responsable de la serialización de objetos en un archivo.
 *
 * @lastModified 03/10/2024
 * @author Samuel, Ana, Juan
 */
public class Serializacion {

//Canal de salida para escribir en el archivo de serializacion
    private FileOutputStream fileOut;
    private ObjectOutputStream salida;

    /**
     * Constructor de la clase Serializacion. Inicializa el canal de salida y el
     * objeto de salida para escribir en el archivo de serialización.
     */
    public Serializacion() throws FileNotFoundException, IOException {
        try {
            // Crear la carpeta "data" si no existe
            File directorio = new File("data");
            if (!directorio.exists()) {
                directorio.mkdirs();  // Crea la carpeta "data"
            }

            // Ruta completa del archivo en la carpeta "data"
            String rutaArchivo = "data/razas.bin";

            // ESCRIBIR
            fileOut = new FileOutputStream(rutaArchivo);
            salida = new ObjectOutputStream(fileOut);
        } catch (IOException ex) {
            System.err.println("Error al inicializar el archivo de serialización: " + ex.getMessage());
        }
    }

    /**
     * Cierra el archivo de serialización y libera los recursos asociados.
     */
    public void cerrarArchSerializado() {
        if (salida != null) {
            try {
                salida.close();
            } catch (IOException ex) {

            }
        }
    }

    /**
     * Escribe una lista de objetos RazaVO en el archivo de serialización.
     *
     * @param razas la lista de objetos RazaVO a escribir en el archivo de
     * serialización.
     */
    public void escribirArchSerializado(ArrayList<RazaVO> razas) {
        if (salida != null) {
            try {
                salida.writeObject(razas);
            } catch (IOException ex) {

            }
        }
    }
}
