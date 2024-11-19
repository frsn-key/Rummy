package Vista;

import Controlador.Controlador2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PseudoConsola extends JFrame implements Vista2{
    private JTextArea textArea;
    private String prompt = ">> ";  // El indicador de entrada para cada línea de comando
    private Controlador2 controlador2;

    public PseudoConsola(Controlador2 controlador2) {
        setTitle("Pseudo Consola");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.controlador2 = controlador2;
        // Crear área de texto para la consola
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        // Agregar el prompt inicial
        // Crear un campo de texto para ingresar comandos
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Escuchar cuando se presiona Enter en el campo de entrada
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = inputField.getText().trim();
                controlador2.procesarInput(command);
                inputField.setText("");  // Limpiar el campo de entrada
            }
        });

        // Añadir área de texto a un JScrollPane para hacerla desplazable
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Organizar los componentes en el JFrame
        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
    }

    // Método para simular la ejecución de comandos

    public void MostrarAgregarAlset() {
        this.Imprimir("""
                Ingrese que desea hacer con el set:\s
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
        this.Imprimir(string);
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
                6- Agregar al set.
                0- Salir.""");
    }

    public void MostrarCartasEnMano(){

    }

@Override
    public void MostrarSelecciondeSet() {
        this.Imprimir("Ingrese el indice del set a colocar cartas: (0 para salir)");

    }

    @Override
    public void MostrarAcomodarset() {
        this.Imprimir("");

    }


    public void MostrarSeleccionCarta() {
        this.Imprimir("Ingrese el indice de la carta a tirar:");
    }


    @Override
    public void MostrarSet(String string) {
        this.Imprimir(string);
    }


    public void Imprimir(String string) {
        if(string!=null)textArea.append(string+"\n");
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
        this.Imprimir("Cartas en mesa:");
        this.Imprimir(string);

    }
}