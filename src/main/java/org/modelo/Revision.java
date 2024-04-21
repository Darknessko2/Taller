package org.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private static final float PRECIO_HORA = 30;// precio hora empleadas en la revision
    private static final float PRECIO_DIA = 10; // precio por dia que pase el vehiculo
    private static final float PRECIO_MATERIAL = 0; // todo preguntar
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd MM yyyy");
    // todo preguntar el formato fecha y precio material


    public Revision(LocalDate fechaInicio, Cliente cliente, Vehiculo vehiculo) {
        try {
            setFechaInicio(fechaInicio);
            setCliente(cliente);
            setVehiculo(vehiculo);
            this.horas = 0;
            this.precioMaterial = 0;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public Revision(Revision revision){
        this.fechaInicio = revision.getFechaInicio();
        this.cliente = new Cliente(revision.getCliente()); // se crea una copia del cliente
        this.vehiculo = revision.getVehiculo();
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior al dia de hoy");
        else
            this.fechaInicio = fechaInicio;
    }
    private void setFechaFin(LocalDate fechaFin){

        if (fechaFin.isEqual(fechaInicio) || fechaFin.isBefore(fechaInicio))
            throw new IllegalArgumentException("La fecha fin no puede ser igual o anterior a la fecha de inicio");
        else if(fechaFin.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha fin no puede ser posterior a hoy");
        else if(estaCerrada())
            throw new IllegalArgumentException("La fecha fin no puede ser modificada una vez ya creada");
        else
            this.fechaFin = fechaFin;
    }

    public void anadirHoras(int horas){ // todo preguntar el control de datos
        if (horas <= 0)
            throw new IllegalArgumentException("El numero de horas agregar tiene que se positivo");
        else if (estaCerrada())
            throw new IllegalArgumentException("Las horas no puede ser modificado si la revision ya ha sido finalizada");
        else
             this.horas = horas;
    }
    public void anadirPrecioMaterial(float precioMaterial){
        if (precioMaterial <= 0)
            throw new IllegalArgumentException("El precio del material tiene que ser positivo");
        else if (estaCerrada())
            throw new IllegalArgumentException("El precio no puede ser modificado si la revision ya ha sido finalizada");
        else
            this.precioMaterial += precioMaterial;
    }
    public void cerrar(LocalDate fechaFin){
        try {
            setFechaFin(fechaFin);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public boolean estaCerrada(){
        return fechaFin != null;
    }
    private float getDias(){ // todo preguntar si el metodo se refiere a esto
        return (fechaInicio.getDayOfYear() - fechaFin.getDayOfYear());
    }
    /*
  Las revisiones tendrán un precio, que se calcula como el resultante de multiplicar el
  número de horas empleadas por 30 € más el número de días que el vehículo haya
  permanecido en el taller multiplicado por 10 €.
  */
    public float getPrecio(){
        return (horas * PRECIO_HORA) + (getDias() * PRECIO_DIA);
    }
    private void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }
    private void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    // Una revisión será igual a otro si es el mismo cliente, el mismo turismo y la fecha de inicio
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Revision revision = (Revision) object;
        return Objects.equals(fechaInicio, revision.fechaInicio) &&
                Objects.equals(cliente, revision.cliente) &&
                Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }

    @Override
    public String toString() {
        return "Revision{" + "fechaInicio=" + fechaInicio + ", fechaFin=" +
                fechaFin + ", horas=" + horas + ", precioMaterial=" +
                precioMaterial + ", cliente=" + cliente +
                ", vehiculo=" + vehiculo + '}';
    }
}

