package co.edu.uniquindio.empresahotel.Factory;

import co.edu.uniquindio.empresahotel.Model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory {

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

    private void inicializarDatos() {
        empresaHotel = new EmpresaHotel("Hotel Paradise");
        List<Servicio> listaServicios = new ArrayList<>();
        listaServicios.add(new Servicio("Spa"));
        listaServicios.add(new Servicio("Restaurante"));
        listaServicios.add(new Servicio("Limpieza"));
        List<ServicioHabitacion> servicioHabitacionList = new ArrayList<>();
        servicioHabitacionList.add(new ServicioHabitacion("Cancha de volei"));
        // Crear habitaciones
        Habitacion habitacion1 = new Habitacion(101, TipoHabitacion.SIMPLE, 100.0f);
        Habitacion habitacion2 = new Habitacion(102, TipoHabitacion.DOBLE, 150.0f);
        Habitacion habitacion3 = new Habitacion(103, TipoHabitacion.SUITE, 250.0f);
        for (Servicio servicio : listaServicios) {
            habitacion1.agregarServicio(servicio);
            habitacion2.agregarServicio(servicio);
            habitacion3.agregarServicio(servicio);
        }
        for (ServicioHabitacion servicio : servicioHabitacionList) {
            habitacion1.agregarServicio(servicio);
            habitacion2.agregarServicio(servicio);
            habitacion3.agregarServicio(servicio);
        }
        // Añadir habitaciones a la empresa hotelera
        empresaHotel.getHabitacionList().add(habitacion1);
        empresaHotel.getHabitacionList().add(habitacion2);
        empresaHotel.getHabitacionList().add(habitacion3);
        // Crear clientes
        Cliente cliente1 = new Cliente("Juan Pérez", "123456789");
        Cliente cliente2 = new Cliente("María López", "987654321");
        // Añadir clientes a la empresa hotelera
        empresaHotel.getClienteList().add(cliente1);
        empresaHotel.getClienteList().add(cliente2);
        // Crear reservas
        Reserva reserva1 = new Reserva("0001", LocalDateTime.of(2024, 9, 10, 15, 0), LocalDateTime.of(2024, 9, 15, 11, 0), habitacion1);
        Reserva reserva2 = new Reserva("0002", LocalDateTime.of(2024, 9, 10, 15, 0), LocalDateTime.of(2024, 9, 20, 11, 0), habitacion2);
        // Añadir reservas a los clientes
        cliente1.agregarReserva(reserva1);
        cliente2.agregarReserva(reserva2);
        // Añadir reservas a la empresa hotelera
        empresaHotel.getReservaList().add(reserva1);
        empresaHotel.getReservaList().add(reserva2);
    }
}

