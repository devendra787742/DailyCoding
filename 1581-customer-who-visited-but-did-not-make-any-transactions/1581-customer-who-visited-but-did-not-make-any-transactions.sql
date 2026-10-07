# Write your MySQL query statement below
SELECT customer_id, count(*) as count_no_trans
FROM Visits v
LEFT JOIN Transactions t
ON v.visit_id = t.visit_id
where transaction_id is null
GROUP BY customer_id;