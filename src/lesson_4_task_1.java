public class lesson_4_task_1 {
    public static void main(String[] args) {
        Dog dog = new Dog("Бобик");
        Cat cat = new Cat("Барсик");
        dog.run(150);
        dog.swim(8);
        cat.run(180);
        cat.swim(2);
        Bowl bowl = new Bowl(25);
        Cat[] cats = {new Cat("Мурзик"), new Cat("Снежок"), new Cat("Рыжик")};
        cats[0].eat(bowl, 10);
        cats[1].eat(bowl, 12);
        cats[2].eat(bowl, 8);
        bowl.printInfo();
        bowl.addFood(5);
        bowl.printInfo();
        for (Cat oneCat : cats) {
            oneCat.printSatiety();
        }
        System.out.println("Всего животных: " + Animal.animalsCount);
        System.out.println("Всего собак: " + Dog.dogsCount);
        System.out.println("Всего котов: " + Cat.catsCount);
    }
}

class Animal {
    String name;
    static int animalsCount;

    public Animal(String name) {
        this.name = name;
        animalsCount++;
    }

    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м.");
    }
}

class Dog extends Animal {
    static int dogsCount;

    public Dog(String name) {
        super(name);
        dogsCount++;
    }

    public void run(int distance) {
        if (distance <= 500) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может столько бежать");
        }
    }

    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может столько плыть");
        }
    }
}

class Cat extends Animal {
    static int catsCount;
    boolean isFull;

    public Cat(String name) {
        super(name);
        catsCount++;
        isFull = false;
    }

    public void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может столько бежать");
        }
    }

    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public void eat(Bowl bowl, int foodAmount) {
        if (bowl.takeFood(foodAmount)) {
            isFull = true;
            System.out.println(name + " поел");
        } else {
            System.out.println(name + " не поел");
        }
    }

    public void printSatiety() {
        System.out.println(name + " сытость: " + isFull);
    }
}

class Bowl {
    int food;

    public Bowl(int food) {
        this.food = food;
    }

    public boolean takeFood(int amount) {
        if (amount <= food) {
            food -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        food += amount;
        System.out.println("В миску добавили " + amount + " еды.");
    }

    public void printInfo() {
        System.out.println("В миске сейчас: " + food + " еды.");
    }
}
