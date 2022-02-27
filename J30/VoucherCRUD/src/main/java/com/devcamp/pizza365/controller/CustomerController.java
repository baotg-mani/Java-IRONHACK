
package com.devcamp.pizza365.controller;

import com.devcamp.pizza365.entity.*;
import com.devcamp.pizza365.repository.*;
import com.devcamp.pizza365.service.ExcelExporter;

import java.sql.SQLException;
import java.util.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CustomerController {
	@Autowired
	CustomerRepository customerRep;
	@Autowired
	OrderRepository orderRep;
	@Autowired
	OrderDetailRepository orderDetailRep;

	@GetMapping("/allCustomers")
	public ResponseEntity<Object> getAllCustomers() {
		try {
			List<Customer> customer = new ArrayList<Customer>();

			customerRep.findAll().forEach(customer::add);

			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/allOrders")
	public ResponseEntity<Object> getAllOrders() {
		try {
			List<Order> orders = new ArrayList<Order>();

			orderRep.findAll().forEach(orders::add);

			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/allOrderDetails")
	public ResponseEntity<Object> getAllOrderDetails() {
		try {
			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

			orderDetailRep.findAll().forEach(orderDetails::add);

			return new ResponseEntity<>(orderDetails, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/export/customers/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Customer> customer = new ArrayList<Customer>();

		customerRep.findAll().forEach(customer::add);

		ExcelExporter excelExporter = new ExcelExporter(customer);

		excelExporter.export(response);
	}
}
