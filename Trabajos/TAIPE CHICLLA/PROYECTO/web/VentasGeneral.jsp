<%-- 
    Document   : VentasGeneral
    Created on : 12-ago-2016, 17:59:27
    Author     : Edison
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style type="text/css">        
        
        </style>
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link href="css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="js/jquery.blockUI.js"></script>    
    <script language="javascript" type="text/javascript" src="js/func_comunes.js"></script>
    <link rel="stylesheet" type="text/css" href="menu/menu.css">
    <style>
        td{font-family: Arial;            
        font-size: 12px}
        input{font-family: Arial;            
        font-size: 12px}
    </style>
    <script language="javascript" type="text/javascript">
        
        var hoy = new Date();
        var dd = hoy.getDate();
        var mm = hoy.getMonth()+1;
        var yyyy = hoy.getFullYear();

        if(dd<10) {dd='0'+dd;} 
        if(mm<10) {mm='0'+mm;} 
        hoy = mm+'/'+dd+'/'+yyyy;
                
        
        $(document).ready(function(){     
            
                document.getElementById('txtfecha').value = hoy;
                document.getElementById('txttotal').value = '0';            
                
                cargarventas();
                
                $("#btnnuevo").click( function(){                                                                                                                          
                    abrirventadetallaNuevo();
                });                                

        });
        
        function abrirventadetalla(idventa){            
            var url;
            url = 'Ventas.jsp?idventa=' + idventa + '&action=' + 'Consulta';
            Fc_Popup_jquery(url,748,550,'Detalle de Venta','#Ventana');
        }
        
        function abrirventadetallaNuevo(){            
            var url;
            url = 'Ventas.jsp?action=' + 'Nuevo';
            Fc_Popup_jquery(url,748,550,'Detalle de Venta','#Ventana');
        }
        
        function recargar(){
            $('#Ventana').dialog('destroy');
            cargarventas();
        }
        
        function cargarventas(){
         $.ajax({
            type :'GET',
            url :'VentasC?action=ConsultarVenta' + '&idventa=' + '%',
            headers : {
                Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"
            },
            success : function(result){             
                var listaVentas = $.parseJSON(result); 
                var tr='';
                var total=0;
                
                var totalreg=$("#VentasDetalle tr").length;
                if(totalreg>1){
                    for(var i = 0; i < totalreg - 1;i++)
                    {                    
                        $("#VentasDetalle tr:last").remove();
                    }   
                }                
                
                for (var i = 0; i < listaVentas.length ; i++){                                                                        
                tr=tr + '<tr onclick="abrirventadetalla(\'' + listaVentas[i].idventa + '\');"><td>' + listaVentas[i].idventa + '</td><td>' + listaVentas[i].DesEmpleado + '</td><td>' + listaVentas[i].tipodoc + '</td><td>' + listaVentas[i].nrodoc + '</td><td>' + listaVentas[i].total + '</td></tr>';                                                                                                                
                total = total + listaVentas[i].total;                                
                }                                
                
                document.getElementById('txttotal').value = parseFloat(total).toFixed(2);                
                
                $('#VentasDetalle tr:last').after(tr).fadeIn();
                
            }
            });   
        }                
            
    </script>

    </head>
    <body style="background-color: black;margin-top: 0px;padding: 0px">
      
      <center>            
      <div style="width: 800px;background-color: white;height: 800px" align="center">
      <div class="egcc_menu" align="center"><jsp:include page="menu/menu.jsp"/></div>
      </br>
      </br>      
        <table width="600" border="0">
  <tr>
    <td align="center">
    <fieldset>
        <legend align="left">Datos Generales:</legend>
        <table width="550" border="0">
  <tr>
    <td>Codigo:</td>
    <td><span style="padding-right:20px">
      <input type="text" name="txtcodigo" width="100px" id="txtcodigo" value="E0006"/>
    </span></td>
    <td>Fecha:</td>
    <td align="right">
      <input type="text" name="txtfecha" width="100px" id="txtfecha" />
    </td>
  </tr>
  <tr>
    <td>Nombres:</td>
    <td>
      <input type="text" name="txtnombre" width="210" id="txtnombre" value="EDISON TAIPE"/>
    </td>
    <td>Sede:</td>
    <td align="right">
      <input type="text" name="txtsede" width="215px" id="txtsede" value="CENTRAL LIMA"/>
    </td>
  </tr>
</table>
        
    </fieldset>
    </td>
  </tr>
  <tr>
    <td valign="top" align="center">    
        </br>
        <fieldset>
        <legend align="left">Ventas</legend>   
        
        <div align="right" style="padding-right:10px"><input name="btnnuevo" id="btnnuevo" type="button" value="Nuevo" /></div>
        <table id="VentasDetalle" width="550" style="border-collapse:collapse" cellspacing=0 border="1">
          <tr>
              <td style="background-color: #0079b2; color: white">Cod. Venta</td>
            <td style="background-color: #0079b2; color: white">Vendedor</td>
            <td style="background-color: #0079b2; color: white">Tipo Doc.</td>
            <td style="background-color: #0079b2; color: white">Nro Doc</td>
            <td style="background-color: #0079b2; color: white">Monto</td>
          </tr>
        </table>
     
    </fieldset></td>
  </tr>
  <tr>
    <td valign="top" align="right" style="padding-right:20px">
        </br>
        Total :  <input type="text" name="txttotal" width="70px" id="txttotal" />
</td>
  </tr>
  <tr>
    <td valign="top">

    </td>
  </tr>
</table>
</div>
</center>
    
     <div id="Ventana" style="overflow:hidden"></div>
     
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
