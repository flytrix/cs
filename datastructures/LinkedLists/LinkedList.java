public class LinkedList {
    private class Node{
        String data;
        Node next;
    }
   // declare head node
    Node head;

   // write constructor
    public LinkedList(){
        head = null;
    }
   // boolean isEmpty()
    public boolean isEmpty(){ //there's only one node and its null
        return head == null;
    }

   // void addLast(String data)
    public void addLast(String value){
        Node n = new Node();
        n.data  = value;
        n.next = null;

        if (isEmpty()) {
            head = n;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = n;
        }
    }

    public void addFirst(String value){
        Node n = new Node();
        n.data = value;
        n.next = null;

        if(isEmpty()){
            head = n;
        }
        else{
            n.next = head;
            head = n;
        }
    }

    public String deleteFirst(){

        if (isEmpty()) {
            return null;
        }

        String item = head.data;
        Node temp = head;
        head = head.next;
        temp = null;

        return item;

    }

    public String deleteLast(){

        if (isEmpty()) {
            return null; // List is empty
        }
        if (head.next == null) {
            // Only one node in the list
            String item = head.data;
            head = null;
            return item;
        }

        // More than one node: Traverse to the second-to-last node
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        String item = current.next.data;
        current.next = null; // Remove the last node
        return item;
    }

    public int theSize(){
        int count = 0;
        Node current = head;
        if(isEmpty()){
            count = 0;
        }
        else{
            count = 1;

        }
        while(current.next!= null){
            count++;
            current = current.next;
        }
        return count;
    }


    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast("A");
        list.addFirst("B");
        list.addFirst("C");
        list.addFirst("D");
        System.out.println(list.theSize());


    }


}
