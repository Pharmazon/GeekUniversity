package ru.shcheglov.oop.task1;

public class TaskOneMain {

    public static void main(String[] args) {
        Person person = new Person.Builder()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setMiddleName("Petrovich")
                .setAddress("Mira Street, 55")
                .setAge(22)
                .setCountry("Russia")
                .setGender("male")
                .setPhone("89865643524")
                .build();
        System.out.println(person);
    }
}
