DROP TABLE example IF EXISTS;

CREATE TABLE example (
  name varchar(255),
  age int,
  CONSTRAINT name_fk PRIMARY KEY (name)
);
