package ru.shcheglov.HW3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Words2 {
    public static void main(String[] args) {
        String[] words = {"flag", "milk", "run", "swim",
                "fill", "walk", "knife", "table", "milk", "swim", "flag", "run",
                "floor", "groom", "flag"};
        System.out.println(uniqueWordsInArray(words));
        countWordsInArray(words);
    }

    private static void countWordsInArray(String[] array) {
        ArrayList<String> list = uniqueWordsInArray(array);
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        for (String word : list) {
            for (String wrd : array) {
                if (word.equals(wrd)) count++;
            }
            map.put(word, count);
            count = 0;
        }
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + ":" + pair.getValue());
        }
    }

    private static ArrayList<String> uniqueWordsInArray(String[] array) {
        ArrayList<String> list = new ArrayList<>();
        for (String word : array) {
            if (!list.contains(word)) list.add(word);
        }
        return list;
    }
}
