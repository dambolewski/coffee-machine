public class CoffeeMachine {
    public static void main(String[] args) {
        MachineMethods mm = MachineMethods.IDLE;

        while(mm!=MachineMethods.EXIT){
            mm = mm.nextState();
        }
    }
}
