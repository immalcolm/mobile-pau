package module1;

abstract class Person implements Comparable<Person>{
    private String name;
    private String hobby;
    private int age;

    public Person(String name, String hobby, int age) {
        this.name = name;
        this.hobby = hobby;
        this.age = age;
    }

    public String getHobby() {
        return this.hobby;
    }

    abstract void introduceHobby();
    
    abstract void displayModules();

    @Override 
    public int compareTo(Person another) {
        return Integer.compare(this.age, another.age);
    }

    @Override
    public String toString() {
        return this.name;
    }
}