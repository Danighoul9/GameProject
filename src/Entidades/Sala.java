package Entidades;

public class Sala {

    private int numeroSala;
    private boolean completada;
    private List<Enemigo> enemigos;

    //Constructor
    public Sala(int numeroSala, boolean completada) {
        this.numeroSala = numeroSala;
        this.completada = completada;
        this.enemigos = new ArrayList();
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

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(List<Enemigo> enemigos) {
        this.enemigos = enemigos;
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

    //Metodos
    public void generarEnemigos() {

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


}
