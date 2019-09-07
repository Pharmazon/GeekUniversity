package ru.shcheglov.Homework5;

public class Employee {

    private String name;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    //task 2. constructor
    Employee(String name, String position,  String email,
             String phone, int salary, int age) {
        this.name = name.trim();
        this.position = position.trim();
        this.email = email.trim();
        this.phone = phone.trim();
        this.salary = salary;
        this.age = age;
    }

    //task 3. print to console
    void print() {
        System.out.println(this.getName() + ", " + this.getPosition() + ", " + this.getEmail() +
                ", " + this.getPhone() + ", " + this.getSalary() + ", " + this.getAge());
    }

    //getter-setter Name
    public void setName(String name) {
        if (name != null) this.name = name.trim();
    }

    public String getName() {
        return name.trim();
    }

    //getter-setter Position
    public void setPosition(String position) {
        if (position != null) this.position = position;
    }

    public String getPosition() {
        return position.trim();
    }

    //getter-setter email
    public void setEmail(String email) {
        if (email != null) this.email = email;
    }

    public String getEmail() {
        return email.trim();
    }

    //getter-setter phone
    public void setPhone(String phone) {
        if (phone != null) this.phone = phone;
    }

    public String getPhone() {
        return phone.trim();
    }

    //getter-setter salary
    public void setSalary(int salary) {
        if (salary > 0) this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    //getter-setter age
    public void setAge(int age) {
        if (age > 0) this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() { //overrided method toString
        return (name +
                "\n| Position: " + position +
                "\n| E-mail: " + email +
                "\n| Phone: " + phone +
                "\n| Salary: " + salary +
                "\n| Age: " + age);
    }
}
