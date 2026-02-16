package Entidades;

public class Personaje {

    protected String nombre;
    protected int puntosVidaActual;
    protected int puntosVidaMax;
    protected int ataque;
    protected int defensa;
    protected boolean vivo; //(true si puntosVidaActual > 0)


    public Personaje(String nombre, int puntosVidaMax, int puntosVidaActual, int ataque, int defensa) {
        this.nombre = nombre;
        this.puntosVidaMax = puntosVidaActual;
        this.puntosVidaActual = puntosVidaActual;
        this.ataque = ataque;
        this.defensa = defensa;
        this.vivo = true;
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

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    /**
     * calcula daño y lo aplica al objetivo
     * Daño = ataque del atacante - defensa del objetivo (mínimo 1 de daño)
     */
    public void atacar(Personaje objetivo){
        int golpe = this.ataque - objetivo.getDefensa();
        //Si el daño es mayor a 1 intercepta si no pues no hace nada este metodo
        if (golpe > 1){
            recibirDanio(golpe);
        }

    }

    /**
     * resta vida, si llega a 0 o menos, marca vivo = false
     */
    public void recibirDanio(int danio){
        this.puntosVidaActual -= danio;

        if (this.puntosVidaActual <= 0){
            this.vivo = false;
        }
    }

    /**
     * suma vida sin pasar del máximo
     */
    public void curar(int cantidad){
        //Para que no nos paseos de la vida actual haremos un if el cual nos hara un capeo de la vida.
        if (this.puntosVidaActual + cantidad > this.puntosVidaMax){
            this.puntosVidaActual = this.puntosVidaMax;
        }else {
            this.puntosVidaActual += cantidad;
        }

    }

    /**
     * devuelve si vivo == true
     */
    public boolean estaVivo(){
        this.vivo = false;

        if(this.puntosVidaActual > 0){
            this.vivo = true;
        }
        return this.vivo;
    }





}
