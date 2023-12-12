CREATE TABLE USERS(
id serial PRIMARY KEY,
name varchar(50),
balance int
);

CREATE TABLE USER_TRANSACTION (
id serial PRIMARY KEY,
user_id bigint,
amount int,
transaction_date timestamp,
CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES USERS(id) ON DELETE CASCADE
);