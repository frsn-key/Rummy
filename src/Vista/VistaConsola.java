package Vista;

import Controlador.ValidacionesIngreso;

import javax.swing.*;

public class VistaConsola implements Vista2{

    @Override
    public void MostrarAgregarAlset() {
        this.Imprimir("""
                Ingrese que tipo de set desea armar:\s
                0- Agregar carta al Set.
                1- LimpiarSet.
                2- Salir.
                """);
    }

    @Override
    public void MostrarMano(String string) {
        this.Imprimir(string);
    }

    public void MostrarMesa(String string) {

    }

    @Override
    public void MostrarSet(String string) {

    }


    public void MostrarSetsEnMesa() {

    }


    public void MostrarTurno() {
        this.Imprimir("""
                Ingrese que desea hacer en su turno:\s
                1- Descartar Carta.
                2- Armar Set.
                3- Acomodar Set en Mesa.
                4- Agarrar de la pila de Descarte.
                5- Tirar Set.
                0- Salir.""");
    }

    public void MostrarCartasEnMano(){

    }


    public void MostrarSelecciondeSet() {
        this.Imprimir(" Ingrese el indice del set a colocar cartas. (0 para salir)");

    }

    public void MostrarAcomodarset() {

    }


    public void MostrarSeleccionCarta() {
        this.Imprimir(" Ingrese el indice de la carta a tirar. (0 para salir)");
    }


    public void Imprimir(String string) {
        System.out.println(string);
    }

    @Override
    public void setVisible(boolean b) {

    }

    @Override
    public void MostrarArmarset() {
        this.Imprimir("""
                Ingrese que tipo de set desea armar:\s
                0- Triada (Cartas Iguales).
                1- Escalera.
                2- LimpiarSet.""");
    }


    public void MostrarCartasenMesa(String string) {
        System.out.println("Cartas en mesa:");
        System.out.println(string);

    }


}
