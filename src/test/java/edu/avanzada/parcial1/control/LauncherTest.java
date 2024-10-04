package edu.avanzada.parcial1.control;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Launcher
 * 
 * @author Samuel, Ana, Juan
 */
public class LauncherTest {

    // Para capturar la salida a la consola
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Redirigir System.out antes de ejecutar las pruebas
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        // Restaurar System.out después de las pruebas
        System.setOut(originalOut);
    }

    /**
     * Test para verificar que el método main se ejecuta sin errores
     */
    @Test
    public void testMain() throws SQLException {
        // Simulación de argumentos vacíos para el método main
        String[] args = {};
        
        // Ejecutar el método main
        Launcher.main(args);
        
        // Verificar que no se lance ninguna excepción durante la ejecución
        assertDoesNotThrow(() -> Launcher.main(args), "El método main no debería lanzar excepciones.");
        
        // Verificar si hubo alguna salida a consola (aunque en este caso no debería haber)
        String output = outContent.toString();
        
        // Como no se espera salida, se puede verificar que está vacía
        assertTrue(output.isEmpty(), "No debería haber salida en consola desde el método main.");
    }
}

