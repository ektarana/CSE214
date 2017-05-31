
/**
 * Ekta Rana
 * 111030624
 * HW#2
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */
package com.company;

/**
 * contains the user input for each order entered
 */
public class Order {
    private String order;
    private String specialInstruction;
    private double price;

    /**
     * checks to see if two orders are similar
     * it compares ONLY the order name and the price, NOT the special instructions
     *
     * @param obj takes in an object
     * @return a boolean value stating whether or not the Order objects are similar
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order) {
            Order candidate = (Order) obj;
            return ((candidate.getPrice() == price) && (candidate.getOrder().equals(order)));
        } else
            return false;
    }

    /**
     * gets the price of the Order
     *
     * @return an int price value
     */
    public double getPrice() {

        return price;
    }

    /**
     * sets the price of the Order
     *
     * @param price takes in a price to set the variable to
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * gets the specifications of the Order
     * @return a String with the special instructions
     */
    public String getSpecialInstruction() {

        return specialInstruction;
    }

    /**
     * sets the specialInstruction variable to the String of specifications the user enters
     * @param specialInstruction takes in a String of special instructions to set the variable to
     */
    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    /**
     * gets the name of the Order
     * @return a String with the order name
     */
    public String getOrder() {

        return order;
    }

    /**
     * sets the name of the order to what the user enters
     * @param order takes in a String drink name
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * constructs an Order object with the three inputs
     * @param order takes in the String drink name
     * @param specialInstruction takes in the user's special instructions
     * @param price takes in the cost of the drink
     */
    public Order(String order, String specialInstruction, double price) {
        this.order = order;
        this.specialInstruction = specialInstruction;
        this.price = price;
    }

    /**
     * takes the Order data and formats it to print easier
     * @return a formatted String with the three input variables
     */
    @Override
    public String toString() {
        return String.format("%s %20s %26.2s", order, specialInstruction, price);
    }
}