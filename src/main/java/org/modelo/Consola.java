package org.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {
    private final static String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private static Scanner sc = new Scanner(System.in);
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
                Opcion opcion =  Opcion.get(numero);
                return opcion;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static float leerReal(String mensaje) {
        System.out.println(mensaje);
        while (!sc.hasNextFloat()) {
            System.out.println("Ingresa un valor valido");
            sc.next();
        }
        return sc.nextFloat();
    }
    private static int leerEntero(String mensaje){
        System.out.println(mensaje);
        while (!sc.hasNextInt()) {
            System.out.println("Ingresa un valor valido");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    private static String leerCadena(String mensaje){
        System.out.println(mensaje);
        return sc.nextLine();
    }
    private static LocalDate leerFecha(String mensaje){
        System.out.println(mensaje);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        while (true){
            try {
                LocalDate fecha = LocalDate.parse(sc.nextLine(),formatter);
                return fecha;
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
            Cliente cliente = new Cliente(nombre,dni,telefono);
            return cliente;
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public static Cliente leerClienteDni(){
        String dni = leerCadena("Introduce el dni del cliente");
        try {
           Cliente cliente = Cliente.get(dni);
           return cliente;
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
            Vehiculo vehiculo = new Vehiculo(marca,modelo,matricula);
            return vehiculo;
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static Vehiculo leerVehiculoMatricula(){
        try {
            Vehiculo vehiculo = Vehiculo.get(leerCadena("Introduce la matricula"));
            return vehiculo;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static Revision leerRevision(){ // todo preguntar
        try {
            Vehiculo vehiculo = leerVehiculo();
            Cliente cliente = leerCliente();
            LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de revision");
            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            return revision;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static int leerHoras(){
        return leerEntero("Introduce las horas a agregar");
    }

    public static float leerPrecioMaterial(){
        return leerReal("Introduce el costo de los materiales");
    }

    public static LocalDate leerFechaCierre(){
        return leerFecha("Introduce la fecha de cierre");
    }
}
