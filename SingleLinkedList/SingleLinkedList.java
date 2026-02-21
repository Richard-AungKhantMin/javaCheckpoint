public class SingleLinkedList implements LinkedList {
    private Node head;
    private int size;

    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public int at(int index) {
        if (index <0 || index > this.size) {
            return -1;
        }
        if (index == 0) {
            return this.head.value;
        }
        if (index == this.size ) {
            return -1;
        }
        Node currentNode = this.head;
        for (int i = 0; i < index; i++) {
             currentNode = this.next(currentNode);
        }
        return currentNode.value;
    }

    @Override
    public void add(int value) {
        if (head == null) {
            size = 0;
            Node last = null;
            this.head = new Node(value);
            this.head.setNext(last);
        } else {
            Node currentNode =  this.head;
            while (currentNode.next != null) {
                currentNode = this.next(currentNode);
            }
            Node newNode = new Node(value);
            newNode.setNext(currentNode.next);
            currentNode.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public void remove(int index) {
        if (index <0 || index > this.size) {
            return;
        }
        if (index == 0) {
            this.head = this.head.next;
            size--;
            return;
        }
        Node currentNode = this.head;
        for (int i = 0; i < index -1; i++) {
//            if ( index == 2) {
//                System.out.println("currentNode " + currentNode.value);
//                System.out.println("current Step " + i);
//            }

           currentNode = this.next(currentNode);
        }

        currentNode.next = this.next(currentNode.next);
        size--;
    }

    @Override
    public int size() {
        return this.size;
         // Implementation for accessing an element by its index
    }

    private Node next(Node node) {
        System.out.println("Go to next node");
        return node.next;
    }
}