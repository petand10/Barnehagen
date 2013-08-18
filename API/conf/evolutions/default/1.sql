# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint not null,
  street                    varchar(255),
  postal                    integer,
  postal_area               varchar(255),
  constraint pk_address primary key (id))
;

create table child (
  id                        bigint not null,
  name                      varchar(255),
  changed                   bigint,
  constraint pk_child primary key (id))
;

create table guardian (
  id                        bigint not null,
  name                      varchar(255),
  phone                     integer,
  address_id                bigint,
  constraint pk_guardian primary key (id))
;


create table child_guardian (
  child_id                       bigint not null,
  guardian_id                    bigint not null,
  constraint pk_child_guardian primary key (child_id, guardian_id))
;
create sequence address_seq;

create sequence child_seq;

create sequence guardian_seq;

alter table guardian add constraint fk_guardian_address_1 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_guardian_address_1 on guardian (address_id);



alter table child_guardian add constraint fk_child_guardian_child_01 foreign key (child_id) references child (id) on delete restrict on update restrict;

alter table child_guardian add constraint fk_child_guardian_guardian_02 foreign key (guardian_id) references guardian (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

drop table if exists child;

drop table if exists child_guardian;

drop table if exists guardian;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_seq;

drop sequence if exists child_seq;

drop sequence if exists guardian_seq;

