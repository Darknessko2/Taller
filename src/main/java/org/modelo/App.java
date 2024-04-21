package org.modelo;


import java.time.LocalDate;

public class App {
    public static void main( String[] args ){
        try {
            Cliente cliente = new Cliente("gerson luque","51703435x","625085332");
            Vehiculo vehiculo = new Vehiculo("seat","a","2107djs");
            LocalDate fechaInicio = LocalDate.of(2024,1,1);
            LocalDate fechaFin = LocalDate.of(2024,2,1);

            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            revision.anadirHoras(100);
            revision.cerrar(fechaFin);
            System.out.println(revision.getFechaFin());



        }catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }
    }
}
