CREATE TABLE RESERVASERVICIO
   (id NUMBER,
    tipoServicio VARCHAR2  (255 BYTE),
    diaReserva DATE,
    horaReserva VARCHAR2  (255 BYTE),
    duracion NUMBER, 
    cedulaCliente NUMBER ,
    servicio NUMBER,
	 CONSTRAINT RESERVASERVICIO_PK PRIMARY KEY (id));

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

