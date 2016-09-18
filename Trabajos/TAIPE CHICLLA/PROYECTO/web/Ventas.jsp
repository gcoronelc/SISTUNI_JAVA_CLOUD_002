<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Documento sin título</title>
    <style>
        td{font-family: Arial;            
        font-size: 12px}
        input{font-family: Arial;            
        font-size: 12px}
        select{font-family: Arial;            
        font-size: 11px}
        option{font-family: Arial;            
        font-size: 11px}
    </style>
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link href="css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="js/jquery.blockUI.js"></script>    
    <script language="javascript" type="text/javascript" src="js/func_comunes.js"></script>    
        
    <script type="text/javascript">
                
        $(document).ready(function(){                      
            $.blockUI( { message: "Cargando"} );    
            
            document.getElementById('txtsubtotal').value = 0;
            document.getElementById('txtigv').value = 0;
            document.getElementById('txttotal').value = 0;
                
            $.ajax({
            type :'GET',
            async:true,
            url :'EmpleadoC?action=CargarCombo',
            headers : {
                Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"
            },
            success : function(result){             
                var listaempleado = $.parseJSON(result); 
                var option = '';
                for (var i = 0; i < listaempleado.length; i++){                    
                    option = option + '<option value =' + listaempleado[i].idempleado + '>' + listaempleado[i].nombre + ' ' + listaempleado[i].apellidos + '</option>';                                                             
                }                
                $('#cboempleado').html(option).fadeIn();
            }
            });
            
            $.ajax({
            type :'GET',
            async:true,
            url :'ClienteC?action=CargarCliente',
            headers : {
                Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"
            },
            success : function(result){             
                var listaclientes = $.parseJSON(result); 
                var option = '';
                for (var i = 0; i < listaclientes.length; i++){                    
                    option = option + '<option value =' + listaclientes[i].idcliente + '>' + listaclientes[i].nombre + '</option>';                                                             
                }                
                $('#cbocliente').html(option).fadeIn();
            }
            });
            
            $.ajax({
            type :'GET',
            async:true,
            url :'ProductosC?action=CargarProductos',
            headers : {
                Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"
            },
            success : function(result){         
                
                var listaproductos = $.parseJSON(result); 
                var option = '<option value =' + '0' + '>' + '--Seleccione--' + '</option>';
                for (var i = 0; i < listaproductos.length; i++){   
                    
                    option = option + '<option value =' + listaproductos[i].idproducto + '>' + listaproductos[i].descripcion + '</option>';                                                             
                }                
                $('#cboproducto').html(option).fadeIn();
            }
            });
            
            $.unblockUI();
            
            $("#cboproducto").change( function(){
                top.$.blockUI( { message: "Cargando"} );
                $.ajax({
                type :'GET',
                async:true,
                url :'ProductosC?idproducto=' + document.getElementById('cboproducto').value + '&action=BuscarProducto',
                headers : {
                    Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"
                },
                success : function(result){ 
                
                var productos = $.parseJSON(result); 
                document.getElementById('txtcodigo').value = productos.idproducto;
                document.getElementById('txtprecio').value = productos.precioventa;
                document.getElementById('txtcantidad').value = '1';
                document.getElementById('txtimporte').value = productos.precioventa;
                            
                top.$.unblockUI();                
                
                }
                });

            });  
            
            $("#btnagregar").click( function(){
                
                top.$.blockUI( { message: "Cargando"} );
                var tr='';
                tr=tr + '<tr><td>' + document.getElementById('txtcodigo').value + '</td><td>' + $("#cboproducto option:selected").html() + '</td><td>' + document.getElementById('txtprecio').value + '</td><td>' + document.getElementById('txtcantidad').value + '</td><td>' + document.getElementById('txtimporte').value + '</td></tr>';
                $('#idtabdetalle tr:last').after(tr).fadeIn();
                
                var subtotal,igv,total;
                subtotal = parseFloat(document.getElementById('txtsubtotal').value) + parseFloat(document.getElementById('txtimporte').value);
                igv = subtotal * 0.18;
                total =   subtotal + igv;  
                
                document.getElementById('txtsubtotal').value = parseFloat(subtotal).toFixed(2);
                document.getElementById('txtigv').value = parseFloat(igv).toFixed(2);
                document.getElementById('txttotal').value = parseFloat(total).toFixed(2);
                
                top.$.unblockUI();
                
            });
            
            $("#txtcantidad").keyup(function() {
                $("#txtimporte" ).val($("#txtcantidad" ).val() * parseFloat($("#txtprecio" ).val()).toFixed(2));
            });
            
            $("#btnregistrar").click( function(){
                
                top.$.blockUI( { message: "Cargando"} );
                
                var table = document.getElementById("idtabdetalle");                
                var jsonventaconc='';
                var idproducto, nombre,precio,cantidad,importe;
                
                for (var i=1;i < table.rows.length ; i++){
                        
                    idproducto =  table.rows[i].cells[0].innerHTML;
                    nombre =  table.rows[i].cells[1].innerHTML;
                    precio =  table.rows[i].cells[2].innerHTML;
                    cantidad =  table.rows[i].cells[3].innerHTML;
                    importe =  table.rows[i].cells[4].innerHTML;
                    
                    if ((table.rows.length-1) == i ){
                    jsonventaconc = jsonventaconc + '{"idproducto":"' + idproducto + '","nombre":"' + nombre + '","precio":' + precio + ',"cantidad":' + cantidad + ',"importe":' + importe + '}';                    
                    }else {jsonventaconc = jsonventaconc + '{"idproducto":"' + idproducto + '","nombre":"' + nombre + '","precio":' + precio + ',"cantidad":' + cantidad + ',"importe":' + importe + '},';}
                   
                }         
                var obj = '['+jsonventaconc+']';
                
                $.ajax({
                    type: 'POST',
                    url: 'VentasC',
                    data: {json : obj, idcliente : $("#cbocliente").val(),idempleado: $("#cboempleado").val(),idtipodocumento: $("#cbotipodoc").val(),txtnumerodoc: $("#txtnumero").val(),Total : $("#txttotal").val(),action : "RegistrarVenta"},
                    success: function (data) {
                    
                    top.$.unblockUI();
                    mostraravisoAceptar("Datos Registrados.");                    
                    top.recargar();
                    }
                });                                                                                
                                
                
            });                                
            
        });
        
        <% String action = request.getParameter("action"); %>
            <% String idventa = request.getParameter("idventa"); %>
        var s="<%=action%>"; 
        var idventa="<%=idventa%>";
        
        if (s == 'Consulta'){
            
                $.blockUI( { message: "Cargando"} );
                $.ajax({
                type :'GET',
                url :'VentasC?idventa=' + idventa + '&action=ConsultarVentaDentalle',
                headers : {
                    Accept : "application/json; charset=utf-8", "Content-Type" : "application/json; charset=utf-8"
                },
                success : function(result){ 
                
                var listaVentas = $.parseJSON(result); 
                var subtotal,igv,total;
                var tr='';
                subtotal = 0;
                
                for (var i=0;i < listaVentas.length ; i++){
                    
                    tr=tr + '<tr><td>' + listaVentas[i].idproducto + '</td><td>' + listaVentas[i].nombre + '</td><td>' + listaVentas[i].precio + '</td><td>' + listaVentas[i].cantidad + '</td><td>' + listaVentas[i].importe + '</td></tr>';   
                    subtotal = subtotal + listaVentas[i].importe;
                }
                
                igv = subtotal * 0.18;
                total =   subtotal + igv;  
                
                document.getElementById('txtsubtotal').value = parseFloat(subtotal).toFixed(2);
                document.getElementById('txtigv').value = parseFloat(igv).toFixed(2);
                document.getElementById('txttotal').value = parseFloat(total).toFixed(2);
                
                $('#idtabdetalle tr:last').after(tr).fadeIn();                                
                            
                top.$.unblockUI();                
                
                }
                });
        }; 
        
    </script>

</head>

        <body style="padding-left: 12px;">             
                             
    <form>
        
<table width="650px" height="336" border="0">
  <tr>
    <td colspan="2">
      <fieldset>
        <legend>Datos de Venta:</legend>        
        <table width="100%" border="0">
          <tr>
            <td>cliente</td>
            <td><select name="cbocliente" id="cbocliente">
            </select></td>
            <td>tipo doc</td>
            <td>
            <select name="cbotipodoc" id="cbotipodoc">
                <option value="1">Factura</option>
                <option value="2">Boleta</option>
            </select>
            </td>
          </tr>
          <tr>
            <td>empleado</td>
            <td><select name="cboempleado" id="cboempleado">
            </select></td>
            <td>Nro doc.</td>
            <td><input name="txtnumero" id="txtnumero" type="text" value="00001" /></td>
          </tr>
		</table>          
      </fieldset>
    </td>
  </tr>
  <tr>
    <td colspan="2">
    <fieldset>
    <legend>Datos de Producto:</legend>
    
        <table width="100%" border="0">
          <tr>
            <td>Producto</td>
            <td>Codigo</td>
            <td>Precio</td>
            <td>Cantidad</td>
            <td>Importe</td>
          </tr>
          <tr>
            <td><select name="cboproducto" id="cboproducto">
            </select></td>
            <td><input name="txtcodigo" id="txtcodigo" type="text" width="80px" style="width:95px" readonly/></td>
            <td><input width="80px" name="txtprecio" id="txtprecio" type="text" style="width:95px" readonly/></td>
            <td><input width="80px" name="txtcantidad" id="txtcantidad" type="text" style="width:95px"/></td>
            <td><input width="80px" name="txtimporte" id="txtimporte" type="text" style="width:95px" readonly/></td>
          </tr>
        </table>

    </fieldset>
    </td>
  </tr>
  <tr>
    <td width="342" height="106" valign="top">
    <fieldset>
    <legend>Detalle:</legend>
    <table width="100%" border="1" id="idtabdetalle" style="border-collapse:collapse" cellspacing=0>
      <tr>
        <td style="background-color: #0079b2; color: white">codigo</td>
        <td style="background-color: #0079b2; color: white">nombre de producto</td>
        <td style="background-color: #0079b2; color: white">Precio</td>
        <td style="background-color: #0079b2; color: white">Cantidad</td>
        <td style="background-color: #0079b2; color: white">Importe</td>
      </tr>
    </table>

   </fieldset>
    </td>
    <td width="93" valign="top">
    
        <table width="140" border="0">
          <tr>
            <td>
              <input type="button" width="90px" name="btnagregar" id="btnagregar" value="Agregar Detalle" style="width:130px"/>
            </td>
          </tr>
          <tr>
            <td><input width="90px" type="button" name="btnquitar" id="btnquitar" value="Quitar Detalle" style="width:130px"/></td>
          </tr>
          <tr>
            <td><input width="90px" type="button" name="btnregistrar" id="btnregistrar" value="Registrar  Venta" style="width:130px"/></td>
          </tr>
          <tr>
            <td><input width="90px" type="button" name="btncerrar" id="btncerrar" value="Cerrar" style="width:80px" style="width:130px"/></td>
          </tr>
        </table>
        
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0">
      <tr>
        <td>Subtotal</td>
        <td><input width="90px" name="txtsubtotal" id="txtsubtotal" type="text" /></td>
        <td>Igv</td>
        <td><input name="txtigv" width="90px" id="txtigv" type="text" /></td>
        <td>Total</td>
        <td><input name="txttotal" width="90px" id="txttotal" type="text" /></td>
      </tr>
    </table></td>
    <td>&nbsp;</td>
  </tr>
</table>

<div id="dialog-message" title="Aviso">
    <p>
        <span style="float:left; margin:0 7px 50px 0; padding-left: 3px;"></span>    
    </p>
    <div id="aviso"></div>
</div>        

        
</form>
            
</div>
</center>
        
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
