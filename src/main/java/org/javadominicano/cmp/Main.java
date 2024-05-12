package org.javadominicano.cmp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        //Creando el subcriptor
        Suscriptor cliente = new Suscriptor();
        cliente.start();

        //iniciando el Publicador
        Publicador.iniciarPrueba();

        //
        System.out.println("Para salir presionar Enter");
        Scanner s = new Scanner(System.in);
        String dato = s.nextLine();
        System.exit(0);
    }
}
