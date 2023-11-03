package module1.pract7demo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        RectangleWithException rectangle = new RectangleWithException();
        try {
            System.out.print("Enter the length value: ");
            double length = input.nextDouble();
            rectangle.setLength(length);
            System.out.println("The length of the rectangle is " + rectangle.getLength());

            System.out.print("Enter the width of the rectangle: ");
            double width = input.nextDouble();
            rectangle.setWidth(width);
            System.out.println("The length of the rectangle is " + rectangle.getWidth());

            double area = rectangle.calculateArea();
            System.out.println("Area of the rectangle: " + area);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid length or width " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid Area: " + e.getMessage());
        } finally {
            input.close(); // close the scanner input to prevent resource leak
        }
    }
}
