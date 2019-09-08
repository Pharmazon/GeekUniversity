package ru.cloud.storage.common.util;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Random;

public class MathFunc {
    public static double round(double value, int precision) {
        return (double) Math.round(value * Math.pow(10, precision)) / Math.pow(10, precision);
    }

    public static int randomInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static double randomDouble(double min, double max) {
        return min + (Math.random() * ((max - min) + 1));
    }

    public static long randomLong(long min, long max) {
        return min + (long) (Math.random() * ((max - min) + 1));
    }

    public static Date randomDate(String from, String to) {
        Random random = new Random();
        String[] fromArray = from.split("-");
        String[] toArray = to.split("-");
        int minDay = (int) LocalDate.of(Integer.parseInt(fromArray[0]), Integer.parseInt(fromArray[1]),
                Integer.parseInt(fromArray[2])).toEpochDay();
        int maxDay = (int) LocalDate.of(Integer.parseInt(toArray[0]), Integer.parseInt(toArray[1]),
                Integer.parseInt(toArray[1])).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        return Date.valueOf(LocalDate.ofEpochDay(randomDay));
    }

    public static Time randomTime(String from, String to) {
        return new Time(randomLong(Time.valueOf(from).getTime(), Time.valueOf(to).getTime()));
    }
}
