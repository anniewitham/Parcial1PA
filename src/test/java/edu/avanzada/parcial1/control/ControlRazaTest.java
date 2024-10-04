package edu.avanzada.parcial1.control;

import edu.avanzada.parcial1.modelo.RazaVO;
import edu.avanzada.parcial1.modelo.RazaDAO; 
import edu.avanzada.parcial1.modelo.Conexion; 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase ControlRaza.
 * 
 * @author Samuel, Ana, Juan
 */
public class ControlRazaTest {

    private ControlRaza controlRaza;
    private Properties propiedades;
    private List<RazaVO> razasSimuladas;

    @BeforeEach
    public void setUp() throws SQLException {
        Conexion conexion = new Conexion("1", "1", "1");
        RazaDAO razaDAO = new RazaDAO((Connection) conexion); 
        controlRaza = new ControlRaza(razaDAO); 

        propiedades = new Properties();
        propiedades.setProperty("Raza1", "Labrador, Estados Unidos");
        propiedades.setProperty("Raza2", "Bulldog, Reino Unido");
        propiedades.setProperty("Raza3", "Pastor Alemán, Alemania");
        propiedades.setProperty("Raza4", "Beagle, Estados Unidos");

        controlRaza.cargarRazasDesdePropiedades(propiedades);

        razasSimuladas = new ArrayList<>();
        razasSimuladas.add(new RazaVO("Pitbull", "Estados Unidos", null, null, null, null, null, null, null, null, null));
        razasSimuladas.add(new RazaVO("Poodle", "Francia", null, null, null, null, null, null, null, null, null));
        controlRaza.insertarRazas(razasSimuladas);
    }

    @AfterEach
    public void tearDown() {
        controlRaza = null; 
        propiedades = null; 
        razasSimuladas.clear(); 
    }

    @Test
    public void testCargarRazasDesdePropiedades() throws Exception {
        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); 
        assertEquals(6, razasConsultadas.size());
        assertEquals("Labrador", razasConsultadas.get(0).getNombre());
        assertEquals("Bulldog", razasConsultadas.get(1).getNombre());
        assertEquals("Pastor Alemán", razasConsultadas.get(2).getNombre());
        assertEquals("Beagle", razasConsultadas.get(3).getNombre());
        assertEquals("Pitbull", razasConsultadas.get(4).getNombre());
        assertEquals("Poodle", razasConsultadas.get(5).getNombre());
    }

    @Test
    public void testInsertarRazas() throws Exception {
        RazaVO nuevaRaza = new RazaVO("Dachshund", "Alemania", null, null, null, null, null, null, null, null, null);
        controlRaza.insertarRazas(List.of(nuevaRaza));

        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); 
        assertEquals(7, razasConsultadas.size());
        assertEquals("Dachshund", razasConsultadas.get(6).getNombre());
    }

    @Test
    public void testCompletarRaza() throws Exception {
        controlRaza.completarRaza("Grupo1", "Sección1", "Apariencia1", "Pelo1", "Color1", "Espalda1", "Lomo1", "Cola1", "Pecho1", 5);
        
        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); 
        RazaVO razaActualizada = razasConsultadas.get(4); 
        
        assertEquals("Grupo1", razaActualizada.getGrupoFCI());
        assertEquals("Apariencia1", razaActualizada.getApariencia());
        assertEquals("Pelo1", razaActualizada.getPelo());
    }

    @Test
    public void testEliminarRaza() throws Exception {
        controlRaza.eliminarRaza("Bulldog"); 

        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); 
        assertEquals(6, razasConsultadas.size());
        assertFalse(razasConsultadas.stream().anyMatch(raza -> raza.getNombre().equals("Bulldog")));
    }

    @Test
    public void testConsultarRazas() throws Exception {
        List<RazaVO> razasConsultadas = controlRaza.consultarRaza(0, ""); 
        assertEquals(6, razasConsultadas.size());
        assertEquals("Labrador", razasConsultadas.get(0).getNombre());
    }

    @Test
    public void testObtenerRazasIncompletas() throws SQLException {
        // Suponiendo que las razas "Pitbull" y "Poodle" son incompletas
        List<RazaVO> razasIncompletas = controlRaza.obtenerRazasIncompletas();
        
        // Verificar que la lista de razas incompletas no esté vacía
        assertNotNull(razasIncompletas);
        assertTrue(razasIncompletas.size() > 0);
    }

    @Test
    public void testValidarRazaCreada() throws SQLException {
        assertTrue(controlRaza.validarRazaCreada("Labrador"));
        assertFalse(controlRaza.validarRazaCreada("RazaInexistente"));
    }

    @Test
    public void testInsertarRazaConDatosNull() throws Exception {
        RazaVO razaNula = new RazaVO(null, null, null, null, null, null, null, null, null, null, null);
        assertThrows(SQLException.class, () -> controlRaza.insertarRaza(razaNula));
    }
}

