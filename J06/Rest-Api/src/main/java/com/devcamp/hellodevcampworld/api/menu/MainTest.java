package com.devcamp.hellodevcampworld.api.menu;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<CMenu> menu = new ArrayList<CMenu>();
		CMenu sizeS = new CMenu('S', 20, 2, 200, 2, 150000);
		CMenu sizeM = new CMenu('M', 25, 4, 300, 3, 200000);
		CMenu sizeL = new CMenu('L', 30, 8, 500, 4, 250000);

		menu.add(sizeS);
		menu.add(sizeM);
		menu.add(sizeL);

		CMenuList menuList = new CMenuList();
		menuList.setMenus(menu);
		System.out.println("Kết quả trả về: " + menuList);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = "";
		try {
			jsonStr = objectMapper.writeValueAsString(menuList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Kết quả trả về json" + jsonStr);
		
		////// TEST ngày tạo, ngày cập nhật của api drinks
		long ngaytao = 1615177934000l;
		long ngaycapnhat = 1615177934000l;
		Date dateTao = new Date(ngaytao);
		Date dateCapNhat = new Date(ngaycapnhat);
		System.out.println("dateTao: " + dateTao);
		System.out.println("dateCapNhat: " + dateCapNhat);
	}
}
