DROP TABLE REGISTER;
CREATE TABLE REGISTER (
	id integer not null primary key,
	name varchar(80),
	register_date timestamp,
	price float
);