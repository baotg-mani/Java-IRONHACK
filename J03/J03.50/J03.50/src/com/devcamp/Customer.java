package com.devcamp;

public class Customer extends Person {
    private int feesPaid;

    public Customer(String firstname, String lastname, int age, String address, int feesPaid) {
        super(firstname, lastname, age, address);
        this.feesPaid = feesPaid;
    }

    public Customer() {
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(int feesPaid) {
        this.feesPaid = feesPaid;
    }

    @Override
    public String toString() {
        return "Name of the Customer: " + this.getFirstname() + " " + this.getLastname()  + "\n"
                +" Fee: " + this.getFeesPaid();
    }
}
