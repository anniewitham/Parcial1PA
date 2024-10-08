package edu.avanzada.parcial1.modelo;

import java.io.Serializable;

/**
 * La clase RazaVO representa una raza de animales con atributos que describen
 * sus características y propiedades.
 * 
 * Esta clase implementa la interfaz Serializable, lo que permite que sus
 * instancias sean serializadas y deserializadas, facilitando el almacenamiento
 * y la transmisión de datos.
 *
 * La clase contiene varios atributos que describen la raza, incluyendo su
 * nombre, país de origen, grupo FCI, sección FCI, apariencia, tipo de pelo, 
 * color, y descripciones detalladas de varias características físicas.
 *
 * Se proporcionan métodos getters y setters para acceder y modificar los 
 * atributos de la raza.
 *
 * @author Ana, Samuel, Juan
 */
public class RazaVO implements Serializable {
    private static final long serialVersionUID = 1L; // Añadir un serialVersionUID para compatibilidad

    private int ID;
    private String nombre;         // Nombre de la raza
    private String paisOrigen;     // País de origen de la raza
    private String grupoFCI;       // Grupo FCI al que pertenece la raza
    private String seccionFCI;     // Sección FCI a la que pertenece la raza
    private String apariencia;     // Descripción de la apariencia
    private String pelo;           // Tipo de pelo
    private String color;          // Color del manto
    private String espalda;        // Descripción de la espalda
    private String lomo;           // Descripción del lomo
    private String cola;           // Descripción de la cola
    private String pecho;          // Descripción del pecho

    /**
     * Constructor vacío que inicializa una instancia de RazaVO.
     */
    public RazaVO() {}
    
    /**
     * Constructor completo que inicializa una instancia de RazaVO con todos sus atributos.
     *
     * @param nombre        Nombre de la raza.
     * @param paisOrigen    País de origen de la raza.
     * @param grupoFCI      Grupo FCI al que pertenece la raza.
     * @param seccionFCI    Sección FCI a la que pertenece la raza.
     * @param apariencia    Descripción de la apariencia.
     * @param pelo          Tipo de pelo.
     * @param color         Color del manto.
     * @param espalda       Descripción de la espalda.
     * @param lomo          Descripción del lomo.
     * @param cola          Descripción de la cola.
     * @param pecho         Descripción del pecho.
     */
    public RazaVO(String nombre, String paisOrigen, String grupoFCI, String seccionFCI,
                  String apariencia, String pelo, String color, String espalda, 
                  String lomo, String cola, String pecho) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.grupoFCI = grupoFCI;
        this.seccionFCI = seccionFCI;
        this.apariencia = apariencia;
        this.pelo = pelo;
        this.color = color;
        this.espalda = espalda;
        this.lomo = lomo;
        this.cola = cola;
        this.pecho = pecho;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getGrupoFCI() {
        return grupoFCI;
    }

    public void setGrupoFCI(String grupoFCI) {
        this.grupoFCI = grupoFCI;
    }

    public String getSeccionFCI() {
        return seccionFCI;
    }

    public void setSeccionFCI(String seccionFCI) {
        this.seccionFCI = seccionFCI;
    }

    public String getApariencia() {
        return apariencia;
    }

    public void setApariencia(String apariencia) {
        this.apariencia = apariencia;
    }

    public String getPelo() {
        return pelo;
    }

    public void setPelo(String pelo) {
        this.pelo = pelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEspalda() {
        return espalda;
    }

    public void setEspalda(String espalda) {
        this.espalda = espalda;
    }

    public String getLomo() {
        return lomo;
    }

    public void setLomo(String lomo) {
        this.lomo = lomo;
    }

    public String getCola() {
        return cola;
    }

    public void setCola(String cola) {
        this.cola = cola;
    }

    public String getPecho() {
        return pecho;
    }

    public void setPecho(String pecho) {
        this.pecho = pecho;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
}
