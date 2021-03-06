package com.facebook.brzone.linkedlist;

/**
 * 模拟链表LinkedList （粗版本）
 * 
 * 1. 位置从1开始
 * 
 * 2. 只是实现单链表，意思说，通过一个节点可以直接找到它的next节点，但是不能直接找到它的prev节点
 * 
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
	
	/**
	 * index从1开始
	 * @param index
	 */
	public void remove(int index) {
		
		if(index < 1 || index > size()) {
			
			throw new IndexOutOfBoundsException("size:" + size() + ",index:" + index);
		}
		
		/**
		 * 1. 找出index的前一个元素和下一个元素（下一个元素也许为nll，意思说，index所在元素也许是end末尾元素，这个要特殊对待）
		 * 
		 * 2. 修改引用指向
		 * 
		 */
		
		
		Node<T> indexNode = getNode( index);  //index元素节点
		
		//如果index是首元素
		if(indexNode == first) {
			
			//意思说，只有一个首元素
			if(end == null) {
				first = null;
				size--;
				return ;
			}
			
			//找首元素的下一个元素作为首元素即可
			first = getNode( index+1);
			size--;
			return ;
			
		}
		
		
		//如果index元素是末元素
		if(indexNode == end) {
			
			first.next = null;
			end = null;
			size --;
			return;
		}
		
		//index既不是first元素也不是end元素，证明index元素有前驱和后置节点
		
		
		Node<T> indexPrevNode = getNode( index-1);  //index的上一个元素节点
		
		Node<T> indexNextNode = getNode( index+1);  //index的上一个元素节点
		
		indexPrevNode.next = indexNextNode;
		indexNode = null;  //让gc在必要时间回收掉
		
		size --;
	}
	
	/**
	 * 获取指定的index，index从1开始
	 * @param index
	 * @return
	 */
	public T get(int index) {
		
		return getNode(index).data;
	}
	
	/**
	 * index从1开始
	 * @param index
	 * @return
	 */
	private Node<T> getNode(int index)  {
		
		if(index < 1 || index > size()) {
			
			throw new IndexOutOfBoundsException("size:" + size() + ",index:" + index);
		}
		
		//循环next，获取指定index的值    （这里也可以有个优化点，如果index靠末尾，就从末尾遍历，靠前就从前遍历）
		Node<T> getFirst = first;
		
		for(int i = 1;i<index;i++) {
			
			getFirst = getFirst.next;
		}
		
		return getFirst;
	}
	
	/**
	 * index从1开始
	 * @param t
	 * @param index
	 */
	public void set(T t ,int index) {
		
		 Node<T> indexNode =  getNode( index);
		 
		 indexNode.data = t;
	}
	
	public int size() {
		return size;
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	public Node<T> getFirst() {
		return first;
	}
	
	
	/**
	 * 节点。因为不需要外部类的this引用，就设置为静态的内部类
	 * @author brzone@126.com
	 *
	 * @date 2015年9月11日 上午10:50:20
	 */
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
		

		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + (next == null ? null : next.getData() ) + "]";
		}
		
	}
	
}
