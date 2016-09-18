/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uni.dao.ClienteDao;

/**
 *
 * @author Edison
 */
@WebServlet(name = "ClienteC", urlPatterns = {"/ClienteC"})
public class ClienteC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String action = request.getParameter("action");
            
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            ClienteDao cliente = new ClienteDao();                                                        
                
            if (action.equalsIgnoreCase("CargarCliente")) {
                                                
            try {
                out.print(gson.toJson(cliente.readAll()));
                out.flush();
                out.close();
            } catch (Exception ex) {
                Logger.getLogger(ClienteC.class.getName()).log(Level.SEVERE, null, ex);
            }                
                
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

}
