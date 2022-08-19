package com.techelevator.dao;

import com.techelevator.model.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao {

    private static final String CREATE_PARK =
            "INSERT INTO park(park_name, date_established, area, has_camping)" +
            " VALUES (?, ?, ?, ?) " +
            " RETURNING park_id;";

    private final JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Park getPark(int parkId) {
        String selectPark = "SELECT park_id, park_name, date_established, area, has_camping FROM park WHERE park_id = ?";
        SqlRowSet parkRowSet = jdbcTemplate.queryForRowSet(selectPark, parkId);
        if (parkRowSet.next()) {
            return mapRowToPark(parkRowSet);
//            Park park = new Park();
//
//            park.setParkId(parkRowSet.getInt("park_id"));
//            park.setParkName(parkRowSet.getString("park_name"));
//
//            if (parkRowSet.getDate("date_established") != null) {
//                park.setDateEstablished(parkRowSet.getDate("date_established").toLocalDate());
//            }
//            else {
//                park.setDateEstablished(null);
//            }
//
//            park.setDateEstablished(parkRowSet.getDate("date_established") == null
//                                        ? null
//                                        : parkRowSet.getDate("date_established").toLocalDate());
//
//            Date dateEst = parkRowSet.getDate("date_esablished");
//            park.setDateEstablished(dateEst == null ? null : dateEst.toLocalDate());
//
//            park.setArea(parkRowSet.getDouble("area"));
//            park.setHasCamping(parkRowSet.getBoolean("has_camping"));
//
//            return park;
        } else {
            return null;
        }
    }

    @Override
    public List<Park> getParksByState(String stateAbbreviation) {
        String selParksByState =
                "SELECT park.park_id, park_name, date_established, area, has_camping " +
                        " FROM " +
                        " park" +
                        " JOIN park_state ON park.park_id = park_state.park_id" +
                        " WHERE" +
                        " state_abbreviation = ?;";
        SqlRowSet parksRowSet = jdbcTemplate.queryForRowSet(selParksByState, stateAbbreviation);

        List<Park> parks = new ArrayList<>();

        while (parksRowSet.next()) {
            parks.add(mapRowToPark(parksRowSet));
        }

        return parks;
    }

    @Override
    public List<Park> searchParksByName(String name) {
        String searchPark =
                "SELECT * FROM park WHERE park_name ILIKE ? ORDER BY park_name";

        String wildcardedSearch = "%" + name + "%";

        List<Park> parks = new ArrayList<>();

        SqlRowSet parksRowSet = jdbcTemplate.queryForRowSet(searchPark, wildcardedSearch);

        while (parksRowSet.next()) {
            Park park = mapRowToPark(parksRowSet);
            parks.add(park);
        }

        return parks;
    }

    @Override
    public Park createPark(Park park) {
        String createPark =
                "INSERT INTO park(park_name, date_established, area, has_camping)" +
                        " VALUES (?, ?, ?, ?) " +
                        " RETURNING park_id;";

        int newParkId = jdbcTemplate.queryForObject(createPark, Integer.class, park.getParkName(), park.getDateEstablished(), park.getArea(), park.getHasCamping());

//        park.setParkId(newParkId);
//
//        return park;

        return getPark(newParkId);
    }

    @Override
    public void updatePark(Park park) {
        String updatePark =
                "UPDATE park SET park_name = ?, date_established = ?, area = ?, has_camping = ?" +
                        " WHERE park_id = ?;";
        int numUpdated = jdbcTemplate.update(updatePark, park.getParkName(), park.getDateEstablished(), park.getArea(), park.getHasCamping(), park.getParkId());
        if (numUpdated == 0) {
            System.err.println("Couldn't update park " + park.getParkId());
        }
    }

    @Override
    public void deletePark(int parkId) {

    }

    @Override
    public void addParkToState(int parkId, String stateAbbreviation) {

    }

    @Override
    public void removeParkFromState(int parkId, String stateAbbreviation) {

    }

    private Park mapRowToPark(SqlRowSet parkRowSet) {
        Park park = new Park();

        park.setParkId(parkRowSet.getInt("park_id"));
        park.setParkName(parkRowSet.getString("park_name"));

        if (parkRowSet.getDate("date_established") != null) {
            park.setDateEstablished(parkRowSet.getDate("date_established").toLocalDate());
        }
        else {
            park.setDateEstablished(null);
        }

        park.setArea(parkRowSet.getDouble("area"));
        park.setHasCamping(parkRowSet.getBoolean("has_camping"));

        return park;
    }
}
