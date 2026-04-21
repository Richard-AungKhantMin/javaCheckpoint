package gp3.DoubleLinkedList;

public class DoubleLinkedList implements LinkedList {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        int value;
        Node next;
        Node prev;
        
         Node(int value){
            this.value = value;
        }
    }
    
  public Node goToIndex(int index){
    if (index <= (size - 1) / 2) {
        // Closer to head - traverse forward
        Node current = this.head;
        for (int i = 0; i < index; i++){
            current = this.next(current);
        }
        return current;

    } else {
        // Closer to tail - traverse backward
        Node current = this.tail;
        for (int i = size - 1; i > index; i--){
            current = this.prev(current);
        }
        return current;
    }
}

    @Override
    public int at(int index) {
       if(index < 0 || index>=size) return -1;
       
       Node current = goToIndex(index);
       return current.value;
    }

    @Override
    public void add(int value) {
       Node newNode = new Node(value);
       
       if(head == null){
          head = newNode;
          tail = newNode;
       }else{
           tail.next = newNode;
           newNode.prev = tail;
           
           tail = newNode;
       }
       size++;
    }

    @Override
    public void remove(int index) {
        if(index <0|| index >= size) return;
        
        Node current = goToIndex(index);

        if (index == 0) {
    
        head = current.next;
        size--;
        return;

        }
        
        if (index == size - 1) {
    
            tail = current.prev;
           size--;
           return;

        }

        current.prev.next = current.next;
        current.next.prev = current.prev;

        size--;
    }

    @Override
    public int size() {
       return size;
    }

    private Node next(Node node) {
        System.out.println("Go to next node");
        return node.next;
    }

    private Node prev(Node node) {
        System.out.println("Go to previous node");
        return node.prev;
    }
}