package com.techelevator.hotels.services;

import com.techelevator.hotels.model.City;
import com.techelevator.hotels.model.Hotel;
import com.techelevator.hotels.model.Review;
import com.techelevator.hotels.model.StarWarsMovie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HotelService {

    private static final String API_BASE_URL = "http://localhost:3000/";
    private final RestTemplate restTemplate = new RestTemplate();

    public Hotel[] listHotels() {
        String endpointUrl = API_BASE_URL + "hotels";
        Hotel[] hotels = restTemplate.getForObject(endpointUrl, Hotel[].class);
        return hotels;
    }

    public Review[] listReviews() {
        String endpointUrl = API_BASE_URL + "reviews";
        ResponseEntity<Review[]> response = restTemplate.getForEntity(endpointUrl, Review[].class);

        System.out.println(response.getStatusCode());

        return response.getBody();
    }

    public Hotel getHotelById(int id) {
        String endpointUrl = API_BASE_URL + "hotels/" + id;
        return restTemplate.getForObject(endpointUrl, Hotel.class);
    }

    public Review[] getReviewsByHotelId(int hotelID) {
        return null;
    }

    public Hotel[] getHotelsByStarRating(int stars) {
        return null;
    }

    public StarWarsMovie getWithCustomQuery(){
        return restTemplate.getForObject("https://swapi.dev/api/films/1", StarWarsMovie.class);
    }

}
