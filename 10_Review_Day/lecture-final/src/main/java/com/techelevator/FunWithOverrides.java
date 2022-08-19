package com.techelevator;

public class FunWithOverrides {

    int howMuchFun;

    public FunWithOverrides(int howMuchFun) {
        this.howMuchFun = howMuchFun;
    }

    @Override
    public String toString() {
        return "FunWithOverrides and we're having " + howMuchFun + " fun";
    }

}
