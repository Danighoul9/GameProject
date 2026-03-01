package Servicios;

public class Estadisticas {
    private int turnosTotales;
    private int danoHechoHeroes;
    private int danoRecibidoHeroes;
    private int enemigosDerrotados;
    private int salasCompletadas;

    public void sumarTurno() {
        turnosTotales++;
    }

    public void sumarDanoHecho(int danio) {
        danoHechoHeroes += danio;
    }

    public void sumarDanoRecibido(int danio) {
        danoRecibidoHeroes += danio;
    }

    public void enemigoDerrotado() {
        enemigosDerrotados++;
    }

    public void salaCompletada() {
        salasCompletadas++;
    }

    public void mostrar() {
        System.out.println("=== ESTADÍSTICAS FINALES ===");
        System.out.println("Turnos totales: " + turnosTotales);
        System.out.println("Daño hecho por héroes: " + danoHechoHeroes);
        System.out.println("Daño recibido por héroes: " + danoRecibidoHeroes);
        System.out.println("Enemigos derrotados: " + enemigosDerrotados);
        System.out.println("Salas completadas: " + salasCompletadas);
    }
    //Trabajo realizado
}

