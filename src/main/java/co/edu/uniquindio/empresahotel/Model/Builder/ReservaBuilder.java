package co.edu.uniquindio.empresahotel.Model.Builder;

import co.edu.uniquindio.empresahotel.Model.Habitacion;
import co.edu.uniquindio.empresahotel.Model.Reserva;

import java.time.LocalDateTime;

public class ReservaBuilder {
    protected String idReserva;
    protected LocalDateTime fechaEntrada;
    protected LocalDateTime fechaSalida;
    protected Habitacion habitacion;

    public ReservaBuilder idReserva(String idReserva){
        this.idReserva=idReserva;
        return this;
    }

    public ReservaBuilder fechaEntrada(LocalDateTime fechaEntrada){
        this.fechaEntrada=fechaEntrada;
        return this;
    }

    public ReservaBuilder fechaSalida(LocalDateTime fechaSalida){
        this.fechaSalida=fechaSalida;
        return this;
    }

    public ReservaBuilder habitacion(Habitacion habitacion){
        this.habitacion=habitacion;
        return this;
    }

    public Reserva build(){
        return new Reserva(idReserva, fechaEntrada, fechaSalida, habitacion);
    }
}
