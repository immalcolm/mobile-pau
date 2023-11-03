package module1;

import java.util.ArrayList;

class Lecturer extends Person {
    private ArrayList<String> modules = new ArrayList<>();

    public Lecturer (String name, String hobby, int age) {
        super(name, hobby, age);
    }

    public void introduceHobby() {
        System.out.println(String.format("Hi I am %s!", super.toString()));
        System.out.println(String.format("I like %s a lot!", super.getHobby()));
    }

    public void displayModules() {
        System.out.print("I am teaching ");
        for (int i = 0; i < modules.size(); i++) {
            if (i < modules.size() - 1) {
                System.out.print(modules.get(i));
                System.out.print(", ");
            } else {
                System.out.print(String.format("and %s this semester.", modules.get(i)));
                System.out.println();
            }
        }
    }

    public void teachModule(String module) {
        modules.add(module);
    }
}