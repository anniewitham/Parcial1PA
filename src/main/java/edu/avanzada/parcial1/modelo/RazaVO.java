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
 * @author Ana, Samuel, Juan
 */
public class RazaVO implements Serializable {
    private static final long serialVersionUID = 1L; // Añadir un serialVersionUID para compatibilidad

    private String nombre;         // Nombre de la raza
    private String paisOrigen;     // País de origen de la raza
    private String grupoFCI;       // Grupo FCI al que pertenece la raza
    private String seccionFCI;     // Sección FCI a la que pertenece la raza
    private transient String apariencia;     // Descripción de la apariencia
    private transient String pelo;           // Tipo de pelo
    private transient String color;          // Color del manto
    private transient String espalda;        // Descripción de la espalda
    private transient String lomo;           // Descripción del lomo
    private transient String cola;           // Descripción de la cola
    private transient String pecho;          // Descripción del pecho

    /**
     * Constructor vacio
     */
    public RazaVO() {}

  /**
   * Constructor de la clase RazaVO que inicializa todos los atributos.
     *
     * @param nombre        Nombre de la raza.
     * @param paisOrigen    País de origen de la raza.
     * @param apariencia    Descripción de la apariencia de la raza.
     * @param pelo          Tipo de pelo de la raza.
     * @param color         Color del manto de la raza.
     * @param espalda       Descripción de la espalda de la raza.
     * @param lomo          Descripción del lomo de la raza.
     * @param cola          Descripción de la cola de la raza.
     * @param pecho         Descripción del pecho de la raza.
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

    /**
     * Obtiene el nombre de la raza.
     *
     * @return El nombre de la raza.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la raza.
     *
     * @param nombre El nuevo nombre de la raza.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el país de origen de la raza.
     *
     * @return El país de origen de la raza.
     */
    public String getPaisOrigen() {
        return paisOrigen;
    }

    /**
     * Establece el país de origen de la raza.
     *
     * @param paisOrigen El nuevo país de origen de la raza.
     */
    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    /**
     * Obtiene la apariencia de la raza.
     *
     * @return La descripción de la apariencia de la raza.
     */
    public String getApariencia() {
        return apariencia;
    }

    /**
     * Establece la apariencia de la raza.
     *
     * @param apariencia La nueva descripción de la apariencia de la raza.
     */
    public void setApariencia(String apariencia) {
        this.apariencia = apariencia;
    }

    /**
     * Obtiene el tipo de pelo de la raza.
     *
     * @return El tipo de pelo de la raza.
     */
    public String getPelo() {
        return pelo;
    }

    /**
     * Establece el tipo de pelo de la raza.
     *
     * @param pelo El nuevo tipo de pelo de la raza.
     */
    public void setPelo(String pelo) {
        this.pelo = pelo;
    }

    /**
     * Obtiene el color del manto de la raza.
     *
     * @return El color del manto de la raza.
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color del manto de la raza.
     *
     * @param color El nuevo color del manto de la raza.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene la descripción de la espalda de la raza.
     *
     * @return La descripción de la espalda de la raza.
     */
    public String getEspalda() {
        return espalda;
    }

    /**
     * Establece la descripción de la espalda de la raza.
     *
     * @param espalda La nueva descripción de la espalda de la raza.
     */
    public void setEspalda(String espalda) {
        this.espalda = espalda;
    }

    /**
     * Obtiene la descripción del lomo de la raza.
     *
     * @return La descripción del lomo de la raza.
     */
    public String getLomo() {
        return lomo;
    }

    /**
     * Establece la descripción del lomo de la raza.
     *
     * @param lomo La nueva descripción del lomo de la raza.
     */
    public void setLomo(String lomo) {
        this.lomo = lomo;
    }

    /**
     * Obtiene la descripción de la cola de la raza.
     *
     * @return La descripción de la cola de la raza.
     */
    public String getCola() {
        return cola;
    }

    /**
     * Establece la descripción de la cola de la raza.
     *
     * @param cola La nueva descripción de la cola de la raza.
     */
    public void setCola(String cola) {
        this.cola = cola;
    }

    /**
     * Obtiene la descripción del pecho de la raza.
     *
     * @return La descripción del pecho de la raza.
     */
    public String getPecho() {
        return pecho;
    }

    /**
     * Establece la descripción del pecho de la raza.
     *
     * @param pecho La nueva descripción del pecho de la raza.
     */
    public void setPecho(String pecho) {
        this.pecho = pecho;
    }
     /**
     * Obtiene la sección FCI a la que pertenece la raza.
     *
     * @return La sección FCI a la que pertenece la raza.
     */
    public String getSeccionFCI() {
        return seccionFCI;
    }

    /**
     * Establece la sección FCI a la que pertenece la raza.
     *
     * @param seccionFCI La nueva sección FCI a la que pertenece la raza.
     */
    public void setSeccionFCI(String seccionFCI) {
        this.seccionFCI = seccionFCI;
    }

    /**
     * Obtiene el grupo FCI a la que pertenece la raza.
     *
     * @return El grupo FCI a la que pertenece la raza.
     */
    public String getGrupoFCI() {
        return grupoFCI;
    }

    /**
     * Establece el grupo FCI a la que pertenece la raza.
     *
     * @param grupoFCI El nuevo grupo FCI a la que pertenece la raza.
     */
    public void setGrupoFCI(String grupoFCI) {
        this.grupoFCI = grupoFCI;
    }
}
