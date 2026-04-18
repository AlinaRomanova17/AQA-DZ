import java.util.Arrays;

public class lesson_2_task_14 {
    public static void main(String[] args) {
        int[] resultArray = createArray(8, 7);
        System.out.println(Arrays.toString(resultArray));
    }
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = initialValue;
        }
        return array;
    }
}
