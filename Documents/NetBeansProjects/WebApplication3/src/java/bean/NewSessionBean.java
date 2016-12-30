/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Stateless;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import entity.Incidencia;
import entity.Empleado;
import java.util.Date;
import entity.Historial;
import java.text.SimpleDateFormat;
/**
 *
 * @author pedro
 */
@Stateless
public class NewSessionBean {

@PersistenceUnit
EntityManagerFactory emf;

private SimpleDateFormat sdf;
    
    
     public List<Incidencia> selectAllIncidencia() {
       return emf.createEntityManager().createNamedQuery("Incidencia.findAll").getResultList();
       
    }
     
     
     public void insertarIncidencia(Incidencia a){
         EntityManager em = emf.createEntityManager();
            em.persist(a);
            em.flush();
            em.close();
         
     }
     
     public void altaEmpleado(Empleado c) {
        EntityManager em = emf.createEntityManager();
//        if (!existeCocinero(c.getNombre())) {
            em.persist(c);
            em.flush();
            em.close();
//        }
    }
     
     
    public boolean existeEmpleado(String nombre) {
        EntityManager em = emf.createEntityManager();
        Empleado c = em.find(Empleado.class, nombre);
        em.flush();
        em.close();
        return c != null;
    }
    
     public boolean validarEmpleado(String usr, String psswd) {
        EntityManager em = emf.createEntityManager();
        Empleado d = em.find(Empleado.class, usr);
        boolean ok;
        if (d != null) {
            if (d.getPassword().equalsIgnoreCase(usr)) {
                Historial h = new Historial(0, "I", sdf.format(new Date()), d);
                ok = true;
            } else {
                ok = false;
            }
        } else {
            ok = false;
        }
        
       em.close();
       return d != null;
    }
     
     public void modificarEmpleado(Empleado c) {
        EntityManager em = emf.createEntityManager();
        Empleado aux = em.find(Empleado.class, c.getNombreusuario());
        if (aux != null) {
            aux.setNombreusuario(c.getNombreusuario());
            aux.setPassword(c.getPassword());
            aux.setNombrecompleto(c.getNombrecompleto());
            aux.setTelefono(c.getTelefono());
           
            em.persist(aux);
            em.flush();
        }
        em.close();
    }
     
     
     
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
