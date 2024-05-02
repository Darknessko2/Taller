package org.modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public Modelo(){}
    public void comenzar(){
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }
    public String terminar(){
        return "El modelo ha terminado";
    }
    public void insertar(Cliente cliente){
        clientes.insertar(new Cliente(cliente));
    }
    public void insertar(Vehiculo vehiculo){ // al ser un record es inmutable
        vehiculos.insertar(vehiculo);
    }
    public void insertar(Revision revision){ // todo preguntar
        Cliente cliente = revision.getCliente();
        Vehiculo vehiculo = revision.getVehiculo();
        revisiones.insertar(new Revision(revision.getFechaInicio(), cliente, vehiculo));
    }
    public Cliente buscar(Cliente cliente){
        Cliente resultado = clientes.buscar(cliente);
        return (resultado != null) ? new Cliente(resultado) : null;
    }
    public Vehiculo buscar(Vehiculo vehiculo){
        return vehiculos.buscar(vehiculo);
    }
    public Revision buscar(Revision revision){
        Revision resultado = revisiones.buscar(revision);
        return (resultado != null) ? new Revision(resultado) : null;
    }
    public boolean modificar(Cliente cliente,String nombre,String telefono){
        return clientes.modificar(cliente,nombre,telefono);
    }
    public void anadirHoras(Revision revision,int horas){
        try {
            revisiones.anadirHoras(revision,horas);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void anadirPrecioMaterial(Revision revision,float precioMaterial){
        try {
            revisiones.anadirPrecioMaterial(revision,precioMaterial);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void cerrar(Revision revision, LocalDate fechaFin){
        try {
            revisiones.cerrar(revision,fechaFin);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void borrar(Cliente cliente){
        try {
            revisiones.get(cliente).forEach(rev ->{ // se borra vehiculos donde las revisiones estan asociadas al cliente
                 vehiculos.borrar(rev.getVehiculo());
                 revisiones.borrar(rev);
                 });
            clientes.borrar(cliente);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void borrar(Vehiculo vehiculo){
        try {
            revisiones.get(vehiculo).forEach(rev ->{
                clientes.borrar(rev.getCliente());
                revisiones.borrar(rev);
            });
            vehiculos.borrar(vehiculo);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    // si se borra solo una revision no tiene sentido borrar todos los datos del cliente o vehiculo
    public void borrar(Revision revision){
        try {
            revisiones.borrar(revision);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /*
    Crea los diferentes métodos get, que deben devolver una nueva lista, pero que
    contenga nuevas instancias no una referencia de los elementos.
     */
    public Set<Cliente> getClientes(){
        Set<Cliente> result = new HashSet<>();
        clientes.get().forEach(cli -> result.add(new Cliente(cli)));
        return result;
    }

    public Set<Vehiculo> getVehiculos(){ // como vehiculo es un record este sera inmutable
        return vehiculos.get();
    }

    public Set<Revision> getRevisiones(){
        return revisiones.get().stream()
                .map(rev -> new Revision(rev))
                .collect(Collectors.toCollection( () -> new HashSet<>()));
    }

    public Set<Revision> getRevisiones(Cliente cliente){
        return revisiones.get(cliente).stream()
                .map(rev -> new Revision(rev))
                .collect(Collectors.toCollection(() -> new HashSet<>()));
    }

    public Set<Revision> getRevisiones(Vehiculo vehiculo){
        return revisiones.get(vehiculo).stream()
                .map(rev -> new Revision(rev))
                .collect(Collectors.toCollection(() -> new HashSet<>()));
    }

}
