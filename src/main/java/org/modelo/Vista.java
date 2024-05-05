package org.modelo;

import java.time.LocalDate;

public class Vista {

    private Controlador controlador;
    public void setControlador(Controlador controlador){
        if (controlador == null)
            throw new IllegalArgumentException("Controlador vacio");
        else
            this.controlador = controlador;
    }

    public void comenzar(){
        Consola.mostrarMenu();
        Opcion opcion ;
        do {
            opcion = Consola.elegirOpcion();
            if (opcion != Opcion.SALIR)
                ejecutar(opcion);
        }while (opcion != Opcion.SALIR);
    }

    public void terminar(){
        System.out.println("La vista ha terminado");
    }

    private void ejecutar(Opcion opcion ){
        switch (opcion){
        }
    }

    private void insertarCliente(){
        Consola.mostrarCabezera("Nuevo cliente");
        try {
            Cliente cliente = Consola.leerCliente();
            controlador.insertar(cliente);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void insertarVehiculo(){
        Consola.mostrarCabezera("Nuevo vehiculo");
        try {
            Vehiculo vehiculo = Consola.leerVehiculo();
            controlador.insertar(vehiculo);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void insertarRevision(){
        Consola.mostrarCabezera("Nuevo vehiculo");
        try {
            Revision revision = Consola.leerRevision();
            controlador.insertar(revision);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void buscarCliente(){
        Consola.mostrarCabezera("Buscador de clientes");
        Cliente cliente = controlador.buscar(Consola.leerClienteDni());

        if (cliente != null){
            System.out.println("Informacion del cliente");
            System.out.println(cliente);
        }else
            System.out.println("El cliente no ha sido encontrado");
    }

    private void buscarVehiculo(){
        Consola.mostrarCabezera("Buscador de vehiculos");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());

        if (vehiculo != null){
            System.out.println("Informacion del vehiculo");
            System.out.println(vehiculo);
        }else
            System.out.println("El vehiculo no ha sido encontrado");
    }

    private void buscarRevision(){ // todo preguntar
    }

    private void modificarCliente() {
        Cliente buscado = Consola.leerClienteDni();
        Cliente cliente = controlador.buscar(buscado);

        System.out.println(cliente);
        String nombre = Consola.leerNuevoNombre();
        String telefono = Consola.leerNuevoTelefono();
        boolean modificado = controlador.modificar(cliente, nombre, telefono);

        if (modificado)
            System.out.println("El cliente ha sido modificado");
        else
            System.out.println("No se ha modificado el cliente");
    }

    public void anadirHoras(){
        try {
            Revision revision = Consola.leerRevision();
            int horas = Consola.leerHoras();
            controlador.anadirHoras(revision,horas);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void anadirPrecioMaterial(){
        try {
            Revision revision = Consola.leerRevision();
            float precio = Consola.leerPrecioMaterial();
            controlador.anadirPrecioMaterial(revision,precio);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void cerrarRevision(){
        try {
            Revision revision = Consola.leerRevision();
            LocalDate fechaFin = Consola.leerFechaCierre();
            controlador.cerrar(revision,fechaFin);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrarCliente(){
        try {
            controlador.borrar(Consola.leerClienteDni());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrarVehiculo(){
        try {
            controlador.borrar(Consola.leerVehiculoMatricula());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrarRevision(){
        try {
            controlador.borrar(Consola.leerRevision());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void listarClientes(){
        controlador.getClientes().forEach(System.out::println);
    }

    private void listarVehiculos(){
        controlador.getVehiculos().forEach(System.out::println);
    }

    private void listarRevisiones(){
        controlador.getRevisiones().forEach(System.out::println);
    }

    private void salir(){
        System.out.println("adios");
    }
}
