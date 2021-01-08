public enum CoffeesInMachine {
    ESPRESSO(250,0,16,4),
    LATTE(350,75,20,7),
    CAPPUCCINO(200,100,12,6);

    int water;
    int milk;
    int coffeeBeans;
    int price;

    CoffeesInMachine(int water, int milk, int coffeeBeans, int price) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.price = price;
    }
}
