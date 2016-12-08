/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidenciasxnd;


import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.*;
import org.xmldb.api.base.*;
import org.xmldb.api.*;
/**
 *
 * @author pedro
 */
public class IncidenciasXND {
    
    
    
    private final Database database;
    private final String uri = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private final String user = "admin";
    private final String pass = "live1234";
    private final String colecEmpleados = "/db/DBIncidencias/Empleados";
    private final String colecIncidencias = "/db/DBIncidencias/Incidencias";

    public IncidenciasXND() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        
        
        
        String driver = "org.exist.xmldb.DatabaseImpl";
        Class c1;
        c1 = Class.forName(driver);
        database = (Database) c1.newInstance();
        DatabaseManager.registerDatabase(database);
        
    
    }

    public void insertarEmpleado(Empleado empleados) throws XMLDBException {
        String consulta = "update insert <empleado> <nombreUsuario>" + empleados.getNombreUsuario() + "</nombreUsuario>"
                + "<password>" + empleados.getPassword() + "</password>"
                + "<nombreCompleto>" + empleados.getNombreCompleto() + "</nombreCompleto>" + "<telefono>" + empleados.getTelefono() + "</telefono></empleado> into /Empleados";
        ejecutarConsultaUpdate(colecEmpleados, consulta);
    }
    
    public boolean validarEmpleado(Empleado empleados) throws XMLDBException{
        
               String consulta = "for $p in //Empleados/empleado/nombreUsuario return $p";
                
                       
               ResourceSet resultado = ejecutarConsultaXQuery(colecEmpleados, consulta);
               ResourceIterator iterador = resultado.getIterator();
        
                while (iterador.hasMoreResources()) {
               
                    XMLResource res = (XMLResource) iterador.nextResource();
                    Node nodo = res.getContentAsDOM();
                    System.out.println(nodo.getNodeName());
                    NodeList hijo = nodo.getChildNodes();
                    NodeList datosEmp = hijo.item(0).getChildNodes();
                    Empleado l = leerDomEmpleados(datosEmp);
                    
                    if (l.equals(empleados.getNombreUsuario())) {
                   
                   
                   System.out.println("Empleado Validado");
                   return true;
                   
               }
  
               else {
                   
                   System.out.println("Empleado No Validado");
               }
                }
    
    return false;
    }
    
    
     private Empleado leerDomEmpleados(NodeList datos) {
        int contador = 1;
        Empleado l = new Empleado();
        for (int i = 0; i < datos.getLength(); i++) {
            Node ntemp = datos.item(i);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                
                        l.setNombreUsuario(ntemp.getChildNodes().item(0).getNodeValue());
                        
                        
                        
                       
                        
                        
                        
                        
                    
                   
                }
            }
        return l;
     
     }
        
    
  public List<Incidencia> selectAllLibros() throws XMLDBException {
        String consulta = "for $l in //Libros/Libro return $l";
        ResourceSet resultado = ejecutarConsultaXQuery(colecIncidencias, consulta);
        ResourceIterator iterador = resultado.getIterator();
        List<Incidencia> todosIncidencias = new ArrayList<>();
        while (iterador.hasMoreResources()) {
            XMLResource res = (XMLResource) iterador.nextResource();
//             Tenemos que leer el resultado como un DOM
            Node nodo = res.getContentAsDOM();
            System.out.println(nodo.getNodeName());
//             Leemos la lista de hijos que son tipo Libro
            NodeList hijo = nodo.getChildNodes();
//             Leemos los hijos del Libro
            NodeList datosLibro = hijo.item(0).getChildNodes();
            Incidencia l = leerDomIncidencias(datosLibro);
            todosIncidencias.add(l);
        }
        return todosIncidencias;
    }

    // M?todo auxiliar que lee los datos de un Libro
    private Incidencia leerDomIncidencias(NodeList datos) {
        int contador = 1;
        Incidencia l = new Incidencia();
        for (int i = 0; i < datos.getLength(); i++) {
            Node ntemp = datos.item(i);
            
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                switch (contador) {
                    case 1:
                        l.setId(Integer.parseInt(ntemp.getChildNodes().item(0).getNodeValue()));
                        contador ++;
                        break;
                    case 2:
                         Empleado a = new Empleado(ntemp.getChildNodes().item(0).getNodeValue());
                         l.setOrigen(a);
                         contador++;
                        break;
                    case 3:
                        Empleado b = new Empleado(ntemp.getChildNodes().item(0).getNodeValue());
                        l.setDestino(b);
                        contador++;
                        break;
                    case 4:
                       
                        l.setFechaHora(ntemp.getChildNodes().item(0).getNodeValue());
                        contador++;
                        break;
                    case 5:
                        l.setTipo(null);
                        contador++;
                        break;
                    default:
                        break;
                }
            }
        }
        return l;
    }  
    
    
    
    
    
    private ResourceSet ejecutarConsultaXQuery(String coleccion, String consulta) throws XMLDBException {
        XQueryService servicio = prepararConsulta(coleccion);
        ResourceSet resultado = servicio.query(consulta);
        return resultado;
    }

    
    
	
    private void ejecutarConsultaUpdate(String coleccion, String consulta) throws XMLDBException {
        XQueryService servicio = prepararConsulta(coleccion);
        CompiledExpression consultaCompilada = servicio.compile(consulta);
        servicio.execute(consultaCompilada);
    }
	
    private XQueryService prepararConsulta(String coleccion) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(uri + coleccion, user, pass);
        XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
        servicio.setProperty(OutputKeys.INDENT, "yes");
        servicio.setProperty(OutputKeys.ENCODING, "UTF-8");
        return servicio;
    }
    
    
}