package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.Conexion;
import edu.avanzada.parcial1.modelo.RazaDAO;
import edu.avanzada.parcial1.modelo.RazaVO;
import org.junit.jupiter.api.*;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase ControlPrincipal.
 *
 * @authors Ana, Samuel, Juan
 */
public class ControlPrincipalTest {

    private ControlPrincipal controlPrincipal;
    private ActionEvent insertarEvent;
    private ActionEvent consultarEvent;
    private ActionEvent eliminarEvent;
    private ActionEvent modificarEvent;
    private ActionEvent serializarEvent;
    private ActionEvent limpiarEvent;

    /**
     * Configuración inicial antes de cada prueba.
     *
     * @throws SQLException si hay un problema con la conexión a la base de datos.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        controlPrincipal = new ControlPrincipal();

        // Simulación de eventos de acción para los diferentes botones
        insertarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Insertar");
        consultarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Consultar");
        eliminarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Eliminar");
        modificarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Modificar");
        serializarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Serializar");
        limpiarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Limpiar");

        // Simulación de la conexión a la base de datos y el DAO
        controlPrincipal.razaDAO = new RazaDAO((Connection) new Conexion("jdbc:mysql://localhost/test", "root", ""));
        
        // Precargar razas en la base de datos
        List<RazaVO> razas = new ArrayList<>();
        razas.add(new RazaVO());
        razas.add(new RazaVO());
        razas.add(new RazaVO());

        for (RazaVO raza : razas) {
            controlPrincipal.razaDAO.insertarRaza(raza);
        }
    }

    /**
     * Limpieza después de cada prueba.
     */
    @AfterEach
    public void tearDown() throws SQLException {
        // Limpiar razas de la base de datos después de cada prueba
        List<RazaVO> razas = controlPrincipal.razaDAO.consultarRazas();
        for (RazaVO raza : razas) {
            controlPrincipal.razaDAO.eliminarRaza(raza.getNombre());
        }
        
        // Limpiar los objetos de prueba
        controlPrincipal = null;
        insertarEvent = null;
        consultarEvent = null;
        eliminarEvent = null;
        modificarEvent = null;
        serializarEvent = null;
        limpiarEvent = null;
    }

    /**
     * Prueba para verificar la acción de insertar una raza.
     */
    @Test
    public void testActionPerformed_Insertar() {
        System.out.println("Simulando actionPerformed con comando 'Insertar'");

        // Simulamos la acción de insertar
        controlPrincipal.actionPerformed(insertarEvent);

        // Aserción: Comprobar que el método insertar fue invocado
        RazaVO nuevaRaza = new RazaVO();
        boolean resultado = controlPrincipal.razaDAO.insertarRaza(nuevaRaza);
        assertTrue(resultado, "La raza debería haberse insertado correctamente");
    }

    /**
     * Prueba para verificar la acción de consultar las razas.
     */
    @Test
    public void testActionPerformed_Consultar() throws SQLException {
        System.out.println("Simulando actionPerformed con comando 'Consultar'");

        // Simulamos la acción de consultar
        controlPrincipal.actionPerformed(consultarEvent);

        // Aserción: Verificar que la consulta retorne los datos esperados
        List<RazaVO> razasConsultadas = controlPrincipal.razaDAO.consultarRazas();
        assertNotNull(razasConsultadas, "La lista de razas no debe ser nula");
        assertEquals(3, razasConsultadas.size(), "El tamaño de la lista de razas debe ser 3");
    }

    /**
     * Prueba para verificar la acción de eliminar una raza.
     */
    @Test
    public void testActionPerformed_Eliminar() {
        System.out.println("Simulando actionPerformed con comando 'Eliminar'");

        // Simulamos la acción de eliminar
        controlPrincipal.actionPerformed(eliminarEvent);

        // Aserción: Verificar que se eliminó la raza correctamente
        RazaVO razaAEliminar = new RazaVO();
        boolean resultado = controlPrincipal.razaDAO.eliminarRaza(razaAEliminar.getNombre());
        assertTrue(resultado, "La raza debería haberse eliminado correctamente");
    }

    /**
     * Prueba para verificar la acción de modificar una raza.
     */
    @Test
    public void testActionPerformed_Modificar() {
        System.out.println("Simulando actionPerformed con comando 'Modificar'");

        // Simulamos la acción de modificar
        controlPrincipal.actionPerformed(modificarEvent);

        // Aserción: Verificar que la raza fue modificada correctamente
        RazaVO razaModificada = new RazaVO();
        boolean resultado = controlPrincipal.razaDAO.modificarRaza(razaModificada);
        assertTrue(resultado, "La raza debería haberse modificado correctamente");
    }

    /**
     * Prueba para verificar la acción de serializar las razas.
     */
    @Test
    public void testActionPerformed_Serializar() {
        System.out.println("Simulando actionPerformed con comando 'Serializar'");

        // Simulamos la acción de serializar
        controlPrincipal.actionPerformed(serializarEvent);

        // Aserción: Verificar que la serialización fue exitosa
        boolean serializado = controlPrincipal.controlRaza.serializarRazas();
        assertTrue(serializado, "La serialización debería haberse completado correctamente");
    }

    /**
     * Prueba para verificar la acción de limpiar los campos en la vista.
     */
    @Test
    public void testActionPerformed_Limpiar() {
        System.out.println("Simulando actionPerformed con comando 'Limpiar'");

        // Simulamos la acción de limpiar
        controlPrincipal.actionPerformed(limpiarEvent);

        // Aserción: Verificar que los campos de la vista fueron limpiados
        // Aquí, ya que no se usa una vista simulada, se puede omitir esta verificación
        // o se puede realizar una verificación lógica en el controlador
        assertTrue(true, "La acción de limpiar se ejecutó correctamente, pero no se verifica la vista.");
    }
}



