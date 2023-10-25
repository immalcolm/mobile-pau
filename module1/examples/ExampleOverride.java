package module1.examples;

public class ExampleOverride {
    public static void main(String[] args) {

        class Animal {
            void eat() {
                System.out.println("This animal eats food.");
            }
        }

        class Dog extends Animal {
            //keyword @Override
            @Override
            void eat() {
                System.out.println("The dog eats dog food.");
            }
        }

        Animal a = new Dog();
        a.eat(); // Outputs: The dog eats dog food.
    }
}
/*
 * Benefits of Method Overriding:
Polymorphism: It allows Java to support runtime polymorphism. 
When a superclass reference variable refers to a subclass object, 
the overridden method in the subclass is called, enabling dynamic method dispatch.

Flexibility: Subclasses can provide a specific implementation of a method 
defined in the superclass without changing the superclass's code.

Maintainability: It helps in creating abstract classes and interfaces which 
define methods but delegate the responsibility of providing an implementation to derived classes.
 */
