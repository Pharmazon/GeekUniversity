package ru.shcheglov.homework2.task2;

import java.io.*;

public class Main {

    private static final String PATH = "C:\\Users\\SuperUser\\Desktop\\";
    private static final String FILE_PATH_WRITE = PATH + "save.txt";
    private static final String FILE_PATH_SER = PATH + "save.ser";
    private static final Hero hero = Hero.builder()
            .className("Dark Elf")
            .name("Magician")
            .level(15)
            .hp(56)
            .maxHp(100)
            .build();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //write Hero to file as string
        try (FileWriter fw = new FileWriter(FILE_PATH_WRITE)) {
            fw.write("class: " + hero.getClassName() + "\r\n");
            fw.write("name: " + hero.getName() + "\r\n");
            fw.write("level: " + hero.getLevel() + "\r\n");
            fw.write("hp: " + hero.getHp() + "\r\n");
            fw.write("maxHp: " + hero.getMaxHp() + "\r\n");
        }

        //read lines from file and convert to Hero
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_WRITE))) {
            Hero newHero = new Hero();
            while (reader.ready()) {
                String line = reader.readLine().trim();
                String[] array = line.split(":");
                String key = array[0].trim();
                String value = array[1].trim();
                switch (key) {
                    case "class":
                        newHero.setClassName(value);
                        break;
                    case "name":
                        newHero.setName(value);
                        break;
                    case "level":
                        newHero.setLevel(Integer.parseInt(value));
                        break;
                    case "hp":
                        newHero.setHp(Integer.parseInt(value));
                        break;
                    case "maxHp":
                        newHero.setMaxHp(Integer.parseInt(value));
                        break;
                }
            }
            System.out.println("Read: " + newHero);
            System.out.println("HashCodes equals: " + (hero.hashCode() == newHero.hashCode()));
        }

        //serialize Hero to file
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH_SER)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(hero);
            }
        }

        //deserialize Hero from file
        try (FileInputStream fis = new FileInputStream(FILE_PATH_SER)) {
            try (ObjectInputStream ois = new ObjectInputStream (fis)) {
                Hero readObject = (Hero) ois.readObject();
                System.out.println("Deserialized: " + readObject);
                System.out.println("Object equals: " + (readObject.equals(hero)));
                System.out.println("HashCodes equals: " + (readObject.getHashCode() == readObject.hashCode()));
            }
        }

    }
}
