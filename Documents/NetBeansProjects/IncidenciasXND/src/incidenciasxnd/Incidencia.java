/*
 * Entidad Incidencia
 */
package incidenciasxnd;


/**
 *
 * @author mfontana
 */
public class Incidencia {
    
    private int id;
    private Empleado origen;
    private Empleado destino;
    private String fechaHora;
    private String detalle;
    private String tipo;
    
    
    
    public Incidencia() {
    }

    public Incidencia(int id, Empleado origen, Empleado destino, String fechaHora, String detalle, String tipo) {
       
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaHora = fechaHora;
        this.detalle = detalle;
        this.tipo = tipo;
    }
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }


    public Empleado getDestino() {
        return destino;
    }

    public void setDestino(Empleado destino) {
        this.destino = destino;
    }


    public Empleado getOrigen() {
        return origen;
    }

    public void setOrigen(Empleado origen) {
        this.origen = origen;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "id=" + id + ", origen=" + origen + ", destino=" + destino + ", fechaHora=" + fechaHora + ", detalle=" + detalle + ", tipo=" + tipo + '}';
    }
    
}
