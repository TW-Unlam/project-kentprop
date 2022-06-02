DROP SCHEMA IF EXISTS bd_;
CREATE SCHEMA bd_;
USE bd_;

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

INSERT INTO publicacion (id,precio,descripcion,estatus,tipoAccion,fechaPublicacion,propiedad_id,isActivo)VALUES 
(1,300000,'departamento en capital',0,0,'2020-05-05',1,1),
(2,45000,'departamento en capital',1,1,'2021-05-06',1,1),
(3,40000,'departamento en capital',2,2,'2022-05-07',2,0);

CREATE TABLE propiedad (
  id int NOT NULL AUTO_INCREMENT,
  cantidadAmbientes int DEFAULT NULL,
  cochera boolean DEFAULT NULL,
  metrosCuadrados double DEFAULT NULL,
  tipoPropiedad int DEFAULT NULL,
  ubicacion_id int DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT  FOREIGN KEY (ubicacion_id) REFERENCES ubicacion (id)
);

INSERT INTO propiedad VALUES 
(1,2,0,50.5,2,1),
(2,1,1,45.5,1,12),
(3,4,1,100,3,2);

select pu.tipoAccion,po.id,po.tipoPropiedad ,u.*
from publicacion pu
join propiedad po on pu.propiedad_id=po.id
join ubicacion u on u.id=po.ubicacion_id;

CREATE TABLE ubicacion (
  id int NOT NULL AUTO_INCREMENT,
  localidad varchar(255) DEFAULT NULL,
  provincia varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE usuario (
  id bigint NOT NULL AUTO_INCREMENT,
  activo bit(1) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  rol varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


INSERT INTO usuario VALUES 
(1,'kentprop@mail','1234',1,'ADMIN');

INSERT INTO ubicacion VALUES 
(1,'Ciudad Autónoma de Buenos Aires','Agronomía'),
(2,'Ciudad Autónoma de Buenos Aires','Almagro'),
(3,'Ciudad Autónoma de Buenos Aires','Balvanera'),
(4,'Provincia de Buenos Aires','Juan E. Barra'),
(5,'Provincia de Buenos Aires','Napaleofú'),
(6,'Provincia de Buenos Aires','Ramos Otero'),
(7,'Provincia de Buenos Aires','San Agustín'),
(8,'Provincia de Buenos Aires','Vásquez'),
(9,'Provincia de Buenos Aires','Baradero'),
(10,'Provincia de Buenos Aires','Benito Juárez'),
(11,'Provincia de Buenos Aires','Irineo Portela'),
(12,'Provincia de Buenos Aires','San Justo');






