-- database: tenmo
BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id;

-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance numeric(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);
CREATE TABLE transfer (
	transfer_id int NOT NULL DEFAULT nextval ('seq_transfer_id'),
	account_from int NOT NULL,
	account_to int NOT NULL
	transfer_amount decimal(13, 2) NOT NULL,
	boolean transfer_status NOT NULL,
	CONSTRAINT PK_transfer PRIMARY KEY (transfer_id),
	CONSTRAINT FK_transfer_account FOREIGN KEY (account_from AND account_to) REFERENCES account (account_id)
	CONSTRAINT  ck_transfer_to_self CHECK (account_from != account_to)
	CONSTRAINT CK
	--Add constriant check so you can't send money to yourself
	--can't send more money than there is in account
	--can't send zero/negative number
);

COMMIT;
