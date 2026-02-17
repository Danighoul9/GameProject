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

        for (int i = 1; i <= 4; i++) {
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
        Combate c = new Combate(0);
        while (!juegoTerminado) {
            if (salaActual != 0) {
                menuEntreSalas();
            }

            c.iniciarCombate();
            verificarEstadoJuego();
            salaActual++;

        }
    }

    /**
     * Mostrar opciones: Ver equipo / Usar pociones / Descansar / Continuar
     */
    public void menuEntreSalas() {
        int respuesta = 0;

        do {
            IO.println("----* MENU ENTRE SALAS *----");
            IO.println("1. Ver equipo");
            IO.println("2. Usar poción");
            IO.println("3. Descansar");
            IO.println("4. Continuar");

            respuesta = Integer.parseInt(IO.readln());

            switch (respuesta) {
                case 1 -> {
                    IO.println("--- EQUIPO ---");
                    for (Heroe h : equipo) {
                        IO.println(h);
                    }
                }

                case 2 -> {
                    //Pedimos al usuario que elija un heroes
                    IO.println("Elige un héroe:");

                    //Mostramos los heroes en el equipo
                    for (int i = 0; i < equipo.size(); i++) {
                        IO.println((i + 1) + ". " + equipo.get(i).getNombre());
                    }
                    int idHeroe = Integer.parseInt(IO.readln()) - 1;

                    //Si el numero otorgado por el usuario no es valido lo notificamos y metemos un break
                    if (idHeroe < 0 || idHeroe >= equipo.size()) {
                        IO.println("Héroe no válido");
                        break;
                    }

                    //Escogemos al heroe
                    Heroe h = equipo.get(idHeroe);
                    //Si el heroe ha agotado todas sus pociones entonces notificamos que no tiene
                    if (h.getItem().isEmpty()) {
                        IO.println("Este héroe no tiene pociones");
                        break;
                    }

                    //Si el heroe tiene pociones disponibles le mostramos cuales tiene 
                    // y el usuario deberia de elegir cual pocion usar
                    IO.println("Elige una poción:");
                    for (int i = 0; i < h.getItem().size(); i++) {
                        IO.println((i + 1) + ". " + h.getItem().get(i));
                    }
                    int idItem = Integer.parseInt(IO.readln()) - 1;

                    //Si el numero otorgado por el usuario no es valido lo notificamos y metemos un break
                    if (idItem < 0 || idItem >= h.getItem().size()) {
                        IO.println("Poción no válida");
                        break;
                    }

                    //Cogemos el item, lo usamos y lo borramos del inventario del heroe
                    Item item = h.getItem().get(idItem);
                    item.usar(h);
                    h.getItem().remove(idItem);

                    IO.println("Poción usada correctamente");
                }


                case 3 -> {
                    IO.println("El equipo descansa (+20 HP)");
                    for (Heroe h : equipo) {
                        if (h.estaVivo()) {
                            h.curar(20);
                        }
                    }
                }

                case 4 -> IO.println("Continuando a la siguiente sala...");

                default -> IO.println("Opción no válida");
            }

        } while (respuesta != 4);
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
        if (salaActual > 4) {
            IO.println("¡¡VICTORIA!!");
            IO.println("Has superado las 5 salas de la mazmorra");
            IO.println("--------------------------------");

            IO.println("Heroes que sobrevivieron");
            for (Heroe h : equipo) {
                IO.println("- " + h.getNombre() + " (" + h.getTipo() + " (" + h.getPuntosVidaActual()
                        + " (" + h.getPuntosVidaMax());
            }
        } else if(salaActual < 4 && equipo.isEmpty()) {
            IO.println("GAME OVER");
            IO.println("El equipo ha caído en la Sala " + (salaActual + 1));
        }
    }
}




