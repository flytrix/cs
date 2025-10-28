import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

    private Node<Item> last;     // end of queue
    private int size;            // queue size

    // Node class
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    // constructor
    public Queue() {
        last = null;
        size = 0;
    }

    // check if queue is empty
    public boolean isEmpty() {
        return last == null;
    }

    // return the size of the queue
    public int size() {
        return size;
    }

    // Returns the item least recently added to this queue.
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return last.item;
    }

    // add item to the queue
    public void enqueue(Item item) {

        if (isEmpty()) {
            last = new Node<Item>();
            last.item = item;
            last.next = last;
        } else {
            Node<Item> oldLast = last;
            Node<Item> first = last.next;
            last = new Node<Item>();
            last.item = item;
            last.next = first;
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item on the queue that was least recently added
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Node<Item> oldFirst = last.next;
        Node<Item> first = oldFirst.next;
        Item item = oldFirst.item;
        if (oldFirst == first) {
            last = null;
            size--;
            return item;
        }
        last.next = first;
        size--;
        if (isEmpty()) last = null;
        return item;
    }

    // return a string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        // iterate from the first node
        return new LinkedIterator(last.next);
    }

    // implement iterator
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        // need following variable to know if we have come full circle
        private boolean atFirstNode = true;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            if (current == last.next && !atFirstNode)
                return false;
            return true;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            atFirstNode = false; // set this to false since we are not at first node
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> qos = new Queue<String>();
        System.out.println("size = " + qos.size());
        qos.enqueue("A");
        System.out.println(qos.dequeue());
        qos.enqueue("A");
        qos.enqueue("B");
        qos.enqueue("C");
        qos.enqueue("D");
        System.out.println("size = " + qos.size());
        System.out.println("\n\nfor-each test: ");
        for (String s : qos)
            System.out.println(s);
        System.out.println("\n\ntoString test: ");
        System.out.println(qos);
        System.out.println("\n\ndequeue all test: ");
        while(!qos.isEmpty())
            System.out.println(qos.dequeue());
        //  exception System.out.println(qos.dequeue());
        Queue<Integer> qoi = new Queue<Integer>();
        for (int i = 100; i >= 0; i -= 10) {
            qoi.enqueue(i);
        }
        System.out.println("Queue of ints: " + qoi);
        System.out.println("size = " + qoi.size());
        for (int count = 0; count < qoi.size; count++) {
            int val = qoi.dequeue();
            qoi.enqueue(val);
        }
        System.out.println("Queue of ints: " + qoi);
        System.out.println("size = " + qoi.size());
    }
}
