/**
 * Ekta Rana
 * 111030624
 * HW#3
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */
package com.company;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * driver class
 */
public class CoffeeOrderManager {
    public static Order clipboard = null;

    /**
     * simply prints the initial menu options to the console
     */
    public static void printMenu() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Menu:");
        System.out.println("O) Order");
        System.out.println("P) Print Order Lists");
        System.out.println("C) Cursor Options");
        System.out.println("Q) Quit");
        System.out.println("Select an option: ");
    }

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        OrderList[] baristas = new OrderList[]{new OrderList(), new OrderList()};

        Scanner input = new Scanner(System.in);
        String selection;
        boolean run = true;
        System.out.println("Welcome to Star Duck Coffee, the number one coffee shop for flannel enthusiasts.");

        while (run) {
            printMenu();
            selection = input.nextLine().toUpperCase();

            if (selection.equals("O")) {
                try {
                    System.out.print("Please enter drink name: ");
                    String drinkName = input.nextLine();

                    System.out.print("Please enter special requests: ");
                    String specialInstruction = input.nextLine();

                    System.out.print("Please enter the price: ");
                    double price = input.nextDouble();
                    input.nextLine();

                    Order order = new Order(drinkName, specialInstruction, price);
                    System.out.println();

                    System.out.print("Please select Barista (1 or 2): ");
                    int baristaSelection = input.nextInt() - 1;
                    input.nextLine();
                    if (baristaSelection == 1 || baristaSelection == 0) {
                        System.out.println("Where should the order be added? Options: F - Front of List, B - Back of List, A - After Cursor, S - After Similar Order (default: end of list)");
                        System.out.print("Please select an option: ");
                        selection = input.nextLine().toUpperCase();

                        if (selection.equals("F")) {
                            baristas[baristaSelection].appendToHead(order);
                        } else if (selection.equals("B")) {
                            baristas[baristaSelection].appendToTail(order);
                        } else if (selection.equals("A")) {
                            baristas[baristaSelection].insertAfterCursor(order);
                        } else if (selection.equals("S")) {
                            baristas[baristaSelection].insertAfterSimilar(order);
                        } else {
                            System.out.println("Invalid input. ");
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid. Try again.");
                }

            } else if (selection.equals("P")) {
                System.out.println("Barista 1");
                baristas[0].printList();
                System.out.println("Barista 2");
                baristas[1].printList();
            } else if (selection.equals("C")) {
                try {
                    System.out.println("Please select a cursor (1 or 2): ");
                    int cursorSelection = input.nextInt() - 1;
                    input.nextLine();


                    if (cursorSelection == 1 || cursorSelection == 0) {
                        System.out.println("Cursor options: F - Forward, B - Backward, H-To Head, T - To Tail, R - Remove, C - Cut, P - Paste.");
                        selection = input.nextLine().toUpperCase();
                        try {
                            if (selection.equals("F")) {
                                baristas[cursorSelection].cursorForward();
                            } else if (selection.equals("B")) {
                                baristas[cursorSelection].cursorBackward();
                            } else if (selection.equals("H")) {
                                baristas[cursorSelection].resetCursorToHead();
                            } else if (selection.equals("T")) {
                                baristas[cursorSelection].resetCursorToTail();
                            } else if (selection.equals("R")) {
                                Order remove = baristas[cursorSelection].removeCursor();
                                System.out.println(remove.getOrder() + " has been removed");
                            } else if (selection.equals("C")) {
                                clipboard = baristas[cursorSelection].cut();
                                System.out.println(clipboard.getOrder() + " has been copied to the clipboard");
                            } else if (selection.equals("P")) {
                                baristas[cursorSelection].paste(clipboard);
                            } else {
                                System.out.println("Invalid choice, try again.");
                            }
                        } catch (EndOfListException e) {
                            System.out.println("This is an empty list, you can't do that.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error, try again.");
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("You entered incorrect input. Try again.");
                }
            } else if (selection.equals("Q")) {
                System.out.println("Only traitors go to Dunkin, see you soon!");
                run = false;
            }else {
                System.out.println("Invalid input.");
            }
        }//closes while
    }
}