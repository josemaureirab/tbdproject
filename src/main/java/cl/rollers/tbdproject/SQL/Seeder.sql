create table task (
	id int,
    name text,
    description text,
    status boolean,
	primary key(id)
);
create table users (
    id int,
    username text,
    password text,
    name text,
    lastName text,
    rut text,
    age int,
    roles text,
    username text,
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