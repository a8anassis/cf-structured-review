package testbed.review;

public class LowAndHighIndexApp {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 4, 5, 5, 5};
        int[] returned;

        returned =  getLowAndHighIndexOf(arr, 1);

        System.out.printf("(%d, %d)", returned[0] + 1, returned[1] + 1);
    }

    public static int[] getLowAndHighIndexOf(int[] arr, int key) {
        int low = 0, high = 0;
        int pivot;

        if (arr == null) return new int[] {};


        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                low = i;
                break;
            }
        }

        high = low;
        pivot = low + 1;

        while ((pivot < arr.length) && (arr[pivot++] == key)) {
            high++;
        }

        return new int[] { low, high };
    }
}
