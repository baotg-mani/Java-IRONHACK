package com.devcamp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.devcamp.model.CDrink;
import com.devcamp.model.CMenu;
import com.devcamp.model.COrder;
import com.devcamp.model.CUser;
import com.devcamp.repository.IDrinkRepository;
import com.devcamp.repository.IMenuRepository;
import com.devcamp.repository.IOrderRepository;
import com.devcamp.repository.IUserRepository;

@RestController
public class Pizza365Controller {
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	IMenuRepository menuRepository;
	
	@Autowired
	IDrinkRepository drinkRepository;
	
	////////////////////////USER///////////////////////////
	
	@CrossOrigin
	@PostMapping("/user/create")
	public ResponseEntity<Object> createUser(@RequestBody CUser cUser) {
		try {
			CUser savedRole = userRepository.save(cUser);
			return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@PutMapping("/user/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody CUser cUser) {
		Optional<CUser> userData = userRepository.findById(id);
		if (userData.isPresent()) {
			CUser newRole = userData.get();
			newRole.setFullname(cUser.getFullname());
			newRole.setEmail(cUser.getEmail());
			newRole.setAddress(cUser.getAddress());
			newRole.setPhone(cUser.getPhone());
			newRole.setOrders(cUser.getOrders());
			CUser savedCountry = userRepository.save(newRole);
			return new ResponseEntity<>(savedCountry, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@DeleteMapping("/user/delete/all")
	public ResponseEntity<Object> deleteAllUser(){
		try {
			userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@GetMapping("/user/details/{id}")
	public CUser getUserById(@PathVariable Long id) {
		if (userRepository.findById(id).isPresent())
			return userRepository.findById(id).get();
		else
			return null;
	}

	@CrossOrigin
	@GetMapping("/user/all")
	public List<CUser> getAllUser() {
		return userRepository.findAll();
	}
	
	
	/////////////////////////ORDER//////////////////////////
	
	@CrossOrigin
	@PostMapping("/order/create/{id}")
	public ResponseEntity<Object> createOrder(@PathVariable("id") Long id, @RequestBody COrder cOrder) {
		try {
			Optional<CUser> userData = userRepository.findById(id);
			if (userData.isPresent()) {
				CUser _user = userData.get();
				cOrder.setUser(_user);
				COrder savedRole = orderRepository.save(cOrder);
				return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity()
					.body("Failed to Create specified Voucher: " + e.getCause().getCause().getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@CrossOrigin
	@PutMapping("/order/update/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable("id") Long id, @RequestBody COrder cOrder) {
		Optional<COrder> orderData = orderRepository.findById(id);
		if (orderData.isPresent()) {
			COrder newOrder = orderData.get();
			newOrder.setOrderCode(cOrder.getOrderCode());
			newOrder.setPizzaSize(cOrder.getPizzaSize());
			newOrder.setPizzaType(cOrder.getPizzaType());
			newOrder.setVoucherCode(cOrder.getVoucherCode());
			newOrder.setPrice(cOrder.getPrice());
			newOrder.setPaid(cOrder.getPaid());
			COrder savedOrder = orderRepository.save(newOrder);
			return new ResponseEntity<>(savedOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@DeleteMapping("/order/delete/{id}")
	public ResponseEntity<Object> deleteOrderById(@PathVariable Long id) {
		try {
			orderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@GetMapping("/order/details/{id}")
	public COrder getOrderById(@PathVariable Long id) {
		if (orderRepository.findById(id).isPresent())
			return orderRepository.findById(id).get();
		else
			return null;
	}
	
	@CrossOrigin
	@GetMapping("/order/all")
	public List<COrder> getAllOrder(){
		return orderRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/user/{userId}/orders")
	public List<COrder> getOrdersByUserId(@PathVariable(value = "userId") Long userId) {
		return orderRepository.findByUserId(userId);
	}
	
	
	/////////////////////DRINK///////////////////////
	@CrossOrigin
	@GetMapping("/drinks")
	public ResponseEntity<List<CDrink>> getAllDrinks() {
		try {
			List<CDrink> pDrinks = new ArrayList<CDrink>();

			drinkRepository.findAll().forEach(pDrinks::add);

			return new ResponseEntity<>(pDrinks, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/drinks/{id}")
	public ResponseEntity<CDrink> getCDrinkById(@PathVariable("id") long id) {
		Optional<CDrink> drinkData = drinkRepository.findById(id);
		if (drinkData.isPresent()) {
			return new ResponseEntity<>(drinkData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@PostMapping("/drinks")
	public ResponseEntity<Object> createCDrink(@Valid @RequestBody CDrink pDrinks) {
		try {
			pDrinks.setNgayTao(new Date());
			pDrinks.setNgayCapNhat(null);
			CDrink _drinks = drinkRepository.save(pDrinks);
			return new ResponseEntity<>(_drinks, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("+++++++++++++++++++++::::: "+e.getCause().getCause().getMessage());
			//Hiện thông báo lỗi tra back-end
			//return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified Drink: "+e.getCause().getCause().getMessage());
		}
	}
	
	@CrossOrigin
	@PutMapping("/drinks/{id}")
	public ResponseEntity<Object> updateCDrinkById(@PathVariable("id") long id, @RequestBody CDrink pDrinks) {
		Optional<CDrink> drinkData = drinkRepository.findById(id);
		if (drinkData.isPresent()) {
			CDrink drink= drinkData.get();
			drink.setMaNuocUong(pDrinks.getMaNuocUong());
			drink.setTenNuocUong(pDrinks.getTenNuocUong());
			drink.setDonGia(pDrinks.getDonGia());
			drink.setNgayCapNhat(new Date());
			try {
				return new ResponseEntity<>(drinkRepository.save(drink), HttpStatus.OK);	
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().body("Failed to Update specified Drink:"+e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified Drink: "+id + "  for update.");
		}
	}
	
	@CrossOrigin
	@DeleteMapping("/drinks/{id}")
	public ResponseEntity<CDrink> deleteCDrinkById(@PathVariable("id") long id) {
		try {
			drinkRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@DeleteMapping("/drinks")
	public ResponseEntity<CDrink> deleteAllCDrink() {
		try {
			drinkRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//////////////////////MENU///////////////////////
	
	@CrossOrigin
	@GetMapping("/menus")
	public ResponseEntity<List<CMenu>> getAllMenus () {
		try {
			List<CMenu> pMenus = new ArrayList<CMenu>();
			menuRepository.findAll().forEach(pMenus::add);
			return new ResponseEntity<>(pMenus, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@GetMapping("/menus/{id}")
	public ResponseEntity<CMenu> getMenuById(@PathVariable("id") long id) {
		Optional<CMenu> menuData = menuRepository.findById(id);
		if(menuData.isPresent()) {
			return new ResponseEntity<>(menuData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin
	@PostMapping("/menus")
	public ResponseEntity<Object> createMenu(@Valid @RequestBody CMenu pMenus) {
		try {
			CMenu _menus = menuRepository.save(pMenus);
			return new ResponseEntity<>(_menus, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("+++++++++++++::::: " + e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified Menu: "+e.getCause().getCause().getMessage());
		}
	}
	
	@CrossOrigin
	@PutMapping("/menus/{id}")
	public ResponseEntity<Object> updateMenuById(@PathVariable("id") long id, @RequestBody CMenu pMenus){
		Optional<CMenu> menuData = menuRepository.findById(id);
		if (menuData.isPresent()) {
			CMenu menu = menuData.get();
			menu.setDonGia(pMenus.getDonGia());
			menu.setDuongKinh(pMenus.getDuongKinh());
			menu.setSalad(pMenus.getSalad());
			menu.setSize(pMenus.getSize());
			menu.setSoLuongNuocNgot(pMenus.getSoLuongNuocNgot());
			menu.setSuon(pMenus.getSuon());
			try {
				return new ResponseEntity<>(menuRepository.save(menu), HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().body("Failed to Update specified Menu:"+e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified Menu: "+id + "  for update.");
		}
	}
	
	@CrossOrigin
	@DeleteMapping("/menus/{id}")
	public ResponseEntity<CMenu> deleteMenuById(@PathVariable("id") long id) {
		try {
			menuRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@DeleteMapping("/menus")
	public ResponseEntity<CDrink> deleteAllMenu() {
		try {
			menuRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
