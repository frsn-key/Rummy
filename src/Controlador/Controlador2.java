package Controlador;

import Modelo.Estados.EstadoGame;
import Modelo.Estados.EstadoTurno;
import Modelo.Estados.TipodeSets;
import Modelo.Juego.Partida;
import Vista.Vista;
import Vista.*;
import javax.swing.*;

public class Controlador2 implements Observador {
    private Partida partida;
    private Vista2 vista2 = new PseudoConsola(this);
    public int idJugador;

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void setIdJugador(int idJugador){
        this.idJugador= idJugador; }

    public void IniciarVista(){
        SwingUtilities.invokeLater(() -> {
            vista2.setVisible(true);
        });
        this.IniciarTurno();
    }

    public void IniciarTurno() {
        this.partida.InicializarTurno();
        this.MostrarTurno();
    }

    public void SelecciondeTurno(Integer SelecciondeTurno){
        if(SelecciondeTurno==null){return;}
         switch (SelecciondeTurno) {
            case 2 -> {partida.setEstadoTurno(EstadoTurno.ArmarSet);this.MostrarArmarSet(); }
            case 3 -> {partida.setEstadoTurno(EstadoTurno.AcomodarSetMesa);this.MostrarAcomodarset();}
            case 4 -> {partida.AgarrarCartaenMesa();this.MostrarTurno();}
            case 5 -> {partida.TirarSet();this.MostrarTurno();}
            case 6 -> {partida.setEstadoTurno(EstadoTurno.SeleccionAgregarSet);this.MostrarAgregarAlset();}
            case 1, 0 -> {partida.setEstadoTurno(EstadoTurno.TirarCarta);this.MostrarSelecciondeCarta();}
         };

    }
    public void MostrarArmarSet(){
        this.vista2.MostrarArmarset();
        partida.setEstadoTurno(EstadoTurno.SeleccionarSet);
    }
    public void MostrarAgregarAlset(){
        this.vista2.MostrarAgregarAlset();
        this.partida.setEstadoTurno(EstadoTurno.SeleccionAgregarSet);
    }

    public void MostrarSelecciondeCarta(){
        this.vista2.MostrarSeleccionCarta();
    }

    public void MostrarTurno(){
        this.MostrarMesa();
        this.MostrarMano();
        this.vista2.MostrarTurno();
    }

    public void MostrarMano(){
        this.vista2.MostrarMano(partida.JuagdorActual().toString());
    }

    public void MostrarMesa(){
        this.vista2.MostrarMesa(this.partida.CartasEnMesa());
    }

    public void Notificar(EstadoGame estadoGame, int IndexJugador){

    }

    public void procesarInput(String input){
        switch (partida.getEstadoTurno()){

            case TirarCarta ->{Integer Input = this.validar(input,1,partida.getIndexMayor());
                if (Input==null)return;
                partida.TirarCarta(Input);}

            case ArmarSet ->this.MostrarArmarSet();

            case SeleccionarSet ->{ Integer Input = this.validar(input,0,2);
                if (Input==null)return;
                partida.ArmarSet(TipodeSets.values()[Input]);
                this.MostrarTurno();this.partida.setEstadoTurno(EstadoTurno.Null);}

            case SeleccionAgregarSet -> {
                Integer Input = this.validar(input,0,2);
                if (Input==null)return;
                switch (Input){
                    case 0 -> {this.MostrarSelecciondeCarta();this.partida.setEstadoTurno(EstadoTurno.AgregarAlSet);}
                    case 1 -> {partida.ArmarSet(TipodeSets.Null);this.MostrarTurno();}
                    case 2 -> {partida.setEstadoTurno(EstadoTurno.Null);this.MostrarTurno();}
                }
            }

            case AgregarAlSet -> { Integer Input = this.validar(input,0,partida.getIndexMayor());
                if (Input==null)return;
                partida.AgregaralSet(Input);this.MostrarSet();this.MostrarTurno();}

            case Null -> {Integer Input = this.validar(input,0,6);
                if (Input==null)return;
                this.SelecciondeTurno(Input);
            }
            case AcomodarSetMesa -> {}
        }
    }

    public void MostrarAcomodarset() {
        this.vista2.MostrarAcomodarset();
    }


    public void MostrarSet() {
        this.vista2.MostrarSet(this.partida.getSet().toString());
    }

    public Integer validar(String dato, Integer min, Integer max) { // Valido el dato ingresado para saber si es un
        // numero valido.
        if (es_numero(dato)) { // verifico si es un numero.
            Integer datoint = Integer.parseInt(dato); // Lo transformo a un entero.
            if ((datoint >= min) & (datoint <= max)) { // Verifico que este en los minimos y maximos disponibles.
                return datoint; // Si pasa todo devuelvo true.
            }
        }
        return null; // Si no cumple en ser numero o estar en los minimos devuelvo false.

    }

    public Boolean es_numero(String dato) { // Devuelvo si es un numero o no.
        try { // Intento transformar el dato en un numero.
            Integer.parseInt(dato); // Si funciona devuelvo true.
            return true;
        } catch (NumberFormatException excepcion) { // Si hay algun error en la transformacion devuelvo false.
            return false;
        }
    }

}
