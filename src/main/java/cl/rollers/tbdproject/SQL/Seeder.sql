DROP TABLE dimension CASCADE;
DROP TABLE emergencies CASCADE;
DROP TABLE task CASCADE;
DROP TABLE users CASCADE;
DROP TABLE voluntary CASCADE;
DROP TABLE voluntary_dimension CASCADE;
DROP TABLE voluntary_emergency CASCADE;
DROP TABLE voluntary_task CASCADE;
DROP TABLE user_roles CASCADE;

/*CREATE EXTENSION postgis;*/

create table dimension(
    id int,
    name text,
    score Int,
    voluntaryDimensionList TEXT,
	primary key(id)
);

create table emergencies(
    id int,
    name text,
    description text,
    taskList TEXT,
	primary key(id)
);

create table task (
	id int,
    name text,
    description text,
    status boolean,
    emergency_id int,
	primary key(id),
	foreign key (emergency_id)
	references emergencies(id)
);
create table users (
    id int,
    username text,
    PASSWORD text,
    name text,
    lastName text,
    rut text,
    age int,
    roles text,
	primary key(id)
);

create table voluntary (
	id int,
	name text,
    lastName text,
    mail text,
    gender text,
    rut text,
    age int,
	primary key(id)
);

create table voluntary_dimension (
	id int,
	voluntary_id int,
	dimension_id int,
	primary key(id),
	foreign key (voluntary_id)
	references voluntary(id),
	foreign key (dimension_id)
	references dimension(id)
);

create table voluntary_emergency (
	id int,
	voluntary_id int,
	emergency_id int,
	primary key(id),
	foreign key (voluntary_id)
	references voluntary(id),
	foreign key (emergency_id)
	references emergencies(id)
);

create table voluntary_task (
	id int,
	voluntary_id int,
	task_id int,
	primary key(id),
	foreign key (voluntary_id)
	references voluntary(id),
	foreign key (task_id)
	references task(id)
);

create table user_roles (
	id int,
	user_id int,
	roles TEXT,
	primary key(id),
	foreign key (user_id)
	references users(id)
);

insert into emergencies (id,name,description)
values (1,'Emergency 1','description 1');

ALTER TABLE voluntary ADD COLUMN location
geometry(point);

INSERT INTO voluntary (id, name, lastname, mail, gender, rut, age, location)
VALUES (1, 'Guillermo', 'Campos', 'guillermo.campos@usach.cl', 'masculino', '19.441.052-2', 22, ST_GeomFromText('POINT(-72.928177 -41.474096)', 4326));