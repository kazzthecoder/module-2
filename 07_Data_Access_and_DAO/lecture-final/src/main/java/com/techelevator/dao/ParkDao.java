package com.techelevator.dao;

import com.techelevator.model.Park;

import java.util.List;

public interface ParkDao {

    Park getPark(int parkId);

    List<Park> getParksByState(String stateAbbreviation);

    List<Park> searchParksByName(String nameToSearchFor);

    Park createPark(Park park);

    void updatePark(Park park);

    void deletePark(int parkId);

    void addParkToState(int parkId, String stateAbbreviation);

    void removeParkFromState(int parkId, String stateAbbreviation);
}
