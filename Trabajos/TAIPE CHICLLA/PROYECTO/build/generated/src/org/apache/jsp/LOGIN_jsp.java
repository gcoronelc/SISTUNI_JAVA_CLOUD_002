package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class LOGIN_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<noscript><meta http-equiv=\"refresh\" content=\"0;url=Error/Oops.aspx?Error=Habilite JAVASCRIPT en su navegador!!!\"></noscript>\n");
      out.write("\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head id=\"Head1\" runat=\"server\">\n");
      out.write("    <title>Acceso al Sistema</title>\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=8;IE=9;IE=10;IE=11\" />\n");
      out.write("    <link href=\"css/csslogin.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("    <link href=\"css/cssbase.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("    <script src=\"js/jsValidaInputs.js\" type=\"text/javascript\"></script>\n");
      out.write("    \n");
      out.write("    <script src=\"js/jquery-1.8.3.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    <link href=\"css/jquery-ui-1.9.2.custom.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("    <script src=\"js/jquery-ui-1.9.1.custom.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    <script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery.blockUI.js\"></script>    \n");
      out.write("    <script language=\"javascript\" type=\"text/javascript\" src=\"js/func_comunes.js\"></script>\n");
      out.write("    \n");
      out.write("    <script language=\"javascript\" type=\"text/javascript\" >\n");
      out.write("             function Validar()\n");
      out.write("\t\t     {\n");
      out.write("\t\t        if (document.getElementById(\"txtPass\").value == '' || document.getElementById(\"txtUser\").value == '') \n");
      out.write("\t\t         {\n");
      out.write("\t\t            mostraravisoAceptar(\"Debe ingresar usuario y clave.\");\n");
      out.write("\t\t\t        return false;\n");
      out.write("\t\t         }\n");
      out.write("\t\t     }\n");
      out.write("    \t\t \n");
      out.write("\t\t     function Fc_AbrirVentanas() {\n");
      out.write("\n");
      out.write("\t\t         window.open('Tra_Mantenimiento/Tra_ManUsuarios.aspx', 'cambiarclave', 'toolbar=0,location=0,directories=0, status=0,menubar=0,scrollbars=0,resizable=0,width=320,height=240,top=0,left=0');\t             \t\t            \n");
      out.write("\n");
      out.write("\t\t     }\n");
      out.write("    \t\t                                          \n");
      out.write("            </script>\n");
      out.write("            <style type=\"text/css\">\n");
      out.write("            \n");
      out.write("            body   \n");
      out.write("            {\n");
      out.write("                /*background-color:#F4F4F4;*/\n");
      out.write("                background: #b6b7bc;                                \n");
      out.write("                font-family: Arial, Helvetica, sans-serif;\n");
      out.write("                margin: 0px;\n");
      out.write("                padding: 0px;\n");
      out.write("                background-image: url(\"Images/pjback.gif\");\n");
      out.write("                background-repeat:repeat;\n");
      out.write("                /*color: white;*/\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            </style>    \n");
      out.write("            \n");
      out.write("            <script type=\"text/javascript\">\n");
      out.write("$(document).ready(function(){              \n");
      out.write("\n");
      out.write("    $(\"#btnLogIn\").click( function(){\n");
      out.write("        $.blockUI( { message: \"Cargando\"} );\n");
      out.write("        $.ajax({\n");
      out.write("        type :'GET',\n");
      out.write("        async:true,\n");
      out.write("        url :'EmpleadoC?usuario=' + document.getElementById('txtUser').value + '&clave=' + document.getElementById('txtPass').value + '&action=' + 'Validar',\n");
      out.write("        headers : {\n");
      out.write("            Accept : \"application/json; charset=utf-8\", \"Content-Type\" : \"application/json; charset=utf-8\"\n");
      out.write("        },\n");
      out.write("        success : function(result){ \n");
      out.write("            \n");
      out.write("            if (result=='\"1\"'){ \n");
      out.write("                window.location.href = 'principal.jsp';\n");
      out.write("            }else{\n");
      out.write("                mostraravisoAceptar(\"usuario no existe\");        \n");
      out.write("            }            \n");
      out.write("            \n");
      out.write("            $.unblockUI();        \n");
      out.write("        }\n");
      out.write("        });\n");
      out.write("\n");
      out.write("    });    \n");
      out.write("       \n");
      out.write("});\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div id=\"contenedor\" align=\"center\">\n");
      out.write("        <div id=\"wrapper\">\n");
      out.write("\t        <div id=\"login\">\n");
      out.write("\t            <div id=\"loginControls\">\t            \n");
      out.write("\t                <span style=\"color:#FFF;font-weight:bold;text-align:right;padding-right:10px;\"><br />\n");
      out.write("                    Ver. 1.0.0.0&nbsp;&nbsp; </span>&nbsp;<br />\n");
      out.write("\t                <br />\n");
      out.write("\t                <br />\n");
      out.write("\t                <br />\n");
      out.write("\t                <br />\n");
      out.write("\t                <hr />\n");
      out.write("\t                <h2>BIENVENIDO AL MÓDULO</h2>\n");
      out.write("                    <hr />\n");
      out.write("                    <form id=\"wf_Login\" method=\"post\" runat=\"server\">\n");
      out.write("\t\t                <table align=\"center\" border=\"0\" id=\"tablalogin\" cellpadding=\"0\" cellspacing=\"0\" >\t\t            \n");
      out.write("                            <tr>\n");
      out.write("                                <th width=\"90\">\n");
      out.write("                                    &nbsp;&nbsp;USUARIO:\n");
      out.write("                                    </th>\n");
      out.write("                                <td>\n");
      out.write("                                    <input name=\"txtUser\" autocomplete=\"off\" type=\"text\" class=\"user\" id=\"txtUser\" runat=\"server\" maxlength=\"20\" style=\"width:170px\" />\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>\n");
      out.write("                                    &nbsp;&nbsp;CLAVE:\n");
      out.write("                                    </th>\n");
      out.write("                                <td>\n");
      out.write("                                    <input name=\"txtPass\" autocomplete=\"off\" type=\"password\" class=\"password\" maxlength=\"20\" id=\"txtPass\" runat=\"server\" style=\"width:170px\" value=\"\"/>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            <tr>\n");
      out.write("                                <th></th>\n");
      out.write("                                <td>\n");
      out.write("                                                                                                \n");
      out.write("                  <cc11:CaptchaControl ID=\"CaptchaControl1\" runat=\"server\"  CaptchaHeight=\"31\" \n");
      out.write("                      CaptchaWidth=\"160\" Font-Names=\"Arial\" Font-Size=\"X-Small\" ForeColor=\"White\" \n");
      out.write("                      Height=\"30px\" Text=\"Codigo mostrado:\" Width=\"160px\" \n");
      out.write("                      style=\"margin-bottom: 0px\" CaptchaMinTimeout=\"5\" />\n");
      out.write("                                                \n");
      out.write("                                                \n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            <tr>\n");
      out.write("                                <th></th>\n");
      out.write("                                <td>\n");
      out.write("                                    <div align=\"left\" style=\"margin-top:10px;\">\n");
      out.write("                                        <input type=\"button\" ID=\"btnLogIn\" onclick=\"javascript:Validar();\" Class=\"btnIngreso\" value=\"&nbsp;&nbsp;&nbsp;INGRESAR\" />\n");
      out.write("                                    </div>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            <tr>\n");
      out.write("                                <td colspan=\"2\" style=\"line-height:16px;\">\n");
      out.write("                                    <br />&nbsp;&nbsp;&nbsp;Se recomienda el uso de Internet Explorer 8 o superior\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            <script type=\"text/javascript\">document.getElementById(\"txtUser\").focus();</script>                        \n");
      out.write("                        </table>\n");
      out.write("                        <br /><br />\n");
      out.write("                        <div id=\"keypaddiv\" style=\"position: fixed ;background-color:#c3c3c3;\"></div>\n");
      out.write("    \t            </form>\n");
      out.write("                </div>\n");
      out.write("\t        </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div id=\"ventana\"></div>\n");
      out.write("    <div id=\"dialog-message\" title=\"Aviso\">\n");
      out.write("    <p>\n");
      out.write("        <span style=\"float:left; margin:0 7px 50px 0; padding-left: 3px;\"></span>    \n");
      out.write("    </p>\n");
      out.write("    <div id=\"aviso\"></div>\n");
      out.write("   </div>\n");
      out.write("    \n");
      out.write("    <style type=\"text/css\">\n");
      out.write("     .ui-dialog .ui-dialog-content {padding: 0;}\n");
      out.write("     .ui-widget {font-family:Arial;}\n");
      out.write("     .ui-datepicker SELECT.ui-datepicker-year{width: 55px;}\n");
      out.write("     .ui-dialog .ui-dialog-titlebar{font-family:Verdana,Arial,sans-serif;font-size:13px;height: 13px;padding-top:0px;}\n");
      out.write("     .ui-dialog .ui-widget-header {background:#0160a4;border:1px solid #333;height:20px;}\n");
      out.write("     .ui-dialog .ui-dialog-title {width: 100%;text-align: center;color:#fff;padding-top:0.2em;font-size:13px}     \n");
      out.write("    </style>\n");
      out.write("    \n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
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
