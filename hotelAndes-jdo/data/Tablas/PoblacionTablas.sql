INSERT INTO HOTEL (cadenahotelera,ciudadhotel,idhotel,nombrehotel,numeroestrellas,paishotel)
VALUES ('Sheraton', 'Bogotá', 1, 'Hotel Sheraton', 4, 'Colombia');

INSERT INTO TIPOUSUARIO (idempleo,tipoempleo) VALUES (1, 'Empleado');
INSERT INTO TIPOUSUARIO (idempleo,tipoempleo) VALUES (2, 'AdministradorDatos');
INSERT INTO TIPOUSUARIO (idempleo,tipoempleo) VALUES (3, 'Cliente');
INSERT INTO TIPOUSUARIO (idempleo,tipoempleo) VALUES (4, 'OrganizadorEventos');
INSERT INTO TIPOUSUARIO (idempleo,tipoempleo) VALUES (5, 'Recepcionista');

INSERT INTO TIPOHABITACION(idtipohabitacion,tipohabitacion) VALUES (1,'Suite');
INSERT INTO TIPOHABITACION(idtipohabitacion,tipohabitacion) VALUES (2,'Familiar');

INSERT INTO USUARIO(cedula,correo,estadia,gastoshotel,idhotel,nombre,tipousuario)
VALUES (1000612379, 'm.diaza2', 0, 0, 1, 'Mario', 1);

INSERT INTO USUARIO(cedula,correo,estadia,gastoshotel,idhotel,nombre,tipousuario)
VALUES (0, 'no', 0, 0, 1, 'no', 3);

INSERT INTO USUARIO(cedula,correo,estadia,gastoshotel,idhotel,nombre,tipousuario)
VALUES (63510603, 'maria.admin', 0, 0, 1, 'Maria', 2);

INSERT INTO USUARIO(cedula,correo,estadia,gastoshotel,idhotel,nombre,tipousuario)
VALUES (12345678, 'pedro.perez', 0, 0, 1, 'Pedro', 3);

INSERT INTO USUARIO(cedula,correo,estadia,gastoshotel,idhotel,nombre,tipousuario)
VALUES (1000364541, 'pabloG', 0, 0, 1, 'Pablo', 4);

INSERT INTO HABITACION (capacidad,cliente,costonoche,estado,idhotel,numhabitacion,tipohabitacion)
VALUES (4,0,12000,'Vacia',1,350,1);

INSERT INTO HABITACION (capacidad,cliente,costonoche,estado,idhotel,numhabitacion,tipohabitacion)
VALUES (4,0,12000,'Vacia',1,360,1);

INSERT INTO HABITACION (capacidad,cliente,costonoche,estado,idhotel,numhabitacion,tipohabitacion)
VALUES (4,0,12000,'Vacia',1,370,2);

INSERT INTO SERVICIO(id, nombreServicio, demanda) VALUES (1,'Spa',0);

INSERT INTO PRODUCTO(id, nombreProducto, costoProducto, idServicio)
VALUES (1, 'Masaje', 12000, 1);

INSERT INTO PLANDEPAGO (tipoPlan, caracteristicas) VALUES ('Convencion1','Gratis');


COMMIT;