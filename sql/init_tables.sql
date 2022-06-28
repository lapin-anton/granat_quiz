create table person (
                      id serial primary key,
                      login varchar(256) not null unique,
                      password varchar(256) not null,
                      created timestamp default Now()
);

create table result (
                        id serial primary key,
                        try_count int default 0,
                        start timestamp,
                        finish timestamp,
                        grade int default 0,
                        person_id int references person(id)
);

create table question (
                          id serial primary key,
                          content text
);

create table answer (
                        id serial primary key,
                        content text,
                        question_id int references question(id),
                        is_right boolean
);

create table detail (
                        id serial primary key,
                        result_id int references result(id),
                        answer_id int references answer(id)
);
