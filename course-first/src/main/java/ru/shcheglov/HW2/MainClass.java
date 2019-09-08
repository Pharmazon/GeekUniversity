package ru.shcheglov.HW2;

public class MainClass {

    public static void main(String[] args) {

        String[][] array1 = {
                {"1", "2", "3", "4"},
                {"1", "6", "34", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "13", "1"}};
        String[][] array2 = {
                {"1", "2", "3", "4"},
                {"1", " ", "34", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "13", "1"}};
        String[][] array3 = {
                {"1", "2", "3", "4"},
                {"1", "6", "34"},
                {"1", "2", "3", "4"},
                {"1", "2", "13", "1"}};
        try {
            System.out.println("Sum of elements = " + sumOf2DimArray(array1));
        } catch (MyArrayDataException | MyArraySizeException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Sum of elements = " + sumOf2DimArray(array2));
        } catch (MyArrayDataException | MyArraySizeException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Sum of elements = " + sumOf2DimArray(array3));
        } catch (MyArrayDataException | MyArraySizeException e) {
            e.printStackTrace();
        }
    }

    public static int sumOf2DimArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int elem = 0;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4 || array.length != 4) {
                throw new MyArraySizeException("Size of input array should be 4x4!");
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    elem = Integer.parseInt(array[i][j]);
                } catch (Exception e) {
                    throw new MyArrayDataException("Not integer value in array cell [" + i + "][" + j + "]!");
                }
                sum += elem;
            }
        }
        return sum;
    }
}
