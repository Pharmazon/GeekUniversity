package ru.shcheglov.HW3;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook1 {
    private HashMap<String, String> map;

    public static void main(String[] args) {
        PhoneBook1 book = new PhoneBook1();
        book.add("Иванов","8(925)645-72-51");
        book.add("Петров","8(928)765-73-45");
        book.add("Сидоров","8(842)575-41-23");
        book.add("Иванов","8(925)644-42-51");
        book.add("Иванов","8(929)644-42-51");

        book.get("Петров");
        book.get("Иванов");
        book.get("Саврин");
    }

    public PhoneBook1() {
        this.map = new HashMap<>();
    }

    public void add(String lastname, String phonenumber) {
        map.put(phonenumber, lastname);
    }

    public void get(String lastname) {
        boolean flag = true;
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (lastname.equals(pair.getValue())) {
                System.out.println(pair.getValue() + ": " + pair.getKey());
                flag = false;
            }
        }
        if (flag) System.out.println("Не найдено ни одной записи по запросу: " + lastname);
    }
}
