# 🗄️ Experiment 2 – MongoDB on WSL

## 🎯 Aim

To install and configure MongoDB Community Server on Ubuntu using WSL and perform **CRUD (Create, Read, Update, Delete) operations** using MongoDB.

---

## 📌 Case Study

**Database:** `CollegeDB`

**Collection:** `students`

The experiment uses student records to demonstrate MongoDB database and CRUD operations.

---

## 🛠️ Technologies Used

* Windows Subsystem for Linux (WSL)
* Ubuntu 22.04
* MongoDB Community Server 7.0
* MongoDB Shell (`mongosh`)
* MongoDB Query Language

---

## 📚 Experiment Overview

This experiment covers:

1. Setting up WSL and Ubuntu
2. Installing prerequisites
3. Adding the MongoDB signing key
4. Adding the MongoDB repository
5. Installing MongoDB Community Server
6. Starting and verifying the MongoDB service
7. Opening `mongosh`
8. Creating the `CollegeDB` database
9. Creating a collection
10. Inserting documents
11. Reading documents
12. Filtering documents
13. Updating documents
14. Deleting documents
15. Counting documents
16. Listing databases and collections

---

## 🔄 CRUD Operations

### Create

Documents are inserted using:

```javascript
db.students.insertOne({...})
db.students.insertMany([...])
```

### Read

Documents can be retrieved using:

```javascript
db.students.find()
db.students.findOne({...})
```

### Update

Documents can be modified using:

```javascript
db.students.updateOne(
    { studentId: 101 },
    { $set: { cgpa: 9.0 } }
)
```

Fields can also be removed using `$unset`.

### Delete

Documents can be deleted using:

```javascript
db.students.deleteOne(
    { studentId: 104 }
)
```

---

## 🔍 Filtering Operations

The experiment demonstrates:

* Exact-match filtering
* `$gt` comparison operator
* Nested document filtering using dot notation
* `.pretty()` for formatted output

Example:

```javascript
db.students.find({ department: "AIDS" }).pretty()

db.students.find({ cgpa: { $gt: 8.5 } }).pretty()

db.students.find({ "address.city": "Chennai" }).pretty()
```

---

## 🧩 MongoDB Concepts Demonstrated

### Schema Flexibility

MongoDB documents do not require a fixed structure. Different student documents can contain different fields.

### Arrays

Subjects can be stored directly inside a document:

```javascript
Subjects: ["BigData", "AI", "ML"]
```

### Embedded Documents

Related information can be stored as a nested document:

```javascript
address: {
    city: "Chennai",
    state: "...",
    pincode: "..."
}
```

---

## 🔧 Useful MongoDB Commands

```javascript
show dbs
show collections

db.students.find()

db.students.countDocuments()
```

---

## ⚠️ Important Note

MongoDB collection names are **case-sensitive**.

The lab material contains both `Students` and `students`. These represent different collection names in MongoDB.

The CRUD operations in this experiment use the `students` collection consistently.

---

## ✅ Result

MongoDB was configured on Ubuntu using WSL, and the `CollegeDB` database was used to demonstrate document creation, retrieval, filtering, updating, deletion, counting, and collection/database listing.

---

## 📖 Learning Outcome

After completing this experiment, the following MongoDB concepts are demonstrated:

* MongoDB installation on WSL
* MongoDB server and shell
* Database and collection handling
* Document-oriented data storage
* CRUD operations
* Query filtering
* Comparison operators
* Embedded documents
* Arrays
* Schema flexibility
* Document updates and deletion
