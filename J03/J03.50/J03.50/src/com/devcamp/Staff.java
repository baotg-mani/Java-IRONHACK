package com.devcamp;

public class Staff extends Person {
    private String job;
    private int salary;

    public Staff(String firstname, String lastname, int age, String address, String job, int salary) {
        super(firstname, lastname, age, address);
        this.job = job;
        this.salary = salary;
    }

    public Staff() {
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name of the Staff: " + this.getFirstname() + " " + this.getLastname()  + "\n"
                +" Job: " + this.getJob() + "\n"
                +" Total salary earned: " + this.getSalary();
    }
}
