package ru.shcheglov.homework2.task2;

import java.io.*;

public class Main {

    private static final String PATH = "C:\\Users\\SuperUser\\Desktop\\save.ser";
    private static final Hero hero = Hero.builder()
            .className("Dark Elf")
            .name("Magician")
            .level(15)
            .hp(56)
            .maxHp(100)
            .build();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //write string to file
        try (FileWriter fw = new FileWriter("C:\\Users\\SuperUser\\Desktop\\save.txt")) {
            fw.write("class: " + hero.getClassName() + "\r\n");
            fw.write("name: " + hero.getName() + "\r\n");
            fw.write("level: " + hero.getLevel() + "\r\n");
            fw.write("hp: " + hero.getHp() + "\r\n");
            fw.write("maxHp: " + hero.getMaxHp() + "\r\n");
        }

        //serialize to file
        try (FileOutputStream fos = new FileOutputStream(PATH)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(hero);
                oos.flush();
            }
        }

        //deserialize from file
        try (FileInputStream fis = new FileInputStream(PATH)) {
            try (ObjectInputStream ois = new ObjectInputStream (fis)) {
                Hero readObject = (Hero) ois.readObject();
                System.out.println(readObject);
            }
        }

    }
}
