package ru.shcheglov.HW3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Words3 {
    public static void main(String[] args) {
        String[] words = {"flag", "milk", "run", "swim",
                "fill", "walk", "knife", "table", "milk", "swim", "flag", "run",
                "floor", "groom", "flag"};
        HashSet<String> set = new HashSet<>(Arrays.asList(words));
        System.out.println(set);

        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        System.out.println(map);
        System.out.println(map.keySet());
    }
}
