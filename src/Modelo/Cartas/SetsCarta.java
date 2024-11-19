package Modelo.Cartas;

import Modelo.Colecciones.ColeccionCartas;
import Modelo.Estados.TipodeSets;
import Modelo.Estados.Ubicacion;

public class SetsCarta extends ColeccionCartas {
    //--------------------------Metodos y Atributos Static--------------\\
    public static SetsCarta CrearSet(){return new SetsCarta(13);}

    //--------------------------Atributos--------------\\
    private TipodeSets tipo;
    private Ubicacion ubicacion;

    //--------------------------Metodos Privados y Protected--------------\\

    private SetsCarta(int tamano) {
        super(tamano);
        this.tipo = TipodeSets.Null;
        this.ubicacion = Ubicacion.Null;
    }
    private Boolean VerificarEscalera(Carta carta){
        if(TipodeSets.Escalera!=this.tipo)return false;
        if(this.getCant()==0){return true;}
        Carta cartasetFin = this.RecuperarCarta(this.getCant());
        if (cartasetFin==null)return true;
        if (!cartasetFin.MismoPalo(carta))return false;
        Carta cartasetInic = this.RecuperarCarta(1);
        return (cartasetInic.CartaAdyacente(carta)||cartasetFin.CartaAdyacente(carta));
    }
    private Boolean VerficarTriada(Carta carta){
        if(TipodeSets.Triada!=this.tipo)return false;
        Carta cartaset = this.RecuperarCarta(this.getCant());
        if (cartaset==null)return true;
        return (cartaset.MismoValor(carta));
    }
    private Boolean VerificarTamano(){return ((((this.ubicacion == Ubicacion.MANO)||(this.getTipoSet()==TipodeSets.Triada))
            &&(this.getCant()>=4)));}

    //--------------------------Metodos Publicos--------------\\
    public Boolean AgregarCarta(Carta carta){
        if(this.getTipoSet() == TipodeSets.Null){return false;}
        if(this.VerificarTamano())return false;
        if((this.getTipoSet() == TipodeSets.Escalera)&&(this.VerificarEscalera(carta))){super.AgregarCarta(carta);return true;}
        if((this.getTipoSet() == TipodeSets.Triada)&&(this.VerficarTriada(carta))){super.AgregarCarta(carta);return true;}
        this.Ordenar_Numero();
        return false;
    }
    public void setTipoSets(TipodeSets sets){this.tipo = sets;}
    public TipodeSets getTipoSet() {
        return tipo;
    }
    public Carta getCarta(int index){return this.SacarCarta(index);}
    public Boolean SetValido(){
        Boolean flag = true;
        for (int i = 1; i < this.getCant(); i++) {
            if(this.getTipoSet()==TipodeSets.Escalera){flag &= this.RecuperarCarta(i).CartaAdyacente(this.RecuperarCarta(i+1));}
            if(this.getTipoSet()==TipodeSets.Triada){flag &= this.RecuperarCarta(i).MismoValor(this.RecuperarCarta(i+1));}
        }
        return flag;
    }
    public void setUbicacion(Ubicacion ubicacion){
        this.ubicacion = ubicacion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    @Override
    public String toString() {
        return "\nSetsCarta:  \n" +
                "{\ttipo: " + tipo +"\n"+
                "\tset: " + super.toString() +"\n"+
                "\tubicacion=" + ubicacion +"}\n";
    }


}
