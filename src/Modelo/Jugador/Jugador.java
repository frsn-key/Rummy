package Modelo.Jugador;

import Modelo.Cartas.Carta;
import Modelo.Cartas.SetsCarta;
import Modelo.Colecciones.ColeccionCartas;
import Modelo.Estados.EstadoJugador;
import Modelo.Estados.TipodeSets;
import Modelo.Estados.Ubicacion;

public class Jugador {
    //--------------------------Metodos y Atributos Static--------------\\
    private static Integer id_actual = 0;
    private static Integer getId_actual(){return Jugador.id_actual;}
   public static Jugador crearJugador(String nombre){return new Jugador(nombre);}

    //--------------------------Atributos--------------\\
    private String nombre;
    private Integer id;
    private ColeccionCartas mano;
    private EstadoJugador estadoJugador;
    private int PuntosPartida;
    private SetsCarta setJugador;

    //--------------------------Metodos Privados y Protected--------------\\
    private Jugador(String nombre) {
        this.nombre = nombre;
        this.id = Jugador.getId_actual();
        this.mano = ColeccionCartas.crerColecciodeCartas(52);
        this.estadoJugador = EstadoJugador.Inactivo;
        this.PuntosPartida=0;
        Jugador.id_actual++;
    }

    private int ContarPuntos(){
        if(this.mano.getVacio()){return -10;}
        int puntos = 0;
        for (int i = 1; i < this.mano.getCant() ; i++) {
           puntos += this.mano.RecuperarCarta(i).getPunto();
        }
        return puntos;
    }

    //--------------------------Metodos Publicos--------------\\

    public void AgregarCarta(Carta carta){
        if (carta == null)return;
        if(this.estadoJugador!=EstadoJugador.Inactivo)this.mano.AgregarCarta(carta);
    }
    public Carta SacarCarta(int index){
        if(this.estadoJugador.equals(EstadoJugador.Turno))return this.mano.SacarCarta(index);
        else return null;
    }
    public void setEstadoJugador(EstadoJugador estadoJugador){this.estadoJugador = estadoJugador;}
    public int getCant() { return this.mano.getCant();}
    public String getNombre() {return nombre;}
    public Integer getId() {return id;}
    public EstadoJugador getEstadoJugador() {return estadoJugador;}
    @Override
    public String toString() {
        return ("Jugador: "+this.getNombre()+" \n\t" +
                "ID: "+this.getId()+"\n\t" +
                "Estado: "+this.getEstadoJugador()+"\n\t" +
                "Cartas:\n\t\t"+this.mano.toString());
    }
    public void ActualizarPuntos(){
        if (this.estadoJugador!= EstadoJugador.Finalizo){return;}
        this.PuntosPartida += this.ContarPuntos();
    }

    public void ArmarSet(TipodeSets sets){
        //if(!this.estadoJugador.equals(EstadoJugador.Turno))return;
        if(sets.equals(TipodeSets.Null)){this.mano.AgregarColeccion(this.setJugador);this.setJugador=null;};
        SetsCarta set = SetsCarta.CrearSet();
        set.setTipoSets(sets);
        set.setUbicacion(Ubicacion.MANO);
        this.setJugador = set;
    }
    public Boolean Agregar_AlSet(Carta carta){
        //if(!this.estadoJugador.equals(EstadoJugador.Turno))return false;
        return this.setJugador.AgregarCarta(carta);
    }
    public SetsCarta getSetJugador(){
        return this.setJugador;
    }

    public Boolean TieneSetArmado(){if(this.setJugador==null){return false;} return this.setJugador.getCant()!=0;}

    public void LimpiarSet(){this.setJugador=null;}

}