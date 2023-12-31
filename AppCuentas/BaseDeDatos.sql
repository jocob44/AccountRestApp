create table cliente
(
    id         bigint not null,
    cliente_id varchar(255),
    contrasena varchar(255),
    estado     varchar(255) check (estado in ('ACTIVO', 'INACTIVO')),
    primary key (id)
);
create table cuenta
(
    saldo_inicial    numeric(38, 2) not null,
    cliente_id       bigint,
    id               bigint generated by default as identity,
    last_movement_id bigint,
    numero_cuenta    bigint         not null unique,
    estado           varchar(255) check (estado in ('ACTIVA', 'INACTIVA')),
    tipo             varchar(255) check (tipo in ('CAJA_DE_AHORRO', 'CUENTA_CORRIENTE')),
    primary key (id)
);
create table movimiento
(
    fecha           date           not null,
    saldo           numeric(38, 2) not null,
    valor           numeric(38, 2) not null,
    cuenta_id       bigint,
    id              bigint generated by default as identity,
    tipo_movimiento varchar(255) check (tipo_movimiento in ('DEBITO', 'CREDITO')),
    primary key (id)
);
create table persona
(
    edad           integer not null check ((edad <= 100) and (edad >= 18)),
    id             bigint generated by default as identity,
    direccion      varchar(255),
    genero         varchar(255) check (genero in ('MASCULINO', 'FEMENINO', 'OTRO')),
    identificacion varchar(255),
    nombre         varchar(255),
    telefono       varchar(255),
    primary key (id)
);
alter table if exists cliente
    add constraint FKkpvkbjg32bso6riqge70hwcel foreign key (id) references persona;
alter table if exists cuenta
    add constraint FK4p224uogyy5hmxvn8fwa2jlug foreign key (cliente_id) references cliente;
alter table if exists cuenta
    add constraint FKgdl3vf52nr6ydvqc2ua26a09t foreign key (last_movement_id) references movimiento;
alter table if exists movimiento
    add constraint FK4ea11fe7p3xa1kwwmdgi9f2fi foreign key (cuenta_id) references cuenta;