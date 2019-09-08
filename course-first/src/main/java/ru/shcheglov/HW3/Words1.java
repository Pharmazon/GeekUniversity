package ru.shcheglov.HW3;

import java.util.*;

public class Words1 {
    public static void main(String[] args) {
        String[] words = {"flag", "milk", "run", "swim",
                "fill", "walk", "knife", "table", "milk", "swim", "flag", "run",
                "floor", "groom", "flag"};
        System.out.println(uniqueWordsAsSet(words));
        countWordsInArray(words);
    }

    private static HashSet<String> uniqueWordsAsSet(String[] words) {
        HashSet<String> set = new HashSet<>(Arrays.asList(words));
        return set;
    }

    private static void countWordsInArray(String[] words) {
        HashSet<String> set = uniqueWordsAsSet(words);
        Iterator<String> iter = set.iterator();
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        while (iter.hasNext()) {
            String elem = iter.next();
            for (String word : words) {
                if (elem.equals(word)) {
                    count++;
                }
            }
            map.put(elem, count);
            count = 0;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
