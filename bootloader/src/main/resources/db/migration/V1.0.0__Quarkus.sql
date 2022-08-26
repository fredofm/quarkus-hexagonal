create table PropEntity (
	id int8 not null,
	name varchar(255),
	running boolean not null,
	primary key (id)
);

create sequence hibernate_sequence start 1 increment 1;

INSERT INTO
	propentity(id, name, running)
VALUES
	(1, 'Prop 1', false);

INSERT INTO
	propentity(id, name, running)
VALUES
	(2, 'Prop 2', false);

INSERT INTO
	propentity(id, name, running)
VALUES
	(3, 'Prop 3', false);

INSERT INTO
	propentity(id, name, running)
VALUES
	(4, 'Prop 4', false);

INSERT INTO
	propentity(id, name, running)
VALUES
	(5, 'Prop 5', false);