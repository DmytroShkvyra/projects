drop table T_TASK if exists;
create table T_TASK (ID integer identity primary key, STATUS varchar(9) not null, NAME varchar(150) not null);
