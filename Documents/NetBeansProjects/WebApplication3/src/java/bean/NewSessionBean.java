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
import java.util.ArrayList;
import entity.Ranking;
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
            if (d.getPassword().equalsIgnoreCase(psswd)) {
                
               
                Historial h = new Historial("I", "01/12/2016,8:00",d);
                em.persist(h); 
                ok = true;
                
           
                
            } else {
                ok = false;
            }
        } else {
            ok = false;
        }
       
       em.close();
       return ok;
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
     
      public void modificarpasswordEmpleado(Empleado c) {
        EntityManager em = emf.createEntityManager();
        Empleado aux = em.find(Empleado.class, c.getNombreusuario());
        if (aux != null) {
            
            aux.setPassword(c.getPassword());
           
           
            em.persist(aux);
            em.flush();
        }
        em.close();
      }
        
        
        public void borrarEmpleado(Empleado c) {
        EntityManager em = emf.createEntityManager();
        Empleado aux = em.find(Empleado.class, c.getNombreusuario());
        if (aux != null) {
            em.remove(aux);
            em.flush();
        }
        em.close();
    }
  

        
        public List<Incidencia> obtenerIncidenciaById(int id) {
       
            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("SELECT i FROM Incidencia i WHERE i.idincidencia = :idincidencia");
            q.setParameter("idincidencia", id);
            List<Incidencia> testid = q.getResultList();
            return testid;
            
        }
    
        
        public void insertarIncidencia(Incidencia a){
         EntityManager em = emf.createEntityManager();
         em.persist(a);
         
         if (a.getTipo().equalsIgnoreCase("urgente")){
             
             Historial h = new Historial("U", "31/12/2016, 18:54", a.getOrigen());
             em.persist(h);
         }
           
           em.flush();
           em.close();
         
     }
        
        public List<Incidencia> incidenciasByDestino(Empleado e) {
        
          EntityManager em = emf.createEntityManager();
          Query q = em.createQuery("SELECT i FROM Incidencia i WHERE i.destino = :destino");
          Historial h = new Historial("C", "31/12/2017, 18:54", e);
          q.setParameter("destino", e);
          List<Incidencia> testid1 = q.getResultList();
          return testid1;
  
        
        
}    
        
        public List<Incidencia> IncidenciaEmpleadoConcreto(Empleado nombre) {
        EntityManager em = emf.createEntityManager();
          Query q = em.createQuery("SELECT i FROM Incidencia i WHERE i.origen = :origen");
          
          q.setParameter("origen", nombre);
          List<Incidencia> testid2 = q.getResultList();
          return testid2;
  
       
    }
        
       
     public List<Historial> lastLogin(Empleado e){
         
         EntityManager em = emf.createEntityManager();
         Query q = em.createQuery("SELECT h FROM Historial h WHERE h.tipo = :tipo AND" + " h.empleado = :empleado");
         q.setParameter("tipo", "I");
         q.setParameter("empleado", e);
         
         List<Historial> testLog = q.getResultList();
         return testLog;
         
     }   
        
//   "SELECT m FROM Professor m WHERE (SELECT COUNT(e) "
//  + "FROM Professor e WHERE e.manager = m) > 0")
     
     
     public List<Object[]> rankingIncidencias() {
         EntityManager em = emf.createEntityManager();
         Query q = em.createQuery("SELECT h.empleado, COUNT(h.empleado) FROM Historial h WHERE h.tipo =:tipo GROUP BY h.empleado ORDER BY COUNT(h.empleado)DESC ");
         q.setParameter("tipo", "U");
         return q.getResultList();
    }
     
     
     
        
}       
        
        

     
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

