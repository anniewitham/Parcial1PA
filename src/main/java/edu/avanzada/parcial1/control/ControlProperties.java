package edu.avanzada.parcial1.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ControlProperties es la clase encargada de gestionar la carga y 
 * recuperación de datos desde un archivo properties.
 * 
 * @author Juan, Samuel, Ana
 */
public class ControlProperties {
    private Properties propiedades;

    public ControlProperties() {
        propiedades = new Properties();
    }

    /**
     * Carga las propiedades desde el archivo indicado.
     * 
     * @param rutaArchivo la ruta al archivo properties.
     * @throws IOException si hay problemas al leer el archivo.
     */
    public void cargarPropiedades(String rutaArchivo) throws IOException {
        if (rutaArchivo == null || rutaArchivo.isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacía.");
        }

        try (FileInputStream input = new FileInputStream(rutaArchivo)) {
            propiedades.load(input);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("El archivo de propiedades no se encontró: " + rutaArchivo);
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo de propiedades: " + e.getMessage());
        }
    }

    /**
     * Retorna los valores de las razas escritas en el archivo properties.
     * 
     * @param clave indica la clave de la raza que se quiere retornar.
     * @return un arreglo con los datos de la raza (nombre y país), o null si no se encuentra.
     */
    public String[] obtenerRaza(String clave) {
        String valorRaza = propiedades.getProperty(clave);
        
        if (valorRaza != null) {
            return valorRaza.split(","); // Separa el nombre y el país
        }
        return null; // Retorna null si no se encuentra la raza
    }

    /**
     * Retorna el valor de una credencial específica del archivo properties.
     * 
     * @param clave la clave de la credencial que se desea obtener.
     * @return el valor de la credencial, o null si no se encuentra.
     */
    public String obtenerCredencial(String clave) {
        return propiedades.getProperty(clave);
    }
}
