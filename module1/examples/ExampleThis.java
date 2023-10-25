package module1.examples;

public class ExampleThis {

    //properties
    private int num;

    public void setNum(int num) {
        this.num = num; // Assign parameter to instance variable using "this"
    }

    public static void main(String[] args) {
        ExampleThis obj = new ExampleThis();
        obj.num = 5;//this works but is not best practice
        obj.setNum(10);
        System.out.println(obj.num);
    }
}

/*
 * 
In Java, if you do not specify an access modifier for a member (be it a field/property, method, or inner class), 
it will have "default" (often also called "package-private") access. This means:

Visibility: The member with default access modifier is visible only within its own package. 
It cannot be accessed from classes in other packages.

Notation: There's no keyword for the default access modifier; 
you simply don't add an access modifier.

private: The member is accessible only within its own class.
default (package-private): The member is accessible only within its own package.
protected: The member is accessible within its own package and by subclasses.
public: The member is accessible from any other class.
 */
