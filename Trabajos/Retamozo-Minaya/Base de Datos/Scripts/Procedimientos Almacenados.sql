<---------------------------------------------------------------------------->
<------------------------------------SP_EMPLEADO----------------------------->
<---------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_Empleado
		(	
			pnombre varchar(50),
			papellido varchar(80),
			psexo varchar(1),
			pfechanac date,
			pdireccion varchar(100),
			ptelefono varchar(10),
			pcelular varchar(15),
			pemail varchar(80),
			pdni varchar(8),
			pfechaing date,
			psueldo decimal(8,2),
			pestado varchar(30),
			pusuario varchar(20),
			pcontrasena text,
			pidtipousuario int
		)
	BEGIN		
		INSERT INTO empleado(nombre,apellido,sexo,fechanac,direccion,telefono,celular,email,dni,fechaing,sueldo,estado,usuario,contrasena,idtipousuario)
		VALUES(pnombre,papellido,psexo,pfechanac,pdireccion,ptelefono,pcelular,pemail,pdni,pfechaing,psueldo,pestado,pusuario,pcontrasena,pidtipousuario);
	END;//
	DELIMITER &&
	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_Empleado
		(	
		   pidempleado int,
		   pnombre varchar(50),
		   papellido varchar(80),
		   psexo varchar(1),
		   pfechanac date,
		   pdireccion varchar(100),
		   ptelefono varchar(10),
		   pcelular varchar(15),
		   pemail varchar(80),
		   pdni varchar(8),
			pfechaing date,
			psueldo decimal(8,2),
		   pestado varchar(30),
		   pusuario varchar(20),
		   pcontrasena text,
		   pidtipousuario int
		)
	BEGIN
		UPDATE empleado SET
			nombre=pnombre,
			apellido=papellido,
			sexo=psexo,
			fechanac=pfechanac,
			direccion=pdireccion,
			telefono=ptelefono,
			celular=pcelular,
			email=pemail,
			dni=pdni,
			fechaing=pfechaing,
			sueldo=psueldo,
			estado=pestado,
			usuario=pusuario,
			contrasena=pcontrasena,
			idtipousuario=pidtipousuario			
		WHERE idempleado = pidempleado;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_Empleado
		(	
			pcriterio varchar(20),
			pbusqueda varchar(20)
		)
	BEGIN
	IF pcriterio = "id" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,e.IdTipoUsuario
		FROM empleado AS e WHERE e.IdEmpleado=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,e.IdTipoUsuario
		FROM empleado AS e WHERE ((e.Nombre LIKE CONCAT("%",pbusqueda,"%")) OR (e.Apellido LIKE CONCAT("%",pbusqueda,"%")));
	ELSEIF pcriterio = "dni" THEN
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,e.IdTipoUsuario
		FROM empleado AS e WHERE e.Dni LIKE CONCAT("%",pbusqueda,"%");
	ELSE
	   SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,e.IdTipoUsuario FROM empleado AS e;
	END IF; 
	END;//
	DELIMITER &&	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_EmpleadoIdMaximo
		(	
		
		)
	BEGIN
		SELECT MAX(IdEmpleado) AS Maximo FROM empleado;
	END;//
	DELIMITER &&	
<--------------------------------------------------------------------------->	
	DELIMITER //
	CREATE PROCEDURE SP_S_Login
		(	
			pusuario varchar(20),
			pcontrasena text,
			pdescripcion varchar(80)
		)
	BEGIN
	
		SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,
		e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,tu.Descripcion
		FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario WHERE e.Usuario = pusuario AND e.`Contraseña` = pcontrasena AND tu.Descripcion=pdescripcion;
		
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->	
	DELIMITER //
	CREATE PROCEDURE SP_S_LoginPermisos
		(	
			pnombre_usuario varchar(20),
			pdescripcion_tipousuario varchar(80)

		)
	BEGIN
	
		SELECT tu.IdTipoUsuario,e.Usuario,tu.Descripcion,tu.p_venta,tu.p_compra,tu.p_producto,tu.p_proveedor,tu.p_empleado,tu.p_cliente,tu.p_categoria,tu.p_tipodoc,tu.p_tipouser,
		tu.p_anularv,tu.p_anularc,tu.p_estadoprod,tu.p_ventare,tu.p_ventade,tu.p_estadistica,tu.p_comprare,tu.p_comprade,tu.p_pass,tu.p_respaldar,tu.p_restaurar,tu.p_caja
		FROM tipousuario AS tu INNER JOIN empleado AS e ON tu.IdTipoUsuario = e.IdTipoUsuario WHERE e.Usuario = pnombre_usuario AND tu.Descripcion=pdescripcion_tipousuario;
		
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_EmpleadoCantidadTotal
		(	
		
		)
	BEGIN
		SELECT COUNT(*) as total FROM empleado;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_EmpleadoPorParametro
		(	
			pcriterio varchar(20),
			pbusqueda varchar(20),
			plimit varchar(20)
		)
	BEGIN
	
	
	IF pcriterio = "id" THEN					
			SET @sentencia = CONCAT("SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,tu.Descripcion AS TipoUsuario FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario WHERE e.IdEmpleado=",pbusqueda," ORDER BY e.IdEmpleado DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSEIF pcriterio = "nombre" THEN
			SET @sentencia = CONCAT("SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,tu.Descripcion AS TipoUsuario FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario WHERE (e.Nombre LIKE '",CONCAT("%",pbusqueda,"%"),"') OR (e.Apellido LIKE '",CONCAT("%",pbusqueda,"%"),"')"," ORDER BY e.IdEmpleado DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;			
		ELSEIF pcriterio = "dni" THEN
			SET @sentencia = CONCAT("SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,tu.Descripcion AS TipoUsuario FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario WHERE e.Dni LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY e.IdEmpleado DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSE	
			SET @sentencia = CONCAT("SELECT e.IdEmpleado,e.Nombre,e.Apellido,e.Sexo,e.FechaNac,e.Direccion,e.Telefono,e.Celular,e.Email,e.Dni,e.FechaIng,e.Sueldo,e.Estado,e.Usuario,e.Contrasena,tu.Descripcion AS TipoUsuario FROM empleado AS e INNER JOIN tipousuario AS tu ON e.IdTipoUsuario = tu.IdTipoUsuario ORDER BY e.IdEmpleado DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;	
	END IF; 
	
	
	
	END;//
	DELIMITER &&	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_CambiarPass
		(	
		   pidempleado int,
			pcontrasena text
		)
	BEGIN
		UPDATE empleado SET
			contrasena=pcontrasena
		WHERE idempleado = pidempleado;
	END;//
	DELIMITER &&
<---------------------------------------------------------------------------->
<------------------------------------SP_PRODUCTO----------------------------->
<---------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_Producto
		(	
			pcodigo varchar(50),
			pnombre varchar(100),
			pdescripcion text,
			pstock decimal(8,2),
			pstockmin decimal(8,2),
			ppreciocosto decimal(8,2),
			pprecioventa decimal(8,2),
			putilidad decimal(8,2),
			pestado varchar(30),
			pimagen varchar(100),
			pidcategoria int
		)
	BEGIN		
		INSERT INTO producto(codigo,nombre,descripcion,stock,stockmin,preciocosto,precioventa,utilidad,estado,imagen,idcategoria)
		VALUES(pcodigo,pnombre,pdescripcion,pstock,pstockmin,ppreciocosto,pprecioventa,putilidad,pestado,pimagen,pidcategoria);
	END;//
	DELIMITER &&
	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_Producto
		(	
		   pidproducto int,
			pcodigo varchar(50),
			pnombre varchar(100),
			pdescripcion text,
			pstock decimal(8,2),
			pstockmin decimal(8,2),
			ppreciocosto decimal(8,2),
			pprecioventa decimal(8,2),
			putilidad decimal(8,2),
			pestado varchar(30),
			pimagen varchar(100),
			pidcategoria int
		)
	BEGIN
		UPDATE producto SET
			codigo=pcodigo,
			nombre=pnombre,
			descripcion=pdescripcion,
			stock=pstock,
			stockmin=pstockmin,
			preciocosto=ppreciocosto,
			precioventa=pprecioventa,
			utilidad=putilidad,
			estado=pestado,
			imagen=pimagen,
			idcategoria=pidcategoria
			
		WHERE idproducto = pidproducto;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_ActualizarProductoStock
		(	
		   pidproducto int,
			pstock decimal(8,2)
		)
	BEGIN
		UPDATE producto SET
			stock=pstock
		WHERE idproducto = pidproducto;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProductoIdMaximo
		(	
		
		)
	BEGIN
		SELECT MAX(IdProducto) AS Maximo FROM producto;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_Producto
		(	
			pcriterio varchar(20),
			pbusqueda varchar(50)
		)
	BEGIN
	IF pcriterio = "id" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,p.IdCategoria
		FROM producto AS p WHERE p.IdProducto=pbusqueda;
	ELSEIF pcriterio = "codigo" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,p.IdCategoria
		FROM producto AS p WHERE p.Codigo=pbusqueda;
	ELSEIF pcriterio = "nombre" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,p.IdCategoria
		FROM producto AS p WHERE p.Nombre LIKE CONCAT("%",pbusqueda,"%");
	ELSEIF pcriterio = "descripcion" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,p.IdCategoria
		FROM producto AS p WHERE p.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,p.IdCategoria
		FROM producto AS p;
	END IF; 
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProductoActivo
	(
	
	)
	BEGIN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria WHERE p.Estado="Activo"
		ORDER BY p.IdProducto;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProductoCantidadTotal
		(	
		
		)
	BEGIN
		SELECT COUNT(*) as total FROM producto;
	END;//
	DELIMITER &&	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProductoPorParametro
		(	
			pcriterio varchar(20),
			pbusqueda varchar(50),
			plimit varchar(50)
		)
	BEGIN		
		
	IF pcriterio = "id" THEN					
			SET @sentencia = CONCAT("SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria WHERE p.IdProducto=",pbusqueda," ORDER BY p.IdProducto DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSEIF pcriterio = "codigo" THEN
			SET @sentencia = CONCAT("SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria WHERE p.Codigo=",pbusqueda," ORDER BY p.IdProducto DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;				
		ELSEIF pcriterio = "nombre" THEN
			SET @sentencia = CONCAT("SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria WHERE p.Nombre LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY p.IdProducto DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;			
		ELSEIF pcriterio = "descripcion" THEN
			SET @sentencia = CONCAT("SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria WHERE p.Descripcion LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY p.IdProducto DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;		
		ELSEIF pcriterio = "categoria" THEN
			SET @sentencia = CONCAT("SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria WHERE c.Descripcion LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY p.IdProducto DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;							
		ELSE	
			SET @sentencia = CONCAT("SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria ORDER BY p.IdProducto DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;	
	END IF; 		
		
		
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProductoActivoPorParametro
		(	
			pcriterio varchar(20),
			pbusqueda varchar(50)
		)
	BEGIN
	IF pcriterio = "id" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.IdProducto=pbusqueda AND p.Estado="ACTIVO";
 	ELSEIF pcriterio = "codigo" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Codigo=pbusqueda AND p.Estado="Activo";
	ELSEIF pcriterio = "nombre" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Nombre LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSEIF pcriterio = "descripcion" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Descripcion LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSEIF pcriterio = "categoria" THEN
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE c.Descripcion LIKE CONCAT("%",pbusqueda,"%") AND p.Estado="Activo";
	ELSE
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria WHERE p.Estado="Activo";
	END IF; 
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProductoVerificarCodigoBar
		(	
			pbusqueda varchar(50)
		)
	BEGIN
	
		SELECT p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,p.Stock,p.StockMin,p.PrecioCosto,p.PrecioVenta,p.Utilidad,p.Estado,p.Imagen,c.Descripcion AS Categoria
		FROM producto AS p INNER JOIN categoria AS c ON p.IdCategoria = c.IdCategoria
		WHERE p.Codigo=pbusqueda;

	END;//
	DELIMITER &&
<---------------------------------------------------------------------------->
<------------------------------------SP_CLIENTE------------------------------>
<---------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_Cliente
		(	
			pnombre varchar(100),
			pruc varchar(11),
			pdni varchar(8),
			pdireccion varchar(50),
			ptelefono varchar(15),
			pobsv text,
			pusuario varchar(30),
			pcontrasena varchar(10)
		)
	BEGIN		
		INSERT INTO cliente(nombre,ruc,dni,direccion,telefono,obsv,usuario,contrasena)
		VALUES(pnombre,pruc,pdni,pdireccion,ptelefono,pobsv,pusuario,pcontrasena);
	END;//
	DELIMITER &&
	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_Cliente
		(	
		   pidcliente int,
		   pnombre varchar(100),
		   pruc varchar(11),
		   pdni varchar(8),
		   pdireccion varchar(50),
		   ptelefono varchar(15),
		   pobsv text,
  			pusuario varchar(30),
			pcontrasena varchar(10)
		)
	BEGIN
		UPDATE cliente SET
			nombre=pnombre,
			ruc=pruc,
			dni=pdni,
			direccion=pdireccion,
			telefono=ptelefono,
			obsv=pobsv,
			usuario=pusuario,
			contrasena=pcontrasena
		WHERE idcliente = pidcliente;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_Cliente
	(
	
	)
	BEGIN
		SELECT * FROM cliente;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ClienteIdMaximo
		(	
		
		)
	BEGIN
		SELECT MAX(IdCliente) AS Maximo FROM cliente;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ClienteCantidadTotal
		(	
		
		)
	BEGIN
		SELECT COUNT(*) as total FROM cliente;
	END;//
	DELIMITER &&	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ClientePorParametro
		(	
			pcriterio varchar(20),
			pbusqueda varchar(20),
			plimit varchar(20)
		)
	BEGIN	
		IF pcriterio = "id" THEN		
			SET @sentencia = CONCAT("SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv,c.Usuario,c.Contrasena FROM cliente AS c WHERE c.IdCliente=",pbusqueda," ORDER BY c.IdCliente DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSEIF pcriterio = "nombre" THEN
			SET @sentencia = CONCAT("SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv,c.Usuario,c.Contrasena FROM cliente AS c WHERE c.Nombre LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY c.IdCliente DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;			
		ELSEIF pcriterio = "ruc" THEN
			SET @sentencia = CONCAT("SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv,c.Usuario,c.Contrasena FROM cliente AS c WHERE c.Ruc LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY c.IdCliente DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSEIF pcriterio = "dni" THEN
			SET @sentencia = CONCAT("SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv,c.Usuario,c.Contrasena FROM cliente AS c WHERE c.Dni LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY c.IdCliente DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;		
		ELSE	
			SET @sentencia = CONCAT("SELECT c.IdCliente,c.Nombre,c.Ruc,c.Dni,c.Direccion,c.Telefono,c.Obsv,c.Usuario,c.Contrasena FROM cliente AS c ORDER BY c.IdCliente DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;	
		END IF; 
	 
	END;//
	DELIMITER &&
	

<---------------------------------------------------------------------------->
<------------------------------------SP_PROVEEDOR------------------------------>
<---------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_Proveedor
		(	
			pnombre varchar(100),
			pruc varchar(11),
			pdni varchar(8),
			pdireccion varchar(100),
			ptelefono varchar(10),
			pcelular varchar(15),
			pemail varchar(80),
			pcuenta1 varchar(50),
			pcuenta2 varchar(50),
			pestado varchar(30),
			pobsv text
		)
	BEGIN		
		INSERT INTO proveedor(nombre,ruc,dni,direccion,telefono,celular,email,cuenta1,cuenta2,estado,obsv)
		VALUES(pnombre,pruc,pdni,pdireccion,ptelefono,pcelular,pemail,pcuenta1,pcuenta2,pestado,pobsv);
	END;//
	DELIMITER &&
	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_Proveedor
		(	
		   pidproveedor int,
		   pnombre varchar(100),
			pruc varchar(11),
			pdni varchar(8),
			pdireccion varchar(100),
			ptelefono varchar(10),
			pcelular varchar(15),
			pemail varchar(80),
			pcuenta1 varchar(50),
			pcuenta2 varchar(50),
			pestado varchar(30),
			pobsv text
		)
	BEGIN
		UPDATE proveedor SET
			nombre=pnombre,
			ruc=pruc,
			dni=pdni,
			direccion=pdireccion,
			telefono=ptelefono,
			celular=pcelular,
			email=pemail,
			cuenta1=pcuenta1,
			cuenta2=pcuenta2,
			estado=pestado,
			obsv=pobsv
		WHERE idproveedor = pidproveedor;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_Proveedor
	(
	
	)
	BEGIN
		SELECT * FROM proveedor;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProveedorIdMaximo
		(	
		
		)
	BEGIN
		SELECT MAX(IdProveedor) AS Maximo FROM proveedor;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProveedorCantidadTotal
		(	
		
		)
	BEGIN
		SELECT COUNT(*) as total FROM proveedor;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_ProveedorPorParametro
		(	
			pcriterio varchar(20),
			pbusqueda varchar(20),
			plimit varchar(20)
		)
	BEGIN
	
	
	IF pcriterio = "id" THEN					
			SET @sentencia = CONCAT("SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.IdProveedor=",pbusqueda," ORDER BY p.IdProveedor DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSEIF pcriterio = "nombre" THEN
			SET @sentencia = CONCAT("SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Nombre LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY p.IdProveedor DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;			
		ELSEIF pcriterio = "ruc" THEN
			SET @sentencia = CONCAT("SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Ruc LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY p.IdProveedor DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSEIF pcriterio = "dni" THEN
			SET @sentencia = CONCAT("SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p WHERE p.Dni LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY p.IdProveedor DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;			
		ELSE	
			SET @sentencia = CONCAT("SELECT p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv FROM proveedor AS p ORDER BY p.IdProveedor DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;	
	END IF; 
	
	
	END;//
	DELIMITER &&
<----------------------------------------------------------------------------->
<------------------------------------SP_CATEGORIA----------------------------->
<----------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_Categoria
		(	
			pdescripcion varchar(100)
		)
	BEGIN		
		INSERT INTO categoria(descripcion)
		VALUES(pdescripcion);
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_Categoria
		(	
		   pidcategoria int,
			pdescripcion varchar(100)
		)
	BEGIN
		UPDATE categoria SET
			descripcion=pdescripcion	
		WHERE idcategoria = pidcategoria;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_Categoria
		(	
		
		)
	BEGIN
		SELECT * FROM categoria ORDER BY descripcion ASC;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_CategoriaIdMaximo
		(	
		
		)
	BEGIN
		SELECT MAX(IdCategoria) AS Maximo FROM categoria;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_CategoriaCantidadTotal
		(	
		
		)
	BEGIN
		SELECT COUNT(*) as total FROM categoria;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_CategoriaPorParametro
		(	
			pcriterio varchar(20),
			pbusqueda varchar(20),
			plimit varchar(20)
		)
	BEGIN
		IF pcriterio = "id" THEN		
			SET @sentencia = CONCAT("SELECT c.IdCategoria,c.Descripcion FROM categoria AS c WHERE c.IdCategoria=",pbusqueda," ORDER BY c.IdCategoria DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSEIF pcriterio = "descripcion" THEN
			SET @sentencia = CONCAT("SELECT c.IdCategoria,c.Descripcion FROM categoria AS c WHERE c.Descripcion LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY c.IdCategoria DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;	
		ELSE	
			SET @sentencia = CONCAT("SELECT c.IdCategoria,c.Descripcion FROM categoria AS c ORDER BY c.IdCategoria DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;	
		END IF; 
	END;//
	DELIMITER &&
<----------------------------------------------------------------------------->
<----------------------------------SP_TIPO_USUARIO---------------------------->
<----------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_TipoUsuario
		(	
			pdescripcion varchar(80),
			pp_venta int,
			pp_compra int,
			pp_producto int,
			pp_proveedor int,
			pp_empleado int,
			pp_cliente int,
			pp_categoria int,
			pp_tipodoc int,
			pp_tipouser int,
			pp_anularv int,
			pp_anularc int,
			pp_estadoprod int,
			pp_ventare int,
			pp_ventade int,
			pp_estadistica int,
			pp_comprare int,
			pp_comprade int,
			pp_pass int,
			pp_respaldar int,
			pp_restaurar int,
			pp_caja int
		)
	BEGIN		
		INSERT INTO tipousuario(descripcion,p_venta,p_compra,p_producto,p_proveedor,p_empleado,p_cliente,p_categoria,p_tipodoc,p_tipouser,p_anularv,p_anularc,
		p_estadoprod,p_ventare,p_ventade,p_estadistica,p_comprare,p_comprade,p_pass,p_respaldar,p_restaurar,p_caja)
		VALUES(pdescripcion,pp_venta,pp_compra,pp_producto,pp_proveedor,pp_empleado,pp_cliente,pp_categoria,pp_tipodoc,pp_tipouser,pp_anularv,pp_anularc,
		pp_estadoprod,pp_ventare,pp_ventade,pp_estadistica,pp_comprare,pp_comprade,pp_pass,pp_respaldar,pp_restaurar,pp_caja);
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_TipoUsuario
		(	
		   pidtipousuario int,
			pdescripcion varchar(80),
			pp_venta int,
			pp_compra int,
			pp_producto int,
			pp_proveedor int,
			pp_empleado int,
			pp_cliente int,
			pp_categoria int,
			pp_tipodoc int,
			pp_tipouser int,
			pp_anularv int,
			pp_anularc int,
		   pp_estadoprod int,
			pp_ventare int,
			pp_ventade int,
			pp_estadistica int,
			pp_comprare int,
			pp_comprade int,
			pp_pass int,
			pp_respaldar int,
			pp_restaurar int,
			pp_caja int
		)
	BEGIN
		UPDATE tipousuario SET
			descripcion=pdescripcion,
			p_venta=pp_venta,
			p_compra=pp_compra,
			p_producto=pp_producto,
			p_proveedor=pp_proveedor,
			p_empleado=pp_empleado,
			p_cliente=pp_cliente,
			p_categoria=pp_categoria,
			p_tipodoc=pp_tipodoc,
			p_tipouser=pp_tipouser,
			p_anularv=pp_anularv,
			p_anularc=pp_anularc,
			p_estadoprod=pp_estadoprod,
			p_ventare=pp_ventare,
			p_ventade=pp_ventade,
			p_estadistica=pp_estadistica,
			p_comprare=pp_comprare,
			p_comprade=pp_comprade,
			p_pass=pp_pass,
			p_respaldar=pp_respaldar,
			p_restaurar=pp_restaurar,
			p_caja=pp_caja
		WHERE idtipousuario = pidtipousuario;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_TipoUsuario
		(	
		
		)
	BEGIN
		SELECT * FROM tipousuario;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_TipoUsuarioPorParametro
		(	
			pcriterio varchar(20),
			pbusqueda varchar(20)		
		)
	BEGIN
	IF pcriterio = "id" THEN
		SELECT * FROM tipousuario AS tp WHERE tp.IdTipoUsuario=pbusqueda;
	ELSEIF pcriterio = "descripcion" THEN
		SELECT * FROM tipousuario AS tp WHERE tp.Descripcion LIKE CONCAT("%",pbusqueda,"%");
	ELSE
		SELECT * FROM tipousuario AS tp;
	END IF; 
	END;//
	DELIMITER &&
<----------------------------------------------------------------------------->
<----------------------------------SP_TIPO_DOCUMENTO---------------------------->
<----------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_TipoDocumento
		(	
			pdescripcion varchar(80)
		)
	BEGIN		
		INSERT INTO tipodocumento(descripcion)
		VALUES(pdescripcion);
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_TipoDocumento
		(	
		   pidtipodocumento int,
			pdescripcion varchar(80)
		)
	BEGIN
		UPDATE tipodocumento SET
			descripcion=pdescripcion	
		WHERE idtipodocumento = pidtipodocumento;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_TipoDocumento
		(	
		
		)
	BEGIN
		SELECT * FROM tipodocumento;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_TipoDocumentoIdMaximo
		(	
		
		)
	BEGIN
		SELECT MAX(IdTipoDocumento) AS Maximo FROM tipodocumento;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_TipoDocumentoCantidadTotal
		(	
		
		)
	BEGIN
		SELECT COUNT(*) as total FROM tipodocumento;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_TipoDocumentoPorParametro
		(	
			pcriterio varchar(20),
			pbusqueda varchar(20),
			plimit varchar(20)
		)
	BEGIN
			
	IF pcriterio = "id" THEN		
			SET @sentencia = CONCAT("SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td WHERE td.IdTipoDocumento=",pbusqueda," ORDER BY td.IdTipoDocumento DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;
		ELSEIF pcriterio = "descripcion" THEN
			SET @sentencia = CONCAT("SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td WHERE td.Descripcion LIKE '",CONCAT("%",pbusqueda,"%"),"'"," ORDER BY td.IdTipoDocumento DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;	
		ELSE	
			SET @sentencia = CONCAT("SELECT td.IdTipoDocumento,td.Descripcion FROM tipodocumento AS td ORDER BY td.IdTipoDocumento DESC ",plimit);
			PREPARE consulta FROM @sentencia;
			EXECUTE consulta;	
	END IF; 

	
	END;//
	DELIMITER &&	
<---------------------------------------------------------------------------->
<-------------------------------------SP_VENTA------------------------------->
<---------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_Venta
		(	
			pidtipodocumento int,
			pidcliente int,
			pidempleado int,
			pserie varchar(5),
			pnumero varchar(20),
			pfecha date,
			ptotalventa decimal(8,2),
			pigv decimal(8,2),
			ptotalpagar decimal(8,2),
			pestado varchar(30)
		)
	BEGIN		
		INSERT INTO venta(idtipodocumento,idcliente,idempleado,serie,numero,fecha,totalventa,igv,totalpagar,estado)
		VALUES(pidtipodocumento,pidcliente,pidempleado,pserie,pnumero,pfecha,ptotalventa,pigv,ptotalpagar,pestado);
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_Venta
		(	
		   pidventa int,
			pidtipodocumento int,
			pidcliente int,
			pidempleado int,
			pserie varchar(5),
			pnumero varchar(20),
			pfecha date,
			ptotalventa decimal(8,2),
			pigv decimal(8,2),
			ptotalpagar decimal(8,2),
			pestado varchar(30)
		)
	BEGIN
		UPDATE venta SET
			idtipodocumento=pidtipodocumento,
			idcliente=pidcliente,
			idempleado=pidempleado,
			serie=pserie,
			numero=pnumero,
			fecha=pfecha,
			totalventa=ptotalventa,
			igv=pigv,
			totalpagar=ptotalpagar,
			estado=pestado
		WHERE idventa = pidventa;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_Venta
	(
	
	)
	BEGIN
		SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,
		v.Igv,v.TotalPagar,v.Estado
		FROM venta AS v 
		INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
		INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
		INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
		ORDER BY v.IdVenta;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_UltimoIdVenta
	(
	
	)
	BEGIN
		SELECT MAX(IdVenta) AS id FROM venta;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_VentaPorParametro
	(
		   pcriterio varchar(20),
			pbusqueda varchar(20)
	)
	BEGIN
			IF pcriterio = "id" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,
				v.Igv,v.TotalPagar,v.Estado  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				WHERE v.IdVenta=pbusqueda ORDER BY v.IdVenta;
			ELSEIF pcriterio = "documento" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,
				v.Igv,v.TotalPagar,v.Estado  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				WHERE td.Descripcion=pbusqueda ORDER BY v.IdVenta;
			END IF; 
			

	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_VentaPorFecha
	(
			pcriterio varchar(30),
			pfechaini date,
			pfechafin date,
			pdocumento varchar(30)
	)
	BEGIN
		IF pcriterio = "anular" THEN
			SELECT v.IdVenta,c.Nombre AS Cliente,v.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,v.Serie,v.Numero,
			v.Estado,v.TotalPagar  FROM venta AS v
			INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
			INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) AND td.Descripcion=pdocumento ORDER BY v.IdVenta DESC;
		
		ELSEIF pcriterio = "consultar" THEN	
			SELECT v.IdVenta,c.Nombre AS Cliente,v.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,v.Serie,v.Numero,
			v.Estado,v.TotalVenta,v.Igv,v.TotalPagar  FROM venta AS v 
			INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento 
			INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente 
			INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado 
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) ORDER BY v.IdVenta DESC;
	
		ELSEIF pcriterio = "caja" THEN	
		   SELECT SUM(dv.Cantidad) AS Cantidad,p.Nombre AS Producto,dv.Precio,
			SUM(dv.Total) AS Total, SUM(TRUNCATE((Total-(dv.Costo*dv.Cantidad)),2)) AS Ganancia,v.Fecha FROM venta AS v
			INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
			INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
			INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
			WHERE v.Fecha=pfechaini AND v.Estado="EMITIDO" GROUP BY p.IdProducto
			ORDER BY v.IdVenta DESC;
			
		ELSE
			SELECT v.IdVenta,c.Nombre AS Cliente,v.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,v.Serie,v.Numero,
			v.Estado,v.TotalVenta,v.Igv,v.TotalPagar  FROM venta AS v 
			INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento 
			INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente 
			INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado ORDER BY v.IdVenta DESC LIMIT 10;	
		END IF;

	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_Venta_DetallePorParametro
	(
		   pcriterio varchar(20),
			pbusqueda varchar(20)
	)
	BEGIN
			IF pcriterio = "id" THEN
				SELECT v.IdVenta,td.Descripcion AS TipoDocumento,c.Nombre AS Cliente,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,v.Serie,v.Numero,v.Fecha,v.TotalVenta,
				v.Igv,v.TotalPagar,v.Estado,p.Codigo,p.Nombre,dv.Cantidad,p.PrecioVenta,dv.Total  FROM venta AS v
				INNER JOIN tipodocumento AS td ON v.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN cliente AS c ON v.IdCliente=c.IdCliente
				INNER JOIN empleado AS e ON v.IdEmpleado=e.IdEmpleado
				INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
				INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
				WHERE v.IdVenta=pbusqueda ORDER BY v.IdVenta;
			
			END IF; 
			

	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_VentaMensual
	(
		   pcriterio varchar(20),
			pfecha_ini varchar(20),
			pfecha_fin varchar(20)
	)
	BEGIN
			IF pcriterio = "consultar" THEN
			SELECT CONCAT(UPPER(MONTHNAME(v.Fecha))," ",YEAR(v.Fecha)) AS Fecha,SUM(v.TotalPagar) AS Total,
				ROUND((SUM(v.TotalPagar)*100)/(SELECT SUM(v.TotalPagar) AS TotalVenta FROM venta AS v WHERE ((date_format(v.Fecha,'%Y-%m') >= pfecha_ini) AND (date_format(v.Fecha,'%Y-%m') <= pfecha_fin)) AND v.Estado="EMITIDO")) AS Porcentaje
				FROM venta AS v
				WHERE ((date_format(v.Fecha,'%Y-%m') >= pfecha_ini) AND (date_format(v.Fecha,'%Y-%m') <= pfecha_fin)) AND v.Estado="EMITIDO" GROUP BY v.Fecha;			
								
			END IF; 
			

	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_ActualizarVentaEstado
		(	
		   pidventa int,
			pestado varchar(30)
		)
	BEGIN
		UPDATE venta SET
			estado=pestado
		WHERE idventa = pidventa;
	END;//
	DELIMITER &&
<---------------------------------------------------------------------------->
<--------------------------------SP_DETALLE_VENTA---------------------------->
<---------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_DetalleVenta
		(	
			pidventa int,
			pidproducto int,
			pcantidad decimal(8,2),
			pcosto decimal(8,2),
			pprecio decimal(8,2),
			ptotal decimal(8,2)
		)
	BEGIN		
		INSERT INTO detalleventa(idventa,idproducto,cantidad,costo,precio,total)
		VALUES(pidventa,pidproducto,pcantidad,pcosto,pprecio,ptotal);
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_DetalleVenta
		(	
			pidventa int,
			pidproducto int,
			pcantidad decimal(8,2),
			pcosto decimal(8,2),
			pprecio decimal(8,2),
			ptotal decimal(8,2)
		)
	BEGIN
		UPDATE venta SET
			idventa=pidventa,
			idproducto=pidproducto,
			cantidad=pcantidad,
			costo=pcosto,
			precio=pprecio,
			total=ptotal
		WHERE idventa = pidventa;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_DetalleVenta
	(
	
	)
	BEGIN
		SELECT * FROM detalleventa;
	END;//
	DELIMITER &&

<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_DetalleVentaPorParametro
	(
		   pcriterio varchar(20),
			pbusqueda varchar(20)
	)
	BEGIN
			IF pcriterio = "id" THEN
				SELECT dv.IdVenta,p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,dv.Cantidad,dv.Precio,dv.Total  FROM detalleventa AS dv
				INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
				WHERE dv.IdVenta=pbusqueda ORDER BY dv.IdVenta;
			
			END IF; 
			
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->

	DELIMITER //
	CREATE PROCEDURE SP_S_VentaPorDetalle
	(
			pcriterio varchar(30),
			pfechaini date,
			pfechafin date
	)
	BEGIN
		IF pcriterio = "consultar" THEN
			SELECT p.Codigo,p.Nombre AS Producto,c.Descripcion AS Categoria,dv.Costo,dv.Precio,
			SUM(dv.Cantidad) AS Cantidad,SUM(dv.Total) AS Total,
			SUM(TRUNCATE((Total-(dv.Costo*dv.Cantidad)),2)) AS Ganancia FROM venta AS v
			INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
			INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
			INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
			WHERE (v.Fecha>=pfechaini AND v.Fecha<=pfechafin) AND v.Estado="EMITIDO" GROUP BY p.IdProducto
			ORDER BY v.IdVenta DESC;
		ELSE
			SELECT p.Codigo,p.Nombre AS Producto,c.Descripcion AS Categoria,dv.Costo,dv.Precio,
			SUM(dv.Cantidad) AS Cantidad,SUM(dv.Total) AS Total,
			SUM(TRUNCATE((Total-(dv.Costo*dv.Cantidad)),2)) AS Ganancia FROM venta AS v
			INNER JOIN detalleventa AS dv ON v.IdVenta=dv.IdVenta
			INNER JOIN producto AS p ON dv.IdProducto=p.IdProducto
			INNER JOIN categoria AS c ON p.IdCategoria=c.IdCategoria
			WHERE v.Estado="EMITIDO" GROUP BY p.IdProducto
			ORDER BY v.IdVenta DESC LIMIT 10;
		END IF;

	END;//
	DELIMITER &&
<---------------------------------------------------------------------------->
<-------------------------------------SP_COMPRA------------------------------->
<---------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_Compra
		(	
			pidtipodocumento int,
			pidproveedor int,
			pidempleado int,
			pnumero varchar(20),
			pfecha date,
			psubtotal decimal(8,2),
			pigv decimal(8,2),
			ptotal decimal(8,2),
			pestado varchar(30)
		)
	BEGIN		
		INSERT INTO compra(idtipodocumento,idproveedor,idempleado,numero,fecha,subtotal,igv,total,estado)
		VALUES(pidtipodocumento,pidproveedor,pidempleado,pnumero,pfecha,psubtotal,pigv,ptotal,pestado);
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_Compra
		(	
		   pidcompra int,
			pidtipodocumento int,
			pidproveedor int,
			pidempleado int,
			pnumero varchar(20),
			pfecha date,
			psubtotal decimal(8,2),
			pigv decimal(8,2),
			ptotal decimal(8,2),
			pestado varchar(30)
		)
	BEGIN
		UPDATE compra SET
			idtipodocumento=pidtipodocumento,
			idproveedor=pidproveedor,
			idempleado=pidempleado,
			numero=pnumero,
			fecha=pfecha,
			subtotal=psubtotal,
			igv=pigv,
			total=ptotal,
			estado=pestado
		WHERE idcompra = pidcompra;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_Compra
	(
	
	)
	BEGIN
		SELECT c.IdCompra,td.Descripcion AS TipoDocumento,p.Nombre AS Proveedor,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,c.Numero,c.Fecha,c.SubTotal,c.Igv,c.Total,c.Estado
		FROM compra AS c
		INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento	 
		INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor		
		INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
		ORDER BY c.IdCompra;
	END;//
	DELIMITER &&
	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_UltimoIdCompra
	(
	
	)
	BEGIN
		SELECT MAX(IdCompra) AS id FROM compra;
	END;//
	DELIMITER &&
	
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_CompraPorFecha
	(
			pcriterio varchar(30),
			pfechaini date,
			pfechafin date,
			pdocumento varchar(30)
	)
	BEGIN
		IF pcriterio = "anular" THEN
			SELECT c.IdCompra,p.Nombre AS Proveedor,c.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,c.Numero,
			c.Estado,c.Total FROM compra AS c
			INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
			INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
			WHERE (c.Fecha>=pfechaini AND c.Fecha<=pfechafin) AND td.Descripcion=pdocumento ORDER BY c.IdCompra DESC;
		ELSEIF pcriterio = "consultar" THEN
		   SELECT c.IdCompra,p.Nombre AS Proveedor,c.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,c.Numero,
			c.Estado,c.Total FROM compra AS c
			INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
			INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
			WHERE c.Fecha>=pfechaini AND c.Fecha<=pfechafin ORDER BY c.IdCompra DESC;
		ELSE
		   SELECT c.IdCompra,p.Nombre AS Proveedor,c.Fecha,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,td.Descripcion AS TipoDocumento,c.Numero,
			c.Estado,c.Total FROM compra AS c
			INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
			INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
			INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado ORDER BY c.IdCompra DESC LIMIT 10;			
		END IF;

	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_ActualizarCompraEstado
		(	
		   pidcompra int,
			pestado varchar(30)
		)
	BEGIN
		UPDATE compra SET
			estado=pestado
		WHERE idcompra = pidcompra;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_CompraPorParametro
	(
		   pcriterio varchar(20),
			pbusqueda varchar(20)
	)
	BEGIN
			IF pcriterio = "id" THEN
				SELECT c.IdCompra,td.Descripcion AS TipoDocumento,p.Nombre AS Proveedor,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,c.Numero,c.Fecha,c.SubTotal,
				c.Igv,c.Total,c.Estado  FROM compra AS c
				INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
				INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
				WHERE c.IdCompra=pbusqueda ORDER BY c.IdCompra;
			ELSEIF pcriterio = "documento" THEN
				SELECT c.IdCompra,td.Descripcion AS TipoDocumento,p.Nombre AS Proveedor,CONCAT(e.Nombre," ",e.Apellido) AS Empleado,c.Numero,c.Fecha,c.SubTotal,
				c.Igv,c.Total,c.Estado  FROM compra AS c
				INNER JOIN tipodocumento AS td ON c.IdTipoDocumento=td.IdTipoDocumento
				INNER JOIN proveedor AS p ON c.IdProveedor=p.IdProveedor
				INNER JOIN empleado AS e ON c.IdEmpleado=e.IdEmpleado
				WHERE td.Descripcion=pbusqueda ORDER BY c.IdCompra;
			END IF; 
			

	END;//
	DELIMITER &&
<---------------------------------------------------------------------------->
<--------------------------------SP_DETALLE_COMPRA---------------------------->
<---------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_I_DetalleCompra
		(	
			pidcompra int,
			pidproducto int,
			pcantidad decimal(8,2),
			pprecio decimal(8,2),
			ptotal decimal(8,2)
		)
	BEGIN		
		INSERT INTO detallecompra(idcompra,idproducto,cantidad,precio,total)
		VALUES(pidcompra,pidproducto,pcantidad,pprecio,ptotal);
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_U_DetalleCompra
		(	
			pidcompra int,
			pidproducto int,
			pcantidad decimal(8,2),
			pprecio decimal(8,2),
			ptotal decimal(8,2)
		)
	BEGIN
		UPDATE venta SET
			idcompra=pidcompra,
			idproducto=pidproducto,
			cantidad=pcantidad,
			precio=pprecio,
			total=ptotal
		WHERE idcompra = pidcompra;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_DetalleCompra
	(
	
	)
	BEGIN
		SELECT * FROM detallecompra;
	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->
	DELIMITER //
	CREATE PROCEDURE SP_S_DetalleCompraPorParametro
	(
		   pcriterio varchar(20),
			pbusqueda varchar(20)
	)
	BEGIN
			IF pcriterio = "id" THEN
				SELECT dc.IdCompra,p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,dc.Cantidad,dc.Precio,dc.Total  FROM detallecompra AS dc
				INNER JOIN producto AS p ON dc.IdProducto=p.IdProducto
				WHERE dc.IdCompra=pbusqueda ORDER BY dc.IdCompra;
			
			END IF; 
			
	END;//
	DELIMITER &&

<--------------------------------------------------------------------------->

	DELIMITER //
	CREATE PROCEDURE SP_S_CompraPorDetalle
	(
			pcriterio varchar(30),
			pfechaini date,
			pfechafin date
	)
	BEGIN
		IF pcriterio = "consultar" THEN
			SELECT p.Codigo,p.Nombre AS Producto,ca.Descripcion AS Categoria,dc.Precio,
			SUM(dc.Cantidad) AS Cantidad,SUM(dc.Total) AS Total FROM compra AS c
			INNER JOIN detallecompra AS dc ON c.IdCompra=dc.IdCompra
			INNER JOIN producto AS p ON dc.IdProducto=p.IdProducto
			INNER JOIN categoria AS ca ON p.IdCategoria=ca.IdCategoria
			WHERE (c.Fecha>=pfechaini AND c.Fecha<=pfechafin) AND c.Estado="NORMAL" GROUP BY p.IdProducto
			ORDER BY c.IdCompra DESC;
		END IF;

	END;//
	DELIMITER &&
<--------------------------------------------------------------------------->


DELIMITER //
CREATE PROCEDURE reporte_grafico_totales()
BEGIN
SELECT
distinct monthname(v.Fecha) as mes ,
 sum(p.precioventa-p.preciocosto) as total_utilidad, sum(v.totalpagar) as total_venta,
	sum(c.total) as total_compra
	from venta v inner join detalleventa dv on v.IdVenta = dv.IdVenta
	inner join producto p on p.IdProducto = dv.IdProducto
	inner join detallecompra dc on dc.IdProducto = p.IdProducto
	inner join compra c on c.idcompra=dc.IdCompra
	where year(v.Fecha) = year(curdate())
		group by monthname(v.Fecha),p.IdProducto,v.IdVenta,c.idcompra
		order by monthname(v.Fecha) desc
			limit 1;
			
	END;//
	DELIMITER &&
			
				
<--------------------------------------------------------------------------->
DELIMITER //			
CREATE PROCEDURE reporte_grafico_dias()
BEGIN
SELECT
 v.fecha,dayname(v.Fecha) as mes, sum(v.totalpagar) as total_dia
	from venta v inner join detalleventa dv on v.IdVenta = dv.IdVenta

			where year(v.Fecha) = year(curdate())
		group by v.fecha
		order by day(v.Fecha) desc
			limit 15;
			
END;//
	DELIMITER &&
				
			
<--------------------------------------------------------------------------->
DELIMITER //			
CREATE PROCEDURE reporte_grafico_mes()
BEGIN
SELECT
 monthname(v.Fecha) as mes, sum(v.totalpagar) as total_dia
	from venta v inner join detalleventa dv on v.IdVenta = dv.IdVenta

			where year(v.Fecha) = year(curdate())
		group by monthname(v.Fecha)
		order by month(v.Fecha) desc
			limit 12;

END;//
	DELIMITER &&

-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `DiaEnLetras`(pfecha DATE
) RETURNS varchar(10) CHARSET latin1
BEGIN
DECLARE Dia varchar(10);
SELECT 
CONCAT(ELT(WEEKDAY( PFECHA ) + 1, 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo')) 
into Dia;
RETURN Dia;
END;//
DELIMITER &&