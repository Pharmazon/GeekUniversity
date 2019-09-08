package ru.shcheglov.Homework2; /**
 * Java 1. Homework2
 *
 * @author Alexey Shcheglov
 * @version dated Nov 27, 2017
 * @link https://github.com/Pharmazon
 */

import java.util.Arrays; 
 
public class Main {

    public static void main(String[] args) {

        //task 1
        invertArray(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0});
        
        //task 2
        arrayFill(new int[8]);

        //task 3
        multiplyElementTo2(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1});
        
        //task 4
        arrayDiagonal(new int[15][15]);
        
        //task 5
        findMinMaxElement(new int[]{-4, 56, 3, 6, 11, 4, 5, -25, 47, 82, -15, 7});

        //task 6
        taskDivider(6);
        int[] array0 = {1, 1, 1, 2, 1};
        int[] array1 = {1, 2, 3, 1, 5};
        int[] array2 = {2, 1, 1, 2, 1};
        int[] array3 = {10, 10};
        System.out.println("The result for array " + Arrays.toString(array0) + " is " + checkBalance(array0));
        System.out.println("The result for array " + Arrays.toString(array1) + " is " + checkBalance(array1));
        System.out.println("The result for array " + Arrays.toString(array2) + " is " + checkBalance(array2));
        System.out.println("The result for array " + Arrays.toString(array3) + " is " + checkBalance(array3));

        //task 7
        taskDivider(7);
        int[] array4 = {1, -2, 3, -4, 5};
        int[] array5 = {1, -2, 3, -4, 5};
        int[] array6 = {1, -2, 3, -4, 5};
        int[] array7 = {1, -2, 3, -4, 5};
        int[] array8 = {1, -2, 3, -4, 5};
        int[] array9 = {1, -2, 3, -4, 5};
        int[] array10 = {1, -2, 3, -4, 5};
        int[] array11 = {1, -2, 3, -4, 5};
        int[] array12 = {1, -2, 3, -4, 5};
        int[] array13 = {1, -2, 3, -4, 5};
        int[] array14 = {1, -2, 3, -4, 5};

        System.out.println("Source array = " + Arrays.toString(array4));
        System.out.println("Offset n=-3. Resulting array = " + Arrays.toString(arrayOffset(array5, -3)));
        System.out.println("Offset n=-2. Resulting array = " + Arrays.toString(arrayOffset(array6, -2)));
        System.out.println("Offset n=-1. Resulting array = " + Arrays.toString(arrayOffset(array7, -1)));
        System.out.println("Offset n= 0. Resulting array = " + Arrays.toString(arrayOffset(array8, 0)));
        System.out.println("Offset n=+1. Resulting array = " + Arrays.toString(arrayOffset(array9, 1)));
        System.out.println("Offset n=+2. Resulting array = " + Arrays.toString(arrayOffset(array10, 2)));
        System.out.println("Offset n=+3. Resulting array = " + Arrays.toString(arrayOffset(array14, 3)));
        System.out.println("Offset n=+64. Resulting array = " + Arrays.toString(arrayOffset(array11, 64)));
        System.out.println("Offset n=-125. Resulting array = " + Arrays.toString(arrayOffset(array12, -125)));
        System.out.println("Offset n=-1249. Resulting array = " + Arrays.toString(arrayOffset(array13, -1249)));
    }

    /**
     * Task 1
     * Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. 
     * С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    public static void invertArray(int[] arr) {
        taskDivider(1);
        System.out.println("Source array = " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 - arr[i];
        }
        System.out.println("Resulting array = " + Arrays.toString(arr));
    }

    /**
     * Task 2
     * Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
     */
    public static void arrayFill(int[] arr) {
        taskDivider(2);
        System.out.println("Source array = " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
        System.out.println("Resulting array = " + Arrays.toString(arr));
    }

    /**
     * Task 3
     * Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    public static void multiplyElementTo2(int[] arr) {
        taskDivider(3);
        System.out.println("Source array = " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *= 2;
        }
        System.out.println("Resulting array = " + Arrays.toString(arr));
    }
    
    /**
     * Task 4
     * ** Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), 
     * и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
     */
    public static void arrayDiagonal(int[][] arr) {
        taskDivider(4);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                    arr[i][arr.length - j - 1] = 1;
                }
            }
        }
        System.out.println("Resulting array:");
        int g = 0;
        do {
            System.out.println(Arrays.toString(arr[g]));
            g++;
        } while (g < arr.length);
    }
     
    /**
     * Task 5
     * ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
     */
    public static void findMinMaxElement(int[] arr) {
        taskDivider(5);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        System.out.println("Array = " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        System.out.println("Minimum = " + min);
        System.out.println("Maximum = " + max);
    }
    
    /**
     * Task 6
     * ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true
     * если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) → true,
     * checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 10]) → true, граница показана символами ||, эти символы в массив не входят.
     */
    public static boolean checkBalance(int[] arr) {
        int sum1 = 0;
        int sum2 = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            sum1 += arr[i];
            sum2 = 0;
            for (int j = i + 1; j < arr.length; j++) {
                sum2 += arr[j];
            }
            if (sum1 == sum2) {
                flag = true;
                break;
            }
        }
        if (flag) return true;
        else return false;
    }

    /**
     * Task 7
     * **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным), 
     * при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
     */
    public static int[] arrayOffset(int[] arr, int n) {
        int memory = 0;
        int k = Math.abs(n);
        if (k > arr.length) k = k % arr.length;
        if (k == 0 || k == arr.length) return arr;
        for (int i = 1; i <= k; i++) {
            if (n < 0) {
                //offset to left for k times
                memory = arr[0];
                for (int j = 0; j < arr.length - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[arr.length - 1] = memory;
            } else if (n > 0) {
                //offset to right for k times
                memory = arr[arr.length - 1];
                for (int j = arr.length - 1; j > 0; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[0] = memory;
            } else { 
                return arr;
            }
        }
        return arr;
    }

    //Border between the tasks
    public static void taskDivider(int tasknum) {
        System.out.println("\n===================== TASK " + tasknum + " ====================");
    }
}