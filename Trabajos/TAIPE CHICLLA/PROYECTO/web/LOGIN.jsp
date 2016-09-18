<%-- 
    Document   : LOGIN
    Created on : 09-ago-2016, 20:52:06
    Author     : Edison
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<noscript><meta http-equiv="refresh" content="0;url=Error/Oops.aspx?Error=Habilite JAVASCRIPT en su navegador!!!"></noscript>

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <title>Acceso al Sistema</title>
    <meta http-equiv="X-UA-Compatible" content="IE=8;IE=9;IE=10;IE=11" />
    <link href="css/csslogin.css" rel="stylesheet" type="text/css" />
    <link href="css/cssbase.css" rel="stylesheet" type="text/css" />
    <script src="js/jsValidaInputs.js" type="text/javascript"></script>
    
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link href="css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="js/jquery.blockUI.js"></script>    
    <script language="javascript" type="text/javascript" src="js/func_comunes.js"></script>
    
    <script language="javascript" type="text/javascript" >
             function Validar()
		     {
		        if (document.getElementById("txtPass").value == '' || document.getElementById("txtUser").value == '') 
		         {
		            mostraravisoAceptar("Debe ingresar usuario y clave.");
			        return false;
		         }
		     }
    		 
		     function Fc_AbrirVentanas() {

		         window.open('Tra_Mantenimiento/Tra_ManUsuarios.aspx', 'cambiarclave', 'toolbar=0,location=0,directories=0, status=0,menubar=0,scrollbars=0,resizable=0,width=320,height=240,top=0,left=0');	             		            

		     }
    		                                          
            </script>
            <style type="text/css">
            
            body   
            {
                /*background-color:#F4F4F4;*/
                background: #b6b7bc;                                
                font-family: Arial, Helvetica, sans-serif;
                margin: 0px;
                padding: 0px;
                background-image: url("Images/pjback.gif");
                background-repeat:repeat;
                /*color: white;*/
            }

            </style>    
            
            <script type="text/javascript">
$(document).ready(function(){              

    $("#btnLogIn").click( function(){
        $.blockUI( { message: "Cargando"} );
        $.ajax({
        type :'GET',
        async:true,
        url :'EmpleadoC?usuario=' + document.getElementById('txtUser').value + '&clave=' + document.getElementById('txtPass').value + '&action=' + 'Validar',
        headers : {
            Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"
        },
        success : function(result){ 
            
            if (result=='"1"'){ 
                window.location.href = 'principal.jsp';
            }else{
                mostraravisoAceptar("usuario no existe");        
            }            
            
            $.unblockUI();        
        }
        });

    });    
       
});

</script>
</head>
<body>
    <div id="contenedor" align="center">
        <div id="wrapper">
	        <div id="login">
	            <div id="loginControls">	            
	                <span style="color:#FFF;font-weight:bold;text-align:right;padding-right:10px;"><br />
                    Ver. 1.0.0.0&nbsp;&nbsp; </span>&nbsp;<br />
	                <br />
	                <br />
	                <br />
	                <br />
	                <hr />
	                <h2>BIENVENIDO AL MÓDULO</h2>
                    <hr />
                    <form id="wf_Login" method="post" runat="server">
		                <table align="center" border="0" id="tablalogin" cellpadding="0" cellspacing="0" >		            
                            <tr>
                                <th width="90">
                                    &nbsp;&nbsp;USUARIO:
                                    </th>
                                <td>
                                    <input name="txtUser" autocomplete="off" type="text" class="user" id="txtUser" runat="server" maxlength="20" style="width:170px" />
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    &nbsp;&nbsp;CLAVE:
                                    </th>
                                <td>
                                    <input name="txtPass" autocomplete="off" type="password" class="password" maxlength="20" id="txtPass" runat="server" style="width:170px" value=""/>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                                                                                
                  <cc11:CaptchaControl ID="CaptchaControl1" runat="server"  CaptchaHeight="31" 
                      CaptchaWidth="160" Font-Names="Arial" Font-Size="X-Small" ForeColor="White" 
                      Height="30px" Text="Codigo mostrado:" Width="160px" 
                      style="margin-bottom: 0px" CaptchaMinTimeout="5" />
                                                
                                                
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <div align="left" style="margin-top:10px;">
                                        <input type="button" ID="btnLogIn" onclick="javascript:Validar();" Class="btnIngreso" value="&nbsp;&nbsp;&nbsp;INGRESAR" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="line-height:16px;">
                                    <br />&nbsp;&nbsp;&nbsp;Se recomienda el uso de Internet Explorer 8 o superior
                                </td>
                            </tr>
                            <script type="text/javascript">document.getElementById("txtUser").focus();</script>                        
                        </table>
                        <br /><br />
                        <div id="keypaddiv" style="position: fixed ;background-color:#c3c3c3;"></div>
    	            </form>
                </div>
	        </div>
        </div>
    </div>
    
    <div id="ventana"></div>
    <div id="dialog-message" title="Aviso">
    <p>
        <span style="float:left; margin:0 7px 50px 0; padding-left: 3px;"></span>    
    </p>
    <div id="aviso"></div>
   </div>
    
    <style type="text/css">
     .ui-dialog .ui-dialog-content {padding: 0;}
     .ui-widget {font-family:Arial;}
     .ui-datepicker SELECT.ui-datepicker-year{width: 55px;}
     .ui-dialog .ui-dialog-titlebar{font-family:Verdana,Arial,sans-serif;font-size:13px;height: 13px;padding-top:0px;}
     .ui-dialog .ui-widget-header {background:#0160a4;border:1px solid #333;height:20px;}
     .ui-dialog .ui-dialog-title {width: 100%;text-align: center;color:#fff;padding-top:0.2em;font-size:13px}     
    </style>
    
</body>
</html>


