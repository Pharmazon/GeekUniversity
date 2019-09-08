package ru.shcheglov.Homework1.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArray {

    public static void main(String[] args) {
        Integer[] arrayInteger = new Integer[]{1, 2, 3, 4, 5, 6};
        String[] arrayString = new String[]{"1", "2", "56", "4", "5", "9"};

        System.out.println(MyArray.toArrayList(arrayString));
        System.out.println(MyArray.toArrayList(arrayInteger));

        MyArray.swap(arrayInteger, 0, 1);
        System.out.println(Arrays.toString(arrayInteger));

        MyArray.swap(arrayString, 3, 5);
        System.out.println(Arrays.toString(arrayString));
    }

    public static <T> List<T> toArrayList(T[] array) {
        if (isArrayEmpty(array)) return null;
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    public static <T> void swap(T[] array, int srcIndex, int dstIndex) {
        if (isArrayEmpty(array)) return;
        if (srcIndex < 0 || srcIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Source array element doesn't exist!");
        }
        if (dstIndex < 0 || dstIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Destination array element doesn't exist!");
        }
        T tmp = array[srcIndex];
        array[srcIndex] = array[dstIndex];
        array[dstIndex] = tmp;
    }

    public static <T> boolean isArrayEmpty(T[] array) {
        if (array.length == 0) {
            return true;
        } else return false;

    }
}
