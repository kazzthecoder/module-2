package com.techelevator.pizza.model;

import java.math.BigDecimal;

public class Pizza {

    private int pizzaId;
    private Integer saleId;
    private String sizeId;
    private String crust;
    private String sauce;
    private BigDecimal price;
    private String additionalInstructions;

    public Pizza(int pizzaId, Integer saleId, String sizeId, String crust, String sauce, BigDecimal price, String additionalInstructions) {
        this.pizzaId = pizzaId;
        this.saleId = saleId;
        this.sizeId = sizeId;
        this.crust = crust;
        this.sauce = sauce;
        this.price = price;
        this.additionalInstructions = additionalInstructions;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAdditionalInstructions() {
        return additionalInstructions;
    }

    public void setAdditionalInstructions(String additionalInstructions) {
        this.additionalInstructions = additionalInstructions;
    }
}
