CREATE TABLE TIPOUSUARIO
   (idEmpleo NUMBER,
    tipoEmpleo VARCHAR2 (255 BYTE)
	CONSTRAINT TIPOUSUARIO_PK PRIMARY KEY (idEmpleo));


ALTER TABLE TIPOUSUARIO
   ADD CONSTRAINT CK_TIPOEMPLEO
   CHECK (tipoEmpleo IN ('Recepcionista','Empleado','AdministradorDatos','Gerente','OrganizadorEventos', 'Cliente'))
ENABLE;