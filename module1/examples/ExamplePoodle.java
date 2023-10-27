package module1.examples;

public class ExamplePoodle extends ExampleDog{
    //class inheritance - 
    //ExamplePoodle inherits from ExampleDog
    //inherited breed(string)
    //inherit age(int)
    int legs = 5;

    public ExamplePoodle(String breed, int age){
        super(breed, age);
    }

    public void printBreed(){
        age = 10;
        System.out.println("I am a Poodle");
    }

    public void printAge(){
        System.out.println("I am " + age + " years old");
    }

    //overload bark
    @Override
    public void bark(){
        System.out.println("woof woof woof");
    }

    public static void main(String[] args){
        ExamplePoodle poodle = new ExamplePoodle("Poodle", 5);
        poodle.printBreed();
        poodle.printAge();
        poodle.bark();
    }
    
}
