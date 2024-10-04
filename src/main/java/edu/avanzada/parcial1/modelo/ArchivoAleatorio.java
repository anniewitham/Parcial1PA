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

    public void abrirArchivo() throws IOException {
        archivoAleatorio = new RandomAccessFile(FILE_PATH, "rw");
        archivoAleatorio.setLength(0); // Limpiamos el archivo antes de escribir nuevos datos
    }

    public void persistirRazas(List<String> razasSerializadas) throws IOException {
        for (String raza : razasSerializadas) {
            archivoAleatorio.writeUTF(fijarLongitud(raza, RECORD_SIZE)); // Persistiendo cada raza serializada
        }
    }

    public void cerrarArchivo() throws IOException {
        if (archivoAleatorio != null) {
            archivoAleatorio.close();
        }
    }

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