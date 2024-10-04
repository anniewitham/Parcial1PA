package edu.avanzada.parcial1.modelo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * La clase RazaModel gestiona la persistencia de los datos de razas en un
 * archivo de acceso aleatorio. El archivo solo se crea o actualiza al salir del
 * sistema.
 *
 * @author Juan, Samuel, Ana
 */
public class ArchivoAleatorio {

    private RandomAccessFile archivoAleatorio;
    private static final String FILE_PATH = "data/razasAleatorias.dat"; // Archivo aleatorio de razas
    private static final int RECORD_SIZE = 200; // Tamaño de cada registro

    /**
     * Abre el archivo para operaciones de lectura y escritura. Se establece la
     * longitud del archivo a 0 para limpiar los datos existentes.
     *
     * @
     * hrows IOException Si ocurre un error al abrir el archivo.
     *
     */
    public void abrirArchivo() throws IOException {
        archivoAleatorio = new RandomAccessFile(FILE_PATH, "rw");
        archivoAleatorio.setLength(0); // Limpiamos el archivo antes de escribir nuevos datos
    }

    /**
     * Persiste una lista de razas serializadas en el archivo. Cada raza se
     * escribe en el archivo, ajustando su longitud al tamaño definido.
     *
     * @param razasSerializadas Lista de razas en formato serializado a
     * persistir.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void persistirRazas(List<String> razasSerializadas) throws IOException {
        for (String raza : razasSerializadas) {
            archivoAleatorio.writeUTF(fijarLongitud(raza, RECORD_SIZE)); // Persistiendo cada raza serializada
        }
    }

    /**
     * Cierra el archivo aleatorio, liberando los recursos asociados.
     *
     * @throws IOException Si ocurre un error al cerrar el archivo.
     */
    public void cerrarArchivo() throws IOException {
        if (archivoAleatorio != null) {
            archivoAleatorio.close();
        }
    }

    /**
     * Ajusta la longitud de una cadena de texto a un tamaño especificado. Si la
     * cadena es más larga que el tamaño, se corta; si es más corta, se completa
     * con espacios hasta alcanzar la longitud deseada.
     *
     * @param data La cadena de texto a ajustar.
     * @param length El tamaño al que se debe ajustar la cadena.
     * @return La cadena ajustada a la longitud especificada.
     */
    private String fijarLongitud(String data, int length) {
        if (data.length() > length) {
            return data.substring(0, length);
        } else {
            StringBuilder sb = new StringBuilder(data);
            while (sb.length() < length) {
                sb.append(" ");
            }
            return sb.toString();
        }
    }
}
