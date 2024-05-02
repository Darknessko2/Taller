package org.modelo;

import java.util.*;

public class Clientes {
    private Set<Cliente> clientes;

    public Clientes() {
        this.clientes = new HashSet<>();
    }
    /*
    Crea el método get que devolverá una nueva lista con los mismos elementos (no
    debe crear nuevas instancias)
     */
    public Set<Cliente> get(){
        return new HashSet<>(clientes);
    }
    public void insertar(Cliente cliente){
        if (cliente != null){
            clientes.add(cliente);
        }
    }
    public boolean modificar(Cliente cliente, String nombre, String telefono){ // todo preguntar si es mejor separar el metodo en dos
        boolean esModificado = false;

        try {
            if (!clientes.contains(cliente))
                throw new IllegalArgumentException("El cliente no existe");

            if (nombre == null || nombre.isEmpty())
                throw new IllegalArgumentException("El campo nombre esta vacio");
            else
                cliente.setNombre(nombre);

            if (telefono == null || telefono.isEmpty())
                throw new IllegalArgumentException("El campo telefono esta vacio");
            else
                cliente.setTelefono(telefono);

            esModificado = true; // si se ha modificado los cambios retorna verdadero
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }finally {
            return esModificado;
        }
    }
    public Cliente buscar(Cliente cliente){
        return clientes.stream()
                .filter(n -> n.equals(cliente))
                .findFirst()
                .orElse(null);
    }
    public void borrar(Cliente cliente){
        if(clientes.remove(cliente))
            throw new IllegalArgumentException("El cliente no se ha borrado porque no esta en la lista");
    }

}
