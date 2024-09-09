package co.edu.uniquindio.empresahotel.Services;

import co.edu.uniquindio.empresahotel.Model.Cliente;

public interface IClienteCrud {
    boolean crearCliente(String nombre, String dni);
    String obtenerCliente(String dni);
    boolean updateCliente(String nombre, String dni);
    boolean eliminarCliente(String dni);
}
