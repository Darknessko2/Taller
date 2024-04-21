package org.modelo;

import java.util.Objects;

public record Vehiculo (String marca, String modelo, String matricula){
    private static final String ER_MARCA = "^(seat|land rover|kia|rolls royce|SsangYong])";
    private static final String ER_MATRICULA = "^[0-9]{4}[a-zA-Z]{3}$";

    public Vehiculo { // constructor compacto
            validarMarca(marca);
            validarModelo(modelo);
            validarMatricula(matricula);
    }
    private void validarMarca(String marca) {
        if (!marca.toLowerCase().matches(ER_MARCA))
            throw new IllegalArgumentException("La marca "+marca+" no es valida");
    }
    private void validarModelo(String modelo){
        if (modelo.isEmpty())
            throw new IllegalArgumentException("El modelo "+modelo+" no es valido");
    }
    private void validarMatricula(String matricula){
        if (!matricula.matches(ER_MATRICULA))
            throw new IllegalArgumentException("La matricula "+matricula+" no es valida");
    }

    public static Vehiculo get(String matricula){
        if (!matricula.matches(ER_MATRICULA))
            throw new IllegalArgumentException("Matricula no valida (get)");

        return new Vehiculo("seat", "a", matricula);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Vehiculo vehiculo = (Vehiculo) object;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "marca='" + marca + '\'' + ", modelo='" + modelo + '\'' + ", matricula='" + matricula + '\'' + '}';
    }
}

