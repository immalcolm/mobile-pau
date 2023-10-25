package module1.examples;

public class ExampleOverloadingMath {

    int addNum(int a, int b) {
        return a + b;
    }

    double addNum(double a, double b) {
        return a + b;
    }

    public static void main(String[] args){
        ExampleOverloadingMath obj = new ExampleOverloadingMath();
        System.out.println(obj.addNum(10, 20));
        System.out.println(obj.addNum(10.5, 20.5));
        
        //@TODO what happens if we do this? Will the overloading work?
        //System.out.println(obj.addNum(10.5, "some string?"));

    }
}
