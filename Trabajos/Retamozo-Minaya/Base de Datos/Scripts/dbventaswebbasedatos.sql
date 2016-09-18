SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `dbventasweb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `dbventasweb` ;

-- -----------------------------------------------------
-- Table `dbventasweb`.`Categoria`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`Categoria` (
  `IdCategoria` INT NOT NULL AUTO_INCREMENT ,
  `Descripcion` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`IdCategoria`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`Producto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`Producto` (
  `IdProducto` INT NOT NULL AUTO_INCREMENT ,
  `Codigo` VARCHAR(50) NULL ,
  `Nombre` VARCHAR(100) NOT NULL ,
  `Descripcion` TEXT NULL ,
  `Stock` DECIMAL(8,2) NULL ,
  `StockMin` DECIMAL(8,2) NULL ,
  `PrecioCosto` DECIMAL(8,2) NULL ,
  `PrecioVenta` DECIMAL(8,2) NULL ,
  `Utilidad` DECIMAL(8,2) NULL ,
  `Estado` VARCHAR(30) NOT NULL ,
  `Imagen` VARCHAR(100) NULL ,
  `IdCategoria` INT NOT NULL ,
  PRIMARY KEY (`IdProducto`) ,
  INDEX `fk_Producto_Categoria_idx` (`IdCategoria` ASC) ,
  CONSTRAINT `fk_Producto_Categoria`
    FOREIGN KEY (`IdCategoria` )
    REFERENCES `dbventasweb`.`Categoria` (`IdCategoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`TipoUsuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`TipoUsuario` (
  `IdTipoUsuario` INT NOT NULL AUTO_INCREMENT ,
  `Descripcion` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`IdTipoUsuario`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`Empleado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`Empleado` (
  `IdEmpleado` INT NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(50) NOT NULL ,
  `Apellido` VARCHAR(80) NOT NULL ,
  `Sexo` VARCHAR(1) NOT NULL ,
  `FechaNac` DATE NOT NULL ,
  `Direccion` VARCHAR(100) NULL ,
  `Telefono` VARCHAR(10) NULL ,
  `Celular` VARCHAR(15) NULL ,
  `Email` VARCHAR(80) NULL ,
  `Dni` VARCHAR(8) NULL ,
  `FechaIng` DATE NOT NULL ,
  `Sueldo` DECIMAL(8,2) NULL ,
  `Estado` VARCHAR(30) NOT NULL ,
  `Usuario` VARCHAR(20) NULL ,
  `Contrasena` TEXT NULL ,
  `IdTipoUsuario` INT NOT NULL ,
  PRIMARY KEY (`IdEmpleado`) ,
  INDEX `fk_Empleado_TipoUsuario1_idx` (`IdTipoUsuario` ASC) ,
  CONSTRAINT `fk_Empleado_TipoUsuario1`
    FOREIGN KEY (`IdTipoUsuario` )
    REFERENCES `dbventasweb`.`TipoUsuario` (`IdTipoUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`TipoDocumento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`TipoDocumento` (
  `IdTipoDocumento` INT NOT NULL AUTO_INCREMENT ,
  `Descripcion` VARCHAR(80) NOT NULL ,
  PRIMARY KEY (`IdTipoDocumento`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`Cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`Cliente` (
  `IdCliente` INT NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(100) NOT NULL ,
  `Ruc` VARCHAR(11) NULL ,
  `Dni` VARCHAR(8) NULL ,
  `Direccion` VARCHAR(50) NULL ,
  `Telefono` VARCHAR(15) NULL ,
  `Obsv` TEXT NULL ,
  `Usuario` VARCHAR(30) NULL ,
  `Contrasena` VARCHAR(10) NULL ,
  PRIMARY KEY (`IdCliente`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`Venta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`Venta` (
  `IdVenta` INT NOT NULL AUTO_INCREMENT ,
  `IdTipoDocumento` INT NOT NULL ,
  `IdCliente` INT NOT NULL ,
  `IdEmpleado` INT NOT NULL ,
  `Serie` VARCHAR(5) NULL ,
  `Numero` VARCHAR(20) NULL ,
  `Fecha` DATE NOT NULL ,
  `TotalVenta` DECIMAL(8,2) NOT NULL ,
  `Igv` DECIMAL(8,2) NOT NULL ,
  `TotalPagar` DECIMAL(8,2) NOT NULL ,
  `Estado` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`IdVenta`) ,
  INDEX `fk_Venta_TipoDocumento1_idx` (`IdTipoDocumento` ASC) ,
  INDEX `fk_Venta_Cliente1_idx` (`IdCliente` ASC) ,
  INDEX `fk_Venta_Empleado1_idx` (`IdEmpleado` ASC) ,
  CONSTRAINT `fk_Venta_TipoDocumento1`
    FOREIGN KEY (`IdTipoDocumento` )
    REFERENCES `dbventasweb`.`TipoDocumento` (`IdTipoDocumento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venta_Cliente1`
    FOREIGN KEY (`IdCliente` )
    REFERENCES `dbventasweb`.`Cliente` (`IdCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venta_Empleado1`
    FOREIGN KEY (`IdEmpleado` )
    REFERENCES `dbventasweb`.`Empleado` (`IdEmpleado` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`Proveedor`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`Proveedor` (
  `IdProveedor` INT NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(100) NOT NULL ,
  `Ruc` VARCHAR(11) NULL ,
  `Dni` VARCHAR(8) NULL ,
  `Direccion` VARCHAR(100) NULL ,
  `Telefono` VARCHAR(10) NULL ,
  `Celular` VARCHAR(15) NULL ,
  `Email` VARCHAR(80) NULL ,
  `Cuenta1` VARCHAR(50) NULL ,
  `Cuenta2` VARCHAR(50) NULL ,
  `Estado` VARCHAR(30) NOT NULL ,
  `Obsv` TEXT NULL ,
  PRIMARY KEY (`IdProveedor`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`Compra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`Compra` (
  `IdCompra` INT NOT NULL AUTO_INCREMENT ,
  `IdTipoDocumento` INT NOT NULL ,
  `IdProveedor` INT NOT NULL ,
  `IdEmpleado` INT NOT NULL ,
  `Numero` VARCHAR(20) NULL ,
  `Fecha` DATE NULL ,
  `SubTotal` DECIMAL(8,2) NULL ,
  `Igv` DECIMAL(8,2) NULL ,
  `Total` DECIMAL(8,2) NULL ,
  `Estado` VARCHAR(30) NULL ,
  PRIMARY KEY (`IdCompra`) ,
  INDEX `fk_Compra_Proveedor1_idx` (`IdProveedor` ASC) ,
  INDEX `fk_Compra_Empleado1_idx` (`IdEmpleado` ASC) ,
  INDEX `fk_Compra_TipoDocumento1_idx` (`IdTipoDocumento` ASC) ,
  CONSTRAINT `fk_Compra_Proveedor1`
    FOREIGN KEY (`IdProveedor` )
    REFERENCES `dbventasweb`.`Proveedor` (`IdProveedor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_Empleado1`
    FOREIGN KEY (`IdEmpleado` )
    REFERENCES `dbventasweb`.`Empleado` (`IdEmpleado` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_TipoDocumento1`
    FOREIGN KEY (`IdTipoDocumento` )
    REFERENCES `dbventasweb`.`TipoDocumento` (`IdTipoDocumento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`DetalleVenta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`DetalleVenta` (
  `IdVenta` INT NOT NULL ,
  `IdProducto` INT NOT NULL ,
  `Cantidad` DECIMAL(8,2) NOT NULL ,
  `Costo` DECIMAL(8,2) NOT NULL ,
  `Precio` DECIMAL(8,2) NOT NULL ,
  `Total` DECIMAL(8,2) NOT NULL ,
  INDEX `fk_DetalleVenta_Producto1_idx` (`IdProducto` ASC) ,
  INDEX `fk_DetalleVenta_Venta1_idx` (`IdVenta` ASC) ,
  CONSTRAINT `fk_DetalleVenta_Producto1`
    FOREIGN KEY (`IdProducto` )
    REFERENCES `dbventasweb`.`Producto` (`IdProducto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetalleVenta_Venta1`
    FOREIGN KEY (`IdVenta` )
    REFERENCES `dbventasweb`.`Venta` (`IdVenta` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`DetalleCompra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`DetalleCompra` (
  `IdCompra` INT NOT NULL ,
  `IdProducto` INT NOT NULL ,
  `Cantidad` DECIMAL(8,2) NOT NULL ,
  `Precio` DECIMAL(8,2) NOT NULL ,
  `Total` DECIMAL(8,2) NOT NULL ,
  INDEX `fk_DetalleCompra_Compra1_idx` (`IdCompra` ASC) ,
  INDEX `fk_DetalleCompra_Producto1_idx` (`IdProducto` ASC) ,
  CONSTRAINT `fk_DetalleCompra_Compra1`
    FOREIGN KEY (`IdCompra` )
    REFERENCES `dbventasweb`.`Compra` (`IdCompra` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetalleCompra_Producto1`
    FOREIGN KEY (`IdProducto` )
    REFERENCES `dbventasweb`.`Producto` (`IdProducto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`Pedido`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`Pedido` (
  `IdPedido` INT NOT NULL AUTO_INCREMENT ,
  `IdCliente` INT NOT NULL ,
  `Fecha_solicitud` DATETIME NULL ,
  `Fecha_entrega` DATETIME NULL ,
  `Total` DECIMAL(8,2) NULL ,
  `Estado` VARCHAR(30) NULL ,
  PRIMARY KEY (`IdPedido`) ,
  INDEX `fk_Pedido_Cliente1` (`IdCliente` ASC) ,
  CONSTRAINT `fk_Pedido_Cliente1`
    FOREIGN KEY (`IdCliente` )
    REFERENCES `dbventasweb`.`Cliente` (`IdCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbventasweb`.`DetallePedido`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbventasweb`.`DetallePedido` (
  `IdPedido` INT NOT NULL ,
  `IdProducto` INT NOT NULL ,
  `Cantidad` DECIMAL(8,2) NULL ,
  `Precio` DECIMAL(8,2) NULL ,
  `Total` DECIMAL(8,2) NULL ,
  INDEX `fk_DetallePedido_Pedido1` (`IdPedido` ASC) ,
  INDEX `fk_DetallePedido_Producto1` (`IdProducto` ASC) ,
  CONSTRAINT `fk_DetallePedido_Pedido1`
    FOREIGN KEY (`IdPedido` )
    REFERENCES `dbventasweb`.`Pedido` (`IdPedido` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetallePedido_Producto1`
    FOREIGN KEY (`IdProducto` )
    REFERENCES `dbventasweb`.`Producto` (`IdProducto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
