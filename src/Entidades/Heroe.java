package Entidades;

import java.util.ArrayList;

public class Heroe {
    private TipoHeroe tipo;
    int nivel = 1;
    int experiencia = 0;
    ArrayList<Item> item;
/*
if (this.tipo == TipoHeroe.GUERRERO){

        } else if (this.tipo == TipoHeroe.ARQUERO) {

        } else if (this.tipo = TipoHeroe.MAGO) {

        }
        this.item = new ArrayList<>();
 */
    public Heroe(TipoHeroe tipo, ArrayList<Item> item) {
        if (this.tipo == TipoHeroe.GUERRERO){

        } else if (this.tipo == TipoHeroe.ARQUERO) {
            
        } else if (this.tipo == TipoHeroe.MAGO) {

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


}
