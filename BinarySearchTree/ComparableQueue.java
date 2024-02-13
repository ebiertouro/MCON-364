package binarySearchTree;

public class ComparableQueue<T> {

		
		private Node<T> head;
		private Node<T> tail;
		private int size;

		public ComparableQueue () {
			head = null;
			tail = null;
			size = 0;	
		}
		
		@SuppressWarnings("hiding")
		public class Node<T>{
			Comparable<T> data;
			Node<T> next;
		
			Node(Comparable<T> data) {
				this.data = data;
				this.next = null;
		}}
		
		public void enqueue(Comparable<T> data) {
			Node<T> newNode = new Node<T>(data);
			
			
			if (isEmpty()) {
				head = newNode;
			}
			else {
				tail.next = newNode;
			}
			tail = newNode;
			size++;
			
		}
		
		public Comparable<T> dequeue() {
			Comparable<T> data = null;
			if (!isEmpty()) {
				data = head.data;
				head = head.next;
				size--;
			}
			if (isEmpty()) {
				tail = null;
			}
			return data;
		}
		
		public Comparable<T> peek() {
			Comparable<T> data = null;
			if (isEmpty()) {
				throw new IllegalStateException("Queue is empty");
			}
			if (!isEmpty()) {
				return head.data;
			}
			return data;
		}
		
		public boolean isEmpty() {
				return size == 0;
		}
		
		public int size() {
			return size;
		}
				
	}
