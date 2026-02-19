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
    private Estadisticas estadisticas;

    public Juego(int salaActual) {
        // Ajustar para que salaActual sea un índice válido (0-4)
        // Si se pasa 1, lo convertimos a 0 para empezar desde la primera sala
        this.salaActual = salaActual - 1;
        if (this.salaActual < 0) this.salaActual = 0;
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
        estadisticas = new Estadisticas();

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

            int opcion = 0;
            boolean valido = false;
            while(!valido) {
                try {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if(opcion >= 1 && opcion <= 3) {
                        valido = true;
                    } else {
                        System.out.println("Opción no válida. Intenta de nuevo.");
                    }
                } catch (Exception e) {
                    System.out.println("Debes ingresar un número.");
                    sc.nextLine();
                }
            }



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
        // Mientras no hayamos terminado y estemos dentro del rango de salas (0 a 4)
        while (!this.juegoTerminado && salaActual < salas.size()) {

            // 1. Antes de entrar, si no es la primera sala, dejamos descansar/curar
            if (salaActual != 0) {
                menuEntreSalas();
            }

            // 2. CREAMOS EL COMBATE CON LA SALA QUE TOCA
            Combate c = new Combate(salas.get(salaActual), this.equipo);

            System.out.println("--- ENTRANDO A LA SALA " + (salaActual + 1) + " ---");
            c.iniciarCombate();

            // 3. Revisamos si hemos muerto todos
            verificarEstadoJuego();

            // Si hemos perdido, salimos del bucle
            if (juegoTerminado) break;

            // 4. Si todos los enemigos están muertos, marcamos la sala como completada
            if (salas.get(salaActual).todosEnemigosMuertos()) {
                salas.get(salaActual).setCompletada(true);
            }

            // 5. Pasamos a la siguiente sala
            salaActual++;

            // Si hemos pasado la última sala, ganamos
            if (salaActual >= salas.size()) {
                juegoTerminado = true;
            }
        }

        // Al salir del bucle, mostramos cómo ha quedado la cosa
        mostrarResultadoFInal();
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

            try {
                respuesta = Integer.parseInt(IO.readln());
            } catch (NumberFormatException e) {
                IO.println("Debes ingresar un número válido.");
                continue;
            }


            switch (respuesta) {
                case 1 -> {
                    /**
                     * Con un poco de ayuda de la IA hemos podido recrear algo mas
                     * grafico en cuanto a ver la info basica del equipo se refiere
                     *
                     * - Hemos preguntado como podriamos tener una barrita de vida visual.
                     * - Y que formato usar para que se viera en la forma que deseabamos.
                     */
                    IO.println("\n============================================================");
                    IO.println(String.format("| %-12s | %-15s | %-10s | %-8s |", "NOMBRE", "SALUD", "HP", "POCIONES"));
                    IO.println("============================================================");

                    for (Heroe h : equipo) {
                        // Creamos una barrita visual de vida: [#####     ]
                        int barras = (int) ((double) h.getPuntosVidaActual() / h.getPuntosVidaMax() * 10);
                        String visualVida = "[" + "#".repeat(Math.max(0, barras)) + " ".repeat(Math.max(0, 10 - barras)) + "]";

                        // Mostramos la info formateada en columnas
                        IO.println(String.format("| %-12s | %-15s | %-3d/%-6d | %-8d |",
                                h.getNombre(),
                                visualVida,
                                h.getPuntosVidaActual(),
                                h.getPuntosVidaMax(),
                                h.getItem().size()
                        ));
                    }
                    IO.println("============================================================\n");
                }

                case 2 -> {
                    //Pedimos al usuario que elija un heroes
                    IO.println("Elige un héroe:");

                    //Mostramos los heroes en el equipo
                    for (int i = 0; i < equipo.size(); i++) {
                        IO.println((i + 1) + ". " + equipo.get(i).getNombre());
                    }
                    
                    int idHeroe = -1;
                    try {
                        idHeroe = Integer.parseInt(IO.readln()) - 1;
                    } catch (NumberFormatException e) {
                        IO.println("Debes ingresar un número válido.");
                        break;
                    }

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
                    
                    int idItem = -1;
                    try {
                        idItem = Integer.parseInt(IO.readln()) - 1;
                    } catch (NumberFormatException e) {
                        IO.println("Debes ingresar un número válido.");
                        break;
                    }

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
            IO.println("EL EQUIPO HA SIDO DERROTADO...");
            IO.println("GAME OVER");
            this.juegoTerminado = true;
        }
    }

    public void mostrarResultadoFInal() {
        // Verificar si hay héroes vivos
        boolean hayHeroesVivos = false;
        for (Heroe h : equipo) {
            if (h.estaVivo()) {
                hayHeroesVivos = true;
                break;
            }
        }
        
        // Si completamos todas las salas Y hay héroes vivos -> VICTORIA
        if (salaActual >= salas.size() && hayHeroesVivos) {
            IO.println("¡¡VICTORIA!!");
            IO.println("Has superado las 5 salas de la mazmorra");
            IO.println("--------------------------------");

            IO.println("Heroes que sobrevivieron:");
            for (Heroe h : equipo) {
                if (h.estaVivo()) {
                    IO.println("- " + h.getNombre() + " (" + h.getTipo() + ") - HP: " + h.getPuntosVidaActual()
                            + "/" + h.getPuntosVidaMax());
                }
            }
        } else {
            // Si no hay héroes vivos -> DERROTA
            IO.println("GAME OVER");
            IO.println("El equipo ha caído en la Sala " + (salaActual + 1));
        }
    }
}




