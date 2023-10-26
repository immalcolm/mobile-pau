package module1.examples;

import module1.Car;

public class MakeCar {
    
    public static void main(String[] args) {
        Car aCar = new Car(); // call the Car class and create object
        aCar.displayCar();
        //aCar.brand = "Toyata";
        String x = aCar.getBrand();
        aCar.color = "Blue";
        aCar.year = 2015;
        aCar.model = "Camry";//model set to public
        //aCar.displayCar();
    }
}
