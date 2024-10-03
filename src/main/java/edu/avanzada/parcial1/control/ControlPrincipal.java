package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.vista.VentanaBuscarArchivo;
import edu.avanzada.parcial1.vista.VentanaRegistrarRaza;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ControlPrincipal sirve como clase fachada para hacer las conexiones entre las
 * vistas y los controles
 * 
 * @author Juan, Ana, Samuel
 */
public class ControlPrincipal implements ActionListener{
    public VentanaRegistrarRaza vistaRegistrarRaza;
    public VentanaBuscarArchivo buscarArchivo;
    
    /**
     * Constructor de la clase
     */
    public ControlPrincipal(){
        buscarArchivo = new VentanaBuscarArchivo();
        
        vistaRegistrarRaza = new VentanaRegistrarRaza(this);
        vistaRegistrarRaza.BotonConsultar.addActionListener(this);
        vistaRegistrarRaza.BotonInsertar.addActionListener(this);
        vistaRegistrarRaza.BotonLimpiar.addActionListener(this);
        vistaRegistrarRaza.BotonModificar.addActionListener(this);
        vistaRegistrarRaza.BotonSerializar.addActionListener(this);
        vistaRegistrarRaza.BotonEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
