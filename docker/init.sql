create table if not exists munchbox.restaurante_tipo_cozinha
(
    id_restaurante_tipo_cozinha bigint auto_increment
        primary key,
    nome                        varchar(50) not null,
    data_atualizacao            datetime(6) not null,
    data_inclusao               datetime(6) not null
);

create table if not exists munchbox.usuario_perfil
(
    id_usuario_perfil bigint auto_increment
        primary key,
    nome              varchar(50) not null,
    data_atualizacao  datetime(6) not null,
    data_inclusao     datetime(6) not null
);

create table if not exists munchbox.usuario
(
    id_usuario        bigint auto_increment
        primary key,
    id_usuario_perfil bigint      not null,
    login             varchar(50) not null,
    senha             varchar(50) not null,
    data_atualizacao  datetime(6) not null,
    data_inclusao     datetime(6) not null,
    constraint fk_usuario_1
        foreign key (id_usuario_perfil) references munchbox.usuario_perfil (id_usuario_perfil)
);

create table if not exists munchbox.cliente
(
    id_cliente       bigint auto_increment
        primary key,
    id_usuario       bigint      not null,
    nome             varchar(50) not null,
    data_nascimento  date        not null,
    celular          varchar(20) not null,
    email            varchar(50) not null,
    data_atualizacao datetime(6) not null,
    data_inclusao    datetime(6) not null,
    constraint uk_cliente_1
        unique (id_usuario),
    constraint fk_cliente_1
        foreign key (id_usuario) references munchbox.usuario (id_usuario)
);

create table if not exists munchbox.cliente_endereco
(
    id_cliente_endereco bigint auto_increment
        primary key,
    id_cliente          bigint       not null,
    rua                 varchar(100) not null,
    numero              varchar(10)  not null,
    complemento         varchar(50)  null,
    bairro              varchar(50)  not null,
    cidade              varchar(50)  not null,
    estado              varchar(50)  not null,
    cep                 varchar(10)  not null,
    data_atualizacao    datetime(6)  not null,
    data_inclusao       datetime(6)  not null,
    constraint fk_cliente_endereco_1
        foreign key (id_cliente) references munchbox.cliente (id_cliente)
);

create table if not exists munchbox.proprietario
(
    id_proprietario  bigint auto_increment
        primary key,
    id_usuario       bigint      not null,
    nome             varchar(50) not null,
    data_nascimento  date        not null,
    celular          varchar(20) not null,
    email            varchar(50) not null,
    data_atualizacao datetime(6) not null,
    data_inclusao    datetime(6) not null,
    constraint uk_proprietario_1
        unique (id_usuario),
    constraint fk_proprietario_1
        foreign key (id_usuario) references munchbox.usuario (id_usuario)
);

create table if not exists munchbox.proprietario_endereco
(
    id_proprietario_endereco bigint auto_increment
        primary key,
    id_proprietario          bigint       not null,
    rua                      varchar(100) not null,
    numero                   varchar(10)  not null,
    complemento              varchar(50)  null,
    bairro                   varchar(50)  not null,
    cidade                   varchar(50)  not null,
    estado                   varchar(50)  not null,
    cep                      varchar(10)  not null,
    data_atualizacao         datetime(6)  not null,
    data_inclusao            datetime(6)  not null,
    constraint fk_proprietario_endereco_1
        foreign key (id_proprietario) references munchbox.proprietario (id_proprietario)
);

create table if not exists munchbox.restaurante
(
    id_restaurante              bigint auto_increment
        primary key,
    id_restaurante_tipo_cozinha bigint       not null,
    id_proprietario             bigint       not null,
    nome                        varchar(50)  not null,
    rua                         varchar(100) not null,
    numero                      varchar(10)  not null,
    complemento                 varchar(50)  null,
    bairro                      varchar(50)  not null,
    cidade                      varchar(50)  not null,
    estado                      varchar(50)  not null,
    cep                         varchar(10)  not null,
    data_atualizacao            datetime(6)  not null,
    data_inclusao               datetime(6)  not null,
    constraint fk_restaurante_1
        foreign key (id_restaurante_tipo_cozinha) references munchbox.restaurante_tipo_cozinha (id_restaurante_tipo_cozinha),
    constraint fk_restaurante_2
        foreign key (id_proprietario) references munchbox.proprietario (id_proprietario)
);

create table if not exists munchbox.restaurante_funcionamento
(
    id_restaurante_funcionamento bigint auto_increment
        primary key,
    id_restaurante               bigint      not null,
    dia_semana                   int         not null,
    horario_abertura             varchar(5)  not null,
    horario_fechamento           varchar(5)  not null,
    data_atualizacao             datetime(6) not null,
    data_inclusao                datetime(6) not null,
    constraint fk_restaurante_funcionamento_1
        foreign key (id_restaurante) references munchbox.restaurante (id_restaurante)
);

create table if not exists munchbox.restaurante_produto
(
    id_restaurante_produto bigint auto_increment
        primary key,
    id_restaurante         bigint                 not null,
    nome                   varchar(50)            not null,
    descricao              varchar(250)           not null,
    valor                  decimal(11, 2)         not null,
    imagem                 varchar(250)           not null,
    flag_permite_entrega   varchar(1) default 'S' not null,
    data_atualizacao       datetime(6)            not null,
    data_inclusao          datetime(6)            not null,
    constraint fk_restaurante_produto_1
        foreign key (id_restaurante) references munchbox.restaurante (id_restaurante)
);

