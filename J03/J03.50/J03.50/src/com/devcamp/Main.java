package com.devcamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //TODO 1: Khởi tạo các instance obj thuộc lớp Staff
	    Staff p_huy = new Staff("Huy", "Pham", 20, "Ha Noi", "Boi ban", 80000);
        Staff p_tu = new Staff("Tu", "Nguyen", 21, "Ha Noi", "Le Tan", 100000);
        Staff p_giang = new Staff("Giang", "Tran", 40, "Ha Noi", "Bao ve", 100000);

        //TODO 2: Khởi tạo và thêm các phần tử vào array list 
        List<Staff> p_staffList = new ArrayList<Staff>();
        p_staffList.add(p_huy);
        p_staffList.add(p_tu);
        p_staffList.add(p_giang);

        //TODO 3: khởi tạo instance obj từ Interface List
        List<Customer> p_customerList = new ArrayList<Customer>();

        //TODO 4: khởi tạo ins obj về thời gian thông qua class Date
        Date p_date = new Date();

        //TODO 5: khởi tạo ins obj từ class Store 
        Store p_store = new Store(p_staffList, p_customerList, p_date, 500000);

        //TODO 6: khởi tạo ins obj từ class Staff
        Staff p_tien = new Staff("Tien", "Tran", 18, "Ha Noi", "Phuc vu parttime", 40000);

        //TODO 7: sử dụng method addStaff để thêm phần tử vào List Staff
        p_store.addStaff(p_tien);

        //TODO 8: gọi method cập nhật tiền
        p_store.updateTotalMoneySpent();

        //TODO 9: 1. nhận thông số tiền đã chi thông qua method getTotal và gán vào biến p_salary
        //		  2. hiển thị ra console
        int p_salarySpent = p_store.getTotalMoneySpent();
        System.out.println("Total salary spent: " + p_salarySpent);

        //TODO 10: Khởi tạo 4 ins obj từ lớp Customer
        Customer p_thanh = new Customer("Thanh", "Pham", 18, "Ha Noi", 200000);
        Customer p_dung = new Customer("Dung", "Vu", 17, "Bac Ninh", 250000);
        Customer p_van = new Customer("Van", "Pham", 20, "Ha Nam", 250000);
        Customer p_nam = new Customer("Nam", "Dinh", 20, "Thai Binh", 500000);

        //TODO 11: Thêm khách hàng vào List Customer vào ins obj p_store thông qua method addCustomer
        p_store.addCustomer(p_thanh);
        p_store.addCustomer(p_dung);
        p_store.addCustomer(p_van);
        p_store.addCustomer(p_nam);

        //TODO 12: Cập nhật tổng số tiên kiếm được
        p_store.updateTotalMoneyEarned();

        //TODO 13: get số tiền kiểm được thông qua method của obj p_store gán vào 1 biến p_moneyEarned
        int p_moneyEarned = p_store.getTotalMoneyEarned();
        System.out.println("Total money earned: " + p_moneyEarned);

        //TODO 14: Hiển thị thông tin của obj dưới dạng Chuỗi ra console
        System.out.println(p_store.toString());
    }
}
