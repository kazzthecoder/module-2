package com.techelevator.pizza;

import com.techelevator.dao.JdbcPizzaDao;
import com.techelevator.dao.JdbcPizzaToppingDao;
import com.techelevator.dao.PizzaDao;
import com.techelevator.dao.PizzaToppingDao;
import com.techelevator.pizza.model.Pizza;
import com.techelevator.pizza.model.PizzaTopping;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class PizzaApp {

    public static void main(String[] args) {

        BasicDataSource pizzaDataSource = new BasicDataSource();
        pizzaDataSource.setUrl("jdbc:postgresql://localhost:5432/PizzaShop");
        pizzaDataSource.setUsername("postgres");
        pizzaDataSource.setPassword("postgres1");

        // Need to add RETURNING clause when creating pizza
        PizzaDao pizzaDao = new JdbcPizzaDao(pizzaDataSource);
        PizzaToppingDao pizzaToppingDao = new JdbcPizzaToppingDao(pizzaDataSource);

        Pizza pizza = new Pizza(0, null, "L", "Pan",
                "Normal", null, "asap. i'm starvin'");

        pizzaDao.createPizza(pizza);

        PizzaTopping pizzaTopping = new PizzaTopping(pizza.)

        pizzaToppingDao.createPizzaTopping();


    }
}
