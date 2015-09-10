package com.facebook.brzone.list;

import java.util.Iterator;

/**
 * 测试
 * 
 * @author brzone@126.com
 *
 * @date 2015年9月10日 下午2:31:49
 */
public class ClientTest {

	public static void main(String[] args) {

		MyArrayList<Integer> myArr = new MyArrayList<Integer>();

		for (int i = 0; i < 30; i++) {

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

		Iterator<Integer> iterator = myArr.iterator();

		while (iterator.hasNext()) {

			Integer i = iterator.next();
			System.out.println(i);
			iterator.remove();
		}

		System.out.println(myArr);
	}
}
