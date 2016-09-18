/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.DetalleVenta;
import com.model.Persona;
import com.model.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laptop
 */
public class VentaDAO extends DAO {

    public void registrar(Venta venta, List<DetalleVenta> lista) throws Exception {
        try {
            this.Conectar();
            this.getCn().setAutoCommit(false);
            PreparedStatement psd = this.getCn().prepareStatement("Insert into Venta(fecha,codPersona,monto) values (?,?,?)");
            psd.setDate(1, venta.getFecha());
            psd.setInt(2, venta.getPersona().getCodigo());
            psd.setDouble(3, venta.getMonto());
            psd.executeUpdate();
            psd.close();
            
            PreparedStatement psd2=this.getCn().prepareStatement("SELECT LAST_INSERT_ID() FROM venta limit 1");
            ResultSet rs;
            int codVenta=0;
            rs=psd2.executeQuery();
            while(rs.next()){
                codVenta=rs.getInt(1);
            }
            rs.close();
            
            for (DetalleVenta det : lista) {
                PreparedStatement psd3 = this.getCn().prepareStatement("Insert into Detalleventa(codVenta,codProducto,cantidad) values (?,?,?)");
                psd3.setInt(1, codVenta);
                psd3.setInt(2, det.getProducto().getCodigo());
                psd3.setInt(3, det.getCantidad());
                psd3.executeUpdate();
                psd3.close();
            }
            this.getCn().commit();

        } catch (Exception e) {
            this.getCn().rollback();
        } finally {
            this.Cerrar();
        }
    }
}
