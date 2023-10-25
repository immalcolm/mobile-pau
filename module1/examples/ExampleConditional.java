package module1.examples;

public class ExampleConditional{

    public static void main(String[] args){
        int x = 10;
        int y = 20;
        int z = 30;
        if(x > y){
            System.out.println("x is greater than y");
        }else if(y > z){
            System.out.println("y is greater than z");
        }else{
            System.out.println("z is greater than x and y");
        }

        int age = 18;
        //shorthand conditional operator
        String status = (age >= 18) ? "Adult" : "Minor";
        System.out.println("Status: " + status);

    }
}