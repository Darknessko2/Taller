package org.modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Revisiones {
    private Set<Revision> listaRevisiones;

    public Revisiones() {
        this.listaRevisiones = new HashSet<>();
    }
    public Set<Revision> get(){
        return new HashSet<>(listaRevisiones);
    }
    public Set<Revision> get(Cliente cliente){ // lista de revisiones cuyo cliente coincida con dni
        return listaRevisiones.stream()
                .filter(revision -> revision.getCliente().equals(cliente))
                .collect(Collectors.toCollection(() -> new HashSet<>()));
    }
    public Set<Revision> get(Vehiculo vehiculo){ // lista de revisiones cuyo vehiculo coincida con la matricula
        return listaRevisiones.stream()
                .filter(revision -> revision.getVehiculo().equals(vehiculo))
                .collect(Collectors.toCollection(() -> new HashSet<>()));
    }
    public void insertar(Revision revision){
        if (revision != null)
            listaRevisiones.add(revision);
    }
    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision){ //todo

        Consumer<Revision> check = rev -> {
            if (rev.estaCerrada()) {
                if (rev.getFechaFin().isAfter(fechaRevision))
                    throw new IllegalArgumentException("La fecha fin es superior a la fecha de revision del cliente"+rev.getCliente().getDni());

            }else
                throw new IllegalArgumentException("La revision del cliente "+rev.getCliente().getDni()+" no esta cerrada");
        };

        get(cliente).stream().forEach(check);
        get(vehiculo).stream().forEach(check);

    }
    private Revision getRevision(Revision revision){
        if (revision == null)
            throw new IllegalArgumentException("La revision esta vacia");

        return listaRevisiones.stream()
                .filter(r -> r.equals(revision))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("La revision no ha sido encontrada"));
        // si no encuentra la revision se crea una excepcion
    }
    public void anadirPrecioMaterial(Revision revision, float precioMaterial){
        try {
            getRevision(revision).anadirPrecioMaterial(precioMaterial);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void cerrar(Revision revision, LocalDate fechaFin){
        try {
            getRevision(revision).cerrar(fechaFin);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public Revision buscar(Revision revision){ // todo preguntar la diferencia entre buscar y getRevision
        return listaRevisiones.stream()
                .filter(r -> r.equals(revision))
                .findFirst()
                .orElse(null);
    }
    public void borrar(Revision revision){
        if (!listaRevisiones.contains(revision))
            throw new IllegalArgumentException("La revision no existe en la lista");
        else
            listaRevisiones.remove(revision);
    }


}
