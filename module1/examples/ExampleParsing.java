package module1.examples;

public class ExampleParsing {

    public static void main(String[] args) {
        String numberString = "123";
        int parsedInt = Integer.parseInt(numberString); // Parse a string to an int

        System.out.println("Type of:" + parsedInt + ">>" + ((Object) parsedInt).getClass().getName()); // int

        // @TODO what if we parse a string that is not a number?
        // String notANumber = "abc";
        // int parsedNotANumber = Integer.parseInt(notANumber); // Parse a string to an int
        try {
            String notANumber = "abc";
            int parsedNotANumber = Integer.parseInt(notANumber); // Parse a string to an int

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        String doubleString = "3.14";
        double parsedDouble = Double.parseDouble(doubleString); // Parse a string to a double
        System.out.println("Type of:" + parsedInt + ">>" + ((Object) parsedDouble).getClass().getName()); // int

    }
}
/*
#[Summary:]
## parseInt:
- Converts a String representation of an integer to a primitive int.
- Can throw a NumberFormatException if the string doesn't represent a valid integer.

## (int):
Casts one primitive type to another (e.g., double to int).
Can result in data loss but doesn't throw exceptions for invalid conversions.
In essence, you'd use parseInt when you have a string that you want to convert to an integer, and you'd use (int) when you have a value of one primitive type that you want to cast to the int type.
 */