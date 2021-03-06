CREATE TABLE RESERVAHABITACION 
   (idReserva NUMBER,
    fechaIngreso DATE,
    fechaSalida DATE ,
    duracion NUMBER,
    cantidadPersonas NUMBER , 
    planPago NUMBER,
    cedulaCliente NUMBER ,
    numHabitacion NUMBER,
    totalCompras NUMBER ,
	CONSTRAINT RESERVAHABITACION_PK PRIMARY KEY (idReserva));

ALTER TABLE RESERVAHABITACION
ADD CONSTRAINT FK_PlandePago
    FOREIGN KEY (planPago)
    REFERENCES PLANDEPAGO(id)
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