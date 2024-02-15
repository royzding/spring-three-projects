
SQL Developer
From SQL Developer, do the following.

Right-click on the connection.
Select the "Reset Password..." option from the popup menu.
In the subsequent dialog, enter the current password and the new password with confirmation.
Click the OK button.

11/02/2022

system/peardoor807x

GRANT CREATE SESSION TO C##WANZUN WITH ADMIN OPTION;
grant create table,  create view, create procedure, create sequence to C##WANZUN;

ALTER USER C##WANZUN quota unlimited on USERS;

GRANT CREATE TRIGGER TO  C##WANZUN;

DROP user C##WANZUN CASCADE;

create user c##wanzun:

create user c##wanzun IDENTIFIED BY peardoor807x;
GRANT CREATE SESSION TO c##wanzun WITH ADMIN OPTION;
grant create table,  create view, create procedure, create sequence to c##wanzun;
ALTER USER c##wanzun quota unlimited on USERS;
GRANT CREATE TRIGGER TO  c##wanzun;

