package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class VentasGeneral_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        \n");
      out.write("        <style type=\"text/css\">        \n");
      out.write("        \n");
      out.write("        </style>\n");
      out.write("    <script src=\"js/jquery-1.8.3.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    <link href=\"css/jquery-ui-1.9.2.custom.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("    <script src=\"js/jquery-ui-1.9.1.custom.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    <script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery.blockUI.js\"></script>    \n");
      out.write("    <script language=\"javascript\" type=\"text/javascript\" src=\"js/func_comunes.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"menu/menu.css\">\n");
      out.write("    <style>\n");
      out.write("        td{font-family: Arial;            \n");
      out.write("        font-size: 12px}\n");
      out.write("        input{font-family: Arial;            \n");
      out.write("        font-size: 12px}\n");
      out.write("    </style>\n");
      out.write("    <script language=\"javascript\" type=\"text/javascript\">\n");
      out.write("        \n");
      out.write("        var hoy = new Date();\n");
      out.write("        var dd = hoy.getDate();\n");
      out.write("        var mm = hoy.getMonth()+1;\n");
      out.write("        var yyyy = hoy.getFullYear();\n");
      out.write("\n");
      out.write("        if(dd<10) {dd='0'+dd;} \n");
      out.write("        if(mm<10) {mm='0'+mm;} \n");
      out.write("        hoy = mm+'/'+dd+'/'+yyyy;\n");
      out.write("                \n");
      out.write("        \n");
      out.write("        $(document).ready(function(){     \n");
      out.write("            \n");
      out.write("                document.getElementById('txtfecha').value = hoy;\n");
      out.write("                document.getElementById('txttotal').value = '0';            \n");
      out.write("                \n");
      out.write("                cargarventas();\n");
      out.write("                \n");
      out.write("                $(\"#btnnuevo\").click( function(){                                                                                                                          \n");
      out.write("                    abrirventadetallaNuevo();\n");
      out.write("                });                                \n");
      out.write("\n");
      out.write("        });\n");
      out.write("        \n");
      out.write("        function abrirventadetalla(idventa){            \n");
      out.write("            var url;\n");
      out.write("            url = 'Ventas.jsp?idventa=' + idventa + '&action=' + 'Consulta';\n");
      out.write("            Fc_Popup_jquery(url,748,550,'Detalle de Venta','#Ventana');\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        function abrirventadetallaNuevo(){            \n");
      out.write("            var url;\n");
      out.write("            url = 'Ventas.jsp?action=' + 'Nuevo';\n");
      out.write("            Fc_Popup_jquery(url,748,550,'Detalle de Venta','#Ventana');\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        function recargar(){\n");
      out.write("            $('#Ventana').dialog('destroy');\n");
      out.write("            cargarventas();\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        function cargarventas(){\n");
      out.write("         $.ajax({\n");
      out.write("            type :'GET',\n");
      out.write("            url :'VentasC?action=ConsultarVenta' + '&idventa=' + '%',\n");
      out.write("            headers : {\n");
      out.write("                Accept : \"application/json; charset=utf-8\", \"Content-Type\" : \"application/json; charset=utf-8\"\n");
      out.write("            },\n");
      out.write("            success : function(result){             \n");
      out.write("                var listaVentas = $.parseJSON(result); \n");
      out.write("                var tr='';\n");
      out.write("                var total=0;\n");
      out.write("                \n");
      out.write("                var totalreg=$(\"#VentasDetalle tr\").length;\n");
      out.write("                if(totalreg>1){\n");
      out.write("                    for(var i = 0; i < totalreg - 1;i++)\n");
      out.write("                    {                    \n");
      out.write("                        $(\"#VentasDetalle tr:last\").remove();\n");
      out.write("                    }   \n");
      out.write("                }                \n");
      out.write("                \n");
      out.write("                for (var i = 0; i < listaVentas.length ; i++){                                                                        \n");
      out.write("                tr=tr + '<tr onclick=\"abrirventadetalla(\\'' + listaVentas[i].idventa + '\\');\"><td>' + listaVentas[i].idventa + '</td><td>' + listaVentas[i].DesEmpleado + '</td><td>' + listaVentas[i].tipodoc + '</td><td>' + listaVentas[i].nrodoc + '</td><td>' + listaVentas[i].total + '</td></tr>';                                                                                                                \n");
      out.write("                total = total + listaVentas[i].total;                                \n");
      out.write("                }                                \n");
      out.write("                \n");
      out.write("                document.getElementById('txttotal').value = parseFloat(total).toFixed(2);                \n");
      out.write("                \n");
      out.write("                $('#VentasDetalle tr:last').after(tr).fadeIn();\n");
      out.write("                \n");
      out.write("            }\n");
      out.write("            });   \n");
      out.write("        }                \n");
      out.write("            \n");
      out.write("    </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body style=\"background-color: black;margin-top: 0px;padding: 0px\">\n");
      out.write("      \n");
      out.write("      <center>            \n");
      out.write("      <div style=\"width: 800px;background-color: white;height: 800px\" align=\"center\">\n");
      out.write("      <div class=\"egcc_menu\" align=\"center\">");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "menu/menu.jsp", out, false);
      out.write("</div>\n");
      out.write("      </br>\n");
      out.write("      </br>      \n");
      out.write("        <table width=\"600\" border=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td align=\"center\">\n");
      out.write("    <fieldset>\n");
      out.write("        <legend align=\"left\">Datos Generales:</legend>\n");
      out.write("        <table width=\"550\" border=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td>Codigo:</td>\n");
      out.write("    <td><span style=\"padding-right:20px\">\n");
      out.write("      <input type=\"text\" name=\"txtcodigo\" width=\"100px\" id=\"txtcodigo\" value=\"E0006\"/>\n");
      out.write("    </span></td>\n");
      out.write("    <td>Fecha:</td>\n");
      out.write("    <td align=\"right\">\n");
      out.write("      <input type=\"text\" name=\"txtfecha\" width=\"100px\" id=\"txtfecha\" />\n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td>Nombres:</td>\n");
      out.write("    <td>\n");
      out.write("      <input type=\"text\" name=\"txtnombre\" width=\"210\" id=\"txtnombre\" value=\"EDISON TAIPE\"/>\n");
      out.write("    </td>\n");
      out.write("    <td>Sede:</td>\n");
      out.write("    <td align=\"right\">\n");
      out.write("      <input type=\"text\" name=\"txtsede\" width=\"215px\" id=\"txtsede\" value=\"CENTRAL LIMA\"/>\n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("        \n");
      out.write("    </fieldset>\n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td valign=\"top\" align=\"center\">    \n");
      out.write("        </br>\n");
      out.write("        <fieldset>\n");
      out.write("        <legend align=\"left\">Ventas</legend>   \n");
      out.write("        \n");
      out.write("        <div align=\"right\" style=\"padding-right:10px\"><input name=\"btnnuevo\" id=\"btnnuevo\" type=\"button\" value=\"Nuevo\" /></div>\n");
      out.write("        <table id=\"VentasDetalle\" width=\"550\" style=\"border-collapse:collapse\" cellspacing=0 border=\"1\">\n");
      out.write("          <tr>\n");
      out.write("              <td style=\"background-color: #0079b2; color: white\">Cod. Venta</td>\n");
      out.write("            <td style=\"background-color: #0079b2; color: white\">Vendedor</td>\n");
      out.write("            <td style=\"background-color: #0079b2; color: white\">Tipo Doc.</td>\n");
      out.write("            <td style=\"background-color: #0079b2; color: white\">Nro Doc</td>\n");
      out.write("            <td style=\"background-color: #0079b2; color: white\">Monto</td>\n");
      out.write("          </tr>\n");
      out.write("        </table>\n");
      out.write("     \n");
      out.write("    </fieldset></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td valign=\"top\" align=\"right\" style=\"padding-right:20px\">\n");
      out.write("        </br>\n");
      out.write("        Total :  <input type=\"text\" name=\"txttotal\" width=\"70px\" id=\"txttotal\" />\n");
      out.write("</td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td valign=\"top\">\n");
      out.write("\n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("</div>\n");
      out.write("</center>\n");
      out.write("    \n");
      out.write("     <div id=\"Ventana\" style=\"overflow:hidden\"></div>\n");
      out.write("     \n");
      out.write("     <style type=\"text/css\">\n");
      out.write("     .ui-dialog .ui-dialog-content {padding: 0;}\n");
      out.write("     .ui-widget {font-family:Arial;}\n");
      out.write("     .ui-datepicker SELECT.ui-datepicker-year{width: 55px;}\n");
      out.write("     .ui-dialog .ui-dialog-titlebar{font-family:Verdana,Arial,sans-serif;font-size:13px;height: 13px;padding-top:0px;}\n");
      out.write("     .ui-dialog .ui-widget-header {background:#0160a4;border:1px solid #333;height:20px;}\n");
      out.write("     .ui-dialog .ui-dialog-title {width: 100%;text-align: center;color:#fff;padding-top:0.2em;font-size:13px}     \n");
      out.write("    </style>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
