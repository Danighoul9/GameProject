package Entidades;

import java.util.ArrayList;

public class Heroe extends Personaje{
    private TipoHeroe tipo;
    int nivel = 1;
    int experiencia = 0;
    ArrayList<Item> item;


    public Heroe(String nombre, boolean vivo, TipoHeroe tipo, ArrayList<Item> item) {
        super(nombre, 0, 0, 0, vivo);
        if (this.tipo == TipoHeroe.GUERRERO){
            this.puntosVidaActual = 100;
            this.ataque = 20;
            this.defensa = 15;
        } else if (this.tipo == TipoHeroe.ARQUERO) {
            this.puntosVidaActual = 60;
            this.ataque = 30;
            this.defensa = 5;
        } else if (this.tipo == TipoHeroe.MAGO) {
            this.puntosVidaActual = 80;
            this.ataque = 25;
            this.defensa = 10;
        }
        this.item = new ArrayList<>();
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    public TipoHeroe getTipo() {
        return tipo;
    }

    public void setTipo(TipoHeroe tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Heroe{");
        sb.append("tipo=").append(tipo);
        sb.append(", nivel=").append(nivel);
        sb.append(", experiencia=").append(experiencia);
        sb.append(", item=").append(item);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", puntosVidaActual=").append(puntosVidaActual);
        sb.append(", puntosVidaMax=").append(puntosVidaMax);
        sb.append(", ataque=").append(ataque);
        sb.append(", defensa=").append(defensa);
        sb.append(", vivo=").append(vivo);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Habilidades Especiales de los heroes
     * @param objetivo
     */
    public void usarHabilidadEspecial(Personaje objetivo){
        if (this.tipo == TipoHeroe.GUERRERO){
            System.out.println("GOLPE PODEROSO");

        }
        if (this.tipo == TipoHeroe.MAGO){
            System.out.println("BOLA DE FUEGO");

        }
        if (this.tipo == TipoHeroe.ARQUERO){
            System.out.println("DISPARO PRECISO");

        }
    }

    /**
     * Subir experiencia y nivel al heroe
     * @param exp
     */
    public void ganarExperiencia(int exp){
        this.experiencia += exp;
    }

    public void subirNivel(){
        if (this.experiencia >= 100){
            this.puntosVidaMax += 20;
            this.ataque += 5;
            this.defensa += 3;
            this.experiencia = 0;
        }
    }

    public void usarItem(Item item){
    }



}
