package Servicios;

import Entidades.Heroe;

import java.util.ArrayList;

public class Juego {

    private ArrayList<Heroe> equipo; //(los 3 héroes del jugador)
    private ArrayList<Sala> salas;  //(las 5 salas de la mazmorra)
    private int salaActual; //(0-4)
    private boolean juegoTerminado;

    public Juego(int salaActual) {
        this.salaActual = salaActual;
        this.equipo = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.juegoTerminado = false;
    }

    public ArrayList<Heroe> getEquipo() {
        return equipo;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public int getSalaActual() {
        return salaActual;
    }

    public void setSalaActual(int salaActual) {
        this.salaActual = salaActual;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Juego{");
        sb.append("equipo=").append(equipo);
        sb.append(", salas=").append(salas);
        sb.append(", salaActual=").append(salaActual);
        sb.append(", juegoTerminado=").append(juegoTerminado);
        sb.append('}');
        return sb.toString();
    }






}
