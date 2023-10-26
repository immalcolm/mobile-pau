package module1.examples;

//basis of a class 
//public class <ClassName>
//this is our object name that we can use
public class ExampleInstance{
    //define properties

    String userName = "John Doe";

    //set num1 to empty and private
    private int num1;
    //constructor
    //instialize num1 to 20
    public ExampleInstance(){
        this.num1 = 20;
        System.out.println("Num1: " + this.num1);
    }

    public void calTotal(){

    }

    public void sumOfNum(){
        //<ClassName> varname = new <ClassName>();
        ExampleInstance obj = new ExampleInstance();
        obj.num1 = 30;

        System.out.println("Obj Num: " + obj.num1);
    }



    public static void main(String[] args){

    }
}