package com.beans;

import com.dao.ProductoDAO;
import com.model.Producto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ProductoBean {

    private Producto producto = new Producto();
    private List<Producto> lstProducto;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void operar() throws Exception {
        switch (accion) {
            case "Registrar":
                this.registrar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }

    private void registrar() throws Exception {
        ProductoDAO dao;
        try {
            dao = new ProductoDAO();
            dao.registrar(producto);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Producto> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Producto> lstProducto) {
        this.lstProducto = lstProducto;
    }

    private boolean isPostBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

    public void listar(String valor) throws Exception {
        ProductoDAO dao;
        try {
            if (valor.equals("F")) {
                if (isPostBack() == false) {
                    dao = new ProductoDAO();
                    lstProducto = dao.listar();
                }
            } else {
                dao = new ProductoDAO();
                lstProducto = dao.listar();
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(Producto per) throws Exception {
        ProductoDAO dao;
        Producto temp;
        try {
            dao = new ProductoDAO();
            temp = dao.leerID(per);
            if (temp != null) {
                this.producto = temp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        this.producto.setCodigo(0);
        this.producto.setNombre("");
        this.producto.setPrecio(0);
    }

    private void modificar() throws Exception {
        ProductoDAO dao;
        try {
            dao = new ProductoDAO();
            dao.modificar(producto);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Producto per) throws Exception {
        ProductoDAO dao;
        try {
            dao = new ProductoDAO();
            dao.eliminar(per);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }
}
