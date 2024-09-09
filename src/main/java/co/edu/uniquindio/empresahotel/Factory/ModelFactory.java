package co.edu.uniquindio.empresahotel.Factory;

import co.edu.uniquindio.empresahotel.Model.*;
import co.edu.uniquindio.empresahotel.Services.IClienteCrud;
import co.edu.uniquindio.empresahotel.Services.IHabitacionCrud;
import co.edu.uniquindio.empresahotel.Services.IServicioCrud;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory implements IClienteCrud, IHabitacionCrud, IServicioCrud{

    private static ModelFactory modelFactory;
    private EmpresaHotel empresaHotel;

    private ModelFactory(){
        inicializarDatos();
    }

    public static ModelFactory getInstance(){
        if(modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    public void reservar(String dni, String dniReserva){
        empresaHotel.reservar(empresaHotel.buscarClienteDni(dni), empresaHotel.buscarReserva(dniReserva ,empresaHotel.getReservaList()));
    }

    public void serviciosCliente(String dni, int numeroHabitacion, int opcion, String nombreServicio){
        empresaHotel.serviciosCliente(dni, numeroHabitacion, opcion, nombreServicio);
    }

    public void mostrarReservas(){
        empresaHotel.mostrarReservas();
    }

    public void mostrarServiciosCliente(String dni, int numeroHabitacion){
        empresaHotel.mostrarServiciosCliente(dni, numeroHabitacion);
    }

    public void costoEstadia(String dni, String idReserva){
        empresaHotel.costoEstadia(dni, idReserva);
    }

    private void inicializarDatos() {
        empresaHotel = new EmpresaHotel("Hotel Paradise");
        List<Servicio> listaServicios = new ArrayList<>();
        Servicio servicioSpa = new Servicio("Spa");
        listaServicios.add(servicioSpa);
        Servicio servicioRestaurante = new Servicio("Restaurante");
        listaServicios.add(servicioRestaurante);
        Servicio servicioLimpieza = new Servicio("Limpieza");
        listaServicios.add(servicioLimpieza);
        List<ServicioHabitacion> servicioHabitacionList = new ArrayList<>();
        ServicioHabitacion servicioHabitacionMasaje = new ServicioHabitacion("Masaje especial");
        servicioHabitacionList.add(servicioHabitacionMasaje);
        Habitacion habitacion1 = new Habitacion();
        habitacion1.setNumero(100);
        habitacion1.setTipoHabitacion(TipoHabitacion.SIMPLE);
        habitacion1.setPrecio(150);
        Habitacion habitacion2 = new Habitacion();
        habitacion2.setNumero(200);
        habitacion2.setTipoHabitacion(TipoHabitacion.DOBLE);
        habitacion2.setPrecio(250);
        Habitacion habitacion3 = new Habitacion();
        habitacion3.setNumero(300);
        habitacion3.setTipoHabitacion(TipoHabitacion.SUITE);
        habitacion3.setPrecio(350);
        Habitacion habitacion4 = new Habitacion();
        habitacion4.setNumero(400);
        habitacion4.setTipoHabitacion(TipoHabitacion.DOBLE);
        habitacion4.setPrecio(250);
        for (Servicio servicio : listaServicios) {
            habitacion1.agregarServicio(servicio);
            habitacion2.agregarServicio(servicio);
            habitacion3.agregarServicio(servicio);
            habitacion4.agregarServicio(servicio);
        }
        for (ServicioHabitacion servicioHabitacion : servicioHabitacionList) {
            habitacion1.agregarServicio(servicioHabitacion);
            habitacion2.agregarServicio(servicioHabitacion);
            habitacion3.agregarServicio(servicioHabitacion);
            habitacion4.agregarServicio(servicioHabitacion);
        }
        empresaHotel.getHabitacionList().add(habitacion1);
        empresaHotel.getHabitacionList().add(habitacion2);
        empresaHotel.getHabitacionList().add(habitacion3);
        empresaHotel.getHabitacionList().add(habitacion4);
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan Pérez");
        cliente1.setdNI("123456789");
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("María López");
        cliente2.setdNI("987654321");
        Cliente cliente3 = new Cliente();
        cliente3.setNombre("David Arango");
        cliente3.setdNI("1124312515");
        empresaHotel.getClienteList().add(cliente1);
        empresaHotel.getClienteList().add(cliente2);
        empresaHotel.getClienteList().add(cliente3);
        Reserva reserva1 = new Reserva();
        reserva1.setIdReserva("0001");
        reserva1.setFechaEntrada(LocalDateTime.of(2024, 9, 10, 15, 0));
        reserva1.setFechaSalida(LocalDateTime.of(2024, 9, 15, 11, 0));
        reserva1.setHabitacion(habitacion1);
        Reserva reserva2 = new Reserva();
        reserva2.setIdReserva("0002");
        reserva2.setFechaEntrada(LocalDateTime.of(2024, 9, 11, 15, 0));
        reserva2.setFechaSalida(LocalDateTime.of(2024, 9, 20, 11, 0));
        reserva2.setHabitacion(habitacion3);
        cliente1.agregarReserva(reserva1);
        cliente2.agregarReserva(reserva2);
        Reserva reserva3 = new Reserva();
        reserva3.setIdReserva("0003");
        reserva3.setFechaEntrada(LocalDateTime.of(2024, 9, 15, 15, 0));
        reserva3.setFechaSalida(LocalDateTime.of(2024, 9, 25, 11, 0));
        reserva3.setHabitacion(habitacion4);
        empresaHotel.getReservaList().add(reserva1);
        empresaHotel.getReservaList().add(reserva2);
        empresaHotel.getReservaList().add(reserva3);
    }

    @Override
    public boolean crearCliente(String nombre,
                                String dni) {
        return empresaHotel.crearCliente(nombre, dni);
    }

    @Override
    public String obtenerCliente(String dniCliente){
        return  empresaHotel.obtenerCliente(dniCliente);
    }

    @Override
    public boolean updateCliente(String nombre, String dni) {
        return empresaHotel.updateCliente(nombre, dni);
    }

    @Override
    public boolean eliminarCliente(String dni) {
        return empresaHotel.eliminarCliente(dni);
    }

    @Override
    public boolean crearHabitacion(int numero,
                                   TipoHabitacion tipoHabitacion,
                                   float precio) {
        return empresaHotel.crearHabitacion(numero, tipoHabitacion, precio);
    }

    @Override
    public String obtenerHabitacion(int numero){
        return empresaHotel.obtenerHabitacion(numero);
    }

    @Override
    public boolean updateHabitacion(int numero,
                                    TipoHabitacion tipoHabitacion,
                                    float precio) {
        return empresaHotel.updateHabitacion(numero, tipoHabitacion, precio);
    }

    @Override
    public boolean eliminarHabitacion(int numero) {
        return empresaHotel.eliminarHabitacion(numero);
    }

    @Override
    public boolean crearServicio(String nombre, int numeroHabitacion, String dniCliente) {
        return empresaHotel.crearServicio(nombre, numeroHabitacion, dniCliente);
    }

    @Override
    public String obtenerServicio(String nombre, int numeroHabitacion, String dniCliente){
        return  empresaHotel.obtenerServicio(nombre, numeroHabitacion, dniCliente);
    }

    @Override
    public boolean updateServicio(String nombre, int numeroHabitacion, String dniCliente) {
        return empresaHotel.updateServicio(nombre, numeroHabitacion, dniCliente);
    }

    @Override
    public boolean eliminarServicio(String nombre, int numeroHabitacion, String dniCliente) {
        return empresaHotel.eliminarServicio(nombre, numeroHabitacion, dniCliente);
    }
}

