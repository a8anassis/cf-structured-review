package testbed.review;

public class SecondMinApp {

    public static void main(String[] args) {
        int[] arr = {0, 3, 8, 9, 12, 2};
        int secMin;

        secMin = getSecondMinPosition(arr);
        if (secMin == -1) System.exit(1);
        System.out.println(arr[secMin]);
    }

    public static int getSecondMinPosition(int[] arr) {
        int min = 0;
        int secondMin = 1;
        int minValue = 0;
        int secondMinValue = 0;

        if (arr == null) return -1;
        if (arr.length <= 2) return -1;

        minValue = arr[0];
        secondMinValue = arr[1];

        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] < minValue) && (arr[i] < secondMinValue)) {
                secondMin = min;
                secondMinValue = minValue;
                min = i;
                minValue = arr[i];

            } else if ((arr[i] > minValue) && (arr[i] < secondMinValue)) {
                secondMin = i;
                secondMinValue = arr[i];
            }
        }

        return secondMin;
    }
}
