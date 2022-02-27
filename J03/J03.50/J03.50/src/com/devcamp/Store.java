package com.devcamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Store {
    private List<Staff> staffs;
    private List<Customer> customers;
    private Date date;
    private int operatingCosts;
    private int totalMoneyEarned;
    private int totalMoneySpent;

    public Store(List<Staff> staffs, List<Customer> customers, Date date, int operatingCosts) {
        this.staffs = staffs;
        this.customers = customers;
        this.date = date;
        this.operatingCosts = operatingCosts;
        this.totalMoneyEarned = 0;
        this.totalMoneySpent = 0;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOperatingCosts() {
        return operatingCosts;
    }

    public void setOperatingCosts(int operatingCosts) {
        this.operatingCosts = operatingCosts;
    }

    public int getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public void setTotalMoneyEarned(int totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }

    public int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public void setTotalMoneySpent(int totalMoneySpent) {
        this.totalMoneySpent = totalMoneySpent;
    }

    public void addStaff(Staff staff) {
        this.staffs.add(staff);
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void updateTotalMoneyEarned() {
        for ( Customer p_customer : this.customers) {
            this.totalMoneyEarned += p_customer.getFeesPaid();
        }
    }

    public void updateTotalMoneySpent() {
        for ( Staff p_staff : this.staffs) {
            this.totalMoneySpent += p_staff.getSalary();
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat p_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String p_strDate = p_dateFormat.format(this.date);

        int p_revenue = this.totalMoneyEarned - this.totalMoneySpent - this.operatingCosts;

        return "On " + p_strDate + ", revenue: " + p_revenue;
    }
}
