-- 10. The names of directors who have directed a movie over 3 hours [180 minutes] (15 rows)

SELECT DISTINCT(person_name) FROM person
JOIN movie ON director_id = person.person_id
WHERE movie.length_minutes > 180
ORDER BY person_name;