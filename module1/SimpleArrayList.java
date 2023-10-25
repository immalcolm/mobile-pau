package module1;

// import the ArrayList class from java.util package
// specific import
// Generic import >> import java.util.*;
// Reading: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html
import java.util.ArrayList; 

public class SimpleArrayList {
    public static void main(String[] args) {

        // Create an ArrayList of integers
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        // Add some elements to the ArrayList
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        // Display the elements of the ArrayList
        System.out.println("Elements in the ArrayList: " + numbers);
        
        // Access the element at index 0
        System.out.println("What's the first item? : " + numbers.get(0));

        // Modify the element at index 0
        numbers.set(0, 88);
        System.out.println("What's the first item after modifying it ? : " + numbers.get(0));

        // Size of the ArrayList
        System.out.println("Total elements in the ArrayList: " + numbers.size());

        //remove last item
        numbers.remove(numbers.size()-1);
        System.out.println("New total elements in the ArrayList: " + numbers.size() + " >> " + numbers);

        //clear all items
        numbers.clear();
        System.out.println("New total elements in the ArrayList: " + numbers.size() + " >> " + numbers);

    }
}
