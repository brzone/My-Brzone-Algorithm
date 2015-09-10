package com.facebook.brzone.list;

import java.util.Iterator;

/**
 * 模拟ArrayList
 * @author brzone@126.com
 *
 * @date 2015年9月8日 下午3:09:47
 */
public class MyArrayList<T> implements Iterable<T> {
	
	/**实际数据的元素个数*/
	private int size;
	
	private T[] data;
	
	private static final int DEFAULT_INIT_SIZE = 10;
	
	@SuppressWarnings("unchecked")
	public MyArrayList(){
		data  = (T[])new Object[DEFAULT_INIT_SIZE];
	}
	
	@SuppressWarnings("unchecked")
	public MyArrayList(int size) {
		
		if(size < 1) {
			throw new IllegalArgumentException("size:" + size);
		}
		
		//不能创建泛型数组，可以创建Object数组，然后转型为泛型数组
		data  = (T[])new Object[size];
	}
	
	
	/**ddd
	 * 当调用add(T t)方法时候，需要确定数组是否可以增加保存一个原始，意思是说，是否还有一个空位来保存，如果没有空位的话，
	 * 重新初始化一个新的数组，并扩大相应容量，把旧数组数据copy到新数组中
	 */
	@SuppressWarnings("unchecked")
	private void ensureCapacity(){
		
		//是否需要扩容
		boolean isNeedBiggerCapacity = (size + 1 > data.length);
		
		if(!isNeedBiggerCapacity) {
			return ;
		}
		
		//新的数组
		T[]  newData  = (T[])new Object[size * 2];
		
		//copy
		for(int i = 0;i<size;i++) {
			
			newData[i] = data[i];
			
			//内存回收
			data[i] = null;
		}
		
		//指向新的数组
		data = newData;
		
	}
	
	public void add(T t) {
		
		add( t ,size + 1);
		
	}
	
	public void add(T t ,int index) {
		
		if(index <1) {
			throw new IllegalArgumentException("index:" + index);
		}
		
		ensureCapacity();
		
		//如果正好是在末尾增加数值
		if(index >= size + 1) {
			data[size++] = t;
		
		} else {
			
			//需要移动index后面的值
			for(int i = size;i>=index -1;i--) {
				
				data[i+1] = data[i];
			}
			
			size++;
			
		}
		
	}
	
	/**
	 * 删除指定位置的值，删除第一个，传递0
	 * @param index
	 */
	public void remove(int index) {
		
		if(index < 0) {
			throw new IllegalArgumentException("index:" + index);
		}
		
		if(index >= size) {
			throw new IllegalArgumentException("size:" + size + ",index:" + index);
		}
		
		//删除末尾的数据
		if(index == size -1) {
			
			data[--size] = null;
			
		} else {
			
			//index后面的值要向前移动
			for(int i = index;i<size;i++) {
				
				data[i] = data[i+1];
			}
			
			data[--size] = null; //最后的元素指向为null 
		}
		
	}
	
	public T get(int index) {
		
		if(index < 0) {
			throw new IllegalArgumentException("index:" + index);
		}
		
		if(index >= size) {
			throw new IllegalArgumentException("size:" + size + ",index:" + index);
		}
		
		return data[index];
	}
	
	public void set(T t ,int index) {
		
		if(index <= 0) {
			throw new IllegalArgumentException("size:" + size + ",index:" + index);
		}
		
		if(index > size) {
			throw new IllegalArgumentException("size:" + size + ",index:" + index);
		}
		
		data[index - 1] = t;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		
		if(size <= 0) {
			return "MyArrayList[]";
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("MyArrayList[");
		
		sb.append(data[0]);
		
		for(int i = 1;i<size;i++) {
			
			sb.append(",");
			sb.append(data[i]);
		}
		
		sb.append("]");
		
		return sb.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new MyArrayListIterator();
	}
	
	private class MyArrayListIterator implements Iterator<T> {
		
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < size  ;
		}

		@Override
		public T next() {
			return data[currentIndex++]; 
		}

		@Override
		public void remove() {
			MyArrayList.this.remove(currentIndex);
		}
		
	}

}
