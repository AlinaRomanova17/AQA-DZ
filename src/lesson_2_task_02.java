public class lesson_2_task_02 {
    public static void main(String[] args) {
        checkSumSign();
    }
    public static void checkSumSign() {
        int a = 7;
        int b = -2;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }
}

