public class CircularLinkedList implements LinkedList {
    private Node head;
    int size = 0;
    private class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
            this.next = null;
        }
        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public int at(int index) {
        if (size == 0 || index < 0) {
            return -1;
        }
        Node currentNode = head;
        for (int i = 0 ; i < index; i++){
            currentNode = next(currentNode);
        }
        return currentNode.value;
    }

    @Override
    public void add(int value) {
        if(head == null) {
            head = new Node(value);
            head.next = head;

        } else {
            if(this.size == 1) {
               head.next = new Node(value, head);
            } else {
               Node currentNode = head;
               for(int i = 0; i < this.size - 1; i++) {
                   currentNode = next(currentNode);
               }
               currentNode.next = new Node(value, head);
            }
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if (size == 0 || index < 0) {
            return;
        }
        if (index == 0){
            head = head.next;
            size--;
            Node currentNode = head;
            for(int i = 0; i < this.size - 1; i++) {
                currentNode = next(currentNode);
            }
            currentNode.next = head;
        } else {
            Node currentNode = head;
            for(int i = 0; i < index - 1; i++) {
                currentNode = next(currentNode);
            }
            currentNode.next = next(currentNode.next);
            size --;
        }
    }

    @Override
    public int size() {
        return this.size;
        // Implementation getting the size of the list
    }

    private Node next(Node node) {
        System.out.println("Go to next node");
        return node.next;

    }
}