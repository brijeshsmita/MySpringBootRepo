/*If table exists then comment the ddl configuration 
 * in application.properties file
 * if does not exists then comment the drop table statement
 * 
 */DROP TABLE user_ng_rest;
create database users if not exists;
use users;
CREATE Table user_ng_rest(
user_id int AUTO_INCREMENT,
first_name varchar(30),
last_name varchar(30),
email varchar(50),
PRIMARY KEY ( user_id)
);