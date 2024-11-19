import Modelo.Cartas.*;
import Modelo.Colecciones.ColeccionCartas;
import Modelo.Colecciones.Jugadores;
import Modelo.Estados.TipodeSets;
import Modelo.Estados.Ubicacion;
import Modelo.Juego.Partida;
import Modelo.Jugador.Jugador;
import Vista.PseudoConsola;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Jugador jugador = Jugador.crearJugador("Rsn");
        Jugadores jugadores = Jugadores.crer_Jugadores();
        jugadores.AgregarJugador(jugador);
        jugador = Jugador.crearJugador("Frsn");
        jugadores.AgregarJugador(jugador);
        Partida partida = Partida.CrearPartdia(jugadores);
        partida.InicializarPartida();
    }
}