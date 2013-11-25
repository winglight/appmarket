# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table app_model (
  dtype                     varchar(10) not null,
  id                        bigint not null,
  appname                   varchar(255),
  desc                      varchar(255),
  downurl                   varchar(255),
  author_id                 bigint,
  app_version               varchar(255),
  app_version_code          varchar(255),
  package_name              varchar(255),
  min_sdk_version           varchar(255),
  target_sdk_version        varchar(255),
  icon_url                  varchar(255),
  downloads                 bigint,
  created_at                timestamp,
  constraint pk_app_model primary key (id))
;

create table user_model (
  dtype                     varchar(10) not null,
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  device_id                 varchar(255),
  password                  varchar(255),
  tokenid                   varchar(255),
  token_expiration_time     bigint,
  description               varchar(255),
  status                    varchar(8),
  user_role                 varchar(9),
  created_at                timestamp,
  constraint ck_user_model_status check (status in ('Active','Inactive')),
  constraint ck_user_model_user_role check (user_role in ('ADMIN','DEVELOPER','USER')),
  constraint pk_user_model primary key (id))
;


create table user_model_app_model (
  user_model_id                  bigint not null,
  app_model_id                   bigint not null,
  constraint pk_user_model_app_model primary key (user_model_id, app_model_id))
;
create sequence app_model_seq;

create sequence user_model_seq;

alter table app_model add constraint fk_app_model_author_1 foreign key (author_id) references user_model (id) on delete restrict on update restrict;
create index ix_app_model_author_1 on app_model (author_id);



alter table user_model_app_model add constraint fk_user_model_app_model_user__01 foreign key (user_model_id) references user_model (id) on delete restrict on update restrict;

alter table user_model_app_model add constraint fk_user_model_app_model_app_m_02 foreign key (app_model_id) references app_model (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists app_model;

drop table if exists user_model;

drop table if exists user_model_app_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists app_model_seq;

drop sequence if exists user_model_seq;
