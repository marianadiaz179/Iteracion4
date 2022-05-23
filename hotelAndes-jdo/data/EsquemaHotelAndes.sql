-- Creación del secuenciador
CREATE SEQUENCE hotelAndes_sequence;


-- Creación de tablar BAR
CREATE TABLE BAR 
   (idServicio NUMBER ,
    id NUMBER,
    nombreBar VARCHAR2(255 BYTE) ,
    capacidadBar NUMBER ,
    horario VARCHAR2(255 BYTE) ,
    estiloBar VARCHAR2(255 BYTE) ,
    CONSTRAINT BAR_PK PRIMARY KEY (id));

-- Creación de tabla CONSUMO
CREATE TABLE CONSUMO
   (id NUMBER,
    idServicio NUMBER,
    habitacion NUMBER,
    producto NUMBER,
    estado VARCHAR2(255 BYTE),
    cliente NUMBER,
    fechaConsumo DATE,
   CONSTRAINT CONSUMO_PK PRIMARY KEY (id));

-- Creación de tabla CONVENCION
CREATE TABLE CONVENCION 
   (id NUMBER,
    nombre VARCHAR2 (255 BYTE),
    organizador NUMBER,
    fechaInicio DATE,
    fechaFin DATE,
    duracion NUMBER,
    planPago VARCHAR2(255 BYTE),
   CONSTRAINT CONVENCION_PK PRIMARY KEY (id));

-- Creación de tabla FACTURA
CREATE TABLE FACTURA 
   (idFactura NUMBER,
    habitacion NUMBER,
    montoTotal NUMBER,
   CONSTRAINT FACTURA_PK PRIMARY KEY (idFactura));


-- Creación de tabla GIMNASIO
CREATE TABLE GIMNASIO
   (idServicio NUMBER,
    id NUMBER,
    capacidadGimnasio NUMBER,
    nombreGimnasio VARCHAR2 (255 BYTE),
    horario VARCHAR2 (255 BYTE),
    CONSTRAINT GIMNASIO_PK PRIMARY KEY (id));

-- Creacion de la tabla TIPOHABITACION
CREATE TABLE TIPOHABITACION
   (idTipoHabitacion NUMBER,
   tipoHabitacion VARCHAR2(255 BYTE),
   CONSTRAINT TIPOHABITACION_PK PRIMARY KEY (idTipoHabitacion));

-- Creación de la tabla HABITACION
CREATE TABLE HABITACION 
   (numHabitacion NUMBER,
      capacidad NUMBER ,
      costoNoche NUMBER ,
      tipoHabitacion NUMBER,
      idHotel NUMBER,
      estado VARCHAR2(355 BYTE),
      cliente NUMBER,
   CONSTRAINT HABITACION_PK PRIMARY KEY (numHabitacion));

-- Creación de la tabla HOTEL
CREATE TABLE HOTEL 
      (idHotel NUMBER,
      nombreHotel VARCHAR2 (255 BYTE),
      numeroEstrellas NUMBER ,
      paisHotel VARCHAR2 (255 BYTE),
      ciudadHotel VARCHAR2 (255 BYTE),
      cadenaHotelera VARCHAR2 (255 BYTE),  
   CONSTRAINT HOTEL_PK PRIMARY KEY (idHotel));


-- Creación de la tabla INTERNET
CREATE TABLE INTERNET 
   (capacidadInternet NUMBER ,
    idServicio NUMBER,
    CONSTRAINT INTERNET_PK PRIMARY KEY (idServicio));


-- Creación de la tabla LAVADOPLANCHADO
CREATE TABLE LAVADOPLANCHADO 
   (tipoServicio VARCHAR2 (255 BYTE),
    horario VARCHAR2 (255 BYTE),
    idServicio NUMBER,
    CONSTRAINT LAVADOPLANCHADO_PK PRIMARY KEY (idServicio, tipoServicio));

-- Creación de la tabla PISCINA
CREATE TABLE PISCINA
   (idServicio NUMBER,
    id NUMBER,
    capacidadPiscina NUMBER ,
    profundidad NUMBER ,
    horario VARCHAR2 (255 BYTE),
    nombrePiscina VARCHAR2 (255 BYTE),
    CONSTRAINT PISCINA_PK PRIMARY KEY (id));

-- Creación de la tabla PLANDEPAGO
CREATE TABLE PLANDEPAGO 
   (tipoPlan VARCHAR2 (255 BYTE),
   caracteristicas VARCHAR2 (255 BYTE),  
   CONSTRAINT PLANDEPAGO_PK PRIMARY KEY (tipoPlan));


-- Creación de la tabla PRODUCTO
CREATE TABLE PRODUCTO
   (id NUMBER,
    nombreProducto VARCHAR2 (255 BYTE),
    costoProducto NUMBER, 
    idServicio NUMBER,
   CONSTRAINT PRODUCTO_PK PRIMARY KEY (id));



-- Creación de la tabla RESERVAHABITACION
CREATE TABLE RESERVAHABITACION 
   (idReserva NUMBER,
    fechaIngreso DATE,
    fechaSalida DATE,
    duracion NUMBER,
    cantidadPersonas NUMBER , 
    planPago VARCHAR2 (255 BYTE),
    cedulaCliente NUMBER ,
    numHabitacion NUMBER,
    totalCompras NUMBER ,
   CONSTRAINT RESERVAHABITACION_PK PRIMARY KEY (idReserva));


-- Creación de la tabla RESERVASERVICIO
CREATE TABLE RESERVASERVICIO
   (id NUMBER,
    tipoServicio VARCHAR2  (255 BYTE),
    diaReserva DATE,
    horaReserva VARCHAR2  (255 BYTE),
    duracion NUMBER, 
    cedulaCliente NUMBER ,
    servicio NUMBER,
    CONSTRAINT RESERVASERVICIO_PK PRIMARY KEY (id));


-- Creación de la tabla RESTAURANTE
CREATE TABLE RESTAURANTE 
   (idServicio NUMBER ,
    id NUMBER,
    capacidadRestaurante NUMBER,
    horario VARCHAR2 (255 BYTE),
    estiloRestaurante VARCHAR2 (255 BYTE),
    nombreRestaurante VARCHAR2 (255 BYTE),
    CONSTRAINT RESTAURANTE_PK PRIMARY KEY (id));



-- Creación de la tabla SALONREUNIONES
CREATE TABLE SALONREUNIONES
   (id NUMBER,
    idServicio NUMBER,
    capacidadPersonas NUMBER ,
    nombreSalon VARCHAR2 (255 BYTE),
    CONSTRAINT SALONREUNIONES PRIMARY KEY (id));



-- Creación de la tabla SALONCONFERENCIAS
CREATE TABLE SALONCONFERENCIAS
   (id NUMBER,
    idServicio NUMBER,
    capacidadPersonas NUMBER ,
    nombreSalon VARCHAR2 (255 BYTE),
    CONSTRAINT SALONCONFERENCIAS PRIMARY KEY (id));



-- Creación de la tabla SERVICIO
CREATE TABLE SERVICIO 
   (id NUMBER,
   nombreServicio VARCHAR2 (255 BYTE),
   demanda NUMBER,  
   CONSTRAINT SERVICIO_PK PRIMARY KEY (id));



-- Creación de la tabla SPA
CREATE TABLE SPA 
   (id NUMBER,
    capacidad NUMBER ,
    horario VARCHAR2 (255 BYTE),
    nombreSpa VARCHAR2 (255 BYTE),
    idServicio NUMBER,
    CONSTRAINT SPA_PK PRIMARY KEY (id));


-- Creación de la tabla SUPERMERCADO
CREATE TABLE SUPERMERCADO 
   (id NUMBER,
    idServicio NUMBER,
    horario VARCHAR2 (255 BYTE),
    nombreSupermercado VARCHAR2 (255 BYTE),
    CONSTRAINT SUPERMERCADO_PK PRIMARY KEY (id));


-- Creación de la tabla TIENDA
CREATE TABLE TIENDA 
   (id NUMBER,
    idServicio NUMBER,
    horario VARCHAR2 (255 BYTE),
    tipoTienda VARCHAR2 (255 BYTE),
    nombreTienda VARCHAR2 (255 BYTE),
    CONSTRAINT TIENDA_PK PRIMARY KEY (id));

-- Creación de la tabla TIPOUSUARIO
CREATE TABLE TIPOUSUARIO
   (idEmpleo NUMBER,
    tipoEmpleo VARCHAR2 (255 BYTE),
   CONSTRAINT TIPOUSUARIO_PK PRIMARY KEY (idEmpleo));

-- Creación de la tabla USUARIO
CREATE TABLE USUARIO
   (tipoUsuario NUMBER,
    nombre VARCHAR2(255 BYTE) ,
    cedula NUMBER,
    correo VARCHAR2(255 BYTE) ,  
    idHotel NUMBER,
    gastosHotel NUMBER,
    estadia NUMBER,
    CONSTRAINT USUARIO_PK PRIMARY KEY (cedula));


---------------------------------------------------------------------
/*********************************************************************/
---------------------------------------------------------------------


-- Modificaciones tabla BAR
ALTER TABLE BAR
   ADD CONSTRAINT FK_Servicio_B
   FOREIGN KEY (idServicio)
   REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE BAR
   ADD CONSTRAINT CK_HORARIO_B
   CHECK (horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;


--Modificaciones Tabla CONUSMO
ALTER TABLE CONSUMO
   ADD CONSTRAINT FK_IdServicio_C
   FOREIGN KEY (idServicio)
   REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE CONSUMO 
   ADD CONSTRAINT FK_Habitacion_con
   FOREIGN KEY (habitacion)
   REFERENCES HABITACION (numHabitacion)
ENABLE;

ALTER TABLE CONSUMO
   ADD CONSTRAINT FK_PRODUCTO_CONSUMO
   FOREIGN KEY (producto)
   REFERENCES PRODUCTO (id)
ENABLE;

ALTER TABLE CONSUMO
   ADD CONSTRAINT CK_E_CONSUMO
   CHECK (estado IN ('Pago','Pendiente'))
ENABLE;

ALTER TABLE CONSUMO
   ADD CONSTRAINT FK_CLIENTE_CONSUMO
   FOREIGN KEY (cliente)
   REFERENCES USUARIO(cedula)
ENABLE;

-- Modificaciones tabla CONVENCION
ALTER TABLE CONVENCION 
   ADD CONSTRAINT FK_ORGANIZADOR
   FOREIGN KEY (organizador)
   REFERENCES USUARIO (cedula)
ENABLE;

ALTER TABLE CONVENCION 
   ADD CONSTRAINT FK_PLANPAGO_C
   FOREIGN KEY (planPago)
   REFERENCES PLANDEPAGO (tipoPlan)
ENABLE;


-- Modificaciones tabla FACTURA
ALTER TABLE FACTURA
   ADD CONSTRAINT FK_Habitacion_F
   FOREIGN KEY (habitacion)
   REFERENCES HABITACION(numHabitacion)
ENABLE;


-- Modificaciones de tabla GIMNASIO
ALTER TABLE GIMNASIO
   ADD CONSTRAINT FK_Servicio_G
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE GIMNASIO
   ADD CONSTRAINT CK_HORARIO_G
   CHECK (horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;


--Modificaciones de la tabla HABITACION
ALTER TABLE HABITACION
   ADD CONSTRAINT FK_Hotel_H
    FOREIGN KEY (idHotel)
    REFERENCES HOTEL(idHotel)
ENABLE;

ALTER TABLE HABITACION
   ADD CONSTRAINT FK_TIPOHABITACION_H
   FOREIGN KEY (tipoHabitacion)
   REFERENCES TIPOHABITACION(idTipoHabitacion)
ENABLE;

ALTER TABLE HABITACION
   ADD CONSTRAINT CK_E_HABITACION
   CHECK (estado IN ('Ocupada','Vacia','Reservada','Mantenimiento'))
ENABLE;

ALTER TABLE HABITACION 
   ADD CONSTRAINT FK_CLI_HABI
   FOREIGN KEY (cliente)
   REFERENCES USUARIO(cedula)
ENABLE;


-- Modificaciones de la tabla INTERNET
ALTER TABLE INTERNET
ADD CONSTRAINT FK_Servicio_I
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

-- Modificacion de la tabla LAVADOPLANCHADO

ALTER TABLE LAVADOPLANCHADO
   ADD CONSTRAINT FK_Servicio_LP
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE LAVADOPLANCHADO
   ADD CONSTRAINT CK_HORARIO_LP
   CHECK (horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;


-- Modificaciones de la tabla PISCINA
ALTER TABLE PISCINA
   ADD CONSTRAINT FK_Servicio_P
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE PISCINA
   ADD CONSTRAINT CK_HORARIO_P
   CHECK (horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;

-- Modificaciones tabla PRODUCTO
ALTER TABLE PRODUCTO
ADD CONSTRAINT FK_Servicio_PR
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;


-- Modificaciones de la tabla RESERVAHABITACION

ALTER TABLE RESERVAHABITACION
ADD CONSTRAINT FK_PlandePago
    FOREIGN KEY (planPago)
    REFERENCES PLANDEPAGO(tipoPlan)
ENABLE;

ALTER TABLE RESERVAHABITACION
ADD CONSTRAINT FK_Cliente_RH
    FOREIGN KEY (cedulaCliente)
    REFERENCES USUARIO(cedula)
ENABLE;

ALTER TABLE RESERVAHABITACION
ADD CONSTRAINT FK_Habitacion_R
    FOREIGN KEY (numHabitacion)
    REFERENCES HABITACION(numHabitacion)
ENABLE;


-- Modificaciones de la tabla RESERVASERVICIO
ALTER TABLE RESERVASERVICIO
ADD CONSTRAINT FK_Cliente_RS
    FOREIGN KEY (cedulaCliente)
    REFERENCES USUARIO(cedula)
ENABLE;

ALTER TABLE RESERVASERVICIO
   ADD CONSTRAINT FK_Servicio_RS
   FOREIGN KEY (servicio)
   REFERENCES SERVICIO(id)
ENABLE;


-- Modificaciones de la tabla RESTAURANTE
ALTER TABLE RESTAURANTE
   ADD CONSTRAINT FK_Servicio_R
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE RESTAURANTE
   ADD CONSTRAINT CK_HORARIO_R
   CHECK (horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;


-- Modificaciones de la tabla SALONCONFERENCIAS

ALTER TABLE SALONCONFERENCIAS
   ADD CONSTRAINT FK_Servicio_SC
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;


-- Modificaciones de la tabla SALONREUNIONES
ALTER TABLE SALONREUNIONES
   ADD CONSTRAINT FK_Servicio_SR
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;


-- Modificaciones de la tabla SERVICIO
ALTER TABLE SERVICIO
   ADD CONSTRAINT ND_NOMBRESERVICIO
   UNIQUE (nombreServicio)
ENABLE;


-- Modificaciones de la tabla SPA
ALTER TABLE SPA
ADD CONSTRAINT FK_Servicio_SPA
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE SPA
   ADD CONSTRAINT CK_HORARIO_S
   CHECK(horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;



-- Modificaciones de la tabla SUPERMERCADO

ALTER TABLE SUPERMERCADO
ADD CONSTRAINT FK_Servicio_SUPER
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE SUPERMERCADO
   ADD CONSTRAINT CK_HORARIO_SUPER
   CHECK (horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;

-- Modificaciones de la tabla TIENDA
ALTER TABLE TIENDA
ADD CONSTRAINT FK_Servicio_T
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE TIENDA
   ADD CONSTRAINT CK_HORARIO_T
   CHECK (horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;

-- Modificaciones de la tabla TIPOUSUARIO
ALTER TABLE TIPOUSUARIO
   ADD CONSTRAINT CK_TIPOEMPLEO
   CHECK (tipoEmpleo IN ('Recepcionista','Empleado','AdministradorDatos','Gerente','OrganizadorEventos', 'Cliente'))
ENABLE;


-- Modificaciones de la tabla USUARIO
ALTER TABLE USUARIO
   ADD CONSTRAINT FK_Hotel_EH
   FOREIGN KEY (idHotel)
   REFERENCES HOTEL(idHotel)
ENABLE;

ALTER TABLE USUARIO
   ADD CONSTRAINT ND_CORREO
   UNIQUE (correo)
ENABLE;

ALTER TABLE USUARIO
   ADD CONSTRAINT FK_TIPOUSUARIO_E
   FOREIGN KEY (tipoUsuario)
   REFERENCES TIPOUSUARIO(idEmpleo)
ENABLE;


COMMIT;