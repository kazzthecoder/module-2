package com.techelevator.dao;

import com.techelevator.model.City;

import java.util.ArrayList;
import java.util.List;

public class MemoryCityDao { //

    private List<City> cities = new ArrayList<>();


    public City createCity(City city) {
        // Iterate cities and find largest id
        // Set city.setId(largest+1)
        cities.add(city);
        return city;
    }
}
