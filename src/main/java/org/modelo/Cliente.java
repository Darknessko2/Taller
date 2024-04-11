package org.modelo;

import java.util.Objects;

public class Cliente {
    private String nombre;
    private String dni;
    private String telefono;

    private static final String ER_NOMBRE = "^[a-zA-Z]+ [a-zA-Z]+$"; // nombre que solo permite letra y solo un espacio disponible
    private static final String ER_DNI = "^[0-9]{8}[a-zA-Z]{1}$"; // dni con 8 numeros y la ultima una letra
    private static final String ER_TELEFONO = "^[0-9]{9}$"; // numero de 9 digitos

    public Cliente(String nombre, String dni, String telefono) {
        try {
            setNombre(nombre);
            setDni(dni);
            setTelefono(telefono);
        }catch (IllegalArgumentException e){
            System.out.println("Los parametros introducidos no son correctos");
        }catch (Exception e){
            System.out.println("Ha habido un error");
        }
    }

    public Cliente Cliente(Cliente cliente){ // factory method
        String nombreCompleto = nombreFormato(cliente.getNombre());
        // cambio de formato del nombre

        return new Cliente(cliente.getNombre(),cliente.getDni(),cliente.getTelefono());
    }
    private boolean comprobarLetraDni(String dniCliente){
        int numeros = Integer.parseInt(dniCliente.substring(0,dniCliente.length() -1));
        String letraDni = Character.toString(dniCliente.charAt(dniCliente.length() -1));

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        String[] conjuntoLetras = letras.split("");
        int posicion = numeros % 23 ;

        return conjuntoLetras[posicion].equalsIgnoreCase(letraDni);
    }


    private String nombreFormato(String nombreCliente) { // cambio el formato del nombre
        String[] nombreCompleto = nombreCliente.split(" ");
        for (int i = 0; i < nombreCompleto.length; i++) {
            nombreCompleto[i] = nombreCompleto[i].substring(0,1).toUpperCase() +
                                nombreCompleto[i].substring(1).toLowerCase();
        }
        return nombreCompleto.toString();
    }

    public void setNombre(String nombre) throws IllegalArgumentException{
        if (nombre.matches(ER_NOMBRE))
            this.nombre = nombreFormato(nombre); // si el nombre cumple con los requisitos se formatea de acorde a los requisitos
        else
            throw new IllegalArgumentException("El formato del nombre no es correcto");
    }

    private void setDni(String dni) throws IllegalArgumentException {
        if (dni.matches(ER_DNI)) {

            if (comprobarLetraDni(dni)) // se comprueba si la letra coincide
                this.dni = dni;
            else
                throw new IllegalArgumentException("La letra del dni no es correcta");

        }else
            throw new IllegalArgumentException("El formato del dni no es correcto");
    }

    public void setTelefono(String telefono) {
        if (telefono.matches(ER_TELEFONO))
            this.telefono = telefono;
        else
            throw new IllegalArgumentException("El formato del telefono no es el correcto");
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }



    // LOS CLIENTES SON IGUALES SI TIENEN EL MISMO DNI (hashcode y equals)
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Cliente cliente = (Cliente) object;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre='" + nombre + '\'' + ", dni='" + dni + '\'' + ", telefono='" + telefono + '\'' + '}';
    }
}