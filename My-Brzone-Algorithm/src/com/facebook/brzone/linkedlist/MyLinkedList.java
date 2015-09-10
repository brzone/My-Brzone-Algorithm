package com.facebook.brzone.linkedlist;

/**
 * 模拟链表LinkedList
 * @author brzone@126.com
 *
 * @date 2015年9月10日 下午4:04:14
 */
public class MyLinkedList<T> {
	
	private Node<T> first;
	
	private Node<T> end;
	
	private int size;

	public void add(T t) {
		
		add(t ,size+1);
	}
	
	/**
	 * 添加元素置于index位置，index从1开始
	 * @param t
	 * @param index index从1开始
	 */
	public void add(T t ,int index) {
		
		if(first == null) {
			
			first = new Node<T>(t);
			
			++size;
			
			return ;
		}
		
		//那这个意思就是，这个链表现在只有一个元素first
		if(end == null) {
			
			end = new Node<T>(t);
			
			first.next = end;  //设置首元素的next元素为end
			
			++size;
			
			return ;
		}
		
		
		//如果要放置的位置大于size，那就直接设置为end的next元素，当然end元素是当前需要创建的新元素
		if(index > size) {
			
			Node<T> newDate = new Node<T>(t);
			
			end.next = newDate;
			
			end = newDate;
			
			++size;
			
			return ;
		}
		
		//当然剩下就是要插入到first和end之间的
		
		//index位置的上一个元素
		Node<T> lastIndexNode = first;
		
		for(int i = 1;i<index-2;i++) {
			
			lastIndexNode = lastIndexNode.next;
		}
		
		//index位置的上元素
		Node<T> indexNode = lastIndexNode.next;
		
		
		Node<T> newDate = new Node<T>(t);
		
		lastIndexNode.next = newDate;  //index位置的上一个元素的next元素执行新元素
		
		newDate.next = indexNode; //新元素的next指向原来旧的index元素
		
		++size;
	}
	
	public void remove(int index) {}
	
	/**
	 * 获取指定的index，index从1开始
	 * @param index
	 * @return
	 */
	public T get(int index) {
		
		if(index < 1 || index > size()) {
			
			throw new IndexOutOfBoundsException("size:" + size() + ",index:" + index);
		}
		
		//循环next，获取指定index的值    （这里也可以有个优化点，如果index靠末尾，就从末尾遍历，靠前就从前遍历）
		Node<T> getFirst = first;
		
		for(int i = 1;i<index;i++) {
			
			getFirst = getFirst.next;
		}
		
		return getFirst.data;
	}

	public void set(T t ,int index) {
		
	}
	
	public int size() {
		return size;
	}
	
	public Node<T> getFirst() {
		return first;
	}
	
	
	private static class Node<T> {
		
		T data;
		
		Node<T> next;
		
		public Node(T data) {
			super();
			this.data = data;
			this.next = null;
		}
		

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + (next == null ? null : next.getData() ) + "]";
		}
		
	}
	
}
