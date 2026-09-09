-- Experiment 6: Pig Orders Data Analysis

-- Load orders dataset
orders = LOAD 'input/orders.csv'
USING PigStorage(',')
AS (
    order_id:int,
    customer_id:int,
    order_date:chararray,
    product_id:int,
    order_total:double
);

-- Display orders
DUMP orders;

-- 1. Total number of orders
order_group = GROUP orders ALL;

order_count = FOREACH order_group
              GENERATE COUNT(orders) AS total_orders;

DUMP order_count;


-- 2. Total revenue
total_revenue = FOREACH order_group
                GENERATE SUM(orders.order_total) AS revenue;

DUMP total_revenue;


-- 3. Top 5 products by total revenue
product_group = GROUP orders BY product_id;

product_revenue = FOREACH product_group
                  GENERATE group AS product_id,
                  SUM(orders.order_total) AS revenue;

sorted_products = ORDER product_revenue BY revenue DESC;

top5_products = LIMIT sorted_products 5;

DUMP top5_products;


-- 4. Number of orders by customer
customer_group = GROUP orders BY customer_id;

customer_orders = FOREACH customer_group
                  GENERATE group AS customer_id,
                  COUNT(orders) AS order_count;

DUMP customer_orders;


-- 5. Total revenue by customer
customer_revenue = FOREACH customer_group
                   GENERATE group AS customer_id,
                   SUM(orders.order_total) AS total_revenue;

DUMP customer_revenue;


-- 6. JOIN operation
customers = LOAD 'input/customers.csv'
USING PigStorage(',')
AS (
    customer_id:int,
    customer_name:chararray
);

customer_join = JOIN customer_revenue BY customer_id,
                customers BY customer_id;

result = FOREACH customer_join
         GENERATE customers::customer_id,
                  customers::customer_name,
                  customer_revenue::total_revenue;

DUMP result;
