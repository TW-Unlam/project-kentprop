DROP SCHEMA IF EXISTS bd_;
CREATE SCHEMA bd_;
USE bd_;
INSERT INTO usuario VALUES 
(1,1,'kentprop@mail','1234','ADMIN'),
(2,1,'sullca@mail','1234','PROPIETARIO'),
(3,1,'emiliano@mail','1234','PROPIETARIO'),
(4,1,'valPardo@mail','1234','PROPIETARIO'),
(5,1,'Santy@mail','1234','PROPIETARIO');

INSERT INTO ubicacion (`id`, `latitud`, `localidad`, `longitud`, `provincia`) 
VALUES 
(1,-34.59330330674339,'Agronomía',-58.48538487189992,'Buenos Aires'),
(2,-34.60592951874363,'Almagro', -58.41888062037303,'Buenos Aires'),
(3,-24.191748796490856,'Barrio Cuyaya',-65.31037667253626,'Jujuy'),
(4, -37.821598117601674,'Juan E. Barra', -60.48257712539345,'Buenos Aires'),
(5,-37.62273879983981,'Napaleofú', -58.7468275356217,'Buenos Aires'),
(6,-23.889864569906866,'Ingeniero Juarez',-61.85473820859059,'Formosa'),
(7,-31.97783433860174,'San Agustín',-64.37543410744013,'Cordoba'),
(8,-24.762394585166284,'Tres Cerritos',-65.39772880171193,'Salta'),
(9,-33.007986263943074,'Gualeguaychú', -58.52437556195882,'Entre Rios'),
(10,-38.828130699085655,'Centenario',-68.14342972845226,'Neuquen'),
(11,-24.84148756056162,'Gral.Alvarado', -65.4605786427529,'Salta'),
(12, -34.67016429597653,'San Justo', -58.56286451927776,'Buenos Aires');

select *
from ubicacion;


INSERT INTO propiedad (id,cantidadAmbientes,cochera,metrosCuadrados, tipoPropiedad,ubicacion_id,propietario_id) VALUES
(1,2,0,53,1,1,2),
(2,2,1,49,1,2,2),
(3,3,1,62,1,2,2),
(4,1,0,80,2,1,3),
(5,2,0,55,2,2,3),
(6,3,0,61,2,2,3),
(7,5,1,91.3,1,7,4),
(8,4,1,80,3,2,4),
(9,1,0,49.3,1,2,4),
(10,5,1,80,4,1,5),
(11,2,1,50,4,2,5),
(12,3,1,60.4,1,2,5),
(13,3,1,60.5,1,12,2),
(14,3,1,60.5,1,12,3);

INSERT INTO publicacion (id,descripcion,estatus,fechaPublicacion,isActivo,precio,tipoAccion,propiedad_id) VALUES 
(1,'Departamento 1',0,'2022-05-05',1,45000,0,1),
(2,'Casa 1',0,'2022-06-20',1,50000,0,2),
(3,'Departamento 2',0,'2022-01-10',1,60000,0,3),
(4,'Oficina 1',1,'2022-02-27',1,35000,0,4),
(5,'Casa 2',2,'2022-06-03',1,2000000,1,5),
(6,'Casa 3',0,'2022-04-10',1,70000,0,6),
(7,'Casa 4',0,'2022-03-12',1,9000000,1,7),
(8,'Departamento 3',1,'2022-01-28',1,8550000,1,8),
(9,'Departamento 4',0,'2022-03-15',1,2000000,2,9),
(10,'Casa 4',0,'2022-05-01',1,8200000,1,10),
(11,'Oficina 2',0,'2022-03-22',1,2500000,1,11),
(12,'Departament 5',2,'2022-04-10',1,75000,0,12),
(13,'Departament 6',0,'2022-04-10',1,75000,0,13),
(14,'Departament 7',0,'2022-04-10',1,75000,0,14);

select pu.tipoAccion,po.id,po.tipoPropiedad ,u.*
from publicacion pu
join propiedad po on pu.propiedad_id=po.id
join ubicacion u on u.id=po.ubicacion_id;


select *
from imagen;

INSERT INTO imagen(urlImagen, publicacion_id)
VALUES ('images/PropiedadTipoCasa.jpg', 2),
 ('images/PropiedadTipoCasa.jpg',5),
 ('images/PropiedadTipoCasa.jpg', 6),
 ('images/PropiedadTipoCasa.jpg', 7),
 ('images/PropiedadTipoCasa02.jpg', 5),
 ('images/PropiedadTipoCasa02.jpg', 6),
 ('images/PropiedadTipoCasa02.jpg', 7),
 ('images/PropiedadTipoCasa03.jpg', 7),
 ('images/PropiedadTipoCasa03.jpg', 10),
 ('images/PropiedadTipoDepartamento.jpg', 1),
 ('images/PropiedadTipoDepartamento.jpg', 3),
 ('images/PropiedadTipoDepartamento.jpg', 8),
 ('images/PropiedadTipoDepartamento.jpg', 9),
 ('images/PropiedadTipoDepartamento.jpg', 12),
 ('images/PropiedadTipoDepartamento.jpg', 13);


select *
from usuario;

DELETE FROM imagen;
INSERT INTO pregunta (pregunta, publicacionConsultada_id, Usuario_id) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', 12, 1);
INSERT INTO pregunta (pregunta, publicacionConsultada_id, Usuario_id) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', 13, 1);
INSERT INTO pregunta (pregunta, publicacionConsultada_id, Usuario_id) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', 14, 1);

INSERT INTO pregunta (pregunta, publicacionConsultada_id, Usuario_id) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', 12, 2);
INSERT INTO pregunta (pregunta, publicacionConsultada_id, Usuario_id) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', 13, 2);
INSERT INTO pregunta (pregunta, publicacionConsultada_id, Usuario_id) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', 14, 2);

INSERT INTO pregunta (pregunta, publicacionConsultada_id, Usuario_id)
VALUES ('¿Se aceptan mascotas?',1, 4),
('¿Tiene cocina a gas?',1, 3),
('¿Cual es el valor de las expensas?', 1, 1);









