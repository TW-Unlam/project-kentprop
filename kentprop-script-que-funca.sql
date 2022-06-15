DROP SCHEMA IF EXISTS bd_;
CREATE SCHEMA bd_;
USE bd_;


CREATE TABLE ubicacion (
  id int NOT NULL AUTO_INCREMENT,
  localidad varchar(255) DEFAULT NULL,
  provincia varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE propiedad (
  id int NOT NULL AUTO_INCREMENT,
  cantidadAmbientes int DEFAULT NULL,
  cochera boolean DEFAULT NULL,
  metrosCuadrados double DEFAULT NULL,
  tipoPropiedad int DEFAULT NULL,
  ubicacion_id int DEFAULT NULL,
  propietario_id int DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT  FOREIGN KEY (ubicacion_id) REFERENCES ubicacion (id),
  CONSTRAINT  FOREIGN KEY (propietario_id) REFERENCES usuario (id)
);

CREATE TABLE publicacion (
  id int NOT NULL AUTO_INCREMENT,
  descripcion varchar(255) DEFAULT NULL,
  estatus int DEFAULT NULL,
  fechaPublicacion date DEFAULT NULL,
  isActivo BOOLEAN NOT NULL,
  precio double DEFAULT NULL,
  tipoAccion int DEFAULT NULL,
  propiedad_id int DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT  FOREIGN KEY (propiedad_id) REFERENCES propiedad (id)
);

INSERT INTO ubicacion (id,localidad,provincia) VALUES
(1,'Agronomía','Buenos Aires'),
(2,'Almagro','Buenos Aires'),
(3,'Balvanera','Jujuy'),
(4,'Juan E. Barra','Buenos Aires'),
(5,'Napaleofú','Buenos Aires'),
(6,'Ramos Otero','Formosa'),
(7,'San Agustín','Buenos Aires'),
(8,'Vásquez','Salta'),
(9,'Baradero','Entre Rios'),
(10,'Benito Juárez','Neuquen'),
(11,'Irineo Portela','Salta'),
(12,'San Justo','Buenos Aires');

INSERT INTO propiedad (id,cantidadAmbientes,cochera,metrosCuadrados, tipoPropiedad,ubicacion_id,propietario_id) VALUES
(1,2,0,53,1,1,2),
(2,2,1,49,5,0,2),
(3,3,1,62,1,3,2),
(4,1,0,80,5,4,4),
(5,2,0,55,0,5,4),
(6,3,0,61,0,6,3),
(7,5,1,91.5,0,7,3),
(8,4,1,80,1,8,3),
(9,1,0,49.5,1,9,4),
(10,5,1,80,0,10,4),
(11,2,1,50,5,11,4),
(12,3,1,60.5,1,12,4),
(13,3,1,60.5,1,12,4),
(14,3,1,60.5,1,12,4);

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

CREATE TABLE usuario (
  id bigint NOT NULL AUTO_INCREMENT,
  activo bit(1) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  rol varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


INSERT INTO usuario VALUES 
(1,'kentprop@mail','1234',1,'ADMIN'),
(1,'sullca@mail','1234',1,'PROPIETARIO'),
(1,'emiliano@mail','1234',1,'PROPIETARIO'),
(1,'valPardo@mail','1234',1,'PROPIETARIO'),
(1,'Santy@mail','1234',1,'PROPIETARIO');

CREATE TABLE `imagen`
(
    `id` int NOT NULL AUTO_INCREMENT,
    `urlImagen` varchar(255) DEFAULT NULL,
    `publicacion_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT FOREIGN KEY (`publicacion_id`) REFERENCES `publicacion` (`id`)
);

INSERT INTO imagen VALUES ('images/PropiedadTipoCasa.jpg', 2),
 ('images/PropiedadTipoCasa.jpg',5),
 ('images/PropiedadTipoCasa.jpg', 6),
 ('images/PropiedadTipoCasa.jpg', 7),
 ('images/PropiedadTipoCasa02.jpg', 5),
 ('images/PropiedadTipoCasa02.jpg', 6),
 ('images/PropiedadTipoCasa02.jpg', 7),
 ('images/PropiedadTipoCasa03.jpg', 7),
 ('images/PropiedadTipoCasa03.jpg', 10),
 ('images/PropiedadTipoDepartamento.jpg', '1'),
 ('images/PropiedadTipoDepartamento.jpg', '3'),
 ('images/PropiedadTipoDepartamento.jpg', '8'),
 ('images/PropiedadTipoDepartamento.jpg', '9'),
 ('images/PropiedadTipoDepartamento.jpg', '12'),
 ('images/PropiedadTipoDepartamento.jpg', '13'),
 ('images/PropiedadTipoDepartamento.jpg', '14'),
 ('images/PropiedadTipoDepartamento02.jpg', '3'),
 ('images/PropiedadTipoDepartamento02.jpg', '8'),
 ('images/PropiedadTipoDepartamento02.jpg', '9'),
 ('images/PropiedadTipoDepartamento02.jpg', '12'),
 ('images/PropiedadTipoDepartamento02.jpg', '13'),
 ('images/PropiedadTipoDepartamento02.jpg', '14'),
('images/PropiedadTipoDepartamento03.jpg', '8'),
('images/PropiedadTipoDepartamento03.jpg', '9'),
('images/PropiedadTipoDepartamento03.jpg', '12'),
('images/PropiedadTipoDepartamento03.jpg', '13'),
('images/PropiedadTipoOficina.jpg', '4'),
('images/PropiedadTipoOficina.jpg', '7'),
('images/PropiedadTipoOficina02.jpg', '4'),
('images/PropiedadTipoOficina02.jpg', '7'),
('images/PropiedadTipoOficina03.jpg', '4');

CREATE TABLE `pregunta` (
         `id` int NOT NULL AUTO_INCREMENT,
         `pregunta` varchar(255) DEFAULT NULL,
         `respuesta` varchar(255) DEFAULT NULL,
         `publicacionConsultada_id` int DEFAULT NULL,
         `Usuario_id` bigint DEFAULT NULL,
        PRIMARY KEY (`id`),
        KEY  (`publicacionConsultada_id`),
        KEY  (`Usuario_id`),
        CONSTRAINT  FOREIGN KEY (`publicacionConsultada_id`) REFERENCES `publicacion` (`id`),
         CONSTRAINT FOREIGN KEY (`Usuario_id`) REFERENCES `usuario` (`id`)
);

INSERT INTO pregunta (`pregunta`, `publicacionConsultada_id`, `Usuario_id`) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', '1', '12');
INSERT INTO pregunta (`pregunta`, `publicacionConsultada_id`, `Usuario_id`) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', '1', '13');
INSERT INTO pregunta (`pregunta`, `publicacionConsultada_id`, `Usuario_id`) VALUES ('¿cual es el  tiempo de uso del ultimo inclino', '1', '14');











