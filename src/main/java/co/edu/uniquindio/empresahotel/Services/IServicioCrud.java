package co.edu.uniquindio.empresahotel.Services;

import co.edu.uniquindio.empresahotel.Model.Habitacion;

public interface IServicioCrud {
    boolean crearServicio(String nombre, int numeroHabitacion, String dniCliente);
    String obtenerServicio(String nombre, int numeroHabitacion, String dniCliente);
    boolean updateServicio(String nombre, int numeroHabitacion, String dniCliente);
    boolean eliminarServicio(String nombre, int numeroHabitacion, String dniCliente);
}
