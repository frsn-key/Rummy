package Controlador;

import Modelo.Estados.EstadoGame;
import Modelo.Estados.EstadoJugador;
import Modelo.Estados.EstadoTurno;
import Modelo.Estados.TipodeSets;

import javax.swing.*;

public interface Observador {
    public void setIdJugador(int idJugador);
    public void IniciarVista();
     void IniciarTurno();
    public void SelecciondeTurno(Integer SelecciondeTurno);
    public void MostrarArmarSet();
    public void MostrarAgregarAlset();
    public void MostrarSelecciondeCarta();
    public void MostrarTurno();
    public void MostrarMano();
    public void MostrarMesa();
    public void Notificar(EstadoGame estadoGame, int IndexJugador);
    public void procesarInput(String input);
     void MostrarAcomodarset();
     void MostrarSet();



}
