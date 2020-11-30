-- customer table

CREATE TABLE customer(
id int auto_increment not null,
firstname varchar(20) not null,
lastname varchar(40) not null,
birthyear int,
sex char(1),
streetaddress varchar(30),
postcode char(5),
email varchar(50) not null, 
bonusscore decimal(3,1),
primary key(id)
)engine=InnoDB;