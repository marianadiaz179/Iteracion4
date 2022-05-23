CREATE TABLE TIPOHABITACION
   (idTipoHabitacion NUMBER,
   tipoHabitacion VARCHAR2(255 BYTE),
   CONSTRAINT TIPOHABITACION_PK PRIMARY KEY (idTipoHabitacion));

   ALTER TABLE TIPOHABITACION
   ADD CONSTRAINT CK_TIPOHABITACION
   CHECK (tipoHabitacion IN ('SuitePresidencial', 'Suite','Familiar','DobleConJacuzzi','DobleSinJacuzzi','Sencilla'))
ENABLE;