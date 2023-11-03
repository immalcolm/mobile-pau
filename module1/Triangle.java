package module1;

public class Triangle extends Shapes {
    // attributes for Triangle
    public double sideA;
    public double sideC;
    public double base;
    public double height;

    // Triangle object with data specified
    public Triangle(String colour, double sideA, double sideC, double base, double height) {
        super(colour, 3); // pass on variables to the parent class, Shapes
        this.sideA = sideA;
        this.sideC = sideC;
        this.base = base;
        this.height = height;
    }

    // Calculate area and return
    public double getArea() {
        double area = (1.0/2.0) * base * height;
        return area;
    }

    // Calculate perimeter and return
    public double getPerimeter(){
        double perimeter = sideA + base + sideC;
        return perimeter;
    }
    
}
