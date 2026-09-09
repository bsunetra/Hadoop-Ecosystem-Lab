-- Experiment 7: Cassandra DDL & DML Commands

-- =========================
-- DDL COMMANDS
-- =========================

-- 1. Create a Keyspace
CREATE KEYSPACE IF NOT EXISTS CollegeDB
WITH replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
};

-- Use the keyspace
USE CollegeDB;


-- 2. Create a Table
CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY,
    student_name TEXT,
    department TEXT,
    year INT,
    cgpa DOUBLE
);


-- 3. Alter Table - Add a new column
ALTER TABLE students
ADD email TEXT;


-- =========================
-- DML COMMANDS
-- =========================

-- 4. Insert records
INSERT INTO students
(student_id, student_name, department, year, cgpa, email)
VALUES
(101, 'John', 'CSE', 3, 8.5, 'john@example.com');

INSERT INTO students
(student_id, student_name, department, year, cgpa, email)
VALUES
(102, 'Priya', 'AIDS', 3, 9.1, 'priya@example.com');

INSERT INTO students
(student_id, student_name, department, year, cgpa, email)
VALUES
(103, 'Rahul', 'ECE', 2, 8.2, 'rahul@example.com');


-- 5. Select all records
SELECT * FROM students;


-- 6. Select a specific record
SELECT * FROM students
WHERE student_id = 101;


-- 7. Update a record
UPDATE students
SET cgpa = 8.8
WHERE student_id = 101;


-- 8. Verify the updated record
SELECT * FROM students
WHERE student_id = 101;


-- 9. Delete a record
DELETE FROM students
WHERE student_id = 103;


-- 10. Display remaining records
SELECT * FROM students;


-- =========================
-- ADDITIONAL DDL COMMANDS
-- =========================

-- 11. Remove all records from the table
-- TRUNCATE students;

-- 12. Delete the table
-- DROP TABLE students;

-- 13. Delete the keyspace
-- DROP KEYSPACE CollegeDB;
