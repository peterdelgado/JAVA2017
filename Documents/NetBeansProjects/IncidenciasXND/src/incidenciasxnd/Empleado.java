/*
 * Entidad Empleado
 */
package incidenciasxnd;

/**
 *
 * @author mfontana
 */
public class Empleado {

    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    private String telefono;

    public Empleado() {
    }

    public Empleado(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    
    public Empleado(String nombreUsuario, String password, String nombreCompleto, String telefono) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return nombreUsuario;
    }

    public String todosDatos() {
        return "Nombre usuario: " + nombreUsuario + ", password: " + password + ", nombre completo: " + nombreCompleto + ", telefono: " + telefono;
    }

}
