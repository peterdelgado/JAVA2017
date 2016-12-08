/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidenciasxnd;

import org.xmldb.api.base.XMLDBException;

/**
 *
 * @author pedro
 */
public class TestXND {

    
    public static void main(String[] args)  {
    
        try {
        
            IncidenciasXND gestor = new IncidenciasXND();
            System.out.println("Conexion establecida.");
            Empleado a = new Empleado("whatever", "testpassword", "Peter Delgado", "5555555");
           
//            gestor.insertarEmpleado(a);
//            System.out.println("Empleado insertado");
//            
            gestor.validarEmpleado(a);
//            List<Libro> libros = gestor.selectAllLibros();
//            for (Libro libro : libros) {
//                System.out.println(libro);
//            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException ex) {
            System.out.println("Error con la BBDD: " + ex.getMessage());
        }
    }
}
