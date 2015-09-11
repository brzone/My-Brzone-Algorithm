package com.facebook.brzone.linkedlist.two;

/**
 * 测试
 * @author brzone@126.com
 *
 * @date 2015年9月11日 下午3:21:33
 */
public class Client {
	
	public static void main(String[] args) {
		
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		
		for(int i = 0;i<30;i++) {
			
			list.add(i);
			
		}
		
		/*for(int i = 0;i<30;i++) {
			
			System.out.println(list.get(i));
		}*/
		
	}

}
