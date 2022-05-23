CREATE TABLE PISCINA
   (idServicio NUMBER,
    id NUMBER,
    capacidadPiscina NUMBER ,
    profundidad NUMBER ,
    horario VARCHAR2 (255 BYTE),
    nombrePiscina VARCHAR2 (255 BYTE),
	 CONSTRAINT PISCINA_PK PRIMARY KEY (id));

ALTER TABLE PISCINA
   ADD CONSTRAINT FK_Servicio_P
    FOREIGN KEY (idServicio)
    REFERENCES SERVICIO(id)
ENABLE;

ALTER TABLE PISCINA
   ADD CONSTRAINT CK_HORARIO_P
   CHECK (horario IN ('DIURNO','NOCTURNO','COMPLETO'))
ENABLE;