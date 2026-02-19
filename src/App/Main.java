package App;

import Servicios.Juego;

public class Main {
    static void main(String[] args) {
        Juego juego = new Juego(1);
        juego.inicializarJuego();
        juego.jugar();
    }
}
