create table permission (id integer generated by default as identity, version bigint not null, name varchar(50) not null, description varchar(255) not null, primary key (id));
create table role (id integer generated by default as identity, version bigint not null, name varchar(50) not null, description varchar(255) not null, primary key (id));
create table role_permission (role_permissions_id integer not null, permission_id integer);
create table stimulus (id integer generated by default as identity, version bigint not null, name varchar(50) not null, resource varchar(255) not null, primary key (id));
create table treatment (id integer generated by default as identity, version bigint not null, name varchar(50) not null, user_id integer not null, treatment_timestamp timestamp not null, effectiveness double not null, description varchar(255) not null, duration double not null, primary key (id));
create table treatment_parameter (id integer generated by default as identity, version bigint not null, stimulus_id integer not null, max_value double not null, frecuency double not null, min_value double not null, treatment_id integer not null, primary key (id));
create table user (id integer generated by default as identity, version bigint not null, doc_type varchar(5) not null, doc_number varchar(50) not null, first_name varchar(50) not null, role_id integer not null, password varchar(50) not null, last_name varchar(50) not null, date_of_birth timestamp not null, email varchar(255) not null, primary key (id));
alter table permission add constraint UK_2ojme20jpga3r4r79tdso17gi unique (name);
alter table role add constraint UK_8sewwnpamngi6b1dwaa88askk unique (name);
alter table stimulus add constraint UK_95jar8my37v47lwd301up1eag unique (name);
alter table treatment add constraint UK_me69qpd9vytp1em0f556ma4ds unique (name);
alter table role_permission add constraint FKf8yllw1ecvwqy3ehyxawqa1qp foreign key (permission_id) references permission;
alter table role_permission add constraint FKg8c3wpn8a78g9mtwwa5t9oq17 foreign key (role_permissions_id) references role;
alter table treatment add constraint FKcesd3qmwlcfaemy455u4va5ji foreign key (user_id) references user;
alter table treatment_parameter add constraint FKhi1egevnejswqncqy0eu8erg7 foreign key (stimulus_id) references stimulus;
alter table treatment_parameter add constraint FKaamklulat5bwkd5u5n6i9e30d foreign key (treatment_id) references treatment;
alter table user add constraint FKn82ha3ccdebhokx3a8fgdqeyy foreign key (role_id) references role;
