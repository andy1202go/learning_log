# This is our problematic query as it uses OFFSET and LIMIT.
SELECT *
FROM `docs`
LIMIT 10 OFFSET 85000;

# This is our solution as it knows exactly where from should it start looking for.
SELECT *
FROM `docs`
WHERE id > 85000
LIMIT 10;