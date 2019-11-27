DROP TABLE dimension CASCADE;
DROP TABLE emergencies CASCADE;
DROP TABLE task CASCADE;
DROP TABLE users CASCADE;
DROP TABLE voluntary CASCADE;
DROP TABLE voluntary_dimension CASCADE;
DROP TABLE voluntary_emergency CASCADE;
DROP TABLE voluntary_task CASCADE;
DROP TABLE user_roles CASCADE;

CREATE EXTENSION postgis;

create table dimension(
    id int,
    name VARCHAR(255),
    score Int,
    voluntaryDimensionList TEXT [],
	primary key(id)
);

create table emergencies(
    id INT,
    name VARCHAR(255),
    description TEXT,
    taskList TEXT [],
	primary key(id)
);

create table task (
	id INT,
    name VARCHAR(255),
    description TEXT,
    status BOOLEAN,
    emergency_id INT,
	primary key(id),
	foreign key (emergency_id)
	references emergencies(id)
);

create table voluntary (
	id INT,
	firstName VARCHAR(255),
    lastName VARCHAR(255),
    mail VARCHAR(255),
    gender VARCHAR(255),
    rut VARCHAR(255),
    age INT,
	primary key(id)
);

create table voluntary_dimension (
	id INT,
	voluntary_id INT,
	dimension_id INT,
	primary key(id),
	foreign key (voluntary_id)
	references voluntary(id),
	foreign key (dimension_id)
	references dimension(id)
);

create table voluntary_emergency (
	id INT,
	voluntary_id INT,
	emergency_id INT,
	primary key(id),
	foreign key (voluntary_id)
	references voluntary(id),
	foreign key (emergency_id)
	references emergencies(id)
);

create table voluntary_task (
	id INT,
	voluntary_id INT,
	task_id INT,
	primary key(id),
	foreign key (voluntary_id)
	references voluntary(id),
	foreign key (task_id)
	references task(id)
);

create table users (
    id INT,
    active BOOLEAN,
    birthDate DATE,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    password TEXT,
    rut VARCHAR(255),
    age INT,
    userName VARCHAR(255),
	primary key(id)
);

create table user_roles (
	id INT,
	user_id INT,
	role VARCHAR(255),
	primary key(id),
	foreign key (user_id)
	references users(id)
);

insert into emergencies (id,name,description)
values (1,'Emergency 1','description 1');

ALTER TABLE voluntary ADD COLUMN location
geometry(point);

INSERT INTO voluntary (id, firstName, lastName, mail, gender, rut, age, location)
VALUES (1, 'Guillermo', 'Campos', 'guillermo.campos@usach.cl', 'masculino', '19.441.052-2', 22, ST_GeomFromText('POINT(-72.928177 -41.474096)', 4326));