package Servicios;

import Entidades.Enemigo;
import Entidades.Heroe;
import Servicios.Juego;

import java.util.ArrayList;

public class Combate {

    private int turno;
    private Sala sala;
    private ArrayList<Heroe> heroes;

    //Constructor
    public Combate(Sala sala) {
        this.turno = 1;
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

    public void turnoHeroes() {
        for (Heroe e : heroes) {
            sala.getEnemigosVivos();
            e.atacar(sala.getEnemigosVivos().get(0));
        }
    }

    /**
     * Cada enemigo vivo ataca a un héroe aleatorio
     * <p>
     * El funcionamiento de este metodo es el siguiente --->
     * <p>
     * -Generamos un numero random de heroe para luego en el arraylist coger una posicion random
     * y por ende seleccionar a un heroe random para que el enemigo lo ataque
     * -Despues de generar el numero random en base al .size() del arraylist de heroes,
     * el enemigo ataca a este haciendo un heroes.get() en la posicion RANDOM.
     */
    public void turnoEnemigos() {
        int heroeRandom;
        for (Enemigo e : sala.getEnemigosVivos()) {
            heroeRandom = (int) (Math.random() * heroes.size());
            e.atacar(heroes.get(heroeRandom));
        }
    }

    /**
     * Bucle principal del combate
     */
    public void iniciarCombate() {

        /**
         * Comprobamos que haya heroes vivos para poder
         * comenzar el combate
         */
        boolean heroesVivos = false;

        for (Heroe h : heroes) {
            if (h.estaVivo()) {
                heroesVivos = true;
            }
        }

        //Comenzamos el combate si en la sala en la que estamos
        // hay enemigos vivos y nuestros heroes esten vivos.
        //** AMBAS partes deben cumplirse por lo tanto es un && **
        while (!sala.todosEnemigosMuertos() && heroesVivos) {
            mostrarEstadoCombate();
            turnoHeroes();
            turnoEnemigos();
            turno++;
        }
    }

    public void mostrarEstadoCombate() {
        for (Heroe e : heroes) {
            e.getPuntosVidaActual();
        }
        for (Enemigo e : sala.getEnemigos()) {
            e.getPuntosVidaActual();
        }
    }

    /**
     * true si todos de un bando están muertos
     *
     * @return
     */
    public boolean combateTerminado() {
        if (sala.todosEnemigosMuertos()) {
            System.out.println("Todos los enemigos han muerto");
            return true;
        } else {
            for (Heroe h : heroes) {
                /**
                 * Si no queda ningun héroe vivo en el
                 * arraylist de heroes entonces devuelves true y el combate
                 * termina, por lo contrario, si algun heroe en el
                 * arraylist esta vivo devolvera false haciendo que el combate
                 * no termine.
                 */
                return (!h.estaVivo());
            }
        }
        return false;
    }

    /**
     * Metodo para distribuir la exp entre los heroes que nos queden vivos en el
     * arraylist en base a la exp que otorgue el enemigo
     */
    public void distribuirExperiencia() {
        int experienciaTotal = 0;
        int contadorHeroesVivos = 0;
        int experienciaReparto = 0;
        /**
         * cuando todos los enemigos estan muertos reune la experiencia otorgada de todos los enemigos, cuenta cuantos
         * heroes quedan vivos y dependiendo de la cantidad de dichos heroes vivos reparte y asigna la experiencia a
         * cada 1
         */
        if (sala.todosEnemigosMuertos()) {
            for (Enemigo e : sala.getEnemigos()) {
                experienciaTotal += sala.getEnemigos().get(0).getExpOtorgada();
            }
            for (Heroe h : heroes) {
                if (h.estaVivo()) {
                    contadorHeroesVivos++;
                }
            }

            experienciaReparto = experienciaTotal / contadorHeroesVivos;
            for (Heroe h : heroes) {
                if (h.estaVivo()) {
                    h.ganarExperiencia(experienciaReparto);
                }
            }
        }

    }
}






