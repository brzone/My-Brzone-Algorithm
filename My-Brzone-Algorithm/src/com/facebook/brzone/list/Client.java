package com.facebook.brzone.list;

public class Client {

	public static void main(String[] args) {
		
		MyArrayList<Integer> myArr = new MyArrayList<Integer>();
		
		for(int i = 0;i<30;i++) {
			
			myArr.add(i);
		}
		System.out.println(myArr);
			
		myArr.set(100, 10);
		myArr.set(200, 20);
		System.out.println(myArr);
		
		myArr.remove(2);
		System.out.println(myArr);
		
		myArr.remove(10);
		System.out.println(myArr);
		
		myArr.add(555);
		myArr.add(666);
		myArr.add(777);
		System.out.println(myArr);
		
		System.out.println("----------" + myArr.size());
		
		//
	}
	
	
	public static void info(MyArrayList<Integer> list) {
		
		
	}
	
}
