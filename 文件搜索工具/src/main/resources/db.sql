
create table if not exists file_meta (
    id INTEGER primary key autoincrement,
    name varchar(50) not null,
    path varchar(512) not null,
    is_directory boolean not null,
    pinyin varchar(100) not null,
    pinyin_first varchar(50) not null,
    size BIGINT not null,
    last_modified timestamp not null
);