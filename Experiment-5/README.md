# Experiment 5 – Hive Table and Queries

## Aim

To create a Hive table named `orders`, load order data into it, and execute Hive queries to find the total number of orders, total revenue, top 5 products by revenue, and number of orders for each customer.

## Problem Statement

Create a Hive table `orders` with the following parameters:

* `order_id` – INT
* `customer_id` – INT
* `order_date` – STRING
* `product_id` – INT
* `order_total` – DOUBLE

Load the order dataset into Hive and perform queries to analyze the order data.

## Technologies Used

* Apache Hive
* Hadoop
* HDFS
* HiveQL
* Text/CSV dataset

## Algorithm

1. Start Hive.
2. Create the `orders` table with the required fields.
3. Load the order dataset into the Hive table.
4. Count the total number of orders.
5. Calculate the total revenue using `SUM(order_total)`.
6. Group orders by `product_id` and find the top 5 products based on total revenue.
7. Group orders by `customer_id` and count the number of orders.
8. Display the results.

## Table Structure

| Column      | Data Type | Description              |
| ----------- | --------- | ------------------------ |
| order_id    | INT       | Unique order identifier  |
| customer_id | INT       | Customer identifier      |
| order_date  | STRING    | Date of the order        |
| product_id  | INT       | Product identifier       |
| order_total | DOUBLE    | Total value of the order |

## Sample Input

The input file `orders.csv` contains:

```text
101,1,2026-01-10,501,250.50
102,2,2026-01-11,502,150.00
103,1,2026-01-12,501,300.00
104,3,2026-01-13,503,450.75
105,2,2026-01-14,504,200.25
```

Format:

```text
order_id,customer_id,order_date,product_id,order_total
```

## Hive Table Creation

```sql
CREATE TABLE orders (
    order_id INT,
    customer_id INT,
    order_date STRING,
    product_id INT,
    order_total DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE;
```

## Loading Data

```sql
LOAD DATA LOCAL INPATH '/path/orders.csv'
INTO TABLE orders;
```

Replace `/path/orders.csv` with the actual location of the dataset.

## Queries

### 1. Find the total number of orders

```sql
SELECT COUNT(*) AS total_orders
FROM orders;
```

### 2. Find the total revenue

```sql
SELECT SUM(order_total) AS total_revenue
FROM orders;
```

### 3. Find the top 5 products by total revenue

```sql
SELECT product_id, SUM(order_total) AS revenue
FROM orders
GROUP BY product_id
ORDER BY revenue DESC
LIMIT 5;
```

### 4. Find the number of orders by customer

```sql
SELECT customer_id, COUNT(*) AS order_count
FROM orders
GROUP BY customer_id
ORDER BY customer_id;
```

## Expected Output

### Total Number of Orders

```text
total_orders
5
```

### Total Revenue

```text
total_revenue
1351.50
```

### Top Products by Revenue

```text
product_id    revenue
501           550.50
503           450.75
504           200.25
502           150.00
```

### Number of Orders by Customer

```text
customer_id    order_count
1              2
2              2
3              1
```

## Result

Thus, the Hive `orders` table was successfully created and the order dataset was loaded. The required HiveQL queries were successfully executed to obtain the total number of orders, total revenue, top 5 products by revenue, and number of orders for each customer.
