create table books(
    id int auto_increment primary key,
    book_name VARCHAR(40) NOT NULL,
    author_name VARCHAR(40) NOT NULL,
    category VARCHAR(40),
    description VARCHAR(40)
    );