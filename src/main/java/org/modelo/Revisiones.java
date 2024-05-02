package org.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        try {
            if (revision != null) {
                comprobarRevision(revision.getCliente(),revision.getVehiculo(),revision.getFechaInicio());
                listaRevisiones.add(revision);
            }
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    // al ejecutar este metodo comprobara que las revisiones tanto por vehiculo y cliente
    // esten de manerar correcta si no lanzara un excepcion que se podra controlar despues
    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision){
        List<Revision> revisiones = new ArrayList<>();
        revisiones.addAll(get(cliente));
        revisiones.addAll(get(vehiculo));

        for (Revision rev : revisiones){
            if (rev.estaCerrada()) {
                if (rev.getFechaFin().isAfter(fechaRevision))
                    throw new IllegalArgumentException("La fecha fin es superior a la fecha de revision del cliente"+rev.getCliente().getDni());
            }else
                throw new IllegalArgumentException("La revision del cliente "+rev.getCliente().getDni()+" no esta cerrada");
        }
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
    public void anadirHoras(Revision revision,int horas){
        try {
            getRevision(revision).anadirHoras(horas);
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
    public Revision buscar(Revision revision){
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
