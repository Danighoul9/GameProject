package Servicios;

import Entidades.Enemigo;
import Entidades.Heroe;

import java.util.ArrayList;

public class Combate {

    private int turno;
    private Sala sala;
    private ArrayList<Heroe> heroes = new ArrayList<>();
    public Estadisticas estadisticas;

    //Constructor
    public Combate(Sala sala, ArrayList<Heroe> heroes) {
        this.turno = 1;
        this.sala = sala;
        this.heroes = heroes;


        //De momento no
        //this.estadisticas = estadisticas;
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
        for (Heroe h : heroes) {
            if (!h.estaVivo() || sala.getEnemigosVivos().isEmpty()){
                continue;
            }
            h.usarHabilidadEspecial(sala.getEnemigosVivos().get(0), sala.getEnemigos());
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

    /*
    try{
        respuesta = sc.nextINt();
    }catch(Exception e){
        sout(Introduce un dato valido)
        respuesta = -1;
    }
    */

    public void turnoEnemigos() {
        ArrayList<Heroe> objetivosValidos = getHeroesVivos();

        for (Enemigo e : sala.getEnemigosVivos()) {
            if (objetivosValidos.isEmpty()){
                break;
            }

            // Elegimos un héroe vivo al azar
            int indice = (int)(Math.random() * objetivosValidos.size());
            Heroe objetivo = objetivosValidos.get(indice);

            // Pasamos el objetivo Y la lista de héroes (por si el Dragón usa área)
            e.usarHabilidadEspecial(objetivo, this.heroes);
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
                IO.print(h.getNombre() + " tiene " + h.getPuntosVidaActual() + "hp restantes.");
            }
        }

        // El bucle debe ejecutarse MIENTRAS NO haya terminado el combate
        while (!combateTerminado()) {
            mostrarEstadoCombate();
            turnoHeroes();
            turnoEnemigos();
            this.turno++;
        }
        
        // Si ganamos, distribuimos la experiencia
        if (sala.todosEnemigosMuertos()) {
            distribuirExperiencia();
        }
    }

    public void mostrarEstadoCombate() {
        for (Heroe e : heroes) {
            IO.println(e.getPuntosVidaActual());
        }
        for (Enemigo e : sala.getEnemigos()) {
            System.out.println(e.getPuntosVidaActual());
        }
    }

    /**
     * true si todos de un bando están muertos
     * @return true si el combate ha terminado (todos los enemigos muertos o todos los héroes muertos)
     */
    public boolean combateTerminado() {
        // Si todos los enemigos están muertos, el combate termina
        if (sala.todosEnemigosMuertos()) {
            System.out.println("Todos los enemigos han muerto");
            return true;
        }
        
        // Verificar si TODOS los héroes están muertos
        boolean todosHeroesMuertos = true;
        for (Heroe h : heroes) {
            if (h.estaVivo()) {
                todosHeroesMuertos = false;
                break;
            }
        }
        
        if (todosHeroesMuertos) {
            System.out.println("Todos los héroes han muerto");
            return true;
        }
        
        // Si hay héroes vivos y enemigos vivos, el combate continúa
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
            // Sumar la experiencia de TODOS los enemigos (usando la variable del bucle, no siempre el primero)
            for (Enemigo e : sala.getEnemigos()) {
                experienciaTotal += e.getExpOtorgada();
            }
            
            // Contar héroes vivos
            for (Heroe h : heroes) {
                if (h.estaVivo()) {
                    contadorHeroesVivos++;
                }
            }

            // Solo repartir si hay héroes vivos (evitar división por cero)
            if (contadorHeroesVivos > 0) {
                experienciaReparto = experienciaTotal / contadorHeroesVivos;
                for (Heroe h : heroes) {
                    if (h.estaVivo()) {
                        h.ganarExperiencia(experienciaReparto);
                        h.subirNivel(); // Intentar subir de nivel después de ganar experiencia
                    }
                }
            }
        }

    }

    private ArrayList<Heroe> getHeroesVivos() {
        ArrayList<Heroe> vivos = new ArrayList<>();
        for (Heroe h : this.heroes) {
            if (h.estaVivo()) {
                vivos.add(h);
            }
        }
        return vivos;
    }
}






