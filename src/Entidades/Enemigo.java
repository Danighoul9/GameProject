package Entidades;

public class Enemigo extends Personaje {
    protected TipoEnemigo tipo;
    protected int expOtorgada; //(experiencia que da al morir)

    public Enemigo(String nombre, int puntosVidaMax, int puntosVidaActual, int ataque, int defensa, boolean vivo, int expOtorgada, TipoEnemigo tipo) {
        super(nombre, puntosVidaMax, puntosVidaActual, ataque, defensa, vivo);
        this.expOtorgada = expOtorgada;
        this.tipo = tipo;
        switch (tipo) {
            case GOBLIN:
                this.puntosVidaActual = 30;
                this.ataque = 8;
                this.defensa = 3;
                this.expOtorgada = 20;
                break;

            case ORCO:
                this.puntosVidaActual = 60;
                this.ataque = 15;
                this.defensa = 8;
                this.expOtorgada = 40;
                break;

            case DRAGON:
                this.puntosVidaActual = 150;
                this.ataque = 25;
                this.defensa = 12;
                this.expOtorgada = 100;
                break;
        }

    }

    //void usarHabilidadEspecial(Personaje objetivo) :
    //GOBLIN: "Golpe Rápido" - ataca dos veces seguidas con daño
    //reducido
    //ORCO: "Grito de Guerra" - aumenta su ataque temporalmente
    //DRAGON: "Aliento de Fuego" - daña a todos los héroes

    void usarHabilidadEspecial(Personaje objetivo){

    }
    }





