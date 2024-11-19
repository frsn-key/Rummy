package Modelo.Cartas;

import Vista.Mostrable;

public class Carta implements Mostrable {
    //-----------------------Metodos y Atributos Statics-------------------//
    public static Carta Crear_Carta(Valor valor,Palo palo) {return new Carta(valor,palo);}

    //----------------------------Atributos----------------------------------//
    private final Palo palo;
    private final Valor valor;
    private final int punto;

    //--------------------------Metodos Privados-----------------------------//
    private Carta(Valor valor,Palo palo){
        this.palo= palo;
        this.valor = valor;
        if (valor.ordinal()==0)this.punto=15;
        else if (valor.ordinal()>8)this.punto=10;
        else this.punto=valor.ordinal()+1;
    }

    //------------------------Metodos Public-------------------------------//
    public Palo getPalo() {return palo;}
    public Valor getValor() {return valor;}
    @Override
    public String toString() {return ("["+this.getValor().toString()+" de "+this.getPalo().toString()+"]");}
    public Boolean MismoPalo(Carta carta){return this.getPalo().equals(carta.getPalo());}
    public Boolean MismoValor(Carta carta){return this.getValor().equals(carta.getValor());}
    public Boolean MismaCarta(Carta carta){
        return this.MismoValor(carta)&&this.MismoPalo(carta);
    }
    public Boolean CartaAdyacente(Carta carta){
        return (((carta.getValor().ordinal()-this.getValor().ordinal()) == 1) ||
                ((carta.getValor().ordinal()-this.getValor().ordinal()) == -1));
    }
    public int getPunto() {return punto;}


}
