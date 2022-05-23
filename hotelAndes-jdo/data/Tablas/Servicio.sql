CREATE TABLE SERVICIO 
   (id NUMBER,
   nombreServicio VARCHAR2 (255 BYTE),
   demanda NUMBER,  
	CONSTRAINT SERVICIO_PK PRIMARY KEY (id));

ALTER TABLE SERVICIO
	ADD CONSTRAINT ND_NOMBRESERVICIO
	UNIQUE (nombreServicio)
ENABLE;
