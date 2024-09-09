package co.edu.uniquindio.empresahotel.Services;

import co.edu.uniquindio.empresahotel.Model.TipoHabitacion;

public interface IHabitacionCrud {
    boolean crearHabitacion(int numero, TipoHabitacion tipoHabitacion, float precio);
    String obtenerHabitacion(int numero);
    boolean updateHabitacion(int numero, TipoHabitacion tipoHabitacion, float precio);
    boolean eliminarHabitacion(int numero);
}
