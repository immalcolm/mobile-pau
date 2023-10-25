package module1.examples;

public class ExampleInnerClass {

    public static void main(String[] args) {
        ExampleOuterClass outer = new ExampleOuterClass(); // call outer class object
        ExampleOuterClass.InnerClass inner = outer.new InnerClass(); // call the inner class object
        System.out.println(outer.num1 + " : " +  inner.num2);
    }

}
