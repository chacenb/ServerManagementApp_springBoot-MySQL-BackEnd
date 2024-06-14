
    create table my_list (
       sample_id bigint not null,
        list varchar(255)
    ) engine=InnoDB;

    create table sample (
       id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into sample_seq values ( 1 );

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table my_list 
       add constraint FKr1vwgjmnbg419urew2w3s51mu 
       foreign key (sample_id) 
       references sample (id);
