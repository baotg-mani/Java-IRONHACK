package com.devcamp.pizza365.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import com.devcamp.pizza365.repository.*;
import com.devcamp.pizza365.model.*;

@RestController
public class COrderController {

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private IUserRepository userRepository;

	public COrderController(IOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping("/order/all")
	public List<COrder> getAllOrders() {
		return orderRepository.findAll();
	}

	@GetMapping("/order/details/{id}")
	public COrder getOrderById(@PathVariable Long id) {
		if (orderRepository.findById(id).isPresent())
			return orderRepository.findById(id).get();
		else
			return null;
	}

	@DeleteMapping("/order/delete/{id}")
	public ResponseEntity<Object> deleteOrderById(@PathVariable Long id) {
		try {
			orderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/order/update/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable("id") Long id, @RequestBody COrder cOrder) {
		Optional<COrder> orderData = orderRepository.findById(id);
		if (orderData.isPresent()) {
			COrder newOrder = orderData.get();
			newOrder.setOrderCode(cOrder.getOrderCode());
			newOrder.setKichCo(cOrder.getKichCo());
			newOrder.setDuongKinh(cOrder.getDuongKinh());
			newOrder.setSuon(cOrder.getSuon());
			newOrder.setSalad(cOrder.getSalad());
			newOrder.setLoaiPizza(cOrder.getLoaiPizza());
			newOrder.setIdVourcher(cOrder.getIdVourcher());
			newOrder.setThanhTien(cOrder.getThanhTien());
			newOrder.setGiamGia(cOrder.getGiamGia());
			newOrder.setIdLoaiNuocUong(cOrder.getIdLoaiNuocUong());
			newOrder.setSoLuongNuoc(cOrder.getSoLuongNuoc());
			newOrder.setHoTen(cOrder.getHoTen());
			newOrder.setEmail(cOrder.getEmail());
			newOrder.setSoDienThoai(cOrder.getSoDienThoai());
			newOrder.setDiaChi(cOrder.getDiaChi());
			newOrder.setLoiNhan(cOrder.getLoiNhan());
			newOrder.setNgayCapNhat(new Date());
			COrder saveOrder = orderRepository.save(newOrder);
			return new ResponseEntity<>(saveOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/order/byuserid/{id}")
	public List<COrder> getOrderByUserId(@PathVariable("id") Long id) {
		Optional<CUser> userData = userRepository.findById(id);
		if (userData.isPresent()) {
			return userData.get().getOrders();
		} else {
			return null;
		}
	}

	@PostMapping("/order/create/{id}")
	public ResponseEntity<Object> createOrder(@PathVariable("id") Long id, @RequestBody COrder cOrder) {
		Optional<CUser> userData = userRepository.findById(id);
		if (userData.isPresent()) {

			COrder newOrder = new COrder();
			newOrder.setOrderCode(cOrder.getOrderCode());
			newOrder.setKichCo(cOrder.getKichCo());
			newOrder.setDuongKinh(cOrder.getDuongKinh());
			newOrder.setSuon(cOrder.getSuon());
			newOrder.setSalad(cOrder.getSalad());
			newOrder.setLoaiPizza(cOrder.getLoaiPizza());
			newOrder.setIdVourcher(cOrder.getIdVourcher());
			newOrder.setThanhTien(cOrder.getThanhTien());
			newOrder.setGiamGia(cOrder.getGiamGia());
			newOrder.setIdLoaiNuocUong(cOrder.getIdLoaiNuocUong());
			newOrder.setSoLuongNuoc(cOrder.getSoLuongNuoc());
			newOrder.setHoTen(cOrder.getHoTen());
			newOrder.setEmail(cOrder.getEmail());
			newOrder.setSoDienThoai(cOrder.getSoDienThoai());
			newOrder.setDiaChi(cOrder.getDiaChi());
			newOrder.setLoiNhan(cOrder.getLoiNhan());
			newOrder.setNgayTao(new Date());
			newOrder.setNgayCapNhat(null);

			CUser _user = userData.get();
			newOrder.setUser(_user);
			newOrder.setUsername(_user.getUsername());

			COrder saveOrder = orderRepository.save(newOrder);
			return new ResponseEntity<>(saveOrder, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
