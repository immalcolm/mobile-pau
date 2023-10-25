package module1.examples;

public class ExampleWrapping {
    public static void main(String[] args) {
        int intValue = 42;
        //static method valueOf
        String intString = String.valueOf(intValue); // Convert int to String
        Integer intObject = Integer.valueOf(intValue); // Convert int to Integer

        double doubleValue = 3.14;
        //non static method toString
        //typically used for display purposes
        String doubleString = Double.toString(doubleValue); // Convert double to String

    }
}
/*
# Wrapper classes
In Java, a wrapper class is a class that provides an object representation of a primitive type. 
Java has eight primitive types: boolean, byte, char, short, int, long, float, and double. 
Each of these primitive types has a corresponding wrapper class: Boolean, Byte, Character, Short, Integer, 
Long, Float, and Double.
 
Wrapper classes are useful because they allow primitive types to be treated as objects. 
This means that they can be used in situations where objects are required, 
such as in collections or when passing arguments to methods that expect objects. 
Wrapper classes also provide useful methods for working with primitive types, 
such as converting between different types and performing mathematical operations.

valueOf:

Used for converting various types (often String or primitives) to their corresponding wrapper class objects.
Found in wrapper classes like Integer, Double, etc.
toString:

Used to get a string representation of an object.
Exists in every Java object since it's defined in the Object class. Commonly overridden in custom classes.
In essence, valueOf is about conversion to a wrapper object, whereas toString is about representation as a string.
 */