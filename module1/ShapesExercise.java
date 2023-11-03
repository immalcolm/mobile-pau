package module1;

public class ShapesExercise {
    public static void main(String[] args) {
        // create Circle object with specified data
        Circle circle = new Circle("Red", 4);
        // display colour, no. of sides, area and circumference of the circle
        System.out.println("This " + circle.colour + " circle has " + circle.noOfSides + " sides.");
        System.out.println("Area of circle: " + circle.getArea());
        System.out.println("Circumference of circle: " + circle.getCircumference());

        // create Triangle object with specified date
        Triangle triangle = new Triangle("Yellow", 3, 4, 5, 6);
        // display colour, no. of sides, area and perimeter of the triangle
        System.out.println("This " + triangle.colour + " triangle has " + triangle.noOfSides + " sides.");
        System.out.println("Area of triangle: " + triangle.getArea());
        System.out.println("Perimeter of triangle: " + triangle.getPerimeter());
    }
}
