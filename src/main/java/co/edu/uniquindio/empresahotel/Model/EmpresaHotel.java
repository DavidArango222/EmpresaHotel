package co.edu.uniquindio.empresahotel.Model;

import co.edu.uniquindio.empresahotel.Services.IConsumible;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmpresaHotel {
    private String nombre;

    private List<Reserva> reservaList = new ArrayList<>();
    private List<Habitacion> habitacionList = new ArrayList<>();
    private List<Cliente> clienteList = new ArrayList<>();

    public EmpresaHotel(){}

    public EmpresaHotel(String nombre){
        this.nombre=nombre;
    }

    public Habitacion buscarHabitacionNumero(Cliente cliente, int numeroHabitacion){
        for (Reserva reserva : cliente.getReservaList()){
            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion.getNumero() == numeroHabitacion){
                return habitacion;
            }
        }

        return null;
    }



    public void mostrarConfirmacion(Cliente cliente, Reserva reserva){
        System.out.println("Reserva realizada con exito. ");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Reserva desde " + reserva.getFechaEntrada() + " hasta " + reserva.getFechaSalida());
        System.out.println("Tipo de habitación " + reserva.getHabitacion());
        System.out.println("Precio: " + reserva.getHabitacion().getPrecio());
    }

    public void reservar(Cliente cliente, Reserva reserva){
        cliente.agregarReserva(reserva);
        reservaList.add(reserva);
        mostrarConfirmacion(cliente, reserva);
    }

    public void serviciosCliente(String dNI, int numeroHabitacion, int opcion, String nombreServicio){
        Cliente cliente = buscarClienteDni(dNI);
        verificarClienteExiste(cliente);
        Habitacion habitacion = buscarHabitacionNumero(cliente, numeroHabitacion);
        verificarHabitacionExiste(habitacion);
        menuServicios(habitacion, opcion, nombreServicio);
    }

    public void menuServicios(Habitacion habitacion, int numero, String nombreServicio) {
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
                // Crear un nuevo ServicioHabitacion con el nombre proporcionado
                servicio = new ServicioHabitacion(nombreServicio);
                servicio.consumir();
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción entre 1 y 4.");
                break;
        }

        if (servicio != null) {
            habitacion.agregarServicio(servicio);
        }
    }

    public void mostrarServicios(){
        System.out.println("1- Servicio Spa. ");
        System.out.println("2- Servicio Restaurante. ");
        System.out.println("3- Servicio Limpieza. ");
        System.out.println("4- Otro servicio. ");
    }

    public void mostrarReservas(){
        System.out.println("Listado de reservas: ");
        for (Cliente cliente : clienteList){
            System.out.println("--------------------------------");
            System.out.println("Nombre del cliente: " + cliente.getNombre());
            for (Reserva reserva : cliente.getReservaList()){
                System.out.println("Reserva desde " + reserva.getFechaEntrada() + " hasta " + reserva.getFechaSalida());
            }
            System.out.println("--------------------------------");
        }
    }

    public void mostrarServiciosCliente(String dNI, int numeroHabitacion){
        Cliente cliente = buscarClienteDni(dNI);
        verificarClienteExiste(cliente);
        Habitacion habitacionBuscada = buscarHabitacionNumero(cliente, numeroHabitacion);
        verificarHabitacionExiste(habitacionBuscada);
        System.out.println("--------------------------------------------");
        System.out.println("Servicios consumidos en la habitación " + numeroHabitacion + ":");
        serviciosConsumidosHabitacion(habitacionBuscada);
    }

    public Cliente obtenerCliente(String nombre, String dni){
        Cliente cliente = buscarClienteDni(dni);
        if (cliente == null){
            cliente = new Cliente(nombre, dni);
            clienteList.add(cliente);
        }
        return cliente;
    }

    public void costoEstadia(String dNI, String idReserva){
        int dias = calcularDias(dNI, idReserva);
        float costo = costoHabitacion(buscarReserva(idReserva, Objects.requireNonNull(buscarClienteDni(dNI)).getReservaList()));
        float costoTotal = dias * costo;

        System.out.println("El costo de la estadía es: " + costoTotal + " , espero que vuelvas =). ");
    }

    public void verificarHabitacionExiste(Habitacion habitacion){
        if(habitacion == null){
            System.out.println("Habitación no encontrada para el cliente dado. ");
        }
    }

    public void verificarClienteExiste(Cliente cliente){
        if (cliente == null){
            System.out.println("Cliente no encontrado");
        }
    }

    public void serviciosConsumidosHabitacion(Habitacion habitacion) {
        for (IConsumible servicio : habitacion.getServicioList()) {
            if (servicio != null) {
                System.out.println("- " + servicio.getNombre());
            }
        }
    }

    private Cliente buscarClienteDni(String dni){
        for (Cliente cliente : getClienteList()){
            if (cliente.getdNI().equals(dni)){
                return cliente;
            }
        }

        return null;
    }

    public int calcularDias(String dNI, String iDReserva){
        Reserva reservaCliente = buscarReserva(iDReserva, Objects.requireNonNull(buscarClienteDni(dNI)).getReservaList());
        long diferenciaDias = calcularDiferenciaDias(reservaCliente.getFechaEntrada(), reservaCliente.getFechaSalida());

        return (int) diferenciaDias;
    }

    public float costoHabitacion(Reserva reserva){
        float costo = reserva.getHabitacion().getPrecio();
        return costo;
    }

    public long calcularDiferenciaDias(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
        return ChronoUnit.DAYS.between(fechaInicial,fechaFinal);
    }

    public Reserva buscarReserva(String iDReserva, List<Reserva> reservaList){
        for (Reserva reserva : reservaList){
            if(iDReserva.equals(reserva.getIdReserva())){
                return reserva;
            }
        }

        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    public List<Habitacion> getHabitacionList() {
        return habitacionList;
    }

    public void setHabitacionList(List<Habitacion> habitacionList) {
        this.habitacionList = habitacionList;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }
}
