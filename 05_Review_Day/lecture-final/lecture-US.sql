-- Outer join

SELECT
	city_name, -- population ambiguous  --, state_name AS capital_of
FROM
	city
	LEFT OUTER JOIN state ON city.city_id = state.capital
WHERE
	state_name IS NULL;
	
SELECT
	city_name
FROM
	city
WHERE
	city_id = (SELECT capital FROM state WHERE state_abbreviation = 'OH');
	
SELECT
	city_name, population
FROM
	city
WHERE
	city_id IN (SELECT capital FROM state);