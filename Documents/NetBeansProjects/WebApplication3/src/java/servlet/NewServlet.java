/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Incidencia;
import java.util.List;
import bean.NewSessionBean;
import javax.ejb.EJB;
import entity.Empleado;
/**
 *
 * @author pedro
 */
public class NewServlet extends HttpServlet {
     
    
    @EJB
    NewSessionBean gestor;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
                     
           List<Incidencia> allIncidencias = gestor.selectAllIncidencia();
            out.println("<h1>Listado de Incidencias</h1>");
            for (Incidencia c : allIncidencias) {
                out.println("<p>" + c + "</p>");
            }             
   out.println("\n*************************************************\n");            
             Empleado a = new Empleado("fito", "pez", "meme", "44444");
             Empleado c = new Empleado("jvelasquez", "pope","Jose Velasquez", "2222");
            if (gestor.existeEmpleado(a.getNombreusuario())) {
                out.println("<p>Ya existe un empleado con este nombre en la bbdd</p>");
            } else {
                gestor.altaEmpleado(a);
                out.println("<p>Empleado dado de alta</p>");
            }
//  out.println("\n*************************************************\n");       
//             Incidencia c = new Incidencia(1, a.getNombreusuario(),b.getNombreusuario(), "23/09/2016, 9:00", "prueba", "urgente");
//             Incidencia d = new Incidencia(0,"23/09/2016, 9:00", "otra prueba", "Urgente", a.getNombreusuario(), "jvelasquez");
//             
//             out.println("<p>Insertando Incidencia...<p>");
//             gestor.insertarIncidencia(c);
//             out.println("<p>Incidencia Insertada<p>");
             
  out.println("\n*************************************************\n");          
          if (gestor.validarEmpleado(a.getNombreusuario(), a.getPassword())){
              out.println("<p>Empleado Validado<p>");
              
          }
          else {
              out.println("<p>Empleado NO Validado<p>");
          }
  out.println("\n*************************************************\n");     
            
  
        out.println("<p>Modificando Empleado...<p>");
        gestor.modificarEmpleado(c);
        out.println("<p>Empleado Modificado<p>");
  
  
  
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
