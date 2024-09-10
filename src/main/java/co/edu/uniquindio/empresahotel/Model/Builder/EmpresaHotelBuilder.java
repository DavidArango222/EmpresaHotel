package co.edu.uniquindio.empresahotel.Model.Builder;

import co.edu.uniquindio.empresahotel.Model.EmpresaHotel;

public class EmpresaHotelBuilder {
    protected String nombre;

    public EmpresaHotelBuilder nombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public EmpresaHotel build(){
        return new EmpresaHotel(nombre);
    }
}
