package module1.examples;

public class NonPrimitiveDataType {
    public static void main(String[] args) {
        // String class is used. String is a non-primitive data type
        // that stores a string value.
        String greeting = "Hello World";
        // Arrays are non-primitive data types.
        int[] numbers = {1, 2, 3, 4, 5};
        String[] colors = {"Red", "Green", "Blue"};
        // Creating a custom class as a non-primitive data type
        Person person1 = new Person("John", 30);
        Person person2 = new Person("Alice", 25);
        // Printing values
        System.out.println("String: " + greeting);
        System.out.print("Numbers: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.print("Colors: ");
        for (String color : colors) {
            System.out.print(color + " ");
        }
        System.out.println();
        System.out.println("Person 1: " + person1.getName() + ", Age: " + person1.getAge());
        System.out.println("Person 2: " + person2.getName() + ", Age: " + person2.getAge());
    }

}
