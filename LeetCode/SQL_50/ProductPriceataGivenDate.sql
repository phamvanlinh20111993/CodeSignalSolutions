/**
 * url: https://leetcode.com/problems/product-price-at-a-given-date/description/
 * 
 * Table: Products

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| new_price     | int     |
| change_date   | date    |
+---------------+---------+
(product_id, change_date) is the primary key (combination of columns with unique values) of this table.
Each row of this table indicates that the price of some product was changed to a new price at some date.
 

Write a solution to find the prices of all products on 2019-08-16. Assume the price of all products before any change is 10.

Return the result table in any order.

The result format is in the following example.

 

Example 1:

Input: 
Products table:
+------------+-----------+-------------+
| product_id | new_price | change_date |
+------------+-----------+-------------+
| 1          | 20        | 2019-08-14  |
| 2          | 50        | 2019-08-14  |
| 1          | 30        | 2019-08-15  |
| 1          | 35        | 2019-08-16  |
| 2          | 65        | 2019-08-17  |
| 3          | 20        | 2019-08-18  |
+------------+-----------+-------------+
Output: 
+------------+-------+
| product_id | price |
+------------+-------+
| 2          | 50    |
| 1          | 35    |
| 3          | 10    |
+------------+-------+
**/

# Write your MySQL query statement below
/*
select product_id, change_date from Products  where change_date = '2019-08-16'
union all 
select product_id, min(change_date) from Products where change_date <> '2019-08-16' group by product_id
union all 
select product_id, max(change_date) from Products where change_date <> '2019-08-16' group by product_id
*/
/*
select p.product_id ,
case
    when t.dif = 0 then p.new_price 
    when 
    end price 
 from 
Products p
join 
(
    select product_id, min(DATESUB('2019-08-16', change_date)) dif
    from Products
    group by product_id
) t on t.dif =  min(DATESUB('2019-08-16', p.change_date) */


select p.product_id , 
    case 
        when t.change_date <= '2019-08-16' then p.new_price 
        else 10 
    end price 
from Products p
join (
    select 
        product_id, 
        IFNULL((select max(change_date) from Products where p.product_id = product_id and change_date <= '2019-08-16'),
               (select min(change_date) from Products where p.product_id = product_id and change_date > '2019-08-16'))
        change_date
    from Products p
    group by product_id
) t on t.change_date = p.change_date and p.product_id = t.product_id
