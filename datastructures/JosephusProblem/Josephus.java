import edu.princeton.cs.algs4.StdIn;

public class Josephus extends Queue {

    private Queue<Integer> circle = new Queue<>();

    public void fill(int n) {
        for (int i = 0; i < n; i++) {
            circle.enqueue(i);
        }
    }

    public void eliminationList(int m, int n) {
        fill(n);
        int count;
        Integer deq;
        Integer elim;

        while (!circle.isEmpty()) {
            for (count = 0; count < m - 1; count++) {
                deq = circle.dequeue();
                circle.enqueue(deq);
            }
            if (count == m - 1) {
                elim = circle.dequeue();
                System.out.print(elim + " ");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter values for");
        System.out.println("  m - number of persons to be eliminated");
        System.out.println("  n - total number of persons");
        while (!StdIn.isEmpty()) {
            // get m and n
            int m = StdIn.readInt();
            int n = StdIn.readInt();

            Josephus j = new Josephus();
            j.eliminationList(m, n);

        }
    }
}
