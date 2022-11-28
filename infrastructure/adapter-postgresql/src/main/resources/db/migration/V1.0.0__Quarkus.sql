create table LoanEntity (
	id int8 not null,
	annualInterestRate float8,
	loanAmount int8,
	loanDate date,
	numberOfYears int4,
	userId int8
	primary key (id)
);

create sequence hibernate_sequence start 1 increment 1;

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	(1, 0.15, 10000, NOW(), 10, 1);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	(2, 0.15, 30000, NOW(), 15, 1);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	(3, 0.15, 40000, NOW(), 20, 2);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	(4, 0.15, 10000, NOW(), 3, 2);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	(5, 2.15, 130000, NOW(), 30, 3);