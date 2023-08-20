
/*
    Enter your query here and follow these instructions:
    1. Please append a semicolon ";" at the end of the query and enter your query in a single line to avoid error.
    2. The AS keyword causes errors, so follow this convention: "Select t.Field From table1 t" instead of "select t.Field From table1 AS t"
    3. Type your code immediately after comment. Don't leave any blank line.
*/
 /* select * from functions f order by X, Y; */
 
 -- select X, Y, ROW_NUMBER() OVER (order by X asc) AS r FROM functions f;
 
/*select f.X, f1.Y 
from 
(select X, Y, ROW_NUMBER() OVER (order by X asc) AS r FROM functions order by X) as f
join (select X, Y, ROW_NUMBER() OVER (order by X asc) AS r  from functions order by X) as f1 
on f.X = f1.Y and f.Y = f1.X and f1.r <> f.r
ORDER BY f1.Y; */

-- select X, Y, ROW_NUMBER() OVER (order by X asc) AS r FROM functions order by X;

with temp as(
select X, Y, ROW_NUMBER() OVER (order by X asc) AS r 
    FROM functions order by X
) select f.X, f.Y
from temp as f
join temp as f1 on f.X = f1.Y and f.Y = f1.X and f.r <> f1.r
Where f.X <= f.Y 
group by f.X, f.Y
order by f.X;
