package com.techelevator.pizza.model;

public class PizzaTopping {
    private int pizzaId;
    private String toppingName;

    public PizzaTopping(int pizzaId, String toppingName) {
        this.pizzaId = pizzaId;
        this.toppingName = toppingName;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }
}
