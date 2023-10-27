package module1.examples;

public class ExampleDog {
    
    String breed;
    int age;

    public ExampleDog(String breed, int age){
        this.breed = breed;
        this.age = age;
    }

    public void bark(){
        System.out.println("woof!!");
    }
}
