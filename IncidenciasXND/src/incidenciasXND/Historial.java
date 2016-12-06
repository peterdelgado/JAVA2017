/*
 * Entidad Historial
 */
package incidenciasXND;


/**
 *
 * @author mfontana
 */
public class Historial {
    
    private String tipo;
    private String fechaHora;
    private Empleado empleado;

    public Historial() {
    }

    public Historial(String tipo, String fechaHora, Empleado empleado) {
        this.tipo = tipo;
        this.fechaHora = fechaHora;
        this.empleado = empleado;
    }
    

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }


    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
