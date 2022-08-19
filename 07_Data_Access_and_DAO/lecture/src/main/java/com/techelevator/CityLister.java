package com.techelevator;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class CityLister {

    public static void main(String[] args) {

        // Datasource
        BasicDataSource usaDataSource = new BasicDataSource();
        usaDataSource.setUrl("jdbc:postgresql://localhost:5432/UnitedStates");
        usaDataSource.setUsername("postgres");
        usaDataSource.setPassword("postgres1");

        //jdbcTemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate(usaDataSource);

    }
}
