package ru.geekbrains.java3.lesson2.databases.homework;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> list;

    public Box(T... fruits) {
        list = new ArrayList<T>();
        for (T o : fruits)
            list.add(o);
    }

    public void addFruit(T fruit) {
        list.add(fruit);
    }

    public float getWeight() {
        if (list.size() > 0) {
            return list.get(0).getWeight() * list.size();
        }
        return 0.0f;
    }

    public boolean compare(Box<?> box2) {
        return Math.abs(getWeight() - box2.getWeight()) < 0.005;
    }

    public void transfer(Box<T> box2) {
        box2.list.addAll(this.list);
        this.list.clear();
    }
}
