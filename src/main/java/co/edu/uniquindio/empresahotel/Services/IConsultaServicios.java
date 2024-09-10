package co.edu.uniquindio.empresahotel.Services;

public interface IConsultaServicios {
    void reservar(String dni, String dniReserva);
    void serviciosCliente(String dNI, int numeroHabitacion, int opcion, String nombreServicio);
    void mostrarReservas();
    void mostrarServiciosCliente(String dNI, int numeroHabitacion);
    void costoEstadia(String dNI, String idReserva);
}
