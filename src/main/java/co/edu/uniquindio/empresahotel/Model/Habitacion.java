package co.edu.uniquindio.empresahotel.Model;

import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private int numero;
    private TipoHabitacion tipoHabitacion;
    private float precio;
    private List<Servicio> servicioList = new ArrayList<>();
    private List<ServicioHabitacion> servicioHabitacionList = new ArrayList<>();

    public Habitacion(){}

    public Habitacion(int numero, TipoHabitacion tipoHabitacion, float precio){
        this.numero=numero;
        this.tipoHabitacion=tipoHabitacion;
        this.precio=precio;
    }

    public void agregarServicio(Servicio servicio){
        servicioList.add(servicio);
    }

    public void agregarServicioHabitacion(ServicioHabitacion servicioHabitacion){
        servicioHabitacionList.add(servicioHabitacion);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    public List<ServicioHabitacion> getServicioHabitacionList() {
        return servicioHabitacionList;
    }

    public void setServicioHabitacionList(List<ServicioHabitacion> servicioHabitacionList) {
        this.servicioHabitacionList = servicioHabitacionList;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", tipoHabitacion=" + tipoHabitacion +
                ", precio=" + precio +
                '}';
    }
}
