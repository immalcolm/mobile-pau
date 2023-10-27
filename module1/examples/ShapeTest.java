package module1.examples;
//working with packages
import module1.shapes.CircleClass;
import module1.shapes.TriangleClass;

public class ShapeTest {
    
    public static void main(String[] args) {
        CircleClass circle = new CircleClass();
        TriangleClass triangle = new TriangleClass();
        System.out.println("Circle: " + circle);
        System.out.println("Triangle: " + triangle);
        
        circle.printType();
        triangle.printType();
    }
}
