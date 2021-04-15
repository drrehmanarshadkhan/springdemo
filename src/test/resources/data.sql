drop table if exists MyTasks;
CREATE TABLE MyTasks (
 taskID UUID PRIMARY KEY,
 owner VARCHAR (50) NOT NULL,
 description VARCHAR (50) NOT NULL
);
INSERT INTO MyTasks (taskID,owner,description) values ('61614667-d279-11e7-a5ac-f941ac8dfc39','ray','yo');