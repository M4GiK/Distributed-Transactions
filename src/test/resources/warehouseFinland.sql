DROP TABLE DEPOT;
CREATE TABLE DEPOT (
	id integer not null primary key,
	name varchar(80),
	depot_date timestamp,
	amount integer
);
