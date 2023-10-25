package module1.examples;
import java.util.Random;

public class RandomNum {
    
    public static void main(String[] args) {
        Random r = new Random();
        System.out.println("Random Number: " + r.nextInt());
        System.out.println("Random Number: " + r.nextDouble());

        System.out.println("Random Number: " + Math.random());
    }
}
/*
 * Math.random() is a convenient way to generate a random double value 
 * between 0.0 and 1.0, while new Random() is more flexible and can be used 
 * to generate random numbers of different types and with a specific seed value.
 */
