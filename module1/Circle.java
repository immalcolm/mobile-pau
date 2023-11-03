package module1;

public class Circle extends Shapes{
    // attribues for Circle
    public double radius;

    // Circle object with data specified
    public Circle(String colour, double radius) {
        super(colour, 0); // pass on variables to the parent class, Shapes
        this.radius = radius;
        
    }

    // Calculate area and return
    public double getArea() {
        double area = Math.PI * radius * radius;
        return area;
    }

    // Calculate perimeter and return
    public double getCircumference() {
        double circumference = 2 * Math.PI * radius;
        return circumference;
    }
    
}
