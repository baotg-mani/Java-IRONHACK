package com.devcamp.interface_as_an_parameter;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		// test sum for CIntArray
		int array[] = {1, 2, 3, 4, 5};
		ISumable intArr = new CIntArray(array);
		testSumable(intArr);
		
		// test sum fo CIntegerArrayList
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		arrayList.add(5);
		ISumable intArrList = new CIntegerArrayList(arrayList);
		testSumable(intArrList);
	}
	
	public static void testSumable(ISumable paraISum) {
		System.out.println("Tổng của array là: " + paraISum.getSum());
	}

}
