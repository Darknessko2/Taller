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
        Opcion opcion ;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            if (opcion != Opcion.SALIR)
                ejecutar(opcion);
        }while (opcion != Opcion.SALIR);
    }

    public void terminar(){
        System.out.println("La vista ha terminado");
    }

    private void ejecutar(Opcion opcion ){
        try {
            switch (opcion){
                case INSERTAR_CLIENTE -> insertarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case INSERTAR_REVISION -> insertarRevision();
                case BUSCAR_REVISION -> buscarRevision();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BUSCAR_CLIENTE -> buscarCliente();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case BORRAR_CLIENTE -> borrarCliente();
                case BORRAR_REVISION -> borrarRevision();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case LISTAR_CLIENTES -> listarClientes();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
                case SALIR -> salir();
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
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

    private void insertarRevision() { // todo preguntar
        Consola.mostrarCabezera("Nuevo vehiculo");
        try {
            Cliente cliente = controlador.buscar(Consola.leerClienteDni());
            Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
            LocalDate fechaInicio = Consola.leerFechaInicio();
            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            controlador.insertar(revision);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabezera("Buscador de clientes");
        try {
            Cliente buscado = Consola.leerClienteDni();
            Cliente cliente = controlador.buscar(buscado);

            if (cliente == null)
                throw new IllegalArgumentException("El cliente no ha sido encontrado");

            System.out.println("Informacion del cliente");
            System.out.println(cliente);

        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void buscarVehiculo(){
        Consola.mostrarCabezera("Buscador de vehiculos");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());

        if (vehiculo != null){
            System.out.println("Informacion del vehiculo");
            System.out.println(vehiculo);
        }else
            throw new IllegalArgumentException("El vehiculo no ha sido encontrado");
    }

    private void buscarRevision() { // todo preguntar
        Consola.mostrarCabezera("Buscador de revisiones");

        try {
            Cliente cliente = controlador.buscar(Consola.leerClienteDni());
            Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
            LocalDate fechaInicio = Consola.leerFechaInicio();

            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            Revision encontrada = controlador.buscar(revision);

            if (encontrada != null)
                System.out.println(encontrada);
            else
                throw new IllegalArgumentException("Revision no encontrada");

        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void modificarCliente() {
        Consola.mostrarCabezera("Modificacion del cliente");

        Cliente buscado = Consola.leerClienteDni();
        Cliente cliente = controlador.buscar(buscado);

        if (cliente != null)
            System.out.println(cliente);

        String nombre = Consola.leerNuevoNombre();
        String telefono = Consola.leerNuevoTelefono();

        try {
            controlador.modificar(cliente, nombre, telefono);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void anadirHoras(){
        Consola.mostrarCabezera("Agregar horas");
        try {
            Cliente cliente = controlador.buscar(Consola.leerClienteDni());
            Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
            LocalDate fechaInicio = Consola.leerFechaInicio();

            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            Revision encontrada = controlador.buscar(revision);
            int horas = Consola.leerHoras();
            controlador.anadirHoras(revision,horas);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void anadirPrecioMaterial(){
        Consola.mostrarCabezera("Agregar precio material");
        try {
            Cliente cliente = controlador.buscar(Consola.leerClienteDni());
            Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
            LocalDate fechaInicio = Consola.leerFechaInicio();

            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            float precio = Consola.leerPrecioMaterial();
            controlador.anadirPrecioMaterial(revision,precio);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void cerrarRevision(){
        Consola.mostrarCabezera("Cerrar revisiones");
        try {
            Cliente cliente = controlador.buscar(Consola.leerClienteDni());
            Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
            LocalDate fechaInicio = Consola.leerFechaInicio();

            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            LocalDate fechaFin = Consola.leerFechaCierre();
            controlador.cerrar(revision,fechaFin);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrarCliente(){
        Consola.mostrarCabezera("Borrado de clientes");
        try {
            Cliente cliente = controlador.buscar(Consola.leerClienteDni());
            Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
            LocalDate fechaInicio = Consola.leerFechaInicio();

            Revision revision = new Revision(fechaInicio,cliente,vehiculo);
            controlador.borrar(revision);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrarVehiculo(){
        Consola.mostrarCabezera("Borrado de vehiculos");
        try {
            controlador.borrar(Consola.leerVehiculoMatricula());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrarRevision(){
        Consola.mostrarCabezera("Borrado de revisiones");
        try {

        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void listarClientes(){
        Consola.mostrarCabezera("Listado de clientes");
        controlador.getClientes().forEach(System.out::println);
    }

    private void listarVehiculos(){
        Consola.mostrarCabezera("Listado de vehiculos");
        controlador.getVehiculos().forEach(System.out::println);
    }

    private void listarRevisiones(){
        Consola.mostrarCabezera("Listado de revisiones");
        controlador.getRevisiones().forEach(System.out::println);
    }

    private void listarRevisionesCliente(){
        Consola.mostrarCabezera("Listado de revisiones del cliente");
        Cliente cliente = Consola.leerClienteDni();
        controlador.getRevisiones(cliente).forEach(System.out::println);
    }

    private void listarRevisionesVehiculo(){
        Consola.mostrarCabezera("Listado de revisiones del vehiculo");
        Vehiculo vehiculo = Consola.leerVehiculoMatricula();
        controlador.getRevisiones(vehiculo).forEach(System.out::println);
    }

    private void salir(){
        System.out.println("adios");
    }
}
