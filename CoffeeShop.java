public class CoffeeShop{
    public static void main (String []args){
        Coffee blackCoffee = new BlackCoffee();

        System.out.println(blackCoffee.getDescription());
        System.out.println(blackCoffee.getCost());

        Milk milk = new Milk();
        milk.setCoffee(blackCoffee);

        System.out.println(milk.getDescription());
        System.out.println(milk.getCost());

        CaramelSyrup caramelSyrup = new CaramelSyrup();
        caramelSyrup.setCoffee(blackCoffee);

        System.out.println(caramelSyrup.getDescription());
        System.out.println(caramelSyrup.getCost());

        CaramelSyrup milkCaramel = new CaramelSyrup();
        milkCaramel.setCoffee(milk);

        System.out.println(milkCaramel.getDescription());
        System.out.println(milkCaramel.getCost());

    }
}