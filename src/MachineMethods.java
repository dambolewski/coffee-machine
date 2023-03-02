import java.util.Scanner;

public enum MachineMethods {
    IDLE() {
        @Override
        public MachineMethods nextState() {
            System.out.println("Write action (buy,fill,take,remaining,exit):");
            String input = getInput();

            switch (input) {
                case "buy":
                    return BUY_CHOICE;
                case "fill":
                    return FILL_MACHINE;
                case "take":
                    return TAKE_MONEY;
                case "remaining":
                    return REMAINING;
                case "exit":
                    return EXIT;
                default:
                    System.out.println("Error occurred - Machine turning off");
                    return EXIT;
            }
        }
    },
    BUY_CHOICE() {
        @Override
        public MachineMethods nextState() {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            String choice = getInput();
            switch (choice) {
                case "1":
                    makeCoffee(CoffeesInMachine.ESPRESSO);
                    break;
                case "2":
                    makeCoffee(CoffeesInMachine.LATTE);
                    break;
                case "3":
                    makeCoffee(CoffeesInMachine.CAPPUCCINO);
                    break;
                case "back":
                    return IDLE;
                default:
                    System.out.println("learn to type!");
            }
            return IDLE;
        }
    },
    FILL_MACHINE {
        @Override
        public MachineMethods nextState() {
            return FILL_WATER;
        }
    },
    FILL_WATER {
        @Override
        public MachineMethods nextState() {
            System.out.println("Write how many ml of water do you want to add:");
            cmWater += Integer.parseInt(getInput());
            return FILL_MILK;
        }
    },
    FILL_MILK {
        @Override
        public MachineMethods nextState() {
            System.out.println("Write how many ml of milk do you want to add: ");
            cmMilk += Integer.parseInt(getInput());
            return FILL_COFFEE;
        }

    },
    FILL_COFFEE {
        @Override
        public MachineMethods nextState() {
            System.out.println("Write how many grams of coffee beans do you want to add: ");
            cmCoffeeBeans += Integer.parseInt(getInput());
            return FILL_CUPS;
        }
    },
    FILL_CUPS {
        @Override
        public MachineMethods nextState() {
            System.out.println("Write how many disposable cups of coffee do you want to add: ");
            cmCups += Integer.parseInt(getInput());
            return IDLE;
        }
    },
    TAKE_MONEY {
        @Override
        public MachineMethods nextState() {
            System.out.println("I gave you " + cmMoney + "$");
            System.out.println();
            cmMoney = 0;
            return IDLE;
        }
    },
    REMAINING {
        @Override
        public MachineMethods nextState() {
            System.out.println("The coffee machine has");
            System.out.println(cmWater + " of water");
            System.out.println(cmMilk + " of milk");
            System.out.println(cmCoffeeBeans + " of coffee beans");
            System.out.println(cmCups + " of disposable cups");
            System.out.println("$" + cmMoney + " of money");
            System.out.println();
            return IDLE;
        }
    },
    EXIT {
        @Override
        public MachineMethods nextState() {
            return null;
        }
    };


    public void makeCoffee(CoffeesInMachine coffee) {
        int water = coffee.water;
        int milk = coffee.milk;
        int coffeeBeans = coffee.coffeeBeans;
        int price = coffee.price;
        int cups = 1;

        if (cmWater - water < 0) {
            System.out.println("Sorry not enough water! \n");
            return;
        }
        if (cmMilk - milk < 0) {
            System.out.println("Sorry not enough milk! \n");
            return;
        }
        if (cmCoffeeBeans - coffeeBeans < 0) {
            System.out.println("Sorry not enough coffee beans! \n");
            return;
        }
        if (cmCups - cups < 0) {
            System.out.println("Sorry not enough cups! \n");
        }
        System.out.println("I have enough resources, making you coffee \n");
        cmCups -= cups;
        cmWater -= water;
        cmMilk -= milk;
        cmCoffeeBeans -= coffeeBeans;
        cmMoney += price;
    }


    static int cmWater = 400;
    static int cmMilk = 540;
    static int cmCoffeeBeans = 120;
    static int cmCups = 9;
    static int cmMoney = 550;

    public abstract MachineMethods nextState();

    Scanner sc = new Scanner(System.in);

    String getInput() {
        return sc.nextLine();
    }
}
