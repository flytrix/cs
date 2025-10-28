public class GenericLinkedList<Item> {

    private Node first;

    private class Node {
        Item item;
        Node next;
    }

    public GenericLinkedList() { //an empty List of String
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void addFirst(Item str) { //adds a new Node with item str at the beginning of this list
        Node newNode = new Node();
        newNode.item = str;
        newNode.next = null;
        newNode.next = first;
        first = newNode;
    }

    public Item deleteFirst() {
        if (first == null) {
            return null; // List is empty
        }

        Item item = first.item;
        first = first.next; // Move the head to the next node
        return item;
    }

    public void addLast(Item str) {
        Node newNode = new Node();
        newNode.item = str;
        newNode.next = null;

        if (first == null) {
            // If the list is empty, new node becomes the first node
            first = newNode;
            return;
        }

        // Otherwise, traverse to the end of the list and add the new node
        Node current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public Item deleteLast() {
        if (first == null) {
            return null; // List is empty
        }

        if (first.next == null) {
            // Only one node in the list
            Item item = first.item;
            first = null;
            return item;
        }

        // More than one node: Traverse to the second-to-last node
        Node current = first;
        while (current.next.next != null) {
            current = current.next;
        }

        Item item = current.next.item;
        current.next = null; // Remove the last node
        return item;
    }

    public void printList() {
        for (Node current = first; current != null; current = current.next) {
            System.out.println(current.item + " ");
        }
        System.out.println("done");
    }

    public static void main(String[] args) {
        GenericLinkedList llos = new GenericLinkedList();
        llos.addFirst( 1);
        llos.addFirst("A");
        llos.addFirst(6.79);
        llos.printList(); // Z A C

        llos.addLast("X");
        llos.printList(); // Z A C X

        llos.deleteFirst();
        llos.printList(); // A C X

        llos.deleteLast();
        llos.printList(); // A C
    }
}
