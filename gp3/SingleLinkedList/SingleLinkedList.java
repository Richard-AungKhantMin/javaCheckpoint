package gp3.SingleLinkedList;

public class SingleLinkedList implements LinkedList {
    private Node head;
    private int size;

    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    @Override
    public int at(int index) {
        // Implementation for accessing an element by its index
        
        if (index < 0 || index >= size) return -1;
        
        return jump(index).value;
    }

    @Override
    public void add(int value) {
        // Implementation for adding an element at the end of the list
          Node newNode = new Node(value);
        
        if (size == 0){
            this.head = newNode;
        }else{
            Node tail = jump(size-1);
            tail.next = newNode;
        }
        
        size++;
        
    }

    @Override
    public void remove(int index) {
        // Implementation for removing an element by its index
        if (index < 0 || index >= size) return;
        
          if (index == 0){
            this.head = this.head.next;
        }else{
            Node d = jump(index-1);
            d.next = this.next(d.next);
        }
        
        
        
        size--;
    }

    @Override
    public int size() {
        // Implementation for accessing an element by its index
        return this.size;
    }

    private Node next(Node node) {
        // Print the message "Go to next node"
        System.out.println("Go to next node");
        return node.next;
    }
    
    private Node jump(int index){
        Node current = this.head;
        for (int i = 0; i < index; i++){
            current = this.next(current);
        }
        return current;
    }
}