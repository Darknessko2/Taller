package org.modelo;

import java.lang.module.ModuleFinder;
import java.time.LocalDate;
import java.util.Set;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null)
            throw new IllegalArgumentException("El modelo esta vacio");

        if (vista == null)
            throw new IllegalArgumentException("La vista esta vacia");

        this.vista = vista;
        this.modelo = modelo;

        vista.setControlador(this); // la vista es inicializada con el controlador recien creado
    }

    public void comenzar(){
        modelo.comenzar();
        Cliente cliente = new Cliente("gerson luque","51703435x","640231091");
        Vehiculo vehiculo = new Vehiculo("seat","ibiza","2107djs");
        insertar(cliente);
        insertar(vehiculo);

        LocalDate fechaInicio = LocalDate.of(2001,10,10);
        Revision revision = new Revision(fechaInicio,cliente,vehiculo);
        insertar(revision);

        anadirPrecioMaterial(revision,10);

        vista.comenzar();
    }
    public void terminar(){
        vista.terminar();
        modelo.terminar();
    }

    public void insertar(Cliente cliente){
        modelo.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo){
        modelo.insertar(vehiculo);
    }

    public void insertar(Revision revision){
        modelo.insertar(revision);
    }

    public Cliente buscar(Cliente cliente){
       return modelo.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        return modelo.buscar(vehiculo);
    }

    public Revision buscar(Revision revision){
        return modelo.buscar(revision);
    }

    public boolean modificar(Cliente cliente,String nombre ,String telefono){
        try {
            modelo.modificar(cliente,nombre,telefono);
            return true;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void anadirHoras(Revision revision,int horas){
        try {
            modelo.anadirHoras(revision,horas);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial){
        try {
            modelo.anadirPrecioMaterial(revision,precioMaterial);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void cerrar(Revision revision, LocalDate fechaFin){
        try {
            modelo.cerrar(revision,fechaFin);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrar(Cliente cliente){
        try {
            modelo.borrar(cliente);
        }catch (IllegalArgumentException e  ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrar(Vehiculo vehiculo){
        try {
            modelo.borrar(vehiculo);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void borrar(Revision revision){
        try {
            modelo.borrar(revision);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Set<Cliente> getClientes(){
        return modelo.getClientes();
    }

    public Set<Vehiculo> getVehiculos(){
        return modelo.getVehiculos();
    }

    public Set<Revision> getRevisiones(){
        return modelo.getRevisiones();
    }

    public Set<Revision> getRevisiones(Cliente cliente){
        return modelo.getRevisiones(cliente);
    }

    public Set<Revision> getRevisiones(Vehiculo vehiculo){
        return modelo.getRevisiones(vehiculo);
    }
}
