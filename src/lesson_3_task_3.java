public class lesson_3_task_3 {
    public static void main(String[] args) {
        Park park = new Park("Гомельский парк аттракционов");

        park.attractions[0] = park.new Attraction("Колесо обозрения", "11:00 - 21:00", 8);
        park.attractions[1] = park.new Attraction("Автодром", "11:00 - 21:00", 7);
        park.attractions[2] = park.new Attraction("Вальс", "11:00 - 21:00", 6);

        park.printParkInfo();
    }
}
