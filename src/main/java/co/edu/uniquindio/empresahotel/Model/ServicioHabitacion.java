package co.edu.uniquindio.empresahotel.Model;

public class ServicioHabitacion extends Servicio{

    public ServicioHabitacion(String nombre){
        super(nombre);
    }

    public ServicioHabitacion() {
        super();
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public void consumir(){
        System.out.println("El servicio " + getNombre() + " ha sido consumido.");
    }
}
