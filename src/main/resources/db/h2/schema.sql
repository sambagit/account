DROP TABLE IF EXISTS account;
CREATE TABLE account (
	id number NOT NULL,
    name varchar2(10) NOT NULL,
    balance number(10,2) default 0,
	 PRIMARY KEY (id)
);


DROP TABLE IF EXISTS ops;
CREATE TABLE ops (
    id number NOT NULL,
    amount number(10,2) default 0,
    balance number(10,2) default 0,
    date Timestamp(6)  DEFAULT NULL,
    account_id number NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE ops ADD CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account (id);
