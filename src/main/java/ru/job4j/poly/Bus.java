package ru.job4j.poly;

public class Bus implements Transport {

    private int numberOfPassenger;

    @Override
    public void move() {
        System.out.println("Bus move");
    }

    @Override
    public void passenger(int number) {
        this.numberOfPassenger += number;
    }

    @Override
    public double refuel(int amount) {
        return 59.8 * amount;
    }
}
