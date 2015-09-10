package com.facebook.brzone.list;

import java.util.Iterator;

/**
 * ģ��ArrayList
 * @author brzone@126.com
 *
 * @date 2015��9��8�� ����3:09:47
 */
public class MyArrayList<T> implements Iterable<T> {
	
	/**ʵ�����ݵ�Ԫ�ظ���*/
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
		
		//���ܴ����������飬���Դ���Object���飬Ȼ��ת��Ϊ��������
		data  = (T[])new Object[size];
	}
	
	
	/**ddd
	 * ������add(T t)����ʱ����Ҫȷ�������Ƿ�������ӱ���һ��ԭʼ����˼��˵���Ƿ���һ����λ�����棬���û�п�λ�Ļ���
	 * ���³�ʼ��һ���µ����飬��������Ӧ�������Ѿ���������copy����������
	 */
	@SuppressWarnings("unchecked")
	private void ensureCapacity(){
		
		//�Ƿ���Ҫ����
		boolean isNeedBiggerCapacity = (size + 1 > data.length);
		
		if(!isNeedBiggerCapacity) {
			return ;
		}
		
		//�µ�����
		T[]  newData  = (T[])new Object[size * 2];
		
		//copy
		for(int i = 0;i<size;i++) {
			
			newData[i] = data[i];
			
			//�ڴ����
			data[i] = null;
		}
		
		//ָ���µ�����
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
		
		//�����������ĩβ������ֵ
		if(index >= size + 1) {
			data[size++] = t;
		
		} else {
			
			//��Ҫ�ƶ�index�����ֵ
			for(int i = size;i>=index -1;i--) {
				
				data[i+1] = data[i];
			}
			
			size++;
			
		}
		
	}
	
	/**
	 * ɾ��ָ��λ�õ�ֵ��ɾ����һ��������0
	 * @param index
	 */
	public void remove(int index) {
		
		if(index < 0) {
			throw new IllegalArgumentException("index:" + index);
		}
		
		if(index >= size) {
			throw new IllegalArgumentException("size:" + size + ",index:" + index);
		}
		
		//ɾ��ĩβ������
		if(index == size -1) {
			
			data[--size] = null;
			
		} else {
			
			//index�����ֵҪ��ǰ�ƶ�
			for(int i = index;i<size;i++) {
				
				data[i] = data[i+1];
			}
			
			data[--size] = null; //����Ԫ��ָ��Ϊnull 
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
