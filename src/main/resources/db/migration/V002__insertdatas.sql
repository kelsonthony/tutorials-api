delete from tutorials;

alter table tutorials auto_increment = 1;
insert into tutorials(id, title, description, published) values (1, 'Tutorial One', 'description', 1);
insert into tutorials(id, title, description, published) values (2, 'Tutorial Two', 'description Two', 0);
insert into tutorials(id, title, description, published) values (3, 'Tutorial Three', 'description Three', 1);
