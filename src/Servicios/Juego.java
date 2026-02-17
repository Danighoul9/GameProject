package Servicios;

import Entidades.Heroe;
import Entidades.Item;
import Entidades.TipoHeroe;
import Entidades.TipoItem;

import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * Crear las 5 salas
     * Dejar que el jugador elija sus 3 héroes (o crear equipo predefinido)
     * Darles algunas pociones iniciales
     */
    public void inicializarJuego() {

        for (int i = 1; i <= 5; i++) {
            Sala sala = new Sala(i);
            sala.generarEnemigos();
            this.salas.add(sala);//asi creamos 5 salas
        }

        Scanner sc = new Scanner(System.in);
        ArrayList<Heroe> equipo = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            System.out.println("Elige a un heroe" + i + ":");
            System.out.println("1-Arquero 2-Guerrero 3-Mago");

            int opcion = sc.nextInt();
            sc.nextLine();

            System.out.println("Elige un nombre para tu heroe");
            String nombre = sc.nextLine();
            Heroe h = null;

            switch (opcion) {
                case 1 -> {
                    h = new Heroe(nombre, TipoHeroe.ARQUERO);
                }
                case 2 -> {
                    h = new Heroe(nombre, TipoHeroe.GUERRERO);
                }
                case 3 -> {
                    h = new Heroe(nombre, TipoHeroe.MAGO);
                }
            }
            if (h != null) {
                h.getItem().add(new Item(TipoItem.POCION_PEQUENA, 30));
                h.getItem().add(new Item(TipoItem.POCION_GRANDE, 60));
                this.equipo.add(h);
            }
        }
    }

    /**
     * Bucle principal del juego
     */
    public void jugar() {
        while (!juegoTerminado) {
            if (salaActual != 1) {
                menuEntreSalas();
            }

            salaActual++;

        }
    }

    /**
     * Mostrar opciones: Ver equipo / Usar pociones / Descansar / Continuar
     */
    public void menuEntreSalas() {
        int respuesta = -1;
        IO.println("¿Que deseas hacer?");
            IO.println("1. Ver equipo");
            IO.println("2. Usar pociones");
            IO.println("3. Descansar");
            IO.println("4. Continuar");
        respuesta = Integer.parseInt(IO.readln());

        switch (respuesta) {
            case 1 -> {
                IO.println("---- EQUIPO ----");
                for (Heroe h : equipo) {
                    IO.println(h);
                }
            }

            case 2 ->

            case 3 ->

            case 4 ->

            default -> IO.println("Opción no válida");
        }
    }


    public void verificarEstadoJuego() {
        boolean hayHeroesVivos = false;

        //Comprobar si queda algun heroe vivo
        for (Heroe h : equipo) {
            if (h.estaVivo()) {
                hayHeroesVivos = true;
                break;
            }
        }

        //Si no hay heroes vivos -> GAME OVER
        if (!hayHeroesVivos) {
            IO.println("GAME OVER");
            return;
        }

        //Si completo la sala 5 -> Victoria
        if (salaActual == 5 && salas.get(salaActual).todosEnemigosMuertos()) {
            juegoTerminado = true;
        }
    }

    public void mostrarResultadoFInal() {
        if (salaActual > 5) {
            IO.println("¡¡VICTORIA!!");
            IO.println("Has superado las 5 salas de la mazmorra");
            IO.println("--------------------------------");

            IO.println("Heroes que sobrevivieron");
            for (Heroe h : equipo) {
                IO.println("- " + h.getNombre() + " (" + h.getTipo() + " (" + h.getPuntosVidaActual()
                        + " (" + h.getPuntosVidaMax());
            }
        } else if(salaActual < 5 && equipo.isEmpty()) {
            IO.println("GAME OVER");
            IO.println("El equipo ha caído en la Sala " + (salaActual + 1));
        }
    }
}




