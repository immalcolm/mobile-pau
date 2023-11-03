package module1.pract7demo;

import java.util.*;

public class RectangleWithException {
    private double length;
    private double width;

    public void setLength(double length) throws IllegalArgumentException{
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive number.");
        }
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be a positive number.");
        }
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double calculateArea() throws Exception {
        double area;
        area = length * width;
        if (area > 10000) {
            throw new Exception("The area for the soccer field is too large!");
        }
        return area;
    }
}
