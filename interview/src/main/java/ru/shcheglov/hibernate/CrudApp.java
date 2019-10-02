package ru.shcheglov.hibernate;

import java.util.List;

public class CrudApp {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        //CREATE
        studentDAO.openSession();
        for (int i = 0; i < 1000; i++) {
            studentDAO.addOne(new Student("Student_" + i, i * 10));
        }
        Long testId = studentDAO.getOneByName("Student_500").getId();
        List<Student> students = studentDAO.getAll();
        studentDAO.closeSession();
        students.forEach(System.out::println);

        //READ
        studentDAO.openSession();
        Student entity = studentDAO.getOneById(testId);
        studentDAO.closeSession();
        System.out.println(String.format("READ: %s", entity));

        //UPDATE
        studentDAO.openSession();
        Student beforeUpdate = studentDAO.getOneById(testId);
        System.out.println(String.format("BEFORE UPDATE: %s", beforeUpdate));
        beforeUpdate.setName("UPDATED_TEST");
        studentDAO.saveOrUpdate(beforeUpdate);
        Student afterUpdate = studentDAO.getOneById(testId);
        System.out.println(String.format("AFTER UPDATE: %s", afterUpdate));
        studentDAO.closeSession();

        //DELETE
        studentDAO.openSession();
        Student beforeDelete = studentDAO.getOneById(testId);
        System.out.println(String.format("BEFORE DELETE: %s", beforeDelete));
        studentDAO.delete(beforeDelete);
        Student afterDelete = studentDAO.getOneById(testId);
        System.out.println(String.format("AFTER DELETE: %s", afterDelete == null ? "SUCCESS" : "FAIL"));
        studentDAO.closeSession();

    }
}
