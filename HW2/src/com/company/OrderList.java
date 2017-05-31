
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
 * is the doubly linked list that ensures all nodes are connected
 */
public class OrderList {

    private OrderListNode head;
    private OrderListNode tail;
    private OrderListNode cursor;
    private int countOrders = 0;

    /**
     * constructs a new OrderList object, setting head, tail and cursor to null
     */
    public OrderList() {
        head = null;
        tail = null;
        cursor = null;
        countOrders = 0;
    }

    /**
     * @return the cursor reference
     */
    public OrderListNode getCursor() {
        return cursor;
    }

    /**
     * @param cursor takes in a OrderListNode to set or reassign cursor to
     */
    public void setCursor(OrderListNode cursor) {
        this.cursor = cursor;
    }

    /**
     * @return the tail reference
     */
    public OrderListNode getTail() {

        return tail;
    }

    /**
     * @param tail takes in a OrderListNode to set or reassign tail to
     */
    public void setTail(OrderListNode tail) {
        this.tail = tail;
    }

    /**
     * @return the head reference
     */
    public OrderListNode getHead() {

        return head;
    }

    /**
     * @param head takes in a OrderListNode to set or reassign head to
     */
    public void setHead(OrderListNode head) {
        this.head = head;
    }

    /**
     * @return the value of countOrders which has been keeping track of how many orders are in the list
     * runs in O(1) time
     */

    public int numOrders() {
        return countOrders;
    }

    /**
     * @return Gets the reference to the Order wrapped by the OrderListNode currently referenced by cursor.
     * Returns the reference by the Order wrapped by the OrderListNode currently referenced by cursor.
     * If the cursor is null, then this method should return null as well (i.e.the cursor does not reference an Order).
     */
    public Order getCursorOrder() {
        if (cursor == null) {
            return null;
        }
        return cursor.getData();
    }

    /**
     * Returns the cursor to the start of the list.
     * <dt><b>Postconditions</b></dd>
     * If head is not null, the cursor now references the first OrderListNode in this list.
     * If head is null, the cursor is set to null as well (there are no Orders in this list).
     */
    public void resetCursorToHead() {
        if (head != null) {
            if (cursor == head) {
                System.out.println("cursor already at head");
            } else {
                cursor = head;
                System.out.println("cursor reset to head");
            }

        } else {
            cursor = null;
            System.out.println("cursor set to null");
        }

    }

    /**
     * Sets the cursor to the end of the list.
     * <dt><b>Postconditions</b></dd>
     * If tail is not null, the cursor now references the last OrderListNode in this list.
     * If tail is null, the cursor is set to null as well.
     */
    public void resetCursorToTail() {
        if (tail != null) {
            if (cursor == tail) {
                System.out.println("cursor already at tail");
            } else {
                cursor = tail;
                System.out.println("cursor reset to tail");
            }
        } else {
            cursor = null;
            System.out.println("cursor set to null");
        }
    }

    /**
     * Moves the cursor to select the next OrderListNode in the list.
     * <dt><b>Preconditions</b></dd>
     * the cursor cannot be at the tail
     * <dt><b>Postconditions</b></dd>
     * the cursor has moved one node forward in the linked list
     *
     * @throws EndOfListException if cursor is at the tail of the list, or if cursor and tail are both null.
     */
    public void cursorForward() throws EndOfListException {
        if (cursor == tail) {
            System.out.println("cursor already at tail");
            throw new EndOfListException();
        }
        if (cursor == null && tail == null) {
            System.out.println("the tail is null");
            throw new EndOfListException();
        }
        cursor = cursor.getNext();
        System.out.println("cursor moved forward");
    }

    /**
     * Moves the cursor to select the previous OrderListNode in the list.
     * <dt><b>Preconditions</b></dd>
     * the cursor cannot be at the head
     * <dt><b>Postconditions</b></dd>
     * the cursor has moved one node backward in the linked list
     *
     * @throws EndOfListException if cursor is at the head of the list, or if cursor and head are both null.
     */
    public void cursorBackward() throws EndOfListException {
        if (cursor == head) {
            System.out.println("cursor already at head");
            throw new EndOfListException();
        }
        if (cursor == null && head == null) {
            System.out.println("list is empty");
            throw new EndOfListException();
        }
        cursor = cursor.getPrev();
        System.out.println("cursor moved backward");
    }

    /**
     * cuts the node at cursor, saves the data to cutNode and then removes it from the list
     * <dt><b>Preconditions</b></dd>
     * the cursor cannot null
     * <dt><b>Postconditions</b></dd>
     * the OrderListNode referenced by cursor has been removed from the list
     * All other OrderListNodes in the list exist in the same order as before.
     * The cursor now references the previous OrderListNode (or the head, if the cursor previously referenced the head of the list).
     *
     * @return returns an Order object that is saved in cutNode
     * @throws EndOfListException if cursor is null
     */
    public Order cut() throws EndOfListException {
        //cuts and saves the Order data at the cursor
        if (cursor == null) {
            System.out.println("nothing here to cut!");
            throw new EndOfListException();
        }
        Order cutNode = removeCursor();
        System.out.println();
        countOrders--;
        return cutNode;
    }

    /**
     * Removes the OrderListNode referenced by cursor and returns the Order inside.
     * <dt><b>Preconditions</b></dd>
     * cursor is not null.
     * <dt><b>Postconditions</b></dd>
     * The OrderListNode referenced by cursor has been removed from the list.
     * All other OrderListNodes in the list exist in the same order as before.
     * The cursor now references the previous OrderListNode (or the head, if the cursor previously referenced the head of the list).
     *
     * @return removedNodeData, which is the Order data saved in the OrderListNode that is being removed
     * @throws EndOfListException if cursor is null
     */

    public Order removeCursor() throws EndOfListException {
        if (cursor == null) {
            System.out.println("this list is empty!");
            throw new EndOfListException();
        }

        Order removedNodeData;

        if (cursor == head) {
            removedNodeData = head.getData();
            head = cursor.getNext();
            cursor = head;
            countOrders--;

        } else if (cursor == tail) {
            removedNodeData = tail.getData();
            tail = cursor.getPrev();
            cursor = tail;
            countOrders--;

        } else {
            removedNodeData = cursor.getData();
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getPrev();
            countOrders--;
        }
        return removedNodeData;
    }

    /**
     * creates an OrderListnode from the Order data in clipboard and and adds it to the list
     * <dt><b>Preconditions</b></dd>
     * clipboard is not null.
     * <dt><b>Postconditions</b></dd>
     * The Order referenced by clipboard wrapped in an OrderListNode and added to the list.
     * If there cursor is not null, node added after cursor.
     * If the cursor is null and the list is empty then the new node will be the head, tail and cursor.
     *
     * @param clipboard this is the Order object that will be wrapped by OrderListNode
     * @throws IllegalArgumentException if clipboard is null
     */
    public void paste(Order clipboard) throws IllegalArgumentException {
        //pastes the OrderLN saved to the clipboard
        if (clipboard == null) {
            System.out.println("nothing to paste");
            throw new IllegalArgumentException();
        }
        OrderListNode pasteNode = new OrderListNode(clipboard);

        if (cursor == null) { //case where empty list
            head = pasteNode;
            tail = pasteNode;
            cursor = pasteNode;
        } else if (cursor == tail) { //if cursor at tail
            System.out.println(pasteNode.getData().getOrder() + " pasted after cursor");
            tail.setNext(pasteNode);
            pasteNode.setPrev(tail);
            tail = tail.getNext();
            cursor = cursor.getNext();
            countOrders++;

        } else { //any node in the middle of a list
            System.out.println(pasteNode.getData().getOrder() + " pasted after cursor");
            pasteNode.setNext(cursor.getNext());
            pasteNode.setPrev(cursor);
            cursor.getNext().setPrev(pasteNode);
            cursor.setNext(pasteNode);
            cursor = cursor.getNext();
            countOrders++;
        }
    }

    /**
     * Inserts the indicated Order after the a similar order in the list.
     * <dt><b>Preconditions</b></dd>
     * newOrder is not null
     * <dt><b>Postconditions</b></dd>
     * newOrder has been wrapped in a new OrderListNode object.
     * If the order name and price match, an order already in the list is considered a smiliar order.
     * The newOLN will be added after that existing order.
     * If there is no similar order, newOLN is simply appended to the tail.
     * If the list is empty, the new order is the head, tail and cursor.
     *
     * @param newOrder takes in Order data to wrap in an OrderListNode and add to the list
     * @throws IllegalArgumentException if newOrder is null.
     */
    public void insertAfterSimilar(Order newOrder) throws IllegalArgumentException {
        if (newOrder == null) { //if the
            throw new IllegalArgumentException();
        }
        OrderListNode newOLN = new OrderListNode(newOrder);
        if (head == null) { //as in the list is empty
            appendToHead(newOrder);
        } else {
            OrderListNode pointer = head;

            while (pointer != null) {
                if (pointer == tail) {
                    appendToTail(newOrder);
                } else if (newOLN.equals(pointer)) {
                    newOLN.setNext(pointer.getNext());
                    newOLN.setPrev(pointer);
                    pointer.getNext().setPrev(newOLN);
                    pointer.setNext(newOLN);
                    countOrders++;
                }
                pointer = pointer.getNext();
            }
        }
    }

    /**
     * Inserts the indicated Order after the cursor.
     * <dt><b>Preconditions</b></dd>
     * newOrder is not null
     * <dt><b>Postconditions</b></dd>
     * newOrder has been wrapped in a new OrderListNode object.
     * If cursor was previously not null, the newly created OrderListNode has been inserted into the list after the cursor.
     * If cursor was previously null, the newly created OrderListNode has been set as the new head of the list (as well as the tail).
     * The cursor now references the newly created OrderListNode.
     *
     * @param newOrder takes in Order data to wrap in an OrderListNode and add to the list
     * @throws IllegalArgumentException if newOrder is null.
     */
    public void insertAfterCursor(Order newOrder) throws IllegalArgumentException {
        if (newOrder == null) {
            throw new IllegalArgumentException();
        }
        OrderListNode newOLN = new OrderListNode(newOrder);

        if (cursor == null) {
            head = newOLN;
            tail = newOLN;
            cursor = newOLN;
        }
        newOLN.setNext(cursor.getNext());
        newOLN.setPrev(cursor);
        cursor.getNext().setPrev(newOLN);
        cursor.setNext(newOLN);
        cursor = cursor.getNext();
        countOrders++;
    }

    /**
     * Inserts the indicated Order after the tail of the list.
     * <dt><b>Preconditions</b></dd>
     * newOrder is not null
     * <dt><b>Postconditions</b></dd>
     * newOrder has been wrapped in a new OrderListNode object.
     * If tail was previously not null, the newly created OrderListNode has been inserted into the list after the tail.
     * If tail was previously null, the newly created OrderListNode has been set as the new head of the list (as well as the tail and the cursor).
     * The tail now references the newly created OrderListNode.
     * <p>
     * Note:
     * This insertion method does not affect the cursor, unless the list was previously empty. In that case, head, tail, and cursor should all reference the new OrderListNode.
     *
     * @param newOrder
     * @throws IllegalArgumentException if newOrder is null.
     */
    public void appendToTail(Order newOrder) throws IllegalArgumentException {
        if (newOrder == null) {
            throw new IllegalArgumentException();
        }
        OrderListNode newOLN = new OrderListNode(newOrder);
        if (tail == null) {
            head = newOLN;
            tail = newOLN;
            cursor = newOLN;
            countOrders++;

        } else {
            tail.setNext(newOLN);
            newOLN.setPrev(tail);
            tail = tail.getNext();
            countOrders++;

        }
    }

    /**
     * Inserts the indicated Order after the head of the list.
     * <dt><b>Preconditions</b></dd>
     * newOrder is not null
     * <dt><b>Postconditions</b></dd>
     * newOrder has been wrapped in a new OrderListNode object.
     * If the head was null, the new node is now the head, tail and cursor.
     * If head was not null, the new order is now the head, which is linked to the node was that was the head
     *
     * @param newOrder takes in an Order object to wrap and add
     * @throws IllegalArgumentException [if the new Order parameter is null, then the exception is thrown since we can't have a null node or null reference in the list]
     */
    public void appendToHead(Order newOrder) throws IllegalArgumentException {
        if (newOrder == null) {
            throw new IllegalArgumentException();
        }
        OrderListNode newOLN = new OrderListNode(newOrder);

        if (head == null) {
            head = newOLN;
            tail = newOLN;
            cursor = newOLN;
            countOrders++;
        } else {
            newOLN.setNext(head);
            head.setPrev(newOLN);
            head = head.getPrev();
            countOrders++;
        }
    }

    /**
     * prints the table of order data to the console
     */
    public void printList() {
        System.out.printf("%s%26s%19s", "Order Name", "Special Instrutions", "Price\n");
        System.out.println("-------------------------------------------------------------------------------------");
        OrderListNode pointer = head;
        if (pointer != null) {
            while (pointer != null) {
                if (pointer == cursor) {
                    System.out.print("->");
                }
                System.out.println(pointer.getData().toString());
                pointer = pointer.getNext();
            }
        } else {
            System.out.println("[empty]");
        }
        System.out.println();
    }
}