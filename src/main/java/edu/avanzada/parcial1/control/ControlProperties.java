package edu.avanzada.parcial1.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ControlProperties es la clase encargada de gestionar los E/L del archivo
 * properties
 * @author Juan, Samuel, Ana
 * @author avila
 */
public class ControlProperties {
    private Properties propiedades;
    private ControlPrincipal control;
    
    public ControlProperties() {
        propiedades = new Properties();
        control = new ControlPrincipal();
    }
    
    /**
     * Genera la conexion con el documento properties
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void cargarPropiedades() throws FileNotFoundException, IOException {
        try (FileInputStream input = new FileInputStream(control.buscarArchivo.buscarArchivo())){
            propiedades.load(input);
        }
    }
    
    /**
     * Retorna los valores de las razas escritas en el properties
     * 
     * @param clave indica el indice de la raza que se quiere retornar
     * @return retorna la raza encontrada
     */
    public String[] obtenerRaza(String clave) {
        String claveRaza = clave;
        String valorRaza = propiedades.getProperty(claveRaza);
        
        if (valorRaza != null) {
            String[] datosRaza = valorRaza.split(","); // Separa el nombre y el país
            
            return datosRaza;
        }
        return null;// Retorna null si no se encuentra la raza
    }
    
    public String obtenerCredencial(String _clave){
        String clave = _clave;
        String credencial = propiedades.getProperty(clave);
        return credencial;
    }
}
