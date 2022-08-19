package com.techelevator;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class CityLister {

    public static void main(String[] args) {
        // Datasource
        BasicDataSource usaDataSource = new BasicDataSource();
        usaDataSource.setUrl("jdbc:postgresql://localhost:5432/UnitedStates");
        usaDataSource.setUsername("postgres");
        usaDataSource.setPassword("postgres1");

        // JDBCTemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate(usaDataSource);
        String selCities =
                "SELECT city_name, population AS num_folks" +
                        " FROM city " +
                        " WHERE state_abbreviation = ? AND population > ? AND motto ILIKE ? " +
                        " ORDER BY city_name";

        String motto = "The ";
        SqlRowSet cities = jdbcTemplate.queryForRowSet(selCities, "OH", 100000, motto + "%");

        // SqlRowSet
        while (cities.next()) {
            String city = cities.getString("city_name");
            int population = cities.getInt("num_folks");
            System.out.println(city + " " + population);
        }
    }
}
