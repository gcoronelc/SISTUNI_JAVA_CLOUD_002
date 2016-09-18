
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
import uni.dao.ProductoDao;
import uni.entity.ProductoTO;

@WebServlet(name = "ProductosC", urlPatterns = {"/ProductosC"})
public class ProductosC extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String action = request.getParameter("action");
            
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            ProductoDao producto = new ProductoDao();                                                        
                
            if (action.equalsIgnoreCase("CargarProductos")) {                                                
                try {
                    out.print(gson.toJson(producto.readAll()));
                    out.flush();
                    out.close();
                } catch (Exception ex) { Logger.getLogger(ClienteC.class.getName()).log(Level.SEVERE, null, ex); }
            } else if (action.equalsIgnoreCase("BuscarProducto")) { 
                try {
                    ProductoTO p = new ProductoTO();
                    String idproducto = request.getParameter("idproducto");
                    p.setIdproducto(idproducto);
                    out.print(gson.toJson(producto.find(p)));
                    out.flush();
                    out.close();
                } catch (Exception ex) { Logger.getLogger(ClienteC.class.getName()).log(Level.SEVERE, null, ex); }
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
