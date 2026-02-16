package Servicios;

import Entidades.Heroe;
import Entidades.Sala;

import java.util.ArrayList;

public class Combate {

    private int turno;
    private Sala sala;;
    private ArrayList<Heroe> heroes;

    //Constructor
    public Combate(int turno, Sala sala) {
        this.turno = turno;
        this.sala = sala;
        this.heroes = new ArrayList<>();
    }

    //Getter y setter
    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public ArrayList<Heroe> getHeroes() {
        return heroes;
    }

    //toString
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Combate{");
        sb.append("turno=").append(turno);
        sb.append(", sala=").append(sala);
        sb.append(", heroes=").append(heroes);
        sb.append('}');
        return sb.toString();
    }


}
