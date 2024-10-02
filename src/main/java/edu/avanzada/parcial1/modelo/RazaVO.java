package edu.avanzada.parcial1.modelo;

import java.io.Serializable;

/**
 *
 * @author Ana, Samuel, Juan
 *
 */
public class RazaVO implements Serializable {

    private String nombre, paisOrigen;
    private transient String apariencia, peloManto,colorManto, espalda, lomo, cola, pecho;  // datos que no se serializaran
    private int seccionFCI, grupoFCI;

    public RazaVO(String nombre, String paisOrigen, String apariencia, String peloManto, String colorManto, String espalda, String lomo, String cola, String pecho, int seccionFCI, int grupoFCI) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.apariencia = apariencia;
        this.peloManto = peloManto;
        this.colorManto = colorManto;
        this.espalda = espalda;
        this.lomo = lomo;
        this.cola = cola;
        this.pecho = pecho;
        this.seccionFCI = seccionFCI;
        this.grupoFCI = grupoFCI;
    }

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

    public String getApariencia() {
        return apariencia;
    }

    public void setApariencia(String apariencia) {
        this.apariencia = apariencia;
    }

    public String getPeloManto() {
        return peloManto;
    }

    public void setPeloManto(String peloManto) {
        this.peloManto = peloManto;
    }

    public String getColorManto() {
        return colorManto;
    }

    public void setColorManto(String colorManto) {
        this.colorManto = colorManto;
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

    public int getSeccionFCI() {
        return seccionFCI;
    }

    public void setSeccionFCI(int seccionFCI) {
        this.seccionFCI = seccionFCI;
    }

    public int getGrupoFCI() {
        return grupoFCI;
    }

    public void setGrupoFCI(int grupoFCI) {
        this.grupoFCI = grupoFCI;
    }

    
}