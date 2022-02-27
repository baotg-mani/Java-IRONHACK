package com.devcamp.shop_pizza365.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.shop_pizza365.model.Order;
import com.devcamp.shop_pizza365.model.OrderDetail;
import com.devcamp.shop_pizza365.model.Product;
import com.devcamp.shop_pizza365.repository.OrderDetailRepository;
import com.devcamp.shop_pizza365.repository.OrderRepository;
import com.devcamp.shop_pizza365.repository.ProductRepository;

@RestController
@CrossOrigin
public class OrderDetailController {
	@Autowired
	private ProductRepository pProductRepository;
	@Autowired
	private OrderRepository pOrderRepository;
	@Autowired
	private OrderDetailRepository pOrderDetailRepository;
	
	@GetMapping("/order_detail/all")
	public ResponseEntity<Object> getAllOrderDetail() {
		try {
			return new ResponseEntity<>(pOrderDetailRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/order_detail/{id}")
	public ResponseEntity<Object> getOrderDetailById(@PathVariable int id){
		try {
			return new ResponseEntity<>(pOrderDetailRepository.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/order_detail/{orderId}/{productId}") 
	public ResponseEntity<Object> createOrderDetailByOrderIdAndProductId(@PathVariable Integer orderId,@PathVariable Integer productId, @RequestBody OrderDetail cOrderDetail){
		Optional<Order> orderData = pOrderRepository.findById(orderId);
		Optional<Product> productData = pProductRepository.findById(productId);
		if (orderData.isPresent() && productData.isPresent()) {
			try {
				OrderDetail vOrderDetail = new OrderDetail();
				vOrderDetail.setPriceEach(cOrderDetail.getPriceEach());
				vOrderDetail.setQuantityOrder(cOrderDetail.getQuantityOrder());
				vOrderDetail.setOrder(orderData.get()); //memo! lấy theo order id
				vOrderDetail.setProduct(productData.get()); //memo! lấy theo product id
				
				pOrderDetailRepository.save(vOrderDetail);
				return new ResponseEntity<>(pOrderDetailRepository.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Order or Product not found by id, check again");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}	
	}
	
	@PutMapping("/order_detail/{id}")
	public ResponseEntity<Object> updateOrderDetail(@PathVariable Integer id, @RequestBody OrderDetail cOrderDetail) {
		Optional<OrderDetail> orderDetailData = pOrderDetailRepository.findById(id);
		if (orderDetailData.isPresent()) {
			try {
				OrderDetail newUpdate = orderDetailData.get();
				newUpdate.setPriceEach(cOrderDetail.getPriceEach());
				newUpdate.setQuantityOrder(cOrderDetail.getQuantityOrder());
				newUpdate.setOrder(cOrderDetail.getOrder());
				newUpdate.setProduct(cOrderDetail.getProduct());
				
				return new ResponseEntity<>(pOrderDetailRepository.save(newUpdate), HttpStatus.OK);
			} catch(Exception e) {
				System.out.println(e.getCause().getCause().getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Order not found by ID");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/order_detail/{id}")
	public ResponseEntity<Object> deleteOrderDetailById(@PathVariable Integer id) {
		if (pOrderDetailRepository.findById(id).isPresent()) {
			try {
				pOrderDetailRepository.deleteById(id);
				return new ResponseEntity<>(pOrderDetailRepository.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e);
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			System.out.println("Id not found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
