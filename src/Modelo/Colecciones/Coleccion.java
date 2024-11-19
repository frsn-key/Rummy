package Modelo.Colecciones;

import Modelo.Cartas.Carta;

import java.util.Comparator;
import java.util.Random;

public class Coleccion {

    //-----------------------------Metodos y Atributos Static----------------------------------------\\
    public static Coleccion crearColeccion(int tamano){
        if(tamano<=0)return null;
        return new Coleccion(tamano);
    }

    //-----------------------------Atributos----------------------------------------\\
    private Object[] objects;
    private int cant;

    //------------------------------Metodos Protected y Privados------------------------------------\\
    protected Coleccion(int tamano) {this.cant=0;this.objects = new Object[tamano];}
    protected void Eliminar(int index){
        if(index<=0)return ;
        for (int i = index-1; i < this.cant - 1; i++) {
            this.objects[i] = this.objects[i + 1];
        }
        this.objects[this.cant - 1] = null;
        this.cant--;
    }
    private Object getColeccion(){return this.objects;}
    protected void Intercambiar(int index1, int index2){
        if((index1<=0)||(index2<=0))return ;
        if((index1>this.cant)||(index2>this.cant)) return ;
        Object aux = this.objects[index1];
        this.objects[index1]=this.objects[index2];
        this.objects[index2]= aux;
    }
    protected Boolean Esta(Comparator<Object> comparador, Object object) {
        for (int i = 0; i < this.cant; i++) {
            if (comparador.compare(this.objects[i], object) == 0) {
                return true;
            }
        }
        return false;
    }
    protected void Ordenar(Comparator<Object> comparador){
        this.mergeSort((Object[]) this.getColeccion(),0,this.getCant()-1,comparador);
    }
    public void Clear(){for (int i = 0; i < this.cant; i++) {this.objects[i]=null;}this.cant=0;}
    //---------------------------- Metodos Public---------------------\\
    public Object Recuperar(int index){
        if(index<=0)return null;
        if(index>this.cant) return null;
        return this.objects[index-1];}
    public Boolean Agregar(Object object){
        if(object==null){return false;}
        if(this.lleno()){return false;}
        this.objects[cant] = object;
        this.cant++;
        return true;
    }
    public Object Sacar(int index){
        if(index<=0)return null;
        if (this.cant<index) return null;
        Object aux = this.Recuperar(index);
        this.Eliminar(index);
        return aux;
    }
    public Boolean getVacio(){return (this.cant==0);}
    public Boolean lleno(){return this.cant == this.objects.length;}
    public int getCant() {
        return cant;
    }
    public void Mezclar(){
        Random random = new Random();
        int indexNuevo;
        for (int i = this.getCant() - 1; i > -1; i--) {
            indexNuevo = random.nextInt(i + 1);
            this.Intercambiar(i,indexNuevo);
        }
    }
    @Override
    public String toString() {
        if(this.getVacio())return "";
        StringBuilder cartas = new StringBuilder();
        for (int i = 1; i <= this.getCant() ; i++) {
            cartas.append(this.Recuperar(i).toString()).append("\n\t\t");
        }
        return cartas.toString();
    }

    public void AgregarColeccion(Coleccion coleccion){
        if(coleccion==null)return;
        while (!coleccion.getVacio()) this.Agregar(coleccion.Sacar(coleccion.getCant()));
    }

    //--------------------------Metodo de Ordenamiento-----------------------------------------------\\
    private void mergeSort(Object[] cartas, int izquierda, int derecha, Comparator<Object> comparator) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;

            mergeSort(cartas, izquierda, medio, comparator);

            mergeSort(cartas, medio + 1, derecha, comparator);

            merge(cartas, izquierda, medio, derecha, comparator);
        }
    }
    private void merge(Object[] cartas, int izquierda, int medio, int derecha, Comparator<Object> comparator) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        Object[] izquierdaArr = new Carta[n1];
        Object[] derechaArr = new Carta[n2];

        for (int i = 0; i < n1; i++) {izquierdaArr[i] = cartas[izquierda + i];}
        for (int j = 0; j < n2; j++) {derechaArr[j] = cartas[medio + 1 + j];}

        int i = 0, j = 0; int k = izquierda;
        while (i < n1 && j < n2) {
            if (comparator.compare(izquierdaArr[i], derechaArr[j]) <= 0) {
                cartas[k] = izquierdaArr[i];i++;
            } else {cartas[k] = derechaArr[j];j++;}
            k++;
        }

        while (i < n1) {cartas[k] = izquierdaArr[i];i++;k++;}

        while (j < n2) {cartas[k] = derechaArr[j];j++;k++;}
    }
}
