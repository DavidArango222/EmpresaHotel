package co.edu.uniquindio.empresahotel.Model;

import co.edu.uniquindio.empresahotel.Services.IConsumible;

public class Servicio implements IConsumible {
    private String nombre;

    public Servicio(){}

    public Servicio(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Servicio spa(){
        return new Servicio("Spa");
    }

    public static Servicio restaurante(){
        return new Servicio("Restaurante");
    }

    public static Servicio limpieza(){
        return new Servicio("Limpieza");
    }

    @Override
    public void consumir() {
        System.out.println("El servicio " + nombre + " ha sido consumido.");
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
