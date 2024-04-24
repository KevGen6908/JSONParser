package org.example.ClassToParsed;

import java.util.ArrayList;

public class Cat {
    private String name;
    private String age;
    private String color;
    private int[] eating;
    private ArrayList<String> toys;

    public Cat(){
        this.name = "";
        this.age = "";
        this.color = "";
        this.eating = new int[0];
        this.toys = new ArrayList<>();
    }

    public Cat(String name, String age, String color, int[] eating, ArrayList<String> toys) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.eating = eating;
        this.toys = toys;
    }

    @Override
    public String toString() {
        StringBuilder eatingStr = new StringBuilder("[");
        for (int i = 0; i < eating.length; i++) {
            eatingStr.append(eating[i]);
            if (i < eating.length - 1) {
                eatingStr.append(", ");
            }
        }
        eatingStr.append("]");

        return "Cat{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", color='" + color + '\'' +
                ", eating=" + eatingStr.toString() +
                ", toys=" + toys +
                '}';
    }
}
