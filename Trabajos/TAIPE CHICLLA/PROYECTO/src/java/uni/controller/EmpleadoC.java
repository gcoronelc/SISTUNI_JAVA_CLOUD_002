/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uni.entity.*;
import uni.dao.EmpleadoDao;

/**
 *
 * @author Edison
 */
@WebServlet(name = "EmpleadoC", urlPatterns = {"/EmpleadoC"})
public class EmpleadoC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       
        try {
            
            String action = request.getParameter("action");
            
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            EmpleadoDao empleado = new EmpleadoDao();                                                        
                
            if (action.equalsIgnoreCase("Validar")) {
                
                String usuario = request.getParameter("usuario");
                String clave = request.getParameter("clave");
                
                out.print(gson.toJson(empleado.validar(usuario, clave)));
                out.flush();
                out.close();
                
            }else if (action.equalsIgnoreCase("CargarCombo")) {
                
                try {
                    out.print(gson.toJson(empleado.readAll()));
                    out.flush();
                    out.close();
                } catch (Exception ex) {
                    Logger.getLogger(EmpleadoC.class.getName()).log(Level.SEVERE, null, ex);
                }
                                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
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
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
