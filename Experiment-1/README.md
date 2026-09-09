# Experiment 1 – Hadoop Ecosystem Lab

## Aim

To configure and run a single-node Hadoop cluster and verify HDFS, YARN, and MapReduce functionality.

## Technologies Used

* Ubuntu
* Java / OpenJDK 11
* Apache Hadoop 3.3.6
* HDFS
* YARN
* MapReduce
* OpenSSH

## Experiment Overview

This experiment covers:

1. Installing Java
2. Creating a Hadoop user
3. Installing OpenSSH
4. Configuring passwordless SSH
5. Downloading and installing Hadoop
6. Setting Hadoop environment variables
7. Configuring `hadoop-env.sh`
8. Configuring `core-site.xml`
9. Configuring `hdfs-site.xml`
10. Configuring `mapred-site.xml`
11. Configuring `yarn-site.xml`
12. Creating HDFS storage directories
13. Formatting the NameNode
14. Starting HDFS
15. Starting YARN
16. Testing HDFS
17. Running MapReduce WordCount

## Expected Result

The single-node Hadoop cluster should successfully run HDFS and YARN services, allow files to be uploaded to HDFS, and execute the MapReduce WordCount program.

## Verification

The expected Hadoop services are:

* NameNode
* DataNode
* SecondaryNameNode
* ResourceManager
* NodeManager

The experiment also verifies HDFS file upload and MapReduce WordCount execution.
