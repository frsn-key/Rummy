package Modelo.Juego;

import Controlador.Controlador2;
import Modelo.Cartas.Carta;
import Modelo.Cartas.CartasenMesa;
import Modelo.Cartas.Mazo;
import Modelo.Cartas.SetsCarta;
import Modelo.Colecciones.Jugadores;
import Modelo.Estados.*;
import Modelo.Jugador.Jugador;

public class Partida {
    //--------------------------Metodos y Atributos Static--------------\\

    public static Partida CrearPartdia(Jugadores jugadores){
        return new Partida(jugadores);
    }

    //--------------------------Atributos--------------\\
    public Jugadores jugadores_Partida;
    private Mazo mazo;
    private CartasenMesa cartasenMesa;
    private EstadoTurno estadoTurno;
    private EstadoGame estadoGame;
    private Controlador2 controlador = new Controlador2();
    private int indexJugadorAct;

    //--------------------------Metodos Privados y Protected--------------\\

    private Partida(Jugadores jugadores_Partida) {
        this.jugadores_Partida = jugadores_Partida;
    }

    public void InicializarPartida(){
        this.cartasenMesa = CartasenMesa.CrarCartasenMesa();
        this.mazo = Mazo.crearMazo();
        this.mazo.InicializarMazo();
        this.estadoGame = EstadoGame.EnJuego;
        this.estadoTurno = EstadoTurno.Null;
        this.jugadores_Partida.setEstadoJugadores(EstadoJugador.Esperando);
        this.Repartir();
        this.indexJugadorAct = this.jugadores_Partida.getCant();
        this.controlador.setPartida(this);
        this.controlador.IniciarVista();
    }


    //--------------------------Metodos Publicos--------------\\

    public void VerificarSiGano(){if(jugadores_Partida.get_Jugador(this.indexJugadorAct).getCant()==0)
        {jugadores_Partida.get_Jugador(this.indexJugadorAct).setEstadoJugador(EstadoJugador.Finalizo);
        this.jugadores_Partida.ActualizarFinalizados();}}
    public void Repartir(){
        int CantCartas = (this.jugadores_Partida.getCant()==2) ? 10 : 7;
        for (int i = 0; i < CantCartas ; i++) {
            for (int j = 1; j <= this.jugadores_Partida.getCant() ; j++) {
                this.jugadores_Partida.get_Jugador(j).AgregarCarta(this.mazo.getCarta());
            }
        }
        this.cartasenMesa.AgregarPiladeDescarte(this.mazo.getCarta());
    }
    public void ArmarSet(TipodeSets tiposet){
        jugadores_Partida.get_Jugador(this.indexJugadorAct).ArmarSet(tiposet);
        this.estadoTurno = EstadoTurno.Null;
    }
    public void AgregaralSet(int index){
        Carta carta =jugadores_Partida.get_Jugador(this.indexJugadorAct).SacarCarta(index);
        if (carta==null)return;
        if(!(jugadores_Partida.get_Jugador(this.indexJugadorAct).Agregar_AlSet(carta))){jugadores_Partida.get_Jugador(this.indexJugadorAct).AgregarCarta(carta);};
        System.out.println(jugadores_Partida.get_Jugador(indexJugadorAct).TieneSetArmado());
        this.estadoTurno = EstadoTurno.Null;
    }
    public void TirarSet(){
        if(jugadores_Partida.get_Jugador(this.indexJugadorAct).getSetJugador()==null){return;}
        if(jugadores_Partida.get_Jugador(this.indexJugadorAct).getSetJugador().SetValido()) {
            jugadores_Partida.get_Jugador(this.indexJugadorAct).getSetJugador().setUbicacion(Ubicacion.MESA);
            this.cartasenMesa.AgregarSet(jugadores_Partida.get_Jugador(this.indexJugadorAct).getSetJugador());
            this.jugadores_Partida.get_Jugador(indexJugadorAct).LimpiarSet();}
        this.estadoTurno = EstadoTurno.Null;

    }
    public void AcomodarSet(int indexset,int index){
        Carta carta = jugadores_Partida.get_Jugador(this.indexJugadorAct).SacarCarta(index);
        if ((carta==null)){return;}
        if(!(this.cartasenMesa.AgregarASet(indexset,carta)))
            {jugadores_Partida.get_Jugador(this.indexJugadorAct).AgregarCarta(carta);};
        this.estadoTurno = EstadoTurno.Null;
    }
    public void TirarCarta(int index){
        this.cartasenMesa.AgregarPiladeDescarte(jugadores_Partida.get_Jugador(this.indexJugadorAct).SacarCarta(index));
        this.estadoTurno = EstadoTurno.Null;
        this.SiguienteTurno();
    }
    public void AgarrarCartaenMesa(){
        jugadores_Partida.get_Jugador(this.indexJugadorAct).AgregarCarta(cartasenMesa.SacarPiladeDescarte());
        this.setEstadoTurno(EstadoTurno.Null);
    }
    public EstadoTurno getEstadoTurno() {
        return estadoTurno;
    }
    public void setEstadoTurno(EstadoTurno estadoTurno) {
        this.estadoTurno = estadoTurno;
    }
    public void SiguienteTurno(){
        jugadores_Partida.get_Jugador(this.indexJugadorAct).setEstadoJugador(EstadoJugador.Esperando);
        this.VerificarSiGano();
        this.estadoTurno = EstadoTurno.Null;
        this.indexJugadorAct--;
        if(this.indexJugadorAct==0)this.indexJugadorAct = this.jugadores_Partida.getCant();
        if (!(((jugadores_Partida.getCant()-this.jugadores_Partida.getJugadoresFinalizados())>1)||this.estadoGame==EstadoGame.EnJuego)){
            jugadores_Partida.setEstadoJugadores(EstadoJugador.Finalizo);
            jugadores_Partida.ActualizarPuntos();
        }
        this.jugadores_Partida.get_Jugador(this.indexJugadorAct).setEstadoJugador(EstadoJugador.Turno);
        this.controlador.MostrarTurno();
     }
    public Boolean SetArmado(){
        return this.jugadores_Partida.get_Jugador(this.indexJugadorAct).TieneSetArmado();
    }
    public Jugador JuagdorActual(){
        return jugadores_Partida.get_Jugador(this.indexJugadorAct);
    }
    public String CartasEnMesa(){
        return this.cartasenMesa.toString();
    }
    public Integer getIndexMayor(){
        return this.jugadores_Partida.get_Jugador(this.indexJugadorAct).getCant();
    }

    public void InicializarTurno() {
        this.jugadores_Partida.get_Jugador(indexJugadorAct).setEstadoJugador(EstadoJugador.Turno);
    }

    public SetsCarta getSet(){
        return this.jugadores_Partida.get_Jugador(indexJugadorAct).getSetJugador();
    }


    //Metodos Obsoletos????

    public void RondaRapida(){
        Jugador jugador;
        int index = this.jugadores_Partida.getCant();
        while(jugadores_Partida.getJugadoresFinalizados()==0) {
            if(this.mazo.getVacio()){this.mazo.AgregarPiladeDescarte(this.cartasenMesa.getPila_de_descarte());this.cartasenMesa.clearPiladeDescarte();}
            this.Turno(this.estadoTurno);
            index--;
            if(index==0)index = this.jugadores_Partida.getCant();
        }
        jugadores_Partida.setEstadoJugadores(EstadoJugador.Finalizo);
        jugadores_Partida.ActualizarPuntos();
    }

    public void Ronda(){
        Jugador jugador;
        if(this.mazo.getVacio()){this.mazo.AgregarPiladeDescarte(this.cartasenMesa.getPila_de_descarte());this.cartasenMesa.clearPiladeDescarte();}
        jugador = this.jugadores_Partida.get_Jugador(indexJugadorAct);
        jugador.setEstadoJugador(EstadoJugador.Turno);
        this.Turno(estadoTurno);
        if(!jugador.getEstadoJugador().equals(EstadoJugador.Turno))this.indexJugadorAct--;
        if(this.indexJugadorAct==0)this.indexJugadorAct = this.jugadores_Partida.getCant();
        if (!(((jugadores_Partida.getCant()-this.jugadores_Partida.getJugadoresFinalizados())>1)||this.estadoGame==EstadoGame.EnJuego)){
            jugadores_Partida.setEstadoJugadores(EstadoJugador.Finalizo);
            jugadores_Partida.ActualizarPuntos();
        }
    }
    public void Turno(EstadoTurno estadoTurno){
        if(jugadores_Partida.get_Jugador(this.indexJugadorAct).getEstadoJugador()==EstadoJugador.Inactivo)return;
        if(jugadores_Partida.get_Jugador(this.indexJugadorAct).getEstadoJugador()==EstadoJugador.Finalizo)return;
        jugadores_Partida.get_Jugador(this.indexJugadorAct).AgregarCarta(this.mazo.getCarta());
        this.estadoTurno = estadoTurno;
        switch (this.estadoTurno){
            //case TirarCarta -> this.TirarCarta(jugador);
            //case ArmarSet -> this.ArmarSet(jugador,);
            //case AcomodarSetMesa -> this.AcomodarSet(jugador);
            //case PiladeDescarte -> this.AgarrarCartaenMesa();
            //case Null -> jugador.setEstadoJugador(EstadoJugador.Esperando);
        }
    }

}
