package Entidades;

public class Item {
    public String nombre;
    public TipoItem tipo;
    public int ValorCuracion;


    public Item( TipoItem tipo, int valorCuracion) {

        this.tipo = tipo;
        ValorCuracion = valorCuracion;
        switch (tipo) {
            case POCION_PEQUENA:
                this.ValorCuracion = 30;
                break;
            case POCION_GRANDE:
                this.ValorCuracion = 60;
                break;
            case ELIXIR:
                this.ValorCuracion = Integer.MAX_VALUE;
                break;
        }

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public int getValorCuracion() {
        return ValorCuracion;
    }

    public void setValorCuracion(int valorCuracion) {
        ValorCuracion = valorCuracion;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", tipo=").append(tipo);
        sb.append(", ValorCuracion=").append(ValorCuracion);
        sb.append('}');
        return sb.toString();
    }

    //aplica la curación al héroe
    public void usar(Heroe heroe) {
        if (this.tipo == TipoItem.ELIXIR) {
            heroe.curar(Integer.MAX_VALUE);
        } else {
            heroe.curar(ValorCuracion);
        }
    }

}
