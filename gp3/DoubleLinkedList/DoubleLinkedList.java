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
        Node current;
        int step = index;
        
        if (head == null){
            current = head;
        }else if (tail == null){
            current = tail;
        }else if (index <= (size - 1)/2){
            current = head;
            
            
            for(int i = 0; i < step ; i++){
                current = next(current);
            }
        }else{
            current = tail;
            
            for (int i = size -1; i> step;i--){
                current = prev(current);
            }
        }
        return current;
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
           head =tail= newNode;
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
        if(current.prev!= null){
            current.prev.next = current.next;
        }else{
            head = current.next;
        }
                   
        if (current.next != null){
            current.next.prev = current.prev;
        }else{
            tail = current.prev;
        }
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