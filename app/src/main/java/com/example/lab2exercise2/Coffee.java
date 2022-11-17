package com.example.lab2exercise2;

public class Coffee {
    //Private Data Members
    private boolean hasCream;
    private boolean hasChocolate;
    private int quantity;

    private double cost;

    //Constructor
    public Coffee() {
        hasCream = false;
        hasChocolate = false;
        quantity = 1;
        cost = 4.00;
    }

    //Setters
    public void setHasCream(boolean value) {
        hasCream = value;

        if (hasCream)
            cost += 0.50;
        else
            cost -= 0.50;
    }

    public void setHasChocolate(boolean value) {
        hasChocolate = value;

        if (hasChocolate)
            cost += 1.00;
        else
            cost -= 1.00;
    }

    //Increase and decrease for quantity
    public void increaseQuantity() {
        quantity++;

        cost += 4.00;
    }

    public void decreaseQuantity()
    {
        if (quantity > 1) {
            quantity--;
            cost -= 4.00;
        }
    }

    //Getters
    public boolean getHasChocolate() {
        return hasChocolate;
    }

    public boolean getHasCream()
    {
        return hasCream;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getCost() {
        return cost;
    }
}
