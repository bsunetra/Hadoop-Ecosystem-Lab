-- Experiment 5: Hive Table and Queries

-- Create orders table
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

-- Load orders dataset
LOAD DATA LOCAL INPATH 'input/orders.csv'
INTO TABLE orders;

-- Display all orders
SELECT * FROM orders;

-- 1. Total number of orders
SELECT COUNT(*) AS total_orders
FROM orders;

-- 2. Total revenue
SELECT SUM(order_total) AS total_revenue
FROM orders;

-- 3. Top 5 products by total revenue
SELECT product_id,
       SUM(order_total) AS revenue
FROM orders
GROUP BY product_id
ORDER BY revenue DESC
LIMIT 5;

-- 4. Number of orders by customer
SELECT customer_id,
       COUNT(*) AS order_count
FROM orders
GROUP BY customer_id
ORDER BY customer_id;
