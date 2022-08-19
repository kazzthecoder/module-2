-- 13. The directors of the movies in the "Harry Potter Collection" (4 rows)

SELECT DISTINCT person_name FROM person
JOIN movie ON movie.director_id = person.person_id
JOIN collection ON collection.collection_id = movie.collection_id
WHERE collection.collection_name = 'Harry Potter Collection';