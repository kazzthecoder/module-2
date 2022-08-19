
SELECT title FROM movie
JOIN collection ON collection.collection_id = movie.collection_id
JOIN person ON person_id = movie.director_id
WHERE collection.collection_name = 'Star Wars Collection' AND person.person_name != 'George Lucas';
