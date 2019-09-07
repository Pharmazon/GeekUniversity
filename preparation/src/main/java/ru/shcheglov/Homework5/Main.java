package ru.shcheglov.Homework5;

/**
 * Java 1. Homework 5. Employees class 
 * using object-oriented approach
 *
 * @author Alexey Shcheglov
 * @version dated Dec 08, 2017
 * @link https://github.com/Pharmazon
 */
 
class Main {
    
    public static void main(String[] args) {

        //task 4. initialize the array of Employee objects
        Employee[] arr = new Employee[5];
        arr[0] = new Employee("Ivanov Ivan", "Engineer", 
                "​ivivan@company.com", "892312312", 30000, 30);
        arr[1] = new Employee("Petrov Petr", "CEO", 
                "​petpet@company.com", "899343434", 300000, 46);
        arr[2] = new Employee("Sidorov Sider", "Accountant", 
                "sidsid@company.com", "894745747", 20000, 47);
        arr[3] = new Employee("Sultanov Alexey", "Maintenance specialist", 
                "sulalex@company.com", "892131453", 10500, 50);
        arr[4] = new Employee("Kholodnaya Kristina", "Economist", 
                "​kholkris@company.com​", "891112335", 25000, 26);

        //task 5. print employees above 40 age
        for (Employee employee : arr) {
            if (employee.getAge() > 40) {
                System.out.println(employee);
            }
        }
    }
}

