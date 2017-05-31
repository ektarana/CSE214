
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
 * Wrapper class for Order data; creates next and previous references to link to other nodes
 */
public class OrderListNode {
    private Order data;
    private OrderListNode next;
    private OrderListNode prev;

    /**
     * OrderListNode constructor which takes in an Order object and wraps it with next and previous references
     * @param initData takes in an Order
     * @throws IllegalArgumentException if the Order is null
     */
    public OrderListNode(Order initData) throws IllegalArgumentException {
        if (initData != null) {
            data = initData;
            next = null;
            prev = null;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * gets the OrderListNode linked next
     * @return returns the next OrderListNode
     */
    public OrderListNode getNext() {
        return next;
    }

    /**
     * sets the next reference to the OrderListNode next that gets passed in
     * @param next is the new OrderListNode passed in
     */
    public void setNext(OrderListNode next) {
        this.next = next;
    }

    /**
     * gets the OrderListNode linked previous
     * @return the reference to the previous node
     */
    public OrderListNode getPrev() {

        return prev;
    }

    /**
     * sets the previous reference of an OrderListNode
     * @param prev takes in data to set the previous reference to
     */
    public void setPrev(OrderListNode prev) {
        this.prev = prev;
    }

    /**
     * gets the Order data from within the node
     * @return the Order data from within the node
     */
    public Order getData() {

        return data;
    }

    /**
     * sets the Order data in the node
     * @param data
     * takes in new data to set the Order in the node to
     */
    public void setData(Order data) {
        this.data = data;
    }
}
