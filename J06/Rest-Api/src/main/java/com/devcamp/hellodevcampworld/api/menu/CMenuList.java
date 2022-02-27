package com.devcamp.hellodevcampworld.api.menu;

import java.util.ArrayList;

public class CMenuList {
	ArrayList<CMenu> menus = new ArrayList<CMenu>();

	public ArrayList<CMenu> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<CMenu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "CMenuList {menus=" + menus + "}";
	}
}
