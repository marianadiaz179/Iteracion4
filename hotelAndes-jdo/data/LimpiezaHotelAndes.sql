--- Sentencias SQL para la creaci�n del esquema de parranderos
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido deseado de este archivo en una pesta�a SQL de SQL Developer
-- Ejec�telo como un script - Utilice el bot�n correspondiente de la pesta�a utilizada
    
-- Eliminar todas las tablas de la base de datos
DROP TABLE "BAR" CASCADE CONSTRAINTS;
DROP TABLE "CLIENTE" CASCADE CONSTRAINTS;
DROP TABLE "USUARIO" CASCADE CONSTRAINTS;
DROP TABLE "CONVENCION" CASCADE CONSTRAINTS;
DROP TABLE "CONSUMO" CASCADE CONSTRAINTS;
DROP TABLE "FACTURA" CASCADE CONSTRAINTS;
DROP TABLE "GIMNASIO" CASCADE CONSTRAINTS;
DROP TABLE "TIPOHABITACION" CASCADE CONSTRAINTS;
DROP TABLE "HABITACION" CASCADE CONSTRAINTS;
DROP TABLE "HOTEL" CASCADE CONSTRAINTS;
DROP TABLE "INTERNET" CASCADE CONSTRAINTS;
DROP TABLE "LAVADOPLANCHADO" CASCADE CONSTRAINTS;
DROP TABLE "PISCINA" CASCADE CONSTRAINTS;
DROP TABLE "PLANDEPAGO" CASCADE CONSTRAINTS;
DROP TABLE "PRODUCTO" CASCADE CONSTRAINTS;
DROP TABLE "RESERVAHABITACION" CASCADE CONSTRAINTS;
DROP TABLE "RESERVASERVICIO" CASCADE CONSTRAINTS;
DROP TABLE "RESTAURANTE" CASCADE CONSTRAINTS;
DROP TABLE "SALONREUNIONES" CASCADE CONSTRAINTS;
DROP TABLE "SALONCONFERENCIAS" CASCADE CONSTRAINTS;
DROP TABLE "SERVICIO" CASCADE CONSTRAINTS;
DROP TABLE "SPA" CASCADE CONSTRAINTS;
DROP TABLE "SUPERMERCADO" CASCADE CONSTRAINTS;
DROP TABLE "TIENDA" CASCADE CONSTRAINTS;
DROP TABLE "TIPOUSUARIO" CASCADE CONSTRAINTS;
COMMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos
-- El orden es importante. Por qu�?
delete from bar;
delete from cliente;
delete from usuario;
delete from convencion;
delete from consumo;
delete from factura;
delete from gimnasio;
delete from internet;
delete from lavadoPlanchado;
delete from piscina;
delete from planPago;
delete from producto;
delete from reservaHabitacion;
delete from reservaServicio;
delete from restaurante;
delete from salonReuiones;
delete from salonConferencias;
delete from servicio;
delete from spa;
delete from supermercado;
delete from tienda;
delete from tipoUsuario;
commit;