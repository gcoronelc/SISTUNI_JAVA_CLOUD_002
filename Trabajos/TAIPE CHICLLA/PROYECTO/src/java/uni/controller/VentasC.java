
package uni.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uni.entity.VentaTO;
import uni.service.Ventas;
import uni.dao.VentaDao;
import uni.entity.DetalleTO;

@WebServlet(name = "VentasC", urlPatterns = {"/VentasC"})
public class VentasC extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");            
        PrintWriter out = response.getWriter();            
            
        VentaDao ventadao = new VentaDao();
        Gson gson = new Gson();
        
        if (action.equalsIgnoreCase("RegistrarVenta")) { 
            
            String json = request.getParameter("json");                    

            DetalleTO[] ventas = gson.fromJson(json, DetalleTO[].class);        
            List<DetalleTO> listaventas = Arrays.asList(ventas);

            VentaTO ve = new VentaTO();

            String idcliente,idempleado,idtipodocumento,txtnumerodoc;
            Double Total;

            idcliente = request.getParameter("idcliente");
            idempleado = request.getParameter("idempleado");
            idtipodocumento = request.getParameter("idtipodocumento");
            txtnumerodoc = request.getParameter("txtnumerodoc");
            Total = Double.parseDouble(request.getParameter("Total"));

            ve.setIdcliente(idcliente);
            ve.setIdempleado(idempleado);
            ve.setTipodoc(idtipodocumento);
            ve.setNrodoc(txtnumerodoc);
            ve.setTotal(Total);
            ve.setDetalle(listaventas);

            try {
                //graba venta
                ventadao.registraDocumento(ve);
            } catch (Exception ex) {
                Logger.getLogger(VentasC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if (action.equalsIgnoreCase("ConsultarVenta")) {
            try {
                
                String idventa = request.getParameter("idventa");
                out.print(gson.toJson(ventadao.readAllVentas("%")));
                out.flush();
                out.close();
                
            } catch (Exception ex) {
                Logger.getLogger(VentasC.class.getName()).log(Level.SEVERE, null, ex);
            }                
        }else if (action.equalsIgnoreCase("ConsultarVentaDentalle")) {
            try {
                String idventa = request.getParameter("idventa");
                out.print(gson.toJson(ventadao.readAllDetalle( Integer.parseInt(idventa))));
                out.flush();
                out.close();
            } catch (Exception ex) {
                Logger.getLogger(VentasC.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");            
        PrintWriter out = response.getWriter();            
            
        VentaDao ventadao = new VentaDao();
        Gson gson = new Gson();
        
        if (action.equalsIgnoreCase("RegistrarVenta")) { 
            
            String json = request.getParameter("json");                    

            DetalleTO[] ventas = gson.fromJson(json, DetalleTO[].class);        
            List<DetalleTO> listaventas = Arrays.asList(ventas);

            VentaTO ve = new VentaTO();

            String idcliente,idempleado,idtipodocumento,txtnumerodoc;
            Double Total;

            idcliente = request.getParameter("idcliente");
            idempleado = request.getParameter("idempleado");
            idtipodocumento = request.getParameter("idtipodocumento");
            txtnumerodoc = request.getParameter("txtnumerodoc");
            Total = Double.parseDouble(request.getParameter("Total"));

            ve.setIdcliente(idcliente);
            ve.setIdempleado(idempleado);
            ve.setTipodoc(idtipodocumento);
            ve.setNrodoc(txtnumerodoc);
            ve.setTotal(Total);
            ve.setDetalle(listaventas);

            try {
                //graba venta
                ventadao.registraDocumento(ve);
            } catch (Exception ex) {
                Logger.getLogger(VentasC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }        
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
