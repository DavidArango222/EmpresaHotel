package co.edu.uniquindio.empresahotel.Factory;

import co.edu.uniquindio.empresahotel.Model.*;
import co.edu.uniquindio.empresahotel.Model.Builder.HabitacionBuilder;
import co.edu.uniquindio.empresahotel.Model.Builder.ReservaBuilder;
import co.edu.uniquindio.empresahotel.Model.Builder.ServicioBuilder;
import co.edu.uniquindio.empresahotel.Services.IClienteCrud;
import co.edu.uniquindio.empresahotel.Services.IConsultaServicios;
import co.edu.uniquindio.empresahotel.Services.IHabitacionCrud;
import co.edu.uniquindio.empresahotel.Services.IServicioCrud;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory implements IClienteCrud, IHabitacionCrud, IServicioCrud, IConsultaServicios {

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
        empresaHotel.reservar(dni, dniReserva);
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
        Servicio servicioSpa = new ServicioBuilder()
                .nombre("spa")
                .build();
        listaServicios.add(servicioSpa);
        Servicio servicioRestaurante = new ServicioBuilder()
                .nombre("Restaurante")
                .build();
        listaServicios.add(servicioRestaurante);
        Servicio servicioLimpieza = new ServicioBuilder()
                .nombre("Limpieza")
                .build();
        listaServicios.add(servicioLimpieza);
        List<ServicioHabitacion> servicioHabitacionList = new ArrayList<>();
        ServicioHabitacion servicioHabitacionMasaje = new ServicioHabitacion("Masaje especial");
        servicioHabitacionList.add(servicioHabitacionMasaje);
        Habitacion habitacion1 = new HabitacionBuilder()
                .numero(100)
                .tipoHabitacion(TipoHabitacion.SIMPLE)
                .precio(150).build();
        Habitacion habitacion2 = new HabitacionBuilder()
                .numero(200)
                .tipoHabitacion(TipoHabitacion.DOBLE)
                .precio(250)
                .build();
        Habitacion habitacion3 = new HabitacionBuilder()
                .numero(300)
                .tipoHabitacion(TipoHabitacion.SUITE)
                .precio(350)
                .build();
        Habitacion habitacion4 = new HabitacionBuilder()
                .numero(400)
                .tipoHabitacion(TipoHabitacion.DOBLE)
                .precio(250)
                .build();
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
        Cliente cliente1 = Cliente.build()
                .nombre("Juan Pérez")
                .dNI("123456789")
                .build();
        Cliente cliente2 = Cliente.build()
                .nombre("María López")
                .dNI("987654321")
                .build();
        Cliente cliente3 = Cliente.build()
                .nombre("David Arango")
                .dNI("1124312515")
                .build();
        empresaHotel.getClienteList().add(cliente2);
        empresaHotel.getClienteList().add(cliente3);
        empresaHotel.getClienteList().add(cliente1);
        Reserva reserva1 = new ReservaBuilder()
                .idReserva("0001")
                .fechaEntrada(LocalDateTime.of(2024, 9, 10, 15, 0))
                .fechaSalida(LocalDateTime.of(2024, 9, 20, 15, 0))
                .habitacion(habitacion1)
                .build();
        cliente1.agregarReserva(reserva1);
        Reserva reserva2 = new ReservaBuilder()
                .idReserva("0002")
                .fechaEntrada(LocalDateTime.of(2024, 9, 11, 15, 0))
                .fechaSalida(LocalDateTime.of(2024, 9, 20, 11, 0))
                .habitacion(habitacion3)
                .build();
        cliente2.agregarReserva(reserva2);
        Reserva reserva3 = new ReservaBuilder()
                .idReserva("0003")
                .fechaEntrada(LocalDateTime.of(2024, 9, 15, 15, 0))
                .fechaSalida(LocalDateTime.of(2024, 9, 25, 11, 0))
                .habitacion(habitacion4)
                .build();
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

