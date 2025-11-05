CREATE DATABASE PracticeWipro;

use PracticeWipro;

CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    course VARCHAR(30)
);

desc Students;

select * from CollegeStudents;

ALTER TABLE Students
ADD email VARCHAR(100);

ALTER TABLE Students
MODIFY age SMALLINT;

RENAME TABLE Students TO CollegeStudents;
desc CollegeStudents;

ALTER TABLE CollegeStudents
DROP COLUMN course;

INSERT INTO CollegeStudents (student_id, name, age, email)
VALUES
(1, 'Amit Sharma', 20, 'amit.sharma@example.com'),
(2, 'Priya Verma', 22, 'priya.verma@example.com'),
(3, 'Rohan Singh', 21, 'rohan.singh@example.com'),
(4, 'Neha Gupta', 19, 'neha.gupta@example.com'),
(5, 'Vikram Das', 23, 'vikram.das@example.com');

truncate  table CollegeStudents;
