package com.facebook.brzone.linkedlist;

/**
 * 测试
 * @author brzone@126.com
 *
 * @date 2015年9月10日 下午5:58:30
 */
public class Client {

	public static void main(String[] args) {

		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		
		for(int i = 0;i<30;i++) {
			list.add(i);
		}
		
		list.remove(1);
		
		list.remove(3);
		
		//位置从1开始，不是从0开始的
		for(int i = 1;i<=list.size();i++) {
			
			System.out.println(list.get(i));
		}
		
		System.out.println("list.size():" + list.size());
		
	}

}
