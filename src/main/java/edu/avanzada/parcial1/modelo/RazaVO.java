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
    protected static final long serialVersionUID = 1L; // Añadir un serialVersionUID para compatibilidad

    protected  int ID;
    protected  String nombre;         // Nombre de la raza
    protected  String paisOrigen;     // País de origen de la raza
    protected  String grupoFCI;       // Grupo FCI al que pertenece la raza
    protected  String seccionFCI;     // Sección FCI a la que pertenece la raza
    protected  String apariencia;     // Descripción de la apariencia
    protected  String pelo;           // Tipo de pelo
    protected  String color;          // Color del manto
    protected  String espalda;        // Descripción de la espalda
    protected  String lomo;           // Descripción del lomo
    protected  String cola;           // Descripción de la cola
    protected  String pecho;          // Descripción del pecho

    // Constructor vacío
    public RazaVO() {}

    public RazaVO(int ID, String nombre, String paisOrigen, String grupoFCI, String seccionFCI,
                  String apariencia, String pelo, String color, String espalda, 
                  String lomo, String cola, String pecho) {
        this.ID = ID;
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
    
    // Constructor completo
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

    public RazaVO(String grupoFCI, String seccionFCI, String apariencia, String pelo, String color, String espalda, String lomo, String cola, String pecho) {
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
