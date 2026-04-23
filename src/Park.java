public class Park {
    String parkName;
    Attraction[] attractions = new Attraction[3];

    public Park(String parkName) {
        this.parkName = parkName;
    }

    public void printParkInfo() {
        System.out.println("Парк: " + parkName);
        System.out.println("Аттракционы:");
        for (int i = 0; i < attractions.length; i++) {
            if (attractions[i] != null) {
                attractions[i].printAttractionInfo();
            }
        }
    }

    class Attraction {
        String attractionName;
        String workingHours;
        int ticketPrice;

        public Attraction(String attractionName, String workingHours, int ticketPrice) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.ticketPrice = ticketPrice;
        }

        public void printAttractionInfo() {
            System.out.println("Название: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + ticketPrice);
            System.out.println("---------------");
        }
    }
}
