/** Лекция 2.7: сравнение двух целых чисел (как Integer.compare). */
public class lesson_7_task_4 {

    /** Если a меньше b — результат отрицательный; равны — ноль; a больше b — положительный */
    public static int compareInts(int a, int b) {
        return Integer.compare(a, b);
    }

    public static void main(String[] args) {
        System.out.println(compareInts(3, 5));
        System.out.println(compareInts(7, 7));
        System.out.println(compareInts(9, 1));
    }
}
