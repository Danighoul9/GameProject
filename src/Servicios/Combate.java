package Servicios;

import Entidades.Enemigo;
import Entidades.Heroe;
import Entidades.TipoHeroe;

import java.util.ArrayList;
import java.util.Scanner;

public class Combate {

    Scanner sc = new Scanner(System.in);
    private int turno;
    private Sala sala;
    private ArrayList<Heroe> heroes = new ArrayList<>();
    private Estadisticas estadisticas;

    //Constructor
    public Combate(Sala sala, ArrayList<Heroe> heroes, Estadisticas estadisticas) {
        this.turno = 1;
        this.sala = sala;
        this.heroes = heroes;
        this.estadisticas = estadisticas;
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
        Scanner sc = new Scanner(System.in);
        ArrayList<Enemigo> enemigosVivos = sala.getEnemigosVivos();
        ArrayList<Heroe> heroesVivos = getHeroesVivos();

        for (Heroe h : heroes) {
            if (!h.estaVivo()) continue;
            if (enemigosVivos.isEmpty()) break;

            System.out.println("--- Turno de " + h.getNombre() + " (" + h.getTipo() + ") ---");
            System.out.println("1. Atacar normal   2. Habilidad especial");
            int eleccion = 0;
            try {
                eleccion = sc.nextInt();
            } catch (NumberFormatException e) {
                eleccion = 1;
            }

            if (eleccion == 2) {
                if (h.getTipo() == TipoHeroe.MAGO) {
                    h.usarHabilidadEspecial(elegirEnemigo(enemigosVivos), sala.getEnemigos());
                } else if (h.getTipo() == TipoHeroe.CLERIGO) {
                    System.out.println("Curar a que heroe?");
                    for (int i = 0; i < heroesVivos.size(); i++) {
                        Heroe aliado = heroesVivos.get(i);
                        System.out.println((i + 1) + ". " + aliado.getNombre() + " (" + aliado.getPuntosVidaActual() + "/" + aliado.getPuntosVidaMax() + " HP)");
                    }
                    try {
                        int resUsuario = sc.nextInt() - 1;
                        if (resUsuario >= 0 && resUsuario < heroesVivos.size()) {
                            h.usarHabilidadEspecial(heroesVivos.get(resUsuario), sala.getEnemigos());
                        }
                    } catch (NumberFormatException ignored) {}
                } else {
                    Enemigo objetivo = elegirEnemigo(enemigosVivos);
                    if (objetivo != null){
                        h.usarHabilidadEspecial(objetivo, sala.getEnemigos());
                    }
                }
            } else {
                Enemigo objetivo = elegirEnemigo(enemigosVivos);
                if (objetivo != null) h.atacar(objetivo);
            }
            enemigosVivos = sala.getEnemigosVivos();
            heroesVivos = getHeroesVivos();
        }
    }

    private Enemigo elegirEnemigo(ArrayList<Enemigo> enemigosVivos) {
        System.out.println("A quien atacar?");
        for (int i = 0; i < enemigosVivos.size(); i++) {
            Enemigo e = enemigosVivos.get(i);
            System.out.println((i + 1) + ". " + e.getNombre() + " (" + e.getPuntosVidaActual() + "/" + e.getPuntosVidaMax() + " HP)");
        }
        try {
            int numEnemigo = Integer.parseInt(sc.nextLine()) - 1;
            if (numEnemigo >= 0 && numEnemigo < enemigosVivos.size()) {
                return enemigosVivos.get(numEnemigo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Cada enemigo vivo ataca a un héroe aleatorio
     * El funcionamiento de este metodo es el siguiente --->
     * -Generamos un numero random de heroe para luego en el arraylist coger una posicion random
     * y por ende seleccionar a un heroe random para que el enemigo lo ataque
     * -Despues de generar el numero random en base al .size() del arraylist de heroes,
     * el enemigo ataca a este haciendo un heroes.get() en la posicion RANDOM.
     */

    public void turnoEnemigos() {
        ArrayList<Heroe> objetivosValidos = getHeroesVivos();
        int usosHabEspecial = 5;
        for (Enemigo e : sala.getEnemigosVivos()) {
            if (objetivosValidos.isEmpty()){
                break;
            }

            // Elegimos un héroe vivo al azar
            int indice = (int)(Math.random() * objetivosValidos.size());
            Heroe objetivo = objetivosValidos.get(indice);

            // Pasamos el objetivo Y la lista de héroes (por si el Dragón usa área)
            if (usosHabEspecial != 0){
                e.usarHabilidadEspecial(objetivo, this.heroes);
                usosHabEspecial--;
            } else{
                e.atacar(objetivo);
            }


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
            if (estadisticas != null) {
                estadisticas.sumarTurno();
            }
        }
        
        // Si ganamos, distribuimos la experiencia
        if (sala.todosEnemigosMuertos()) {
            distribuirExperiencia();
        }
    }

    public void mostrarEstadoCombate() {
        IO.println("--- Estado del combate ---");
        IO.println("Heroes:");
        for (Heroe h : heroes) {
            IO.println(" - " + h.getNombre() + " (" + h.getTipo() + "): " +
                    h.getPuntosVidaActual() + "/" + h.getPuntosVidaMax() + " HP");
        }
        IO.println("Enemigos:");
        for (Enemigo e : sala.getEnemigos()) {
            IO.println(" - " + e.getNombre() + " (" + e.getTipo() + "): " +
                    e.getPuntosVidaActual() + "/" + e.getPuntosVidaMax() + " HP");
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
                if (estadisticas != null) {
                    estadisticas.enemigoDerrotado();
                }
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

            if (estadisticas != null) {
                estadisticas.salaCompletada();
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






