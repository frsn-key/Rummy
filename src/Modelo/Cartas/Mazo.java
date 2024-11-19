package Modelo.Cartas;
import Modelo.Colecciones.ColeccionCartas;

import java.util.Comparator;
import java.util.Random;

public class Mazo extends ColeccionCartas{

    //--------------------------Metodos y Atributos Static--------------\\
    public static Mazo crearMazo(){return new Mazo();}

    //--------------------------Atributos--------------\\

    //--------------------------Metodos Privados y Protected--------------\\
    private Mazo() {super(53);}
    private void GenerarCartas(){
        for (Palo palo: Palo.values()){ //{if (!(palo.equals(Palo.JOKER)))
            for (Valor valor : Valor.values()) {this.AgregarCarta(Carta.Crear_Carta(valor,palo));}
        }
        // this.mazodeCartas.Agregar(Carta.Crear_Carta(Valor.AS,Palo.JOKER));
       // this.mazodeCartas.Agregar(Carta.Crear_Carta(Valor.AS,Palo.JOKER));
    }

    //--------------------------Metodos Publicos--------------\\
    public void InicializarMazo(){
        this.GenerarCartas();
        this.Mezclar_cartas();
    }
    public Carta getCarta(){return this.SacarCarta(this.getCant());}
    public Integer geCant(){return this.getCant();}
    public void Mezclar_cartas(){
        this.Mezclar();
    }
    public void AgregarPiladeDescarte(ColeccionCartas piladeDescarte){
        if(this.getCant()!=0){return;}
        super.AgregarColeccion(piladeDescarte);
    }
}
