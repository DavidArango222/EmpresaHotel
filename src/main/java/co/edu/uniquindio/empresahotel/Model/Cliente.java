package co.edu.uniquindio.empresahotel.Model;

import co.edu.uniquindio.empresahotel.Model.Builder.ClienteBuilder;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String dNI;
    private List<Reserva> reservaList = new ArrayList<>();

    public Cliente(){}

    public Cliente(String nombre, String dNI){
        this.nombre=nombre;
        this.dNI=dNI;
    }

    public void agregarReserva(Reserva reserva){
        reservaList.add(reserva);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getdNI() {
        return dNI;
    }

    public void setdNI(String dNI) {
        this.dNI = dNI;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", dNI=" + dNI +
                ", reservaList=" + reservaList +
                '}';
    }

    public static ClienteBuilder build(){
        return new ClienteBuilder();
    }
}
