package co.edu.uniquindio.empresahotel.Model;

public class ServicioHabitacion extends Servicio{
    private String nombre;

    public ServicioHabitacion(String nombre){
        super(nombre);
    }

    public ServicioHabitacion() {
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void consumir(){
        System.out.println("El servicio " + getNombre() + " ha sido consumido.");
    }
}
