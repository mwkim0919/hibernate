INSERT INTO Course(id, name, created_date, last_updated_date)
VALUES (10001, 'JPA in 50 steps', sysdate(), sysdate());
INSERT INTO Course(id, name, created_date, last_updated_date)
VALUES (10002, 'Spring Boot in 50 steps', sysdate(), sysdate());
INSERT INTO Course(id, name, created_date, last_updated_date)
VALUES (10003, 'Microservices in 50 steps', sysdate(), sysdate());

INSERT INTO Passport(id, number)
VALUES (40001, 'E123456');
INSERT INTO Passport(id, number)
VALUES (40002, 'N123456');
INSERT INTO Passport(id, number)
VALUES (40003, 'F987654');

INSERT INTO Student(id, name, passport_id)
VALUES (20001, 'Minwoo', 40001);
INSERT INTO Student(id, name, passport_id)
VALUES (20002, 'John', 40002);
INSERT INTO Student(id, name, passport_id)
VALUES (20003, 'Paul', 40003);


INSERT INTO Review(id, rating, description)
VALUES (50001, '5', 'Great Course');
INSERT INTO Review(id, rating, description)
VALUES (50002, '4', 'Wonderful Course');
INSERT INTO Review(id, rating, description)
VALUES (50003, '1', 'Terrible Course');