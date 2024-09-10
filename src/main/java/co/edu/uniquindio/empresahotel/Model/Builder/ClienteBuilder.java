package co.edu.uniquindio.empresahotel.Model.Builder;

import co.edu.uniquindio.empresahotel.Model.Cliente;
import co.edu.uniquindio.empresahotel.Model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ClienteBuilder {
    protected String nombre;
    protected String dNI;
    protected List<Reserva> reservaList = new ArrayList<>();

    public ClienteBuilder nombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public ClienteBuilder dNI(String dNI){
        this.dNI=dNI;
        return this;
    }

    public ClienteBuilder reservaList(List<Reserva> reservaList){
        this.reservaList=reservaList;
        return this;
    }

    public Cliente build(){
        return new Cliente(nombre, dNI);
    }
}
