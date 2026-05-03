public class lesson_7_task_3 {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        return dividend / divisor;
    }

    public static void main(String[] args) {
        int x = 20;
        int y = 5;
        System.out.println(String.format("%d + %d = %d", x, y, add(x, y)));
        System.out.println(String.format("%d - %d = %d", x, y, subtract(x, y)));
        System.out.println(String.format("%d * %d = %d", x, y, multiply(x, y)));
        System.out.println(String.format("%d / %d = %d", x, y, divide(x, y)));
    }
}
