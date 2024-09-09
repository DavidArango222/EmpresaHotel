package co.edu.uniquindio.empresahotel.Model;

import java.time.LocalDateTime;

public class Reserva {
    private String idReserva;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Habitacion habitacion;

    public Reserva (){}

    public Reserva(String idReserva, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Habitacion habitacion){
        this.idReserva=idReserva;
        this.fechaEntrada=fechaEntrada;
        this.fechaSalida=fechaSalida;
        this.habitacion=habitacion;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva='" + idReserva + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", habitacion=" + habitacion +
                '}';
    }
}
