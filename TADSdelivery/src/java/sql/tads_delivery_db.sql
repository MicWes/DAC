create table T01_delivery_status (
	status_id serial primary key,
	name_id varchar(20)
);

create table T02_user_type (
	usertype_id serial primary key,
	usertype_name varchar(10)
);

create table T03_state (
	state_id serial primary key,
	state_name varchar(30),
	state_uf varchar(2)
);

create table T04_city (
	city_id serial primary key,
	city_name varchar(30),
	city_uf int,
	city_state int,
	constraint T04_T03_fk foreign key (city_state) references T03_state(state_id)
);

create table T05_user (
	user_id serial primary key,
	user_name varchar(100),
	user_cpf varchar(11),
	user_login varchar(30),
	user_adress varchar(30),
	user_phone varchar(10),
	user_email varchar(30),
	user_state int,
	user_city int,
	user_type int,
	constraint T05_T03_fk foreign key (user_state) references T03_state(state_id),
	constraint T05_T04_fk foreign key (user_city) references T04_city(city_id),
	constraint T05_T02_fk foreign key (user_type) references T02_user_type(usertype_id)
);

create table T06_delivery (
	delivery_id serial primary key,
	delivery_description varchar(100),
	delivery_local varchar(50),
	delivery_destiny varchar(50),
	delivery_dtCad timestamp,
	delivery_status int,
	delivery_owner int,
	constraint T06_T03_fk FOREIGN KEY (delivery_status) references T01_delivery_status(status_id),
	constraint T06_T05_fk FOREIGN KEY (delivery_owner) references T05_user(user_id)
);

create table T07_action (
	action_id serial primary key,
	action_description varchar(100),
	action_dt date,
	action_user int,
	action_delivery int,
	constraint T07_T05_fk FOREIGN KEY (action_user) REFERENCES T05_user(user_id),
	constraint T07_T06_fk FOREIGN KEY (action_delivery) REFERENCES T06_delivery(delivery_id)
);

create table T08_message (
	message_id serial primary key,
	message_description varchar(100),
	message_delivery int,
	message_user int,
	constraint T08_T05_fk FOREIGN KEY (message_user) REFERENCES T05_user(user_id),
	constraint T08_T06_fk FOREIGN KEY (message_delivery) REFERENCES T06_delivery(delivery_id)	
 );
