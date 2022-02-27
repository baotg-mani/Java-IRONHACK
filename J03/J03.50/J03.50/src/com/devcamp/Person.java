package com.devcamp;

public class Person {
    private String firstname;
    private String lastname;
    private int age;
    private String address;

    public Person(String firstname, String lastname, int age, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.address = address;
    }

    public Person() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Firstname: " + this.getFirstname() + "\n"
                + " Lastname: " + this.getLastname() + "\n"
                + " Address: " + this.getAddress() + "\n"
                + " Age: " + this.getAge() + "\n";
    }
}
