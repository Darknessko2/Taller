package org.modelo;


public class App {
    public static void main( String[] args ){
        try {
            Vehiculo vehiculo = new Vehiculo("seat","a","2107djs");
            System.out.println("hola");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
