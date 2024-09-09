package co.edu.uniquindio.empresahotel.Services;

import co.edu.uniquindio.empresahotel.Model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultaServicios {
    void mostrarConfirmacion(Cliente cliente, Reserva reserva);
    void reservar(Cliente cliente, Reserva reserva);
    void serviciosCliente(String dNI, int numeroHabitacion, int opcion, String nombreServicio);
    void mostrarReservas();
    void mostrarServiciosCliente(String dNI, int numeroHabitacion);
    void costoEstadia(String dNI, String idReserva);
    Cliente buscarClienteDni(String dni);
    int calcularDias(String dNI, String iDReserva);
    float costoHabitacion(Reserva reserva);
    long calcularDiferenciaDias(LocalDateTime fechaInicial,
                                LocalDateTime fechaFinal);
    Reserva buscarReserva(String iDReserva,
                          List<Reserva> reservaList);
    boolean crearCliente(String nombre,
                         String dni);
    boolean updateCliente(String nombre, String dni);
    boolean eliminarCliente(String dni);
    boolean crearHabitacion(int numero, TipoHabitacion tipoHabitacion, float precio);
    boolean updateHabitacion(int numero, TipoHabitacion tipoHabitacion, float precio);
    boolean eliminarHabitacion(int numero);
    boolean crearServicio(String nombre,
                          int numeroHabitacion,
                          String dniCliente);
    boolean updateServicio(String nombre, int numeroHabitacion, String dniCliente);
    boolean eliminarServicio(String nombre, int numeroHabitacion, String dniCliente);
    String obtenerServicio(String nombre, int numeroHabitacion, String dniCliente);
    String obtenerHabitacion(int numero);
    String obtenerCliente(String dni);
}
