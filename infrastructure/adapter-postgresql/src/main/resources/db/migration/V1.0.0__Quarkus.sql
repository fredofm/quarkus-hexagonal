create table LoanEntity (
	id varchar not null,
	annualInterestRate float8,
	loanAmount int8,
	loanDate timestamp,
	numberOfYears int4,
	userId int8,
	primary key (id)
);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	('45828931-8c74-46ad-acd1-7c0167116ad2', 0.15, 10000, NOW(), 10, 1);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	('6e147d79-dc7a-4583-bd79-998de6f91595', 0.15, 30000, NOW(), 15, 1);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	('b046b342-67f0-4ca7-8add-6e165dd98e29', 0.15, 40000, NOW(), 20, 2);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	('c09408cb-3e77-4484-ab69-db44a9426b48', 0.15, 10000, NOW(), 3, 2);

INSERT INTO
	loanentity(id, annualInterestRate, loanAmount, loanDate, numberOfYears, userId)
VALUES
	('f1f29b6c-ec06-41b8-a9d9-bfe778658fb8', 2.15, 130000, NOW(), 30, 3);