function Fc_Popup_jquery(url,ancho,alto,nombre,nombre_div) 
{

$(nombre_div).dialog({ title: nombre,close:function(){$('#idconsulta').remove();},
                                          width :  ancho,
                                          height : alto,                                     
                                          modal: true,                                          
                                          draggable: true,                                                                                 
                                          resizable: true,
                                          position: 'center'});
$(nombre_div).html("<iframe id='idconsulta' frameBorder='0' scrolling='no' style ='border:0;borderStyle=none' width='" + ancho + "' height='" + alto + "' marginheight='0' marginwidth='0' src='" + url + "'></iframe>");
               
}

function Fc_Popup_jquery_3(url,ancho,alto,nombre,nombre_div) 
{

$(nombre_div).dialog({ title: nombre,close:function(){$('#idconsulta_3').remove();},
                                          width :  ancho,
                                          height : alto,                                     
                                          modal: true,                                          
                                          draggable: true,                                                                                 
                                          resizable: true,
                                          position: 'center'});
$(nombre_div).html("<iframe id='idconsulta_3' frameBorder='0' scrolling='yes' style ='border:0;borderStyle=none' width='" + ancho + "' height='" + alto + "' marginheight='0' marginwidth='0' src='" + url + "'></iframe>");
               
}


function mostraravisoAceptar(texto){
            
           document.getElementById('aviso').innerHTML = texto;
           
           $(function() {
                $( "#dialog-message" ).dialog({
                  modal: true,
                  buttons: {
                    Ok: function() {
                      $( this ).dialog( "close" );
                    }
                  }
                });
           });
                                        
    };