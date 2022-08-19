package com.techelevator.reservations.dao;

import com.techelevator.reservations.model.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class JdbcHotelDao implements HotelDao {

    @Override
    public List<Hotel> list() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void create(Hotel hotel) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public Hotel get(int id) {
        throw new RuntimeException("Not implemented yet");
    }
}
