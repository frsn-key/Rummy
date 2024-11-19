package Modelo.Colecciones;

import Modelo.Estados.EstadoJugador;
import Modelo.Jugador.Jugador;

public class Jugadores extends Coleccion{
    //--------------------------Metodos y Atributos Static--------------\\
    public static Jugadores crer_Jugadores(){return new Jugadores();}
    //--------------------------Atributos--------------\\
    private int jugadoresFinalizados;
    //--------------------------Metodos Privados y Protected--------------\\
    private Jugadores (){super(4);this.jugadoresFinalizados =0;}

    //--------------------------Metodos Publicos--------------\\
    public void AgregarJugador(Jugador jugador){this.Agregar(jugador);}

    public void MezclarJugadores(){this.Mezclar();}

    public Jugador get_Jugador(int index){return (Jugador) this.Recuperar(index);}

    public void ActualizarFinalizados(){this.jugadoresFinalizados++;}

    public int getJugadoresFinalizados() {return jugadoresFinalizados;}

    public void ActualizarPuntos(){
        for (int i =1;i<=this.getCant();i++){
            this.get_Jugador(i).ActualizarPuntos();
        }
    }

    public void setEstadoJugadores(EstadoJugador estado){
        for (int i =1;i<=this.getCant();i++){
            this.get_Jugador(i).setEstadoJugador(estado);
        }
    }

}
