package binarySearchTree;
import java.util.*;

public class ComparableStack<T> {
		
		@SuppressWarnings("hiding")
		public class Node<T>{
		
			Comparable<T> value;
			Node<T> next;

			Node(Comparable <T> value) {
				this.value = value;
				next = null;
		}}

		Node<T> top;
		int size;

		ComparableStack() {

			this.top = null;
			this.size = 0;
		}

		ComparableStack(Comparable<T> value) {

			this.size = 0;
			Node<T> n = new Node<T>(value);
			this.top = n;
		}
		 	 
		    // this method adds an element to the top of the stack
		    
		public void push (Comparable <T> value) {
			
			 Node<T> node = new Node<T>(value);
			 node.next = top;
			 top = node;
			 size++;
			 }
		  
		//this method returns and removes the top element
		
		public Comparable <T> pop () {
			 
			 if (size==0) 
				 throw new EmptyStackException();
			 
			 else {
				Node<T> popped = top;
				top = top.next;
				size --;
				return (Comparable<T>) popped.value;
			 }}
		
			// this method returns the value of the top element
		
			public Comparable<T> peek () {
			 
			 if (size==0) {
				 throw new EmptyStackException();	 	
			 }
			 
			 else {
				return top.value;
				
				
		}}
		    public boolean isEmpty() {
		        return size == 0;
		    }
		    
		    public int size() {
		        return size;
		    }
}
