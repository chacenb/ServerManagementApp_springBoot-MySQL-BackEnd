
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










    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255)
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );



    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        server_details jsonb,
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        server_details jsonb,
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        server_details jsonb,
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

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

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

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

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

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

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        server_details jsonb,
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

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

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

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

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        server_details jsonb,
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        server_details jsonb,
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        server_details jsonb,
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

    create table server (
       id bigint not null,
        image_url varchar(255),
        ip_address varchar(255) not null,
        memory varchar(255),
        name varchar(255),
        server_details jsonb,
        status smallint,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table server_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into server_seq values ( 1 );

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);

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

    create table t_sample (
       id_sample bigint not null,
        primary key (id_sample)
    ) engine=InnoDB;

    create table t_sample_list (
       sample_id bigint not null,
        sample_list varchar(255) not null
    ) engine=InnoDB;

    create table t_sample_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into t_sample_seq values ( 1 );

    alter table server 
       add constraint UK_96tx503up4941ibvsnhh8itdi unique (ip_address);

    alter table t_sample_list 
       add constraint FKkio42047i3i4k8acc6uhc92oc 
       foreign key (sample_id) 
       references t_sample (id_sample);
