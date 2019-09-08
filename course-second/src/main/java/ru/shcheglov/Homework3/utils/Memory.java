/*******************************************************************************
 * Java Core 2.
 * Homework 3. File read/write operations
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 28, 2018
 ******************************************************************************/

package ru.shcheglov.Homework3.utils;

public class Memory {

    public static void getUsedMemoryKb() {
        long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
        System.out.println("Used memory: " + memory + " Kb");
    }
}
