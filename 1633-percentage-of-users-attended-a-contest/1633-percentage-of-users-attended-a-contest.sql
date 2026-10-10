# Write your MySQL query statement below
SELECT contest_id,
ROUND(COUNT(user_id)*100.0/(SELECT COUNT(*) FROM Users), 2) as percentage
FROM Register r
GROUP BY contest_id
ORDER BY percentage DESC, r.contest_id ASC; 
