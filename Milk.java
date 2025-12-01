
public class Milk implements CoffeeDecorator {
    private Coffee coffee;

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public String getCost(){
        return coffee.getCost() +   " + ₱ 25.00";
    }
}
