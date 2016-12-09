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
            Empleado a = new Empleado("Fbarnes", "p", "crap", "111111");
            Empleado b = new Empleado("Fbarnes", "p", "FredBarnes", "1111111");
            
                    
//            gestor.insertarEmpleado(a);
//            System.out.println("Empleado insertado");
            
            
             System.out.println("Test: Validando empleado correcto...");
        if (gestor.validarEmpleado(a)) {
            System.out.println("Empleado validado correctamente.");
        } else {
            System.out.println("Usuario o password incorrecto.");
        }
   System.out.println("\n*************************************************\n");
            
   System.out.println("Test:  Modificando del empleado...");
        System.out.println("Datos del empleado");
//        System.out.println(a.todosDatos());
        
//        a.setNombreCompleto("Otro nombre");
        
        if (gestor.ModificarEmpleado("Freddy","Fred")) {
            System.out.println("Datos modificados.");
            System.out.println("Nuevos datos del empleado");
            System.out.println(a.todosDatos());
        } else {
            System.out.println("El usuario no se ha podido modificar. No exist?a usuario en la bbdd.");
        }
        System.out.println("\n*************************************************\n");
   
   
   
   
   
   List<Incidencia> incidencias = gestor.selectAllIncidencias();
            System.out.println(incidencias);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException ex) {
            System.out.println("Error con la BBDD: " + ex.getMessage());
        }
    }
}
