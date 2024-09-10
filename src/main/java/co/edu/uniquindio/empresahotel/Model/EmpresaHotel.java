package co.edu.uniquindio.empresahotel.Model;

import co.edu.uniquindio.empresahotel.Services.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmpresaHotel implements IClienteCrud, IHabitacionCrud, IServicioCrud, IConsultaServicios {
    private String nombre;

    private List<Reserva> reservaList = new ArrayList<>();
    private List<Habitacion> habitacionList = new ArrayList<>();
    private List<Cliente> clienteList = new ArrayList<>();

    public EmpresaHotel(){}

    public EmpresaHotel(String nombre){
        this.nombre=nombre;
    }

    private Habitacion buscarHabitacionNumero(Cliente cliente,
                                             int numeroHabitacion){
        for (Reserva reserva : cliente.getReservaList()){
            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion.getNumero() == numeroHabitacion){
                return habitacion;
            }
        }

        return null;
    }

    private void mostrarConfirmacion(Cliente cliente,
                                    Reserva reserva){
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Reserva desde: " + reserva.getFechaEntrada() + " hasta: " + reserva.getFechaSalida());
        System.out.println(reserva.getHabitacion());
        System.out.println("Precio: " + reserva.getHabitacion().getPrecio());
        System.out.println("----------------------------------------");
    }

    public void reservar(String dni,
                         String dniReserva){
        Cliente cliente = buscarClienteDni(dni);
        Reserva reserva = buscarReserva(dniReserva, reservaList);
        cliente.agregarReserva(reserva);
        reservaList.add(reserva);
        mostrarConfirmacion(cliente, reserva);
    }

    public void serviciosCliente(String dNI,
                                 int numeroHabitacion,
                                 int opcion,
                                 String nombreServicio){
        Cliente cliente = buscarClienteDni(dNI);
        verificarClienteExiste(cliente);
        Habitacion habitacion = buscarHabitacionNumero(cliente, numeroHabitacion);
        verificarHabitacionExiste(habitacion);
        menuServicios(habitacion, opcion, nombreServicio);
    }

    private void menuServicios(Habitacion habitacion,
                              int numero,
                              String nombreServicio) {
        Servicio servicio = null;
        switch (numero) {
            case 1:
                servicio = Servicio.spa();
                break;
            case 2:
                servicio = Servicio.restaurante();
                break;
            case 3:
                servicio = Servicio.limpieza();
                break;
            case 4:
                if (nombreServicio == null || nombreServicio.isEmpty()) {
                    System.out.println("El nombre del servicio no puede ser nulo o vacío.");
                    return;
                }
                servicio = new ServicioHabitacion();
                servicio.setNombre(nombreServicio);
                servicio.consumir();
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción entre 1 y 4.");
                break;
        }
        if (servicio != null) {
            habitacion.agregarServicio(servicio);
        } else {
            System.out.println("No se pudo agregar el servicio. ");
        }
    }

    public void mostrarReservas(){
        System.out.println("Listado de reservas: ");
        for (Cliente cliente : clienteList){
            System.out.println("Nombre del cliente: " + cliente.getNombre());
            for (Reserva reserva : cliente.getReservaList()){
                System.out.println("Reserva desde " + reserva.getFechaEntrada() + " hasta " + reserva.getFechaSalida());
            }
        }
    }

    public void mostrarServiciosCliente(String dNI,
                                        int numeroHabitacion){
        Cliente cliente = buscarClienteDni(dNI);
        verificarClienteExiste(cliente);
        Habitacion habitacionBuscada = buscarHabitacionNumero(cliente, numeroHabitacion);
        verificarHabitacionExiste(habitacionBuscada);
        System.out.println("Servicios consumidos en la habitación " + numeroHabitacion + ":");
        serviciosConsumidosHabitacion(habitacionBuscada);
    }

    public void costoEstadia(String dNI, String idReserva){
        int dias = calcularDias(dNI, idReserva);
        float costo = costoHabitacion(buscarReserva(idReserva,
                Objects.requireNonNull(buscarClienteDni(dNI)).getReservaList()));
        float costoTotal = dias * costo;

        System.out.println("El costo de la estadía es: " + costoTotal + " , espero que vuelvas =). ");
    }

    private void verificarHabitacionExiste(Habitacion habitacion){
        if(habitacion == null){
            System.out.println("Habitación no encontrada para el cliente dado. ");
        }
    }

    private void verificarClienteExiste(Cliente cliente){
        if (cliente == null){
            System.out.println("Cliente no encontrado");
        }
    }

    private void serviciosConsumidosHabitacion(Habitacion habitacion) {
        for (IConsumible servicio : habitacion.getServicioList()) {
            if (servicio != null) {
                if (servicio.getNombre() == null){
                    continue;
                } else {
                    System.out.println("- " + servicio.getNombre());
                }
            }
        }
    }

    private Cliente buscarClienteDni(String dni) {
        for (Cliente cliente : clienteList) {
            if (cliente.getdNI().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }


    private int calcularDias(String dNI, String iDReserva){
        Reserva reservaCliente = buscarReserva(iDReserva,
                Objects.requireNonNull(buscarClienteDni(dNI)).getReservaList());
        long diferenciaDias = calcularDiferenciaDias(reservaCliente.getFechaEntrada(),
                reservaCliente.getFechaSalida());

        return (int) diferenciaDias;
    }

    private float costoHabitacion(Reserva reserva){
        return reserva.getHabitacion().getPrecio();
    }

    private long calcularDiferenciaDias(LocalDateTime fechaInicial,
                                       LocalDateTime fechaFinal){
        return ChronoUnit.DAYS.between(fechaInicial,fechaFinal);
    }

    private Reserva buscarReserva(String iDReserva,
                                 List<Reserva> reservaList){
        for (Reserva reserva : reservaList){
            if(iDReserva.equals(reserva.getIdReserva())){
                return reserva;
            }
        }

        return null;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public List<Habitacion> getHabitacionList() {
        return habitacionList;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }


    @Override
    public boolean crearCliente(String nombre,
                                String dni) {
        Cliente clienteExistente = buscarClienteDni(dni);
        if (clienteExistente == null) {
            Cliente cliente = new Cliente(nombre, dni);
            clienteList.add(cliente);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCliente(String nombre, String dni) {
        Cliente clienteExistente = buscarClienteDni(dni);
        if (clienteExistente != null) {
            clienteExistente.setNombre(nombre);
            clienteExistente.setdNI(dni);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(String dni) {
        Cliente clienteExistente = buscarClienteDni(dni);
        if (clienteExistente != null) {
            clienteList.remove(clienteExistente);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean crearHabitacion(int numero, TipoHabitacion tipoHabitacion, float precio) {
        Habitacion habitacionExistente = buscarHabitacionExiste(numero);
        if (habitacionExistente == null){
            Habitacion habitacion = new Habitacion();
            habitacion.setNumero(numero);
            habitacion.setTipoHabitacion(tipoHabitacion);
            habitacion.setPrecio(precio);
            habitacionList.add(habitacion);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateHabitacion(int numero, TipoHabitacion tipoHabitacion, float precio) {
        Habitacion habitacionExistente = buscarHabitacionExiste(numero);
        if (habitacionExistente != null) {
            habitacionExistente.setTipoHabitacion(tipoHabitacion);
            habitacionExistente.setPrecio(precio);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarHabitacion(int numero) {
        Habitacion habitacionExistente = buscarHabitacionExiste(numero);
        if (habitacionExistente != null) {
            habitacionList.remove(habitacionExistente);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean crearServicio(String nombre,
                                 int numeroHabitacion,
                                 String dniCliente) {
        Servicio servicioExistente = buscarServicioExiste(nombre, numeroHabitacion, dniCliente);
        Cliente cliente = buscarClienteDni(dniCliente);
        Habitacion habitacion = buscarHabitacionNumero(cliente, numeroHabitacion);
        if (servicioExistente == null){
            Servicio servicio = new Servicio(nombre);
            habitacion.getServicioList().add(servicio);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateServicio(String nombre, int numeroHabitacion, String dniCliente) {
        Cliente cliente = buscarClienteDni(dniCliente);
        if (cliente != null) {
            Habitacion habitacion = buscarHabitacionNumero(cliente, numeroHabitacion);
            if (habitacion != null) {
                for (Servicio servicio : habitacion.getServicioList()) {
                    if (servicio != null && servicio.getNombre().equals(nombre)) {
                        servicio.setNombre(nombre);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean eliminarServicio(String nombre, int numeroHabitacion, String dniCliente) {
        Cliente cliente = buscarClienteDni(dniCliente);
        if (cliente != null) {
            Habitacion habitacion = buscarHabitacionNumero(cliente, numeroHabitacion);
            if (habitacion != null) {
                return habitacion.getServicioList().removeIf(servicio -> servicio != null && servicio.getNombre().equals(nombre));
            }
        }
        return false;
    }

    private Servicio buscarServicioExiste(String nombre,
                                          int numeroHabitacion,
                                          String dniCliente) {
        Servicio servicioExistente = null;
        Cliente cliente = buscarClienteDni(dniCliente);
        Habitacion habitacion = buscarHabitacionNumero(cliente, numeroHabitacion);
        for (Servicio servicio : habitacion.getServicioHabitacionList()){
            if (servicio.getNombre().equals(nombre)){
                servicioExistente = servicio;
                break;
            }
        }

        return servicioExistente;
    }

    public String obtenerServicio(String nombre, int numeroHabitacion, String dniCliente){
        Cliente cliente = buscarClienteDni(dniCliente);
        Habitacion habitacion = buscarHabitacionNumero(cliente, numeroHabitacion);
        for (Servicio servicio : habitacion.getServicioList()) {
            if (servicio.getNombre().equals(nombre)) {
                return servicio.toString();
            }
        }
        System.out.println("Servicio no encontrado. ");
        return null;
    }

    public String obtenerHabitacion(int numero) {
        for (Habitacion habitacion : habitacionList) {
            if (habitacion.getNumero() == numero) {
                return habitacion.toString();
            }
        }
        return null;
    }

    public String obtenerCliente(String dni) {
        for (Cliente cliente : clienteList) {
            if (cliente.getdNI().equals(dni)) {
                return cliente.toString();
            }
        }
        return null;
    }

    private Cliente buscarClienteExiste(String dni) {
        Cliente clienteExistente = null;
        for (Cliente cliente : getClienteList()){
            if (cliente.getdNI().equals(dni)){
                clienteExistente = cliente;
                break;
            }
        }

        return clienteExistente;
    }

    private Habitacion buscarHabitacionExiste(int numero) {
        for (Habitacion habitacion : habitacionList) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        return null;
    }
}
