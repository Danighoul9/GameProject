package Entidades;

public class Personaje {

    protected String nombre;
    protected int puntosVidaMax;
    protected int puntosVidaActual;
    protected int ataque;
    protected int defensa;
    protected boolean vivo; //(true si puntosVidaActual > 0)


    public Personaje(String nombre, int puntosVidaMax, int puntosVidaActual, int ataque, int defensa, boolean vivo) {
        this.nombre = nombre;
        this.puntosVidaMax = puntosVidaMax;
        this.puntosVidaActual = puntosVidaActual;
        this.ataque = ataque;
        this.defensa = defensa;
        this.vivo = vivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosVidaMax() {
        return puntosVidaMax;
    }

    public void setPuntosVidaMax(int puntosVidaMax) {
        this.puntosVidaMax = puntosVidaMax;
    }

    public int getPuntosVidaActual() {
        return puntosVidaActual;
    }

    public void setPuntosVidaActual(int puntosVidaActual) {
        this.puntosVidaActual = puntosVidaActual;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }






}
