
import java.util.InputMismatchException;
import java.util.Scanner;

import module1.pract7session.RecentageWithException;

public class RectApp {

    // setup exception
    public static void main(String[] args) throws Exception {
        //declare and initialize
        Scanner input = new Scanner(System.in);
        RecentageWithException r = new RecentageWithException();

        try {
            //set length
            System.out.println("Enter the length value: ");
            double length = input.nextDouble();
            r.setLength(length);
            System.out.println("The length of the recentagle is " + r.getLength());

            //set width
            System.out.println("Enter the width of the Rectangle: ");
            double width = input.nextDouble();
            r.setWidth(width);
            System.out.println("The width of the rectangle is " + r.getWidth());

            //area
            System.out.println("Area of the rectangle: " + r.getArea());
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Error: " + e.getMessage());
        }catch (InputMismatchException e){
            System.out.println("Please key in numbers only... : " + e.getMessage());
        } 
        finally {
            input.close();
        }

    }
}
