/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidenciasxnd;

import org.xmldb.api.base.XMLDBException;
import java.util.List;
/**
 *
 * @author pedro
 */
public class TestXND {

    
    public static void main(String[] args)  {
    
        try {
        
            IncidenciasXND gestor = new IncidenciasXND();
            System.out.println("Conexion establecida.");
            Empleado a = new Empleado("pdelgado", "testpassword", "Peter Delgado", "5555555");
           
//            gestor.insertarEmpleado(a);
//            System.out.println("Empleado insertado");
//            
            gestor.validarEmpleado(a);
            List<Incidencia> incidencias = gestor.selectAllIncidencias();
            for (Incidencia incidencia : incidencias) {
                System.out.println(incidencias);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException ex) {
            System.out.println("Error con la BBDD: " + ex.getMessage());
        }
    }
}
