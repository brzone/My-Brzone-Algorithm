package com.facebook.brzone.linkedlist.two;

/**
 * 
 * @author brzone@126.com
 * 
 * @Function
 *      1. 一些基本操作(add、set、remove etc)
 *      2. 双向链表
 *      3. 头尾的标记节点（这样可以避免一些特殊情况的判断），虚拟的节点，比如，头节点的prev就是headMarkNode，尾节点的next是tailMarkNode，
 *         我们的操作就是在headMarkNode和tailMarkNode之间进行操作
 *      
 * @date 2015年9月11日 上午11:54:46
 */
public class MyLinkedList<T> {
	
	private int size;
	
	private Node<T> headMarkNode;
	
	private Node<T> tailMarkNode;
	
	public MyLinkedList() {
		super();
		clear();
	}
	
	
	public void add(T t) {
		
		add(t,size);
	}

	public void add(T t,int index) {
		
		//意思就是说，要在indexNode的前面插入T
		Node<T> indexNode =  getNode(index);
		
		Node<T> p = new Node<T>(t,indexNode.prev,indexNode);
		
		indexNode.prev.next = p;
		
		indexNode.prev = p;
		
		size++;
	}
	
	public void remove(int index) {
		
		
	}
	
	
	/**
	 * 获取index位上的元素
	 * @param index 从0开始
	 */
	private Node<T> getNode(int index) {
			
		rangeCheck(index);
		
		//这里可以优化，如果index比size/2小，就往头部遍历，反之，往尾部遍历
		Node<T> p =  headMarkNode.next;
		
		for(int i = 0;i<index;i++) {
			p = p.next;
		}
		
		return p;
	}
	
	/**
	 * 获取指定位置的元素
	 * @param index index从0开始
	 * @return
	 */
	public T get(int index) {
		return getNode(index).data;
	}
	
	/**
	 * 检测index的合法性
	 * @param index
	 */
	private void rangeCheck(int index) {
		
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("size:" + size + ",index:" + index);
		}
	}
	
	

	public int size() {
		return size;
	}

	/**
	 * 清空，也相当于重新初始化
	 */
	public void clear(){
		
		headMarkNode = new Node<T>(null,null,tailMarkNode);	
		
		tailMarkNode  = new Node<T>(null,headMarkNode,null);
		
		size = 0;
	}
	
	//节点
	private static class Node<T> {
		
		T data;
		
		Node<T> prev;
		
		Node<T> next;

		public Node(T data, Node<T> prev, Node<T> next) {
			super();
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
	}
}
