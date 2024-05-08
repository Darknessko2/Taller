package org.modelo;

import javax.security.auth.Destroyable;
import java.util.Formattable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public enum Opcion {
    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3, "Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar clientes"),
    MODIFICAR_CLIENTE(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar vehiculo"),
    BUSCAR_VEHICULO(7, "Buscar vehiculo"),
    BORRAR_VEHICULO(8, "Borrar vehiculo"),
    LISTAR_VEHICULOS(9, "Listar vehiculos"),
    INSERTAR_REVISION(10, "Insertar revision"),
    BUSCAR_REVISION(11, "Buscar revision"),
    BORRAR_REVISION(12, "Borrar revision"),
    LISTAR_REVISIONES(13, "Listar revisiones"),
    LISTAR_REVISIONES_CLIENTE(14, "Listar revisiones de un cliente"),
    LISTAR_REVISIONES_VEHICULO(15, "Listar revisiones de un vehiculo"),
    ANADIR_HORAS_REVISION(16, "Anadir horas a revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(17, "Anadir precio de material a revision"),
    CERRAR_REVISION(18, "Cerrar revision"),
    SALIR(19, "Salir");

    private int numeroOpcion;
    public static Map<Integer,Opcion> opciones = new TreeMap<>(); // todo map<integer,string>

    static { // inicializo la lista
        for (Opcion opcion : Opcion.values()){
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }
    private String mensaje;
    private Opcion(int numeroOpcion,String mensaje){
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion){
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion){ // todo
        if (!esValida(numeroOpcion)){
            throw new IllegalArgumentException("Opcion no valida");
        }else {
            return opciones.get(numeroOpcion);
        }
    }

    @Override
    public String toString() {
        return String.format("%02d",numeroOpcion) +". "+ mensaje;
    }
}
