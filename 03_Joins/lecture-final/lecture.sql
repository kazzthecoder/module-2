-- INNER JOIN

SELECT * FROM park_state;

-- Write a query to retrieve the name and state abbreviation for the 2 cities named "Columbus" in the database
SELECT city_name, state_abbreviation FROM city WHERE city_name = 'Columbus';

-- Modify the previous query to retrieve the names of the states (rather than their abbreviations).
SELECT
	city_name, state_name, c.state_abbreviation
FROM
	city AS c
	JOIN state AS s ON c.state_abbreviation = s.state_abbreviation
WHERE
	city_name = 'Columbus';

-- Write a query to retrieve the names of all the national parks with their state abbreviations.
-- (Some parks will appear more than once in the results, because they cross state boundaries.)
SELECT
	park_name, state_abbreviation
FROM
	park AS p
	JOIN park_state AS ps ON p.park_id = ps.park_id
ORDER BY
	park_name; 

	

-- The park_state table is an associative table that can be used to connect the park and state tables.
-- Modify the previous query to retrieve the names of the states rather than their abbreviations.
SELECT
	park_name, state_name, ps.state_abbreviation
FROM
	park AS p
	JOIN park_state AS ps ON p.park_id = ps.park_id
	JOIN state AS s ON ps.state_abbreviation = s.state_abbreviation;
	
SELECT
	--*
	--s.state_abbreviation, ps.*, p.*
	park_name, state_name
FROM
	state AS s
	JOIN park_state AS ps ON s.state_abbreviation = ps.state_abbreviation
	JOIN park AS p ON p.park_id = ps.park_id;

-- Modify the previous query to include the name of the state's capital city.
SELECT
	park_name, state_name, city_name AS capital
FROM
	park AS p
	JOIN park_state AS ps ON p.park_id = ps.park_id
	JOIN state AS s ON ps.state_abbreviation = s.state_abbreviation
	JOIN city AS c ON s.capital = c.city_id
ORDER BY
	park_name;
	
--SELECT * FROM city WHERE city_id = 19;
-- Modify the previous query to include the area of each park.
SELECT
	park_name, p.area, state_name, city_name AS capital_city, s.state_abbreviation
FROM
	city AS c
	JOIN state AS s ON c.city_id = s.capital
	JOIN park_state AS ps ON ps.state_abbreviation = s.state_abbreviation
	JOIN park AS p ON p.park_id = ps.park_id;

-- Write a query to retrieve the names and populations of all the cities in the Midwest census region.
SELECT
	city_name, c.population, s.population AS state_population
FROM
	state AS s
	JOIN city AS c ON c.state_abbreviation = s.state_abbreviation
WHERE
	census_region = 'Midwest';

-- Write a query to retrieve the number of cities in the city table for each state in the Midwest census region.
SELECT
	census_region, state_name, COUNT(*) AS num_cities
FROM
	state AS s
	JOIN city AS c ON s.state_abbreviation = c.state_abbreviation
-- WHERE
-- 	census_region = 'Midwest'
GROUP BY
	census_region, state_name
ORDER BY
	num_cities DESC;

-- Modify the previous query to sort the results by the number of cities in descending order.



-- LEFT OUTER JOIN

-- Write a query to retrieve the state name and the earliest date a park was established in that state (or territory) for every record in the state table that has park records associated with it.
SELECT
	state_name, MIN(date_established) AS min_date_established
FROM
	state AS s
	JOIN park_state AS ps ON s.state_abbreviation = ps.state_abbreviation
	JOIN park AS p ON p.park_id = ps.park_id
GROUP BY
 	state_name;

-- Modify the previous query so the results include entries for all the records in the state table, even if they have no park records associated with them.
SELECT
	state_name, MIN(date_established) AS min_date_established
FROM
	state AS s
	LEFT OUTER JOIN park_state AS ps ON s.state_abbreviation = ps.state_abbreviation
	LEFT OUTER JOIN park AS p ON p.park_id = ps.park_id
-- WHERE
-- 	p.park_id IS NULL;
GROUP BY
	state_name;


-- UNION

-- Write a query to retrieve all the place names in the city and state tables that begin with "W" sorted alphabetically. (Washington is the name of a city and a state--how many times does it appear in the results?)
SELECT city_name AS place_name FROM city WHERE city_name LIKE 'W%'
UNION ALL
SELECT state_name AS place_name FROM state WHERE state_name LIKE 'W%'
ORDER BY place_name;

-- Modify the previous query to include a column that indicates whether the place is a city or state.
SELECT city_name AS place_name, 'City' AS place_type FROM city WHERE city_name LIKE 'W%'
UNION
SELECT state_name AS place_name, 'State' As place_type FROM state WHERE state_name LIKE 'W%'
ORDER BY place_name;


-- MovieDB
-- After creating the MovieDB database and running the setup script, make sure it is selected in pgAdmin and confirm it is working correctly by writing queries to retrieve...

-- The names of all the movie genres
SELECT genre_name FROM genre;

-- The titles of all the Comedy movies
SELECT
	title
FROM
	movie AS m
	JOIN movie_genre AS mg ON m.movie_id = mg.movie_id
	JOIN genre AS g ON g.genre_id = mg.genre_id
WHERE
	genre_name = 'Comedy';
	
