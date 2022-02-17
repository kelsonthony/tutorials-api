create table tutorials (
	id bigint not null auto_increment,
    title varchar(60) not null,
    description varchar(60),
    published TINYINT,
    
    primary key(id)
)engine=InnoDB default charset=UTF8MB4;
