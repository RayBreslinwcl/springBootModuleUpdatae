create table user_shiro(
                           id int primary key auto_increment,
                           name varchar(20),
                           password varchar(30),
                           perms varchar(20)
)


INSERT INTO `test`.`user_shiro`( `name`, `password`,`perms`) VALUES ( 'admin', '123456','user:update');