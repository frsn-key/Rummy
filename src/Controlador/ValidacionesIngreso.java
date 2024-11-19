package Controlador;

import java.util.Scanner;

public class ValidacionesIngreso {
    public String ingreso() { // Devuelvo un dato ingresado.
        @SuppressWarnings("resource")
        Scanner entradaconsola = new Scanner(System.in);
        String entradaTeclado = entradaconsola.nextLine();
        return entradaTeclado;
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

    public Integer ingreso_valido(String quees,Integer min, Integer max) {
        //System.out.println("Ingrese el "+ quees);
        String datos = quees;
        Integer datoi = this.validar(datos, min, max);
        while (datoi == null) {
            System.out.println(quees+" invalido, ingrese nuevamenete");
            //System.out.println("Ingrese el "+ quees);
            datos = this.ingreso();
            datoi = this.validar(datos, min, max);
        }
        return datoi;
    }
}
