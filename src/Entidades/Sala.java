package Entidades;

import java.util.ArrayList;

public class Sala {

    private int numeroSala;
    private boolean completada;
    private ArrayList<Enemigo> enemigos;

    //Constructor
    public Sala(int numeroSala) {
        this.numeroSala = numeroSala;
        this.completada = false;
        this.enemigos = new ArrayList<>();
        generarEnemigos();
    }

    //Getter y setters
    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    //toString
    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuffer sb = new java.lang.StringBuffer("Sala{");
        sb.append("numeroSala=").append(numeroSala);
        sb.append(", completada=").append(completada);
        sb.append(", enemigos=").append(enemigos);
        sb.append('}');
        return sb.toString();
    }

    //Metodos
    public void generarEnemigos() {
        switch (this.numeroSala){
            case 1,2:

            case 3,4:

            case 5:
        }
    }

    public boolean todosEnemigosMuertos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                return false;
            }
        }
            return true;
    }

    public ArrayList<Enemigo> getEnemigosVivos() {
        ArrayList<Enemigo> vivos = new ArrayList<>();
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) {
                vivos.add(e);
            }
        }
            return vivos;
    }
}

