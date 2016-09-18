-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.1.36-community-log - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- Volcando datos para la tabla dbventasweb.categoria: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`IdCategoria`, `Descripcion`) VALUES
	(1, 'PINTURA'),
	(2, 'MOLDES'),
	(3, 'HARDWARE'),
	(4, 'SOFTWARE');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.cliente: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`IdCliente`, `Nombre`, `Ruc`, `Dni`, `Direccion`, `Telefono`, `Obsv`, `Usuario`, `Contrasena`) VALUES
	(1, 'PUBLICO GENERAL', '2415288', '3444533', 'capanique', '527485', 'aaa','dqwwd','123');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.compra: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.detallecompra: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `detallecompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallecompra` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.detalleventa: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `detalleventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleventa` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.empleado: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` (`IdEmpleado`, `Nombre`, `Apellido`, `Sexo`, `FechaNac`, `Direccion`, `Telefono`, `Celular`, `Email`, `Dni`, `FechaIng`, `Sueldo`, `Estado`, `Usuario`, `Contrasena`, `IdTipoUsuario`) VALUES
	(1, 'Edgar', 'Cotrado Flores', 'M', '2013-06-15', 'Para Grande', '315199', '9526572', 'asd@gmail.com', '45736020', '2013-06-15', 750.00, 'ACTIVO', 'edgar', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 1);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.producto: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`IdProducto`, `Codigo`, `Nombre`, `Descripcion`, `Stock`, `StockMin`, `PrecioCosto`, `PrecioVenta`, `Utilidad`, `Estado`,`Imagen`, `IdCategoria`) VALUES
	(1, '11', 'Monitor', 'monitor LCD pantalla de retina liquida', 10.00, 2.00, 80.00, 100.00, 20.00, 'ACTIVO',NULL, 3),
	(2, '22', 'Teclado Multifuncional', 'Teclado ergonomico', 12.00, 3.00, 25.00, 45.00, 20.00, 'ACTIVO',NULL, 3),
	(3, '33', 'CPU', 'Unidad Central de Procesamiento', 10.00, 5.00, 322.58, 482.24, 159.66, 'ACTIVO',NULL, 3);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.proveedor: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` (`IdProveedor`, `Nombre`, `Ruc`, `Dni`, `Direccion`, `Telefono`, `Celular`, `Email`, `Cuenta1`, `Cuenta2`, `Estado`, `Obsv`) VALUES
	(1, 'SIN PROVEEDOR', '', '', '', '', '', '', '', '', 'ACTIVO', '');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.tipodocumento: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `tipodocumento` DISABLE KEYS */;
INSERT INTO `tipodocumento` (`IdTipoDocumento`, `Descripcion`) VALUES
	(1, 'BOLETA'),
	(2, 'FACTURA'),
	(3, 'TICKET');
/*!40000 ALTER TABLE `tipodocumento` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.tipousuario: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` (`IdTipoUsuario`, `Descripcion`) VALUES
	(1, 'ADMINISTRADOR'),
	(2, 'CAJERO');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;

-- Volcando datos para la tabla dbventasweb.venta: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
