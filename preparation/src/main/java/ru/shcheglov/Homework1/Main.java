package ru.shcheglov.Homework1;

/**
 * Java 1. Homework1
 *
 * @author Alexey Shcheglov
 * @version dated Nov 21, 2017
 * @link https://github.com/Pharmazon
 */
class Main {

    public static void main(String[] args) {
        
        //task 2
        datatypes();
        
        //task 3
        System.out.println("calculate = " + calculate(23, -2, 59, -6));
        
        //task 4
        System.out.println("task10and20 = " + task10and20(5, 6));
        
        //task 5
        isPositiveOrNegative(0);
        
        //task 6
        System.out.println("isNegative = " + isNegative(-5));
        
        //task 7
        greetings("Sergey");
        
        //task 8
        isLeapYear(1600);
        isLeapYear(2016);
        isLeapYear(2017);
        isLeapYear(2400);
    }

    /**
     * Задача 2
     * Создать переменные всех пройденных типов данных, и инициализировать их значения; 
     */
    public static void datatypes() {
        taskDivider(2);
        int a = -565642;
        double b = Double.MIN_VALUE;
        float c = 59.5f;
        boolean d = false;
        char e = 'G';
        long f = 34563567878L;
        short g = -567;
        byte h = Byte.MAX_VALUE;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);
        System.out.println("e = " + e);
        System.out.println("f = " + f);
        System.out.println("g = " + g);
        System.out.println("h = " + h);
    }

    /**
     * Задача 3
     * Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат, 
     * где a, b, c, d – входные параметры этого метода;  
     */
    public static int calculate(int a, int b, int c, int d) {
        taskDivider(3);
        return a * (b + (c / d));
    }

    /**
     * Задача 4
     * Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в
     * пределах от 10 до 20(включительно), если да – вернуть true, в противном случае – false;  
     */
    public static boolean task10and20(int x, int y) {
        taskDivider(4);
        if (x + y > 10 && x + y <= 20) {
            return true;
        } else return false;
    }

    /**
     * Задача 5
     *  Написать метод, которому в качестве параметра передается целое число, метод должен 
     * напечатать в консоль положительное ли число передали, или отрицательное; ​Замечание: 
     * ноль считаем положительным числом  
     */
    public static void isPositiveOrNegative(int z) {
        taskDivider(5);
        if (z >= 0) {
            System.out.println("Positive");
        } else {
            System.out.println("Negative");
        }
    }

    /**
     * Задача 6
     * Написать метод, которому в качестве параметра передается целое число, метод должен 
     * вернуть true, если число отрицательное 
     */
    public static boolean isNegative(int z) {
        taskDivider(6);
        if (z < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Задача 7
     * Написать метод, которому в качестве параметра передается строка, обозначающая имя, 
     * метод должен вывести в консоль сообщение «Привет, указанное_имя!» 
     */
    public static void greetings(String name) {
        taskDivider(7);
        System.out.println("Hello, " + name + "!");
    }

    /**
     * Задача 8
     * Написать метод, который определяет является ли год високосным, и выводит сообщение в 
     * консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – 
     * високосный
     */
    public static void isLeapYear(int year) {
        taskDivider(8);
        if (year % 400 == 0) {
            System.out.println(Integer.toString(year) + " is a leap year!");
        } else if (year % 100 == 0) {
            System.out.println(Integer.toString(year) + " is NOT a leap year!");
        } else if (year % 4 == 0) {
            System.out.println(Integer.toString(year) + " is a leap year!");
        } else {
            System.out.println(Integer.toString(year) + " is NOT a leap year!");
        }
    }
    
    //Border between the tasks
    public static void taskDivider(int tasknum) {
        System.out.println("\n===================== TASK " + tasknum + " ====================");
    }    
}