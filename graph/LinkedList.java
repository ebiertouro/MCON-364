package hashing;

import java.util.*;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private int size;

    // Node class to represent elements in the linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    // Iterator implementation
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    // Method to add an element to the end of the list
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        
		if (head == null) {
			head = newNode;
		} else {
			Node<T> current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode; // Append new node at the end
		}
		
		size++;
	}

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

 

    // Method to remove a node with specified data
    public void remove(T data) {
        if (head == null) {
            return;
        }

        // Check if the head node needs to be removed
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;

        // Traverse the list to find the node with the specified data
        while (current != null && !current.data.equals(data)) {
            previous = current;
            current = current.next;
        }

        // If the node is found, remove it
        if (current != null) {
            previous.next = current.next;
            size--;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        current.next = current.next.next;
        size--;
    }

    // Method to get the size of the list
    public int size() {
        return size;
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to print the elements of the list
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
