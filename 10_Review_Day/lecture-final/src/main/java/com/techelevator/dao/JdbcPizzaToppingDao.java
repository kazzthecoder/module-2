package com.techelevator.dao;

import com.techelevator.pizza.model.PizzaTopping;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcPizzaToppingDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public JdbcPizzaToppingDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createPizzaTopping(PizzaTopping pizzatopping) {
        String pizzaToppingSql = "INSERT INTO pizza_topping(pizza_id, topping_name)" +
                " VALUES(?, ?);";
        jdbcTemplate.update(pizzaToppingSql, pizzatopping.getPizzaId(), pizzatopping.getToppingName());
    }
}
