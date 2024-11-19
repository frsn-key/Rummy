package Modelo.Colecciones;

import Modelo.Cartas.Carta;

import java.util.Comparator;

public class ColeccionCartas extends Coleccion {

    //--------------------------Metodos y Atributos Static--------------\\
    public static ColeccionCartas crerColecciodeCartas(int tamano){
        return new ColeccionCartas(tamano);
    }

    //--------------------------Atributos--------------\\

    //--------------------------Metodos Privados y Protected--------------\\
    protected ColeccionCartas(int tamano) {super(tamano);}
    public void Ordenar_Numero() {
        Comparator<Object> comparadorPorValor = (carta1, carta2) ->
                Integer.compare(((Carta)carta1).getValor().ordinal(), ((Carta)carta2).getValor().ordinal());
        super.Ordenar(comparadorPorValor);
    }
    protected void Ordenar_Palo() {
        Comparator<Object> comparadorPorPalo = (carta1, carta2) ->
                Integer.compare(((Carta)carta1).getPalo().ordinal(), ((Carta)carta2).getPalo().ordinal());
        super.Ordenar(comparadorPorPalo);
    }
    public Boolean EstalaCarta(Carta carta){
        return (super.Esta((carta1, carta2) ->
                (((Carta)carta1).MismaCarta((Carta)carta2) ? 0 : -1), carta));
    }
    //--------------------------Metodos Publicos--------------\\
    public Boolean AgregarCarta(Carta carta){if((carta==null)||this.EstalaCarta(carta))return false;return super.Agregar(carta);}
    public Carta SacarCarta(int index){ return (Carta)super.Sacar(index);}
    public Carta RecuperarCarta(int index){return (Carta) super.Recuperar(index);}
    @Override
    public String toString() {
        StringBuilder cartas = new StringBuilder();
        for (int i = 1; i <= this.getCant() ; i++) {
            cartas.append(super.Recuperar(i).toString()).append("\n\t\t");
        }
        return cartas.toString();

    }
}
