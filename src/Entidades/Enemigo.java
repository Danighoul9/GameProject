package Entidades;

import java.util.ArrayList;

public class Enemigo extends Personaje {
    protected TipoEnemigo tipo;
    protected int expOtorgada; //(experiencia que da al morir)
    private ArrayList<Heroe> heroes = new ArrayList<>();

    public Enemigo(String nombre,TipoEnemigo tipo) {
        super(nombre, 0, 0, 0, 0);
        this.tipo = tipo;
        switch (tipo) {
            case GOBLIN ->{
                this.puntosVidaActual = 30;
                this.ataque = 18;
                this.defensa = 3;
                this.expOtorgada = 20;
                this.puntosVidaMax = this.puntosVidaActual;
            }

            case ORCO ->{
                this.puntosVidaActual = 60;
                this.ataque = 28;
                this.defensa = 8;
                this.expOtorgada = 40;
                this.puntosVidaMax = this.puntosVidaActual;
            }

            case DRAGON ->{
                this.puntosVidaActual = 150;
                this.ataque = 36;
                this.defensa = 12;
                this.expOtorgada = 100;
                this.puntosVidaMax = this.puntosVidaActual;
            }
        }

    }

    public int getExpOtorgada() {
        return expOtorgada;
    }

    public Enemigo setExpOtorgada(int expOtorgada) {
        this.expOtorgada = expOtorgada;
        return this;
    }

    public TipoEnemigo getTipo() {
        return tipo;
    }

    public Enemigo setTipo(TipoEnemigo tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enemigo{");
        sb.append("expOtorgada=").append(expOtorgada);
        sb.append(", tipo=").append(tipo);
        sb.append(", ataque=").append(ataque);
        sb.append(", defensa=").append(defensa);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", puntosVidaActual=").append(puntosVidaActual);
        sb.append(", puntosVidaMax=").append(puntosVidaMax);
        sb.append(", vivo=").append(vivo);
        sb.append('}');
        return sb.toString();
    }

    /**
     * void usarHabilidadEspecial(Personaje objetivo) :
     * GOBLIN: "Golpe Rápido" - ataca dos veces seguidas con daño
     * reducido
     * ORCO: "Grito de Guerra" - aumenta su ataque temporalmente
     * DRAGON: "Aliento de Fuego" - daña a todos los héroes
     * @param objetivo
     * @param listaHeroes
     */
    public void usarHabilidadEspecial(Personaje objetivo, ArrayList<Heroe> listaHeroes){

        if (this.tipo==TipoEnemigo.GOBLIN) {
            System.out.println("Golpe Rapido");
            atacar(objetivo);
            atacar(objetivo);
        }
        if (this.tipo==TipoEnemigo.ORCO){
            System.out.println("Grito guerra");
            // Aumentar ataque temporalmente (redondeando hacia arriba)
            this.ataque = (int) Math.ceil(this.ataque * 1.2);
        }
        if (this.tipo == TipoEnemigo.DRAGON) {
            System.out.println("¡Aliento de Fuego!");
            for (Heroe h : listaHeroes) {
                if (h.estaVivo()) atacar(h);
            }
        }


    }

}





