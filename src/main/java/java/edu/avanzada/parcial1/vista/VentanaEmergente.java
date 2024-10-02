package java.edu.avanzada.parcial1.vista;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Ventana emergente. Clase encargada de mostrar mensajes de error.
 * @lastModified  28/09/2024
 */
public class VentanaEmergente {

    /**
     * Ventana error. Muestra errores
     *
     * @param mensaje
     */
    public void ventanaError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Ventana atención. Muestra mensajes de alerta
     *
     * @param mensaje
     */
    public void ventanaAtención(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Atención", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Ventana plana. Muestra mensajes simples
     *
     * @param mensaje
     */
    public void ventanaPlana(String mensaje) {
        Icon o = new ImageIcon(getClass().getResource("/Vista/oso.png"));
        JOptionPane.showMessageDialog(null, mensaje, " ", JOptionPane.PLAIN_MESSAGE, o);
    }
}
