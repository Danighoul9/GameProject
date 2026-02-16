package Entidades;

public class Enemigo extends Personaje {
    protected TipoEnemigo tipo;
    protected int expOtorgada; //(experiencia que da al morir)

    public Enemigo(String nombre, int puntosVidaMax, int puntosVidaActual, int ataque, int defensa, boolean vivo, int expOtorgada, TipoEnemigo tipo) {
        super(nombre, puntosVidaMax, puntosVidaActual, ataque, defensa, vivo);
        this.expOtorgada = expOtorgada;
        this.tipo = tipo;
    }



    }

