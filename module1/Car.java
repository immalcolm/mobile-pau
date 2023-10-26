package module1;

public class Car{
    private String brand;//default set to private
    public String model;
    int year;
    String color;
    
    public Car(){
        
    }

    public String getBrand(){
        return brand;
    }

    public void displayCar(){
        System.out.println("Brand: " + brand 
        + "Model: " + model 
        + "Year: " + year 
        + "Color: " + color);
    }
}