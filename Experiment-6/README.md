# Experiment 6 – Pig Orders Data Analysis

## Aim

To load an orders dataset into Apache Pig and perform data analysis using Pig Latin, including grouping and joining operations.

## Problem Statement

Load the `orders` dataset into Pig and perform the following operations:

1. Find the total number of orders.
2. Find the total revenue.
3. Find the top 5 products by total revenue.
4. Find the number of orders by customer ID.
5. Group orders by customer ID and calculate the total revenue for each customer.
6. Demonstrate the use of `GROUP` and `JOIN` operators.

## Technologies Used

* Apache Pig
* Hadoop
* HDFS
* Pig Latin
* CSV dataset

## Algorithm

1. Start Pig in local or Hadoop mode.
2. Load the `orders.csv` dataset using `LOAD`.
3. Count the total number of orders.
4. Calculate total revenue using `SUM`.
5. Group orders by `product_id` and calculate product-wise revenue.
6. Sort products by revenue in descending order and select the top 5.
7. Group orders by `customer_id` and count the orders.
8. Group orders by `customer_id` and calculate total revenue for each customer.
9. Create customer information and join it with the grouped order data using `JOIN`.
10. Display the results.

## Dataset Structure

| Field       | Data Type | Description             |
| ----------- | --------- | ----------------------- |
| order_id    | INT       | Unique order identifier |
| customer_id | INT       | Customer identifier     |
| order_date  | CHARARRAY | Date of order           |
| product_id  | INT       | Product identifier      |
| order_total | DOUBLE    | Total value of order    |

## Sample Input

```text
101,1,2026-01-10,501,250.50
102,2,2026-01-11,502,150.00
103,1,2026-01-12,501,300.00
104,3,2026-01-13,503,450.75
105,2,2026-01-14,504,200.25
```

## Pig Operations

### Load the Dataset

```pig
orders = LOAD 'input/orders.csv'
USING PigStorage(',')
AS (
    order_id:int,
    customer_id:int,
    order_date:chararray,
    product_id:int,
    order_total:double
);
```

### Total Number of Orders

```pig
order_count = FOREACH (GROUP orders ALL)
              GENERATE COUNT(orders) AS total_orders;
```

### Total Revenue

```pig
total_revenue = FOREACH (GROUP orders ALL)
                GENERATE SUM(orders.order_total) AS revenue;
```

### Top 5 Products by Revenue

```pig
product_group = GROUP orders BY product_id;

product_revenue = FOREACH product_group
                  GENERATE group AS product_id,
                  SUM(orders.order_total) AS revenue;

sorted_products = ORDER product_revenue BY revenue DESC;

top5_products = LIMIT sorted_products 5;
```

### Number of Orders by Customer

```pig
customer_group = GROUP orders BY customer_id;

customer_orders = FOREACH customer_group
                  GENERATE group AS customer_id,
                  COUNT(orders) AS order_count;
```

### Total Revenue by Customer

```pig
customer_revenue = FOREACH customer_group
                   GENERATE group AS customer_id,
                   SUM(orders.order_total) AS total_revenue;
```

## JOIN Operation

A customer relation can be created and joined with the customer revenue relation:

```pig
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
```

## Expected Output

### Total Number of Orders

```text
5
```

### Total Revenue

```text
1351.50
```

### Top Products by Revenue

```text
501    550.50
503    450.75
504    200.25
502    150.00
```

### Number of Orders by Customer

```text
1    2
2    2
3    1
```

### Total Revenue by Customer

```text
1    550.50
2    350.25
3    450.75
```

## Result

Thus, the orders dataset was successfully loaded into Apache Pig and the required Pig Latin operations were performed. The `GROUP` and `JOIN` operators were used to analyze orders and calculate customer-wise revenue.
