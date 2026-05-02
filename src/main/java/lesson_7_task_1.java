/** Лекция 2.7: факториал неотрицательного целого числа. */
public class lesson_7_task_1 {

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал определён только для n >= 0");
        }
        long result = 1L;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));
    }
}
