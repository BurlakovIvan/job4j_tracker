package ru.job4j.oop;

public class VehicleUsage {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        Vehicle aeroplane = new Plane();
        Vehicle train = new Train();
        Vehicle bus = new Bus();
        Vehicle busNew = new Bus();
        Vehicle[] vehicles = new Vehicle[] {plane, aeroplane, train, bus, busNew};
        for (Vehicle veh : vehicles) {
            veh.move();
        }
    }
}
