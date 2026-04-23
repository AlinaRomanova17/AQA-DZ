public class lesson_3_task_2 {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Nokia G42", "12.06.2024", "Nokia", "Finland", 899, false);
        productsArray[1] = new Product("Redmi Note 13", "05.01.2024", "Xiaomi", "China", 999, true);
        productsArray[2] = new Product("Samsung A55", "11.03.2024", "Samsung", "Korea", 1299, false);
        productsArray[3] = new Product("iPhone 13", "24.09.2021", "Apple", "USA", 1999, true);
        productsArray[4] = new Product("Realme 12", "20.02.2024", "Realme", "China", 949, false);

        for (int i = 0; i < productsArray.length; i++) {
            productsArray[i].printInfo();
        }
    }
}
