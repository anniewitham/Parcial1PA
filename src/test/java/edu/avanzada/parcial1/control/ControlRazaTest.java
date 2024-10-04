package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.RazaVO;
import edu.avanzada.parcial1.modelo.RazaDAO; // Asegúrate de importar RazaDAO
import edu.avanzada.parcial1.modelo.Conexion; // Asegúrate de importar Conexion
import java.sql.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Pruebas unitarias para la clase ControlRaza.
 * 
 * @author Samuel, Ana, Juan
 */
public class ControlRazaTest {

    private ControlRaza controlRaza;
    private Properties propiedades;
    private List<RazaVO> razasSimuladas;

    /**
     * Configuración inicial para las pruebas.
     * Se crea una instancia de ControlRaza y se cargan razas simuladas.
     * 
     * @throws SQLException si ocurre un error de SQL al establecer la conexión.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        // Crear instancia de Conexion
        Conexion conexion = new Conexion("1", "1", "1"); // Asegúrate de que Conexion tenga un constructor predeterminado
        // Crear instancia de RazaDAO con la conexión
        RazaDAO razaDAO = new RazaDAO((Connection) conexion); 
        controlRaza = new ControlRaza(razaDAO); // Crear instancia de ControlRaza con RazaDAO

        // Configurar propiedades con razas predeterminadas
        propiedades = new Properties();
        propiedades.setProperty("Raza1", "Labrador, Estados Unidos");
        propiedades.setProperty("Raza2", "Bulldog, Reino Unido");
        propiedades.setProperty("Raza3", "Pastor Alemán, Alemania");
        propiedades.setProperty("Raza4", "Beagle, Estados Unidos");

        // Cargar razas desde las propiedades
        controlRaza.cargarRazasDesdePropiedades(propiedades);

        // Inicializar razas simuladas para pruebas
        razasSimuladas = new ArrayList<>();
        razasSimuladas.add(new RazaVO("Pitbull", "Estados Unidos", null, null, null, null, null, null, null, null, null));
        razasSimuladas.add(new RazaVO("Poodle", "Francia", null, null, null, null, null, null, null, null, null));
        controlRaza.insertarRazas(razasSimuladas);
    }

    /**
     * Limpieza después de cada prueba.
     * Se restablecen las instancias y se limpian las propiedades.
     */
    @AfterEach
    public void tearDown() {
        controlRaza = null; // Restablecer la instancia de ControlRaza
        propiedades = null; // Restablecer propiedades
        razasSimuladas.clear(); // Limpiar la lista de razas simuladas
    }

    /**
     * Test del método cargarRazasDesdePropiedades.
     * Se verifica que las razas sean cargadas correctamente desde las propiedades.
     * 
     * @throws Exception si ocurre un error al consultar las razas.
     */
    @Test
    public void testCargarRazasDesdePropiedades() throws Exception {
        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); // 0 para consultar todas las razas

        // Verificar que se hayan cargado las 4 razas correctamente
        assertEquals(6, razasConsultadas.size());
        assertEquals("Labrador", razasConsultadas.get(0).getNombre());
        assertEquals("Bulldog", razasConsultadas.get(1).getNombre());
        assertEquals("Pastor Alemán", razasConsultadas.get(2).getNombre());
        assertEquals("Beagle", razasConsultadas.get(3).getNombre());
        assertEquals("Pitbull", razasConsultadas.get(4).getNombre());
        assertEquals("Poodle", razasConsultadas.get(5).getNombre());
    }

    /**
     * Test del método insertarRazas.
     * Se verifica que se inserten razas correctamente.
     * 
     * @throws Exception si ocurre un error al insertar razas.
     */
    @Test
    public void testInsertarRazas() throws Exception {
        // Insertar una nueva raza
        RazaVO nuevaRaza = new RazaVO("Dachshund", "Alemania", null, null, null, null, null, null, null, null, null);
        controlRaza.insertarRazas(List.of(nuevaRaza));

        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); // 0 para consultar todas las razas

        // Verificar que la nueva raza se haya insertado correctamente
        assertEquals(7, razasConsultadas.size());
        assertEquals("Dachshund", razasConsultadas.get(6).getNombre());
    }

    /**
     * Test del método completarRaza.
     * Se verifica que se complete correctamente una raza existente.
     * 
     * @throws Exception si ocurre un error al completar la raza.
     */
    @Test
    public void testCompletarRaza() throws Exception {
        // Completar la raza Pitbull
        controlRaza.completarRaza("Grupo1", "Sección1", "Apariencia1", "Pelo1", "Color1", "Espalda1", "Lomo1", "Cola1", "Pecho1", 5);
        
        // Consultar la raza actualizada
        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); // 0 para consultar todas las razas
        RazaVO razaActualizada = razasConsultadas.get(4); // Pitbull es la raza en la posición 4
        
        // Verificar que los campos se hayan completado
        assertEquals("Grupo1", razaActualizada.getGrupoFCI());
        assertEquals("Apariencia1", razaActualizada.getApariencia());
        assertEquals("Pelo1", razaActualizada.getPelo());
    }

    /**
     * Test del método eliminarRaza.
     * Se verifica que se elimine la raza correctamente.
     * 
     * @throws Exception si ocurre un error al eliminar la raza.
     */
    @Test
    public void testEliminarRaza() throws Exception {
        // Eliminar la segunda raza (Bulldog)
        controlRaza.eliminarRaza(2); // Se asume que el Bulldog está en la posición 2

        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); // 0 para consultar todas las razas

        // Verificar que el número de razas sea correcto después de la eliminación
        assertEquals(6, razasConsultadas.size());
        assertNotEquals("Bulldog", razasConsultadas.get(1).getNombre()); // El Bulldog debe haber sido eliminado
    }

    /**
     * Test del método consultarRazas.
     * Se verifica que se obtenga la lista de razas del DAO.
     * 
     * @throws Exception si ocurre un error al consultar las razas.
     */
    @Test
    public void testConsultarRazas() throws Exception {
        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); // 0 para consultar todas las razas

        assertEquals(6, razasConsultadas.size());
        assertEquals("Labrador", razasConsultadas.get(0).getNombre());
    }
}





