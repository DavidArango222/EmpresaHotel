package co.edu.uniquindio.empresahotel;

import co.edu.uniquindio.empresahotel.Factory.ModelFactory;
import co.edu.uniquindio.empresahotel.Model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainEmpresaHotel {
    public static void main(String[] args) {
        EmpresaHotel hotel = inicializarDatos();
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        while (!salir) {
            mostrarMenu();
            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opción 1");
                        String nombre = leerStringConsola("Ingresa el nombre del cliente: ");
                        String dNI = leerStringConsola("Ingresa el DNI del cliente");
                        Cliente cliente = new Cliente(nombre, dNI);
                        hotel.reservar(cliente, hotel.buscarReserva("0001", hotel.getReservaList()));
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opción 2");
                        hotel.mostrarReservas();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opción 3");
                        String dNITres = leerStringConsola("Ingresa el DNI del cliente. ");
                        int numeroHabitacion = leerEntero("Ingresa el número de habitacion. ");
                        hotel.mostrarServicios();
                        int opcionTres = leerEntero("Ingresa la opción que desea. ");
                        String nombreServicio = leerStringConsola("Ingrese el nombre del servicio personalizado: ");
                        hotel.serviciosCliente(dNITres, numeroHabitacion, opcionTres, nombreServicio);
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opción 4");
                        String dNICliente = leerStringConsola("Ingresa el DNI del cliente: ");
                        int numeroHabitacionDato = leerEntero("Ingreas el numero de habitacion");
                        hotel.mostrarServiciosCliente(dNICliente, numeroHabitacionDato);
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion 5");
                        String dNICinco = leerStringConsola("Ingresa el DNI del cliente: ");
                        String idReservaCinco = leerStringConsola("Ingresa el id de reserva del cliente: ");
                        hotel.costoEstadia(dNICinco, idReservaCinco);
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }


    }

    private static EmpresaHotel inicializarDatos() {
            EmpresaHotel empresaHotel = new EmpresaHotel("Hotel Paradise");
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
            return empresaHotel;
    }

    public static void mostrarMenu(){
        System.out.println("------------------------------");
        System.out.println("Por favor selecciona la opción que desea: ");
        System.out.println("1. Reservar");
        System.out.println("2. Mostrar lista de reservas");
        System.out.println("3. Agregar servicios al cliente");
        System.out.println("4. Mostrar lista de servicios según cliente");
        System.out.println("5. Mostrar costo de estadia");
        System.out.println("6. salir");
        System.out.println("------------------------------");
    }

    private static int leerEntero(String mensaje) {
        int dato = 0;
        String captura = "";
        System.out.println(mensaje);
        Scanner teclado = new Scanner(System.in);
        captura = teclado.nextLine();
        dato = Integer.parseInt(captura);
        return dato;
    }

    public static double leerDoubleConsola(String mensaje)
    {
        double dato=0;
        String captura="";
        System.out.println(mensaje);
        Scanner teclado = new Scanner(System.in);
        captura = teclado.nextLine();
        dato=Double.parseDouble(captura);
        return dato;
    }

    public static String leerStringConsola(String mensaje)
    {
        String captura="";
        System.out.println(mensaje);
        Scanner teclado = new Scanner(System.in);
        captura = teclado.nextLine();
        return captura;
    }

    public static boolean leerBooleano(String mensaje){

        Scanner entradaEscaner = new Scanner(System.in);
        boolean dato;

        System.out.println(mensaje);
        dato = entradaEscaner.nextBoolean();
        entradaEscaner.nextLine();
        return dato;
    }

    public static boolean leerBoolean2Consola(String mensaje)
    {
        boolean dato = false;
        String captura="";
        System.out.println(mensaje);
        Scanner teclado = new Scanner(System.in);
        captura = teclado.nextLine();

        if(captura.equals("si")) {
            dato = true;
        }
        return dato;
    }

    private static float leerFloat(String msj) {
        // TODO Auto-generated method stub
        float dato;
        System.out.println(msj);
        Scanner teclado= new Scanner(System.in); // creando el objeto para leer por teclado
        dato= Float.parseFloat(teclado.nextLine()); // capturando lo que ingreso por teclado
        return dato;
    }

}
