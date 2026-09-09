# 🐘 Hadoop Ecosystem Lab

> A practical collection of Hadoop Ecosystem experiments covering **HDFS, YARN, MapReduce, Hadoop configuration, and Big Data processing**.

---

## 📌 About This Repository

This repository contains my practical work and experiments performed as part of the **Hadoop Ecosystem Lab**.

The experiments focus on understanding how Hadoop is configured and used in a **single-node environment**, including HDFS storage, YARN resource management, and MapReduce processing.

Each experiment is organized separately with its commands, configuration files, explanations, and results wherever applicable.

---

## 🎯 Objectives

* Understand the architecture and components of the Hadoop ecosystem.
* Configure a single-node Hadoop environment.
* Work with **HDFS** for distributed file storage.
* Understand and configure **YARN**.
* Execute **MapReduce** programs.
* Practice commonly used Hadoop and HDFS commands.
* Understand Hadoop configuration files and their purpose.
* Document practical experiments in a structured manner.

---

## 🛠️ Technologies Used

| Technology       | Purpose                                 |
| ---------------- | --------------------------------------- |
| 🐘 Apache Hadoop | Big Data processing framework           |
| ☕ OpenJDK 11     | Java runtime required by Hadoop         |
| 💾 HDFS          | Hadoop Distributed File System          |
| ⚙️ YARN          | Resource management and job scheduling  |
| 🔄 MapReduce     | Distributed data processing             |
| 🔐 OpenSSH       | SSH communication for Hadoop services   |
| 🐧 Ubuntu        | Linux environment used for Hadoop setup |

---

## 📂 Repository Structure

```text
Hadoop-Ecosystem-Lab/
│
├── README.md
│
└── Experiment-1/
    │
    ├── README.md
    ├── commands.txt
    │
    └── config/
        ├── hadoop-env.sh
        ├── core-site.xml
        ├── hdfs-site.xml
        ├── mapred-site.xml
        └── yarn-site.xml
```

---

# 🧪 Experiments

## Experiment 1 — Single-Node Hadoop Setup

### 🎯 Aim

To configure and run a **single-node Hadoop cluster** and verify the working of HDFS, YARN, and MapReduce.

### 🔧 Major Steps

The experiment covers:

1. Installing OpenJDK 11
2. Creating a Hadoop user
3. Installing OpenSSH
4. Generating SSH keys
5. Configuring passwordless SSH
6. Downloading and installing Hadoop
7. Setting Hadoop environment variables
8. Configuring `hadoop-env.sh`
9. Configuring `core-site.xml`
10. Configuring `hdfs-site.xml`
11. Configuring `mapred-site.xml`
12. Configuring `yarn-site.xml`
13. Creating HDFS storage directories
14. Formatting the NameNode
15. Starting HDFS
16. Starting YARN
17. Testing HDFS file upload
18. Running MapReduce WordCount

### 📁 Experiment Files

**[Experiment 1 README](./Experiment-1/README.md)**
Detailed description, objective, technologies, and expected result.

**[Commands](./Experiment-1/commands.txt)**
Complete list of commands used throughout the Hadoop setup and testing process.

### ⚙️ Hadoop Configuration

The following configuration files are included:

* `hadoop-env.sh` — Defines the Java environment for Hadoop.
* `core-site.xml` — Defines the default HDFS filesystem URI.
* `hdfs-site.xml` — Defines HDFS replication and storage directories.
* `mapred-site.xml` — Configures MapReduce to use YARN.
* `yarn-site.xml` — Configures the YARN NodeManager auxiliary service.

---

## 🔄 Hadoop Workflow

```text
                  Hadoop Ecosystem
                         │
          ┌──────────────┼──────────────┐
          │              │              │
         HDFS           YARN         MapReduce
          │              │              │
      Storage       Resource Mgmt    Processing
          │              │              │
          └──────────────┼──────────────┘
                         │
                  Hadoop Cluster
```

---

## 📊 Experiment 1 Verification

The experiment verifies the Hadoop environment using:

```bash
jps
```

Expected Hadoop services include:

```text
NameNode
DataNode
SecondaryNameNode
ResourceManager
NodeManager
```

HDFS functionality is tested using commands such as:

```bash
hdfs dfs -mkdir /input
hdfs dfs -put sample.txt /input
hdfs dfs -ls /input
```

MapReduce WordCount is executed using the Hadoop MapReduce examples JAR and the output is viewed using:

```bash
hdfs dfs -cat /output/part-r-00000
```

---

## ✅ Result

The single-node Hadoop cluster was successfully configured and the experiment covers:

* ✅ Java installation
* ✅ Passwordless SSH configuration
* ✅ HDFS configuration
* ✅ YARN configuration
* ✅ Hadoop daemon startup
* ✅ HDFS file upload
* ✅ MapReduce WordCount execution

---

## 📚 Key Hadoop Components

### HDFS

**Hadoop Distributed File System (HDFS)** provides storage for files within the Hadoop environment.

Important components include:

* **NameNode** — Manages HDFS metadata.
* **DataNode** — Stores the actual data blocks.
* **SecondaryNameNode** — Performs checkpoint-related operations.

### YARN

**Yet Another Resource Negotiator (YARN)** manages cluster resources and coordinates application execution.

Important components include:

* **ResourceManager**
* **NodeManager**

### MapReduce

**MapReduce** is used for processing data in Hadoop.

In this experiment, the **WordCount** example is used to demonstrate MapReduce processing.

---

## 📖 Useful HDFS Commands

```bash
hdfs dfs -ls /
hdfs dfs -mkdir /input
hdfs dfs -put sample.txt /input
hdfs dfs -cat /input/sample.txt
hdfs dfs -rm /input/sample.txt
hdfs dfs -du /
```

---

## 🚀 Future Experiments

More Hadoop ecosystem experiments will be added to this repository as they are completed.

```text
Experiment-1  → Single-Node Hadoop Setup       ✅
Experiment-2  → Coming Soon                    ⏳
Experiment-3  → Coming Soon                    ⏳
Experiment-4  → Coming Soon                    ⏳
```

---

## 👩‍💻 Author

**B. Sunetra**

AI & Data Science Student

---

## ⭐ Repository Goal

This repository serves as a practical record of my learning and implementation of **Hadoop and Big Data technologies**.

> *Learn → Implement → Verify → Document*
