package Entidades;

public class Item {
    public String nombre;
    public TipoItem tipo;
    public int ValorCuracion;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", tipo=").append(tipo);
        sb.append(", ValorCuracion=").append(ValorCuracion);
        sb.append('}');
        return sb.toString();
    }

    public Item(String nombre, TipoItem tipo, int valorCuracion) {
        this.nombre = nombre;
        this.tipo = tipo;
        ValorCuracion = valorCuracion;
        switch (tipo){
            case POCION_PEQUENA:
                this.ValorCuracion = 30;
                break;
            case POCION_GRANDE:
                this.ValorCuracion = 60;
            case ELIXIR:
                this.ValorCuracion = 100;


        }
    }
}
