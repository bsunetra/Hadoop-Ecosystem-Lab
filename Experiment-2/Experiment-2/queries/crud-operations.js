// ============================================================
// EXPERIMENT 2 – MONGODB CRUD OPERATIONS
// Database: CollegeDB
// Collection: students
// ============================================================


// 1. SELECT DATABASE
// ------------------------------------------------------------
use("CollegeDB");


// 2. CREATE COLLECTION
// ------------------------------------------------------------
db.createCollection("students");


// 3. CREATE – INSERT ONE DOCUMENT
// ------------------------------------------------------------
db.students.insertOne({
    studentId: 101,
    name: "John",
    department: "CSE",
    subjects: ["BigData", "AI", "ML"],
    address: {
        city: "Chennai"
    }
});


// 4. READ – DISPLAY ALL DOCUMENTS
// ------------------------------------------------------------
db.students.find();


// 5. READ – DISPLAY ONE DOCUMENT
// ------------------------------------------------------------
db.students.findOne({
    studentId: 103
});


// 6. CREATE – INSERT MULTIPLE DOCUMENTS
// ------------------------------------------------------------
db.students.insertMany([
    {
        studentId: 102,
        name: "Priya",
        department: "AIDS",
        cgpa: 8.7,
        year: 3
    },
    {
        studentId: 103,
        name: "Rahul",
        department: "CSE",
        cgpa: 9.1,
        year: 3,
        address: {
            city: "Chennai"
        }
    },
    {
        studentId: 104,
        name: "Anu",
        department: "ECE",
        cgpa: 8.2,
        year: 2
    }
]);


// 7. FILTER – FIND STUDENTS BY DEPARTMENT
// ------------------------------------------------------------
db.students.find({
    department: "AIDS"
});


// 8. FILTER – CGPA GREATER THAN 8.5
// ------------------------------------------------------------
db.students.find({
    cgpa: {
        $gt: 8.5
    }
});


// 9. FILTER – NESTED DOCUMENT
// ------------------------------------------------------------
db.students.find({
    "address.city": "Chennai"
});


// 10. UPDATE – MODIFY CGPA
// ------------------------------------------------------------
db.students.updateOne(
    {
        studentId: 101
    },
    {
        $set: {
            cgpa: 9.0
        }
    }
);


// 11. UPDATE – REMOVE EMAIL FIELD
// ------------------------------------------------------------
db.students.updateOne(
    {
        studentId: 101
    },
    {
        $unset: {
            email: ""
        }
    }
);


// 12. DELETE – REMOVE A DOCUMENT
// ------------------------------------------------------------
db.students.deleteOne({
    studentId: 104
});


// 13. COUNT DOCUMENTS
// ------------------------------------------------------------
db.students.countDocuments();


// 14. DISPLAY ALL DATABASES
// ------------------------------------------------------------
show("dbs");


// 15. DISPLAY COLLECTIONS
// ------------------------------------------------------------
show("collections");


// ============================================================
// END OF EXPERIMENT 2
// ============================================================
