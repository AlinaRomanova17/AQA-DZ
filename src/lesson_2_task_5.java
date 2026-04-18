public class lesson_2_task_5 {
    public static void main(String[] args) {
        System.out.println(checkSumInRange(5, 7));
        System.out.println(checkSumInRange(2, 3));
    }

    public static boolean checkSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }
}
