package com.techelevator.dao;

import com.techelevator.pizza.model.Pizza;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcPizzaDao implements PizzaDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public JdbcPizzaDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createPizza(Pizza pizza) {
        String insertPizzaSql =
            "insert into pizza(sale_id, size_id, crust, sauce, price, additional_instructions)" +
            " values (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(insertPizzaSql, pizza.getSaleId(), pizza.getSizeId(), pizza.getCrust(),
                pizza.getSauce(), pizza.getPrice(), pizza.getAdditionalInstructions());
    }
}
