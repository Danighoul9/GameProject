package Entidades;

public class Enemigo extends Personaje {
    protected TipoEnemigo tipo;
    protected int expOtorgada;

    public Enemigo(String nombre, int puntosVidaMax, int puntosVidaActual, int ataque, int defensa, boolean vivo) {
        super(nombre, puntosVidaMax, puntosVidaActual, ataque, defensa, vivo);
    }
}
