insert into users(id,joindate,name,password,ssn) values(90001,now(), 'Users1', 'test111', '1234567');
insert into users(id,joindate,name,password,ssn) values(90002,now(), 'Users1', 'test111', '1234567');


insert into post(description,user_id) values('My first post',90001);
insert into post(description,user_id) values('My second post',90002);