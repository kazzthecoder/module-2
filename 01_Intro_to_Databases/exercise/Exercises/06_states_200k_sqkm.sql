-- 6. The sum of the population of all states in the "South" census region. Name the column 'south_population'.
-- Expected answer is around 125,000,000
-- (1 row)
SELECT state_name, state_abbreviation, population, area FROM state WHERE area > 200000;