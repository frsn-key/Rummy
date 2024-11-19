package Modelo.Cartas;

import Modelo.Colecciones.Coleccion;
import Modelo.Colecciones.ColeccionCartas;
import Modelo.Estados.TipodeSets;
import Vista.Mostrable;

public class CartasenMesa implements Mostrable {
    //--------------------------Metodos y Atributos Static--------------\\

    public static CartasenMesa CrarCartasenMesa(){
        return new CartasenMesa();
    }

    //--------------------------Atributos--------------\\
    private ColeccionCartas Pila_de_descarte;
    private Coleccion Sets_En_Mesa;

    //--------------------------Metodos Privados y Protected--------------\\


    private CartasenMesa() {
        this.Pila_de_descarte = ColeccionCartas.crerColecciodeCartas(52);
        this.Sets_En_Mesa = Coleccion.crearColeccion(25);
    }

    //--------------------------Metodos Publicos--------------\\
    public void AgregarPiladeDescarte(Carta carta){this.Pila_de_descarte.AgregarCarta(carta);}
    public  Carta SacarPiladeDescarte(){return this.Pila_de_descarte.SacarCarta(this.Pila_de_descarte.getCant());}
    public Boolean AgregarSet(SetsCarta set){
      if(set.getTipoSet()== TipodeSets.Null){return false;}
      if(!set.SetValido()){return false;}
      return this.Sets_En_Mesa.Agregar(set);
   }
    public Boolean AgregarASet(int index, Carta carta){
       SetsCarta set = (SetsCarta) this.Sets_En_Mesa.Recuperar(index);
       if (carta == null){return false;}
       return(set.AgregarCarta(carta));
    }
    public ColeccionCartas getPila_de_descarte(){return this.Pila_de_descarte;}
    public void clearPiladeDescarte(){this.Pila_de_descarte=ColeccionCartas.crerColecciodeCartas(52);}

    @Override
    public String toString() {
        return "Sets en mesa:"+this.Sets_En_Mesa.toString()+"\nPila de descarte:"+this.Pila_de_descarte.RecuperarCarta(this.Pila_de_descarte.getCant());
    }
}
