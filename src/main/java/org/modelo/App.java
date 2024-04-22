package org.modelo;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class App {
    public static void main( String[] args ){
        try {


            Cliente cliente = new Cliente("gerson luque","51703435x","625085332");
            Vehiculo vehiculo = new Vehiculo("seat","a","2107djs");
            LocalDate fechaInicio = LocalDate.of(2024,1,1);
            LocalDate fechaFin = LocalDate.of(2024,2,1);
//
            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            revision.getFechaInicio();
            Vehiculos vehiculos = new Vehiculos();
            vehiculos.insertar(vehiculo);
            vehiculos.borrar(vehiculo);
            Revisiones revisiones = new Revisiones();
            revisiones.insertar(revision);
            revisiones.anadirPrecioMaterial(null,12);

//            revision.anadirHoras(100);
//            revision.cerrar(fechaFin);
//            System.out.println(revision.getFechaFin());
            Clientes clientes = new Clientes();
            clientes.insertar(cliente);
            boolean e = clientes.modificar(cliente,"gerson luque","640231091");
            System.out.println(e);


        }catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }
    }
}
