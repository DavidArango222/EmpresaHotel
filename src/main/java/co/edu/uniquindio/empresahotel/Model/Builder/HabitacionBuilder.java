package co.edu.uniquindio.empresahotel.Model.Builder;

import co.edu.uniquindio.empresahotel.Model.Habitacion;
import co.edu.uniquindio.empresahotel.Model.Servicio;
import co.edu.uniquindio.empresahotel.Model.ServicioHabitacion;
import co.edu.uniquindio.empresahotel.Model.TipoHabitacion;

import java.util.ArrayList;
import java.util.List;

public class HabitacionBuilder {
    protected int numero;
    protected TipoHabitacion tipoHabitacion;
    protected float precio;
    protected List<Servicio> servicioList = new ArrayList<>();
    protected List<ServicioHabitacion> servicioHabitacionList = new ArrayList<>();

    public HabitacionBuilder numero(int numero){
        this.numero=numero;
        return this;
    }

    public HabitacionBuilder tipoHabitacion(TipoHabitacion tipoHabitacion){
        this.tipoHabitacion=tipoHabitacion;
        return this;
    }

    public HabitacionBuilder precio(float precio){
        this.precio=precio;
        return this;
    }

    public HabitacionBuilder servicioList(List<Servicio> servicioList){
        this.servicioList=servicioList;
        return this;
    }

    public HabitacionBuilder servicioHabitacionList(List<ServicioHabitacion> servicioHabitacionList){
        this.servicioHabitacionList=servicioHabitacionList;
        return this;
    }

    public Habitacion build(){
        return new Habitacion(numero, tipoHabitacion, precio);
    }
}
