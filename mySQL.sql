/* Write your T-SQL query statement below */
select request_at day
        ,round(sum(case when status = 'cancelled_by_driver' or status = 'cancelled_by_client' then 1 else 0 end)
         / (count(id) * 1.00),2) 'Cancellation Rate'
  from Trips t 
 where request_at between '2013-10-01' and '2013-10-03'
   and client_id in (select users_id from Users where banned = 'No')
   and driver_id in  (select users_id  from Users where banned = 'No')
group by request_at


# Write your MySQL query statement below
select round(count(player_id)/(select count(distinct player_id) from Activity),2) as fraction
from Activity
where(player_id,date_sub(event_date,interval 1 day)) in(
    select player_id,min(event_date) as first_login from Activity group by player_id
)

SELECT ROUND(SUM(tiv_2016), 2) AS TIV_2016
FROM (
    SELECT tiv_2016,
           COUNT(*) OVER (PARTITION BY tiv_2015) AS cnt_tiv_2015,
           COUNT(*) OVER (PARTITION BY lat, lon) AS cnt_lat_lon
    FROM insurance
) AS subquery
WHERE cnt_tiv_2015 > 1 AND cnt_lat_lon = 1;

# Write your MySQL query statement below
SELECT 
    q1.person_name
FROM Queue q1 JOIN Queue q2 ON q1.turn >= q2.turn
GROUP BY q1.turn
HAVING SUM(q2.weight) <= 1000
ORDER BY SUM(q2.weight) DESC
LIMIT 1

or

select person_name from
(select person_name, weight, turn, sum(weight) over(order by turn) as cum_sum
from queue) as x
where cum_sum <= 1000
order by turn desc limit 1;


# Write your MySQL query statement below
select user_id, name, mail
from Users
where mail REGEXP '^[a-zA-Z][a-zAZ0-9_.-]*@leetcode[.]com'

# Write your MySQL query statement below
SELECT visits.visited_on AS visited_on, SUM(c.amount) AS amount, 
    ROUND(SUM(c.amount) / 7.0, 2) AS average_amount
FROM (
    SELECT DISTINCT visited_on 
    FROM Customer
    WHERE DATEDIFF(visited_on, (SELECT MIN(visited_on) FROM Customer)) >= 6
) visits 
LEFT JOIN Customer c 
ON DATEDIFF(visits.visited_on, c.visited_on) BETWEEN 0 and 6
GROUP BY visits.visited_on
ORDER BY visited_on;

