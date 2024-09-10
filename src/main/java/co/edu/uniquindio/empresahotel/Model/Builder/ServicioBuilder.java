package co.edu.uniquindio.empresahotel.Model.Builder;

import co.edu.uniquindio.empresahotel.Model.Servicio;

public class ServicioBuilder {
    protected String nombre;

    public ServicioBuilder nombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public Servicio build(){
        return new Servicio(nombre);
    }
}
