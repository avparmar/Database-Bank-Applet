drop database if exists bank;
CREATE DATABASE IF NOT EXISTS Bank ;
use bank;



CREATE TABLE IF NOT EXISTS users(
	first_name			varchar(25)		not null,
	last_name 			varchar(25)		not null,
    id					int 			not null,
    street_address		varchar(25),
    city				varchar(25),	
    zip					varchar(25),
    country				varchar(25),
    region				varchar(25),
	salary				double,

    primary key(id)	
	
);


CREATE TABLE IF NOT EXISTS customers(
    id					int 			not null,
	pin					int 			not null,
	account_number		long 			not null,
    balance 			double			not null,
    last_trans			varchar(25),	-- name of last transaction
    last_amount_trans	double,			-- amount on last transaction
    account_status		varchar(25), -- frozen 
    primary key(id),
	FOREIGN KEY (id) REFERENCES users(Id) on delete cascade

    
	
);


CREATE TABLE IF NOT EXISTS employees(
    id					int 			not null,
	login_password		varchar(50)		not null,
    pay_rate			double,
    primary key(id),
	FOREIGN KEY (id) REFERENCES users(Id) on delete cascade

	
);

CREATE TABLE IF NOT EXISTS transactions(
	transaction_number	int 			not null	AUTO_INCREMENT,
    customer_id			int 			not null,
    transaction_date	date			not null,
    transaction_time	time		 	not null,
	transaction_type 	varchar(25)		not null,
    old_balance			double			not null,
	amount_transacted 	double			not null,
    new_balance			double			not null,
    primary key(transaction_number),
    FOREIGN KEY (customer_id) REFERENCES customers(Id)    
	
);


DROP PROCEDURE IF EXISTS customer_statment;
DELIMITER //
 
 CREATE PROCEDURE customer_statment
 (
   customer_id_	int
 )
   BEGIN
   SELECT * 
   FROM transactions 
   WHERE  customer_id_ = customer_id;
   END //
 DELIMITER ;
 

 
 DROP PROCEDURE IF EXISTS withdraw; 
DELIMITER //
 
 CREATE PROCEDURE withdraw
 (
   customer_id_	int,
   amount int
 )
   BEGIN
   UPDATE Customers
   SET balance=balance - amount,
   last_trans = 'withdraw',
   last_amount_trans = amount
   WHERE customer_id_ = Customers.id;
   END //
   
 DELIMITER ;
 
 
  DROP PROCEDURE IF EXISTS deposit;
DELIMITER //
 
 CREATE PROCEDURE deposit
 (
   customer_id_	int,
   amount int
 )
   BEGIN
   UPDATE Customers
   SET balance=balance + amount,
   last_trans = 'deposit',
   last_amount_trans = amount
   WHERE customer_id_ = id;
   END //
 DELIMITER ;
 
   DROP PROCEDURE IF EXISTS transfer;
DELIMITER //
 
 CREATE PROCEDURE transfer
 (
   customer_id_1	int,
   customer_id_2	int,
   amount int
 )
   BEGIN
   call withdraw (customer_id_1, amount);
   call deposit  (customer_id_2, amount);
   END //
 DELIMITER ;
 
 DROP PROCEDURE IF EXISTS get_balance;
DELIMITER //
 
  CREATE PROCEDURE get_balance
 (
   customer_id_	int
 )
   BEGIN
   select balance from customers where customer_id_ = id ;
   END //
 DELIMITER ;
 
 

DROP TRIGGER if exists add_Trans ; 
DELIMITER //

create trigger add_Trans 
after update on customers
FOR EACH ROW   
BEGIN
	IF NEW.balance <> OLD.balance then
      INSERT INTO transactions (customer_id,
		transaction_date,
        transaction_time,
		transaction_type,
		old_balance,
		amount_transacted ,
		new_balance)
      VALUES ( NEW.id,
		(select NOW()),
		(select CURTIME()),
		NEW.last_trans,
        OLD.balance,
		NEW.last_amount_trans,
		NEW.balance);
	end IF;

END; //
delimiter ;    

    
    
	 
 
 
 
 
 
 