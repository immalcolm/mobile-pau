package module1.examples;

//Why super?
/* If the parent class does not have a 
  no-argument constructor and you don't 
  provide any explicit call to another 
  parent constructor, you will get a compile-time error. 
  This scenario typically arises when you've defined a 
  parameterized constructor in the parent class 
  but haven't defined a no-arg constructor.
*/

public class ExampleInheritMe extends Person {

    private int id;

    public ExampleInheritMe(String name, int age, int id) {

        super(name, age);//explicit call to the constructor of the parent class
        this.id = id;
        System.out.println("i am a child class constructor");
    }

    public static void main(String[] args) {
        ExampleInheritMe child = new ExampleInheritMe("John", 20 ,1);
        System.out.println("Name: " + child.getName());
        System.out.println("Age: " + child.getAge());
    }


}
