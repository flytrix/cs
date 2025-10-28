import java.util.Arrays;

public class InsertionSort {

        public static void main(String[] args) {
            int[] aoi = {4, 3, 6, 1, 7, 8, 2};
            insertionSort(aoi);
            System.out.println(Arrays.toString(aoi));

        }

        public static void insertionSort(int[] a) {
            int n = a.length;
            for (int i = 1; i < n; i++) {
                for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                    exch(a, j, j - 1);
                }

            }
        }

        // exchange a[i] and a[j]
        private static void exch(int[] a, int i, int j) {
            int swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
    }
