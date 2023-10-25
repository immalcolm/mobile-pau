package module1.experiments;

import module1.examples.ExampleThis;

public class ExampleGetThis {
    public static void main(String[] args) {
        ExampleThis obj = new ExampleThis();
        //this is no longer accessible as it belongs to a different package
        //obj.num = 5;
        obj.setNum(10);
    }
}
