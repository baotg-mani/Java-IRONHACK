package com.devcamp;

public class WrapperExample1 {
	public static void main(String[] args) {
		// Java Program to convert all primitives into its corresponding
		// wrapper objects and vice-versa
		byte b = 10;
		short s = 20;
		int i = 30;
		long l = 40;
		float f = 50.0F;
		double d = 60.0D;
		char c = 'a';
		boolean b2 = true;
		
		// Convert primitives into objects
		Byte byteObj = b;
		Short shortObj = s;
		Integer intObj = i;
		Long longObj = l;
		Float floatObj = f;
		Double doubleObj = d;
		Character charObj = c;
		Boolean boolObj = b2;
		
		// Printing objects
		System.out.println("---Printing object values---");
		System.out.println("Byte object: " + byteObj);
		System.out.println("Short object: " + shortObj);
		System.out.println("Integer object: " + intObj);
		System.out.println("Long object: " + longObj);
		System.out.println("Float object: " + floatObj);
		System.out.println("Double object: " + doubleObj);
		System.out.println("Character object: " + charObj);
		System.out.println("Boolean object: " + boolObj);
		
		// Convert objects to primitives
		byte byteValue = byteObj;
		short shortValue = shortObj;
		int intValue = intObj;
		long longValue = longObj;
		float floatValue = floatObj;
		double doubleValue = doubleObj;
		char charValue = charObj;
		boolean boolValue = boolObj;
		
		// Printing primitives
		System.out.println("---Printing primitive values---");
		System.out.println("byte value: " + byteValue);
		System.out.println("byte value: " + shortValue);
		System.out.println("byte value: " + intValue);
		System.out.println("byte value: " + longValue);
		System.out.println("byte value: " + floatValue);
		System.out.println("byte value: " + doubleValue);
		System.out.println("byte value: " + charValue);
		System.out.println("byte value: " + boolValue);
	}
}
