# Experiment 7 — Cassandra DDL & DML Commands

## Aim

To create and execute **DDL (Data Definition Language)** and **DML (Data Manipulation Language)** commands using Apache Cassandra.

## Problem Statement

Create a Cassandra keyspace and table, then perform various DDL and DML operations such as creating, altering, inserting, retrieving, updating, and deleting records.

## Technologies Used

* Apache Cassandra
* CQL (Cassandra Query Language)
* cqlsh

## Database Used

**Keyspace:** `CollegeDB`

**Table:** `students`

## Table Structure

| Column         | Data Type | Description             |
| -------------- | --------- | ----------------------- |
| `student_id`   | INT       | Unique student ID       |
| `student_name` | TEXT      | Name of the student     |
| `department`   | TEXT      | Student's department    |
| `year`         | INT       | Current academic year   |
| `cgpa`         | DOUBLE    | Student's CGPA          |
| `email`        | TEXT      | Student's email address |

## DDL Commands

DDL commands are used to define and modify the structure of Cassandra database objects.

### 1. CREATE KEYSPACE

Creates a new keyspace named `CollegeDB`.

```sql
CREATE KEYSPACE IF NOT EXISTS CollegeDB
WITH replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
};
```

### 2. CREATE TABLE

Creates the `students` table with the required columns.

```sql
CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY,
    student_name TEXT,
    department TEXT,
    year INT,
    cgpa DOUBLE
);
```

### 3. ALTER TABLE

Adds a new `email` column to the existing table.

```sql
ALTER TABLE students ADD email TEXT;
```

### 4. TRUNCATE

Removes all records from a table while keeping the table structure.

```sql
TRUNCATE students;
```

### 5. DROP TABLE

Deletes the table from the keyspace.

```sql
DROP TABLE students;
```

### 6. DROP KEYSPACE

Deletes the complete keyspace and its tables.

```sql
DROP KEYSPACE CollegeDB;
```

> `TRUNCATE`, `DROP TABLE`, and `DROP KEYSPACE` are destructive operations and should be executed carefully.

## DML Commands

DML commands are used to insert, retrieve, modify, and delete data stored in Cassandra tables.

### 1. INSERT

Inserts student records into the `students` table.

```sql
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
```

### 2. SELECT

Displays all records from the table.

```sql
SELECT * FROM students;
```

A specific student can also be retrieved using the primary key:

```sql
SELECT * FROM students
WHERE student_id = 101;
```

### 3. UPDATE

Updates an existing student's CGPA.

```sql
UPDATE students
SET cgpa = 8.8
WHERE student_id = 101;
```

### 4. DELETE

Deletes a specific student record.

```sql
DELETE FROM students
WHERE student_id = 103;
```

## Sample Data

| Student ID | Name  | Department | Year | CGPA | Email                                         |
| ---------: | ----- | ---------- | ---: | ---: | --------------------------------------------- |
|        101 | John  | CSE        |    3 |  8.5 | [john@example.com](mailto:john@example.com)   |
|        102 | Priya | AIDS       |    3 |  9.1 | [priya@example.com](mailto:priya@example.com) |
|        103 | Rahul | ECE        |    2 |  8.2 | [rahul@example.com](mailto:rahul@example.com) |

After updating student `101`, the CGPA becomes **8.8**.

After deleting student `103`, only students `101` and `102` remain.

## Procedure

1. Start the Cassandra server.
2. Open the Cassandra Query Language Shell using `cqlsh`.
3. Create the `CollegeDB` keyspace.
4. Select the keyspace using the `USE` command.
5. Create the `students` table.
6. Alter the table by adding the `email` column.
7. Insert student records.
8. Retrieve records using `SELECT`.
9. Update a student's CGPA using `UPDATE`.
10. Delete a record using `DELETE`.
11. The DDL commands `TRUNCATE`, `DROP TABLE`, and `DROP KEYSPACE` are documented for understanding but should be executed carefully.

## Result

Thus, **DDL and DML commands were created and demonstrated using Cassandra**. The experiment covered keyspace and table creation, table modification, data insertion, data retrieval, data updating, and data deletion.
