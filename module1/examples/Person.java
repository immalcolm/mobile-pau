package module1.examples;

public class Person {

    private String name;
    private int age;

    public Person(){}

    // a person has variables name and age.
    public Person(String name, int age) {
        // 'this.name' refers to the 'name' and 'age' instance variables of 'Person'
        // class.
        // 'this.' is used to distinguish them. (avoiding naming conflicts btwn instance
        // variable and constructor parameters)
        this.name = name;
        this.age = age;

        System.out.println("I am a parent class constructor");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
