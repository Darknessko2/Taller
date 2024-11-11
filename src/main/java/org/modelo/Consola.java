package org.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {
    private final static String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    private static Scanner sc = new Scanner(System.in);

    private Consola(){}; // constructor privado para evitar instancias
    public static void mostrarCabezera(String mensaje){
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }
    public static void mostrarMenu(){
        mostrarCabezera("Gestion del taller");
        Opcion.opciones.forEach((k,v) -> System.out.println(v));
    }

    public static Opcion elegirOpcion(){
        while (true){
            try {
                int numero = leerEntero("Introduce el numero de la opcion");
                return Opcion.get(numero);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static float leerFloat(String mensaje) {
        System.out.println(mensaje);
        while (!sc.hasNextFloat()) {
            System.out.println("Ingresa un valor valido");
            sc.next();
        }
        return sc.nextFloat();
    }
    public static int leerEntero(String mensaje){
        System.out.println(mensaje);
        while (!sc.hasNextInt()) {
            System.out.println("Ingresa un valor valido");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    public static String leerCadena(String mensaje){
        System.out.println(mensaje);
        return sc.nextLine();
    }
    public static LocalDate leerFecha(String mensaje){
        System.out.println(mensaje);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        while (true){
            try {
                return LocalDate.parse(sc.nextLine(),formatter);
            }catch (DateTimeParseException e ){
                System.out.println("El formato del fecha no es correcto");
                System.out.println("Formato correcto : "+CADENA_FORMATO_FECHA);
            }
        }
    }

    public static Cliente leerCliente() {
        String nombre = leerCadena("Introduce el nombre y apellido");
        String dni = leerCadena("Introduce el dni");
        String telefono = leerCadena("Introduce el telefono del cliente");
        try {
            return new Cliente(nombre,dni,telefono);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public static Cliente leerClienteDni(){
        String dni = leerCadena("Introduce el dni del cliente");
        try {
            return Cliente.get(dni);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public static String leerNuevoNombre(){
        return leerCadena("Introduce el nombre y apellido del cliente");
    }

    public static String leerNuevoTelefono(){
        return leerCadena("Introduce el telefono del cliente");
    }

    public static Vehiculo leerVehiculo(){
        String matricula = leerCadena("Introduce la matricula");
        String marca = leerCadena("Introduce la marca");
        String modelo = leerCadena("Introduce el modelo");

        try {
            return new Vehiculo(marca,modelo,matricula);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static Vehiculo leerVehiculoMatricula(){
        try {
            return Vehiculo.get(leerCadena("Introduce la matricula"));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static Revision leerRevision(){
        try {
            Vehiculo vehiculo = leerVehiculo();
            Cliente cliente = leerCliente();
            LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de revision");
            return new Revision(fechaInicio,cliente,vehiculo);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static int leerHoras(){
        return leerEntero("Introduce las horas a agregar");
    }

    public static float leerPrecioMaterial(){
        return leerFloat("Introduce el costo de los materiales");
    }

    public static LocalDate leerFechaCierre(){
        return leerFecha("Introduce la fecha de cierre \nFormato -> "+CADENA_FORMATO_FECHA);
    }
    public static LocalDate leerFechaInicio(){
        return leerFecha("Introduce la fecha de inicio \nFormato -> "+CADENA_FORMATO_FECHA);
    }
}
