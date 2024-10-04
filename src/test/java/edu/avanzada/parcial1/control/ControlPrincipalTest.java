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
 * Esta clase verifica las funcionalidades del controlador de razas,
 * incluyendo la inserción, consulta, eliminación, modificación,
 * serialización y limpieza de datos en la base de datos.
 * 
 * @author Samuel, Ana, Juan
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
     * Se inicializa el controlador y se crean los eventos para las acciones.
     * 
     * @throws SQLException si ocurre un error en la conexión a la base de datos.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        controlPrincipal = new ControlPrincipal();
        controlPrincipal.razaDAO = new RazaDAO((Connection) new Conexion("jdbc:mysql://localhost/test", "root", ""));
        
        // Simulación de eventos de acción para los diferentes botones
        insertarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Insertar");
        consultarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Consultar");
        eliminarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Eliminar");
        modificarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Modificar");
        serializarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Serializar");
        limpiarEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Limpiar");

        // Precargar razas en la base de datos
        precargarRazas();
    }

    /**
     * Limpieza después de cada prueba.
     * Se eliminan las razas de la base de datos y se liberan los recursos.
     * 
     * @throws SQLException si ocurre un error en la conexión a la base de datos.
     */
@AfterEach
public void tearDown() throws SQLException {
    // Limpiar razas de la base de datos después de cada prueba
    List<RazaVO> razas = controlPrincipal.razaDAO.consultarRazas();
    for (RazaVO raza : razas) {
        controlPrincipal.razaDAO.eliminarRaza(raza.getNombre()); // Usar "raza" en lugar de "razaVO"
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
     * Método para precargar razas en la base de datos para las pruebas.
     * 
     * @throws SQLException si ocurre un error en la conexión a la base de datos.
     */
    private void precargarRazas() throws SQLException {
        List<RazaVO> razas = new ArrayList<>();
        razas.add(new RazaVO("Raza1", "Pais1", "Grupo1", "Seccion1", "Apariencia1", "Pelo1", "Color1", "Espalda1", "Lomo1", "Cola1", "Pecho1"));
        razas.add(new RazaVO("Raza2", "Pais2", "Grupo2", "Seccion2", "Apariencia2", "Pelo2", "Color2", "Espalda2", "Lomo2", "Cola2", "Pecho2"));
        razas.add(new RazaVO("Raza3", "Pais3", "Grupo3", "Seccion3", "Apariencia3", "Pelo3", "Color3", "Espalda3", "Lomo3", "Cola3", "Pecho3"));

        for (RazaVO raza : razas) {
            controlPrincipal.razaDAO.insertarRaza(raza);
        }
    }

    /**
     * Prueba para verificar la acción de insertar una raza.
     * 
     * Verifica que la raza se inserte correctamente en la base de datos.
     */
    @Test
public void testActionPerformed_Insertar() throws SQLException {
    System.out.println("Simulando actionPerformed con comando 'Insertar'");

    // Creamos una nueva raza para insertar
    RazaVO nuevaRaza = new RazaVO("Raza4", "Pais4", "Grupo4", "Seccion4", "Apariencia4", "Pelo4", "Color4", "Espalda4", "Lomo4", "Cola4", "Pecho4");

    // Simulamos la acción de insertar
    controlPrincipal.setNuevaRaza(nuevaRaza); // Asegúrate de tener un método en ControlPrincipal para establecer la nueva raza
    controlPrincipal.actionPerformed(insertarEvent);

    // Verificamos que la raza se haya insertado correctamente
    List<RazaVO> razasConsultadas = controlPrincipal.razaDAO.consultarRazas();
    assertTrue(razasConsultadas.stream().anyMatch(raza -> raza.getNombre().equals("Raza4")), "La raza debería haberse insertado correctamente");
}


    /**
     * Prueba para verificar la acción de consultar las razas.
     * 
     * Verifica que la consulta retorne los datos esperados.
     * 
     * @throws SQLException si ocurre un error en la conexión a la base de datos.
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
     * 
     * Verifica que la raza se elimine correctamente de la base de datos.
     */
    @Test
    public void testActionPerformed_Eliminar() {
        System.out.println("Simulando actionPerformed con comando 'Eliminar'");

        // Simulamos la acción de eliminar
        controlPrincipal.actionPerformed(eliminarEvent);

        RazaVO razaAEliminar = new RazaVO("Raza1", "Pais1", "Grupo1", "Seccion1", "Apariencia1", "Pelo1", "Color1", "Espalda1", "Lomo1", "Cola1", "Pecho1");
        boolean resultado = controlPrincipal.razaDAO.eliminarRaza(razaAEliminar.getNombre());
        assertTrue(resultado, "La raza debería haberse eliminado correctamente");
    }

    /**
     * Prueba para verificar la acción de modificar una raza.
     * 
     * Verifica que la raza se modifique correctamente en la base de datos.
     */
    @Test
    public void testActionPerformed_Modificar() {
        System.out.println("Simulando actionPerformed con comando 'Modificar'");

        // Simulamos la acción de modificar
        controlPrincipal.actionPerformed(modificarEvent);

        RazaVO razaModificada = new RazaVO("Raza1", "Pais1", "Grupo1", "Seccion1", "Apariencia1 Modificada", "Pelo1", "Color1", "Espalda1", "Lomo1", "Cola1", "Pecho1");
        boolean resultado = controlPrincipal.razaDAO.modificarRaza(razaModificada);
        assertTrue(resultado, "La raza debería haberse modificado correctamente");
    }

    /**
     * Prueba para verificar la acción de serializar las razas.
     * 
     * Verifica que la serialización de las razas se complete correctamente.
     */
    @Test
    public void testActionPerformed_Serializar() {
        System.out.println("Simulando actionPerformed con comando 'Serializar'");

        // Simulamos la acción de serializar
        controlPrincipal.actionPerformed(serializarEvent);

        boolean serializado = controlPrincipal.controlRaza.serializarRazas();
        assertTrue(serializado, "La serialización debería haberse completado correctamente");
    }

    /**
     * Prueba para verificar la acción de actualizar una raza.
     * 
     * Verifica que se actualice correctamente una raza existente en la base de datos.
     */
    @Test
    public void testActionPerformed_Actualizar() {
        System.out.println("Simulando actionPerformed con comando 'Actualizar'");

        // Supongamos que tenemos una raza existente en la base de datos para actualizar
        int idRazaExistente = 1; // ID de la raza existente
        RazaVO razaExistente = controlPrincipal.razaDAO.consultarRazaPorID(idRazaExistente);
        
        // Verificamos que la raza existe
        assertNotNull(razaExistente, "La raza existente debería encontrarse en la base de datos");

        // Simulamos la actualización de la raza
        ventana.add(razaExistente);
        boolean resultado = controlPrincipal.razaDAO.modificarRaza(razaExistente);
        assertTrue(resultado, "La raza debería haberse actualizado correctamente");
    }

    /**
     * Prueba para verificar la acción de limpiar los datos.
     * 
     * Verifica que la limpieza de datos funcione correctamente.
     */
    @Test
    public void testActionPerformed_Limpiar() {
        System.out.println("Simulando actionPerformed con comando 'Limpiar'");

        // Simulamos la acción de limpiar
        controlPrincipal.actionPerformed(limpiarEvent);

        // Aquí se puede verificar que los datos en la vista se hayan limpiado
        // Por ejemplo, se podría verificar que las entradas de texto estén vacías
        assertTrue(controlPrincipal.vista.isCamposLimpios(), "Los campos deberían estar limpios");
    }
}




