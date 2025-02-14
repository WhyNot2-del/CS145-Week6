/*
 * Author: Christopher Waschke
 * Assignment: Lab 6 - Recursion
 * Description: Create a Pascal's Triangle based upon inputted size. Recurses through every method, not using a single loop.
 * For Extra Credit:
 *  There are 5 methods in this file that are recursive, not a single for loop used at all, everywhere it could be recursive, is recursive.
 *  I catch unexpected Scanner input, if the file is ran directly in main. See lines (118-123)
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class PascalTriangle {

    /**
     * Recursively processes the cells for a row in our triangle.
     * @param priorRow The row we're using to get the addition for our new cells
     * @param currRow The array we're adding our new cells too.
     * @param index The index of which cell we are currently working on.
     */
    private static void processCells(int[] priorRow, int[] currRow, int index) {
        // Only recurse if our index is greater than zero.
        if (index > 0) {
            processCells(priorRow, currRow, index - 1); // Recurse at the next lower index.
        }

        // If our index is zero, or we're at the end of the array, assign the value of 1 in line with Pascal's Triangle.
        if (index == 0 || index == currRow.length - 1) {
            currRow[index] = 1;
            return;
        }
        currRow[index] = priorRow[index] + priorRow[index - 1];
    }

    /**
     * Recursively Processes each row we want in our triangle.
     * @param triangle Our 2d Array that represents the triangle.
     * @param priorRow The previous row processed, used for values in the cells.
     * @param rowCount How many rows are in this triangle.
     * @return Returns itself, to recurse in the next loop as the prior row.
     */
    private static int[] processRow(int[][] triangle, int[] priorRow, int rowCount) {

        // Base case, if we're at the first row, return an array with the value of 1.
        if (rowCount == 0) {
            int[] newRow = new int[] { 1 };
            triangle[rowCount] = newRow;
            return newRow;
        }

        priorRow = processRow(triangle, priorRow, rowCount - 1); //Recurse through each row, with the last processed row now being our priorRow.
        int[] row = new int[priorRow.length + 1]; // Grow our new row by one.
        processCells(priorRow, row, row.length - 1); // Process Cells for our current row.
        triangle[rowCount] = row; // Assign the triangle our row at it's expected index.
        return row; // Return our row for processing on the next iteration.
    }

    /**
     * Prints out to the Console each cell in a row.
     * @param row the row we're printing the cells of.
     * @param index Index of the current cell in the row.
     */
    private static void printCell(int[] row, int index) {
        if (index != 0) {
            printCell(row, index - 1);
        }
        if (index == row.length - 1) {
            System.out.printf("|%d|\n", row[index]);
        } else {
            System.out.printf("|%d| ", row[index]);
        }
    }

    /**
     * Prints out tabs recursively to fit the requested tab count.
     * @param tabCount How many tabs we're printing.
     */
    private static void printTab(int tabCount) {
        if (tabCount != 0) {
            printTab(tabCount - 1);
        }
        System.out.print("  ");
    }

    /***
     * Prints the rows found within a 2d array as a triangle, using recursion.
     * @param triangle The 2d array to print as a triangle.
     * @param rowIndex The index of the row within the triangle. This should be our upper array boundary when calling. (Recursively, this will shrink.)
     * @param tabCount How many tabs we are printing with the row. (Should be 0 at initialization.)
     */
    private static void printRow(int[][] triangle, int rowIndex, int tabCount) {
        if (rowIndex != 0) {
            printRow(triangle, rowIndex - 1, tabCount + 1);
        }
        printTab(tabCount);
        printCell(triangle[rowIndex], triangle[rowIndex].length - 1);
    }

    /**
     * Creates an array based up size, and populates using recursive methods with the values of a Pascal's Triangle. Then prints to console.
     * @param rowCount The row count of our triangle.
     */
    public static void createTriangle(int rowCount) {
        int[][] triangle = new int[rowCount][];
        processRow(triangle, null, triangle.length - 1);
        printRow(triangle, triangle.length - 1, 0);

    }

    /**
     * Our main method, prompts the user for input asking for the size our the desired Triangle.
     * @param args Command Line args, unused.
     */
    public static void main(String[] args) {
        Integer size = null;
        do {
            System.out.print("How many layers do you want your triangle?> ");
            try {
                Scanner intScanner = new Scanner(System.in);
                size = intScanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please only enter numbers.");
            }
        } while (size == null);
        createTriangle(size);
    }
}