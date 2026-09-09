# 🌡️ Experiment 4 – Hadoop MapReduce Weather Data Mining

## 🎯 Aim

To implement a Hadoop MapReduce program to find the **maximum and minimum temperature recorded for each year** from weather station readings.

---

## 📌 Problem Statement

Weather data contains temperature readings collected from multiple weather stations.

Each input record contains:

```text
StationID, Date(YYYYMMDD), Temperature
```

The objective is to process these readings and determine, for every year:

* Maximum temperature
* Minimum temperature
* Number of readings

---

## 🛠️ Technologies Used

* Java
* Hadoop 3.3.6
* MapReduce
* HDFS
* YARN
* Ubuntu 24.04 on WSL2

---

# 🔄 MapReduce Design

```text
Input Weather Data
        ↓
      Mapper
        ↓
(year, temperature)
        ↓
  Shuffle & Sort
        ↓
(year, [temperature, ...])
        ↓
     Reducer
        ↓
(year, max, min, readings)
```

---

## 🔹 Mapper

The `WeatherMapper` reads each weather record and extracts:

* The year from the date
* The temperature from the record

For example:

```text
IN001,20230716,39.5
```

is converted into:

```text
("2023", 39.5)
```

The year becomes the MapReduce key and the temperature becomes the value.

Malformed rows are skipped rather than causing the complete job to fail.

---

## 🔹 Shuffle and Sort

Hadoop automatically groups all temperature readings belonging to the same year.

For example:

```text
2023 → [18.2, 26.7, 39.5, 15.0, -8.4, ...]
```

These grouped values are passed to the Reducer.

---

## 🔹 Reducer

The `WeatherReducer` scans all temperature values for a year.

It maintains:

```text
max → maximum temperature
min → minimum temperature
count → number of readings
```

The final result is formatted as:

```text
year max=...°C min=...°C readings=...
```

---

# 📁 Source Files

```text
src/
├── WeatherMapper.java
├── WeatherReducer.java
└── WeatherDriver.java
```

### WeatherMapper.java

Responsible for:

* Reading input records
* Splitting CSV fields
* Extracting the year
* Parsing temperature
* Emitting `(year, temperature)`
* Skipping malformed data

### WeatherReducer.java

Responsible for:

* Receiving all temperatures for each year
* Calculating maximum temperature
* Calculating minimum temperature
* Counting readings
* Producing the final result

### WeatherDriver.java

Responsible for:

* Configuring the Hadoop MapReduce job
* Setting Mapper and Reducer classes
* Setting input/output data types
* Specifying input and output paths
* Starting the MapReduce job

---

# 📄 Sample Input

The weather dataset follows:

```text
StationID,Date(YYYYMMDD),TempC
```

Example records:

```text
IN001,20230105,18.2
IN001,20230312,26.7
IN001,20230716,39.5
IN002,20230108,15.0
US001,20230203,-8.4
US001,20230715,31.2
IN001,20240120,20.1
IN001,20240603,41.3
US001,20240118,-11.6
```

The supplied experiment describes 18 readings across three stations spanning 2023–2024, including negative temperatures to test the minimum-temperature logic.

---

# 🔨 Compilation

All three Java files must be placed in the same source folder.

Create the classes directory:

```bash
mkdir -p classes
```

Compile all three files:

```bash
javac -classpath $(hadoop classpath) -d classes \
WeatherMapper.java WeatherReducer.java WeatherDriver.java
```

Create the JAR:

```bash
jar -cvf weather.jar -C classes/ .
```

---

# ▶️ Run the MapReduce Job

The class containing `main()` is `WeatherDriver`.

Run:

```bash
hadoop jar weather.jar WeatherDriver weather.txt output
```

---

# 📊 Expected Output

The experiment demonstrates output in the following format:

```text
2023 max=39.5°C min=-8.4°C readings=10
2024 max=41.3°C min=-11.6°C readings=9
```

The exact output depends on the input dataset being processed.

---

# ⚠️ HDFS Output Note

When Hadoop services are running and `core-site.xml` points:

```text
fs.defaultFS = hdfs://localhost:9000
```

Hadoop jobs use HDFS paths by default rather than the local Linux filesystem.

Therefore, output may need to be read using:

```bash
hdfs dfs -cat /user/$USER/output/part-r-00000
```

To explicitly force local filesystem input/output while the cluster is running:

```bash
hadoop jar weather.jar WeatherDriver \
file://$(pwd)/weather.txt \
file://$(pwd)/output
```

---

# 🌐 Hadoop Architecture

The experiment demonstrates the following Hadoop components:

```text
Weather Data
     ↓
   HDFS
     ↓
  MapReduce
     ↓
   YARN
     ↓
Weather Statistics
```

---

# 🔍 Possible Extensions

### Per-Station Analysis

The Mapper key can be changed from:

```text
2023
```

to a composite key such as:

```text
2023_IN001
```

This allows maximum and minimum temperatures to be calculated for each station and year.

### Combiner

A custom Combiner can be introduced to reduce network traffic.

Because minimum and maximum operations are associative and commutative, they can safely be pre-combined with an appropriate custom Writable.

### Bad-Data Handling

The Mapper already demonstrates a practical data-quality technique by skipping malformed records instead of terminating the entire MapReduce job.

---

# 📚 Learning Outcomes

After completing this experiment, the following concepts are demonstrated:

* Hadoop MapReduce
* Mapper
* Reducer
* Shuffle and Sort
* CSV data processing
* Key-value transformation
* Minimum and maximum aggregation
* Data counting
* Handling malformed data
* Java compilation using Hadoop classpath
* JAR creation
* HDFS-based execution
* YARN-based MapReduce execution

---

# ✅ Result

The Weather MapReduce program successfully demonstrates how Hadoop can process weather-station records and calculate the **maximum temperature, minimum temperature, and number of readings for each year**.

The experiment also demonstrates practical handling of malformed data and the difference between local filesystem output and HDFS output.
