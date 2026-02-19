package Servicios;

import Entidades.Enemigo;
import Entidades.Heroe;
import Entidades.TipoEnemigo;

import java.util.ArrayList;

public class Sala {

        private int numeroSala;
        private boolean completada;
        private ArrayList<Enemigo> enemigos;
        private ArrayList<Heroe> heroes;


        //Constructor
        public Sala(int numeroSala) {
            this.numeroSala = numeroSala;
            this.completada = false;
            this.enemigos = new ArrayList<>();
            // No generar aquí, se generará desde Juego.inicializarJuego()
            // generarEnemigos();
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


        /**
         * Metodo para generar enemigos dependiendo el numero de this.sala
         */
        public void generarEnemigos() {
            int aleatorio;
            switch (this.numeroSala) {
                case 1, 2 -> {
                    // Generar número aleatorio entre 0 y 2 (3 opciones: 0, 1, 2)
                    // Pero queremos que siempre haya enemigos, así que usamos 1-3
                    aleatorio = 1 + (int) (Math.random() * 3); // Valores: 1, 2, 3
                    switch (aleatorio) {
                        case 1, 2 -> {
                            Enemigo goblin1 = new Enemigo("Goblin de Fuego", TipoEnemigo.GOBLIN);
                            Enemigo goblin2 = new Enemigo("Goblin de Hielo", TipoEnemigo.GOBLIN);
                            enemigos.add(goblin1);
                            enemigos.add(goblin2);
                        }
                        case 3 ->{
                            Enemigo goblin11 = new Enemigo("Goblin de Primavera", TipoEnemigo.GOBLIN);
                            Enemigo goblin22 = new Enemigo("Goblin de Hierro", TipoEnemigo.GOBLIN);
                            Enemigo goblin3 = new Enemigo("Goblin Tosco", TipoEnemigo.GOBLIN);
                            enemigos.add(goblin3);
                            enemigos.add(goblin11);
                            enemigos.add(goblin22);
                        }
                    }
                }

                case 3, 4 -> {
                    // Generar número aleatorio entre 1 y 2 (2 opciones)
                    aleatorio = 1 + (int) (Math.random() * 2); // Valores: 1, 2
                    switch (aleatorio) {
                        case 1 -> {
                            Enemigo orco = new Enemigo("Orco Galactico", TipoEnemigo.ORCO);
                            Enemigo goblin = new Enemigo("Goblin Macarra", TipoEnemigo.GOBLIN);
                            enemigos.add(orco);
                            enemigos.add(goblin);
                        }

                        case 2 -> {
                            Enemigo orco1 = new Enemigo("Orco Galactico", TipoEnemigo.ORCO);
                            Enemigo orco2 = new Enemigo("Orco Metalico", TipoEnemigo.ORCO);
                            Enemigo goblin4 = new Enemigo("Goblin Remolon", TipoEnemigo.GOBLIN);
                            enemigos.add(orco1);
                            enemigos.add(orco2);
                            enemigos.add(goblin4);
                        }
                    }
                }


                case 5->{
                    Enemigo dragon = new Enemigo("Dragon del end", TipoEnemigo.DRAGON);
                    Enemigo orco3 = new Enemigo("Orco Pelon", TipoEnemigo.ORCO);
                    Enemigo orco4 = new Enemigo("Orco Paradisiaco", TipoEnemigo.ORCO);
                    enemigos.add(dragon);
                    enemigos.add(orco3);
                    enemigos.add(orco4);
                }
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


