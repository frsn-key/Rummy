package Modelo.Cartas;
import Modelo.Colecciones.ColeccionCartas;

import java.util.Comparator;
import java.util.Random;

public class Mazo {

    //--------------------------Metodos y Atributos Static--------------\\
    public static Mazo crearMazo(){return new Mazo();}

    //--------------------------Atributos--------------\\
    private ColeccionCartas mazodeCartas;

    //--------------------------Metodos Privados y Protected--------------\\
    private Mazo() {this.mazodeCartas = ColeccionCartas.crerColecciodeCartas(52);}
    private void GenerarCartas(){
        for (Palo palo: Palo.values()){ //{if (!(palo.equals(Palo.JOKER)))
            for (Valor valor : Valor.values()) {this.mazodeCartas.AgregarCarta(Carta.Crear_Carta(valor,palo));}
        }
        // this.mazodeCartas.Agregar(Carta.Crear_Carta(Valor.AS,Palo.JOKER));
       // this.mazodeCartas.Agregar(Carta.Crear_Carta(Valor.AS,Palo.JOKER));
    }

    //--------------------------Metodos Publicos--------------\\
    public void InicializarMazo(){
        this.GenerarCartas();
        this.Mezclar_cartas();
    }
    public Carta getCarta(){return this.mazodeCartas.SacarCarta(this.mazodeCartas.getCant());}
    public Boolean getVacio() {return mazodeCartas.getVacio();}
    public Integer geCant(){return this.mazodeCartas.getCant();}
    public void Mezclar_cartas(){
        this.mazodeCartas.Mezclar();
    }
    public void AgregarPiladeDescarte(ColeccionCartas piladeDescarte){
        if(this.mazodeCartas.getCant()!=0){return;}
        mazodeCartas.AgregarColeccion(piladeDescarte);
    }
}
