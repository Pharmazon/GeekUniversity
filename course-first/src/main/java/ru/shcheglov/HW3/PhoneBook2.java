package ru.shcheglov.HW3;

import java.util.HashMap;
import java.util.HashSet;

public class PhoneBook2 {
    private HashMap<String, HashSet<String>> map;

    public static void main(String[] args) {
        PhoneBook2 book = new PhoneBook2();
        book.add("Ivanov","8(925)645-72-51");
        book.add("Petrov","8(928)765-73-45");
        book.add("Sidorov","8(842)575-41-23");
        book.add("Ivanov","8(925)644-42-51");
        book.add("Ivanov","8(929)644-42-51");

        System.out.println(book.get("Petrov"));
        System.out.println(book.get("Ivanov"));
        System.out.println(book.get("Savrin"));
    }

    public PhoneBook2() {
        this.map = new HashMap<>();
    }

    public void add(String name, String phone) {
        if (!map.containsKey(name)) map.put(name, new HashSet<>());
        map.get(name).add(phone);
    }

    public HashSet<String> get(String name) {
        return map.get(name);
    }
}
