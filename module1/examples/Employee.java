package module1.examples;

//For encapsulation example
public class Employee{

    //attributes
    private String name;
    private int age;
    private String designation;
    private double salary;

    public Employee(String name){
        this.name = name;
    }
    //getters
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getDesignation(){
        return designation;
    }

    public double getSalary(){
        return salary;
    }

    //setters
    public void setAge(int age){
        this.age = age;
    }

    public void setDesignation(String designation){
        this.designation = designation;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    public void printEmployee(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Designation: " + designation);
        System.out.println("Salary: " + salary);
    }
}