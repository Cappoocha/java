package ru.geekbrains.java3.lesson2.databases.homework;
//        Написать метод, который меняет два элемента массива местами.(массив может быть любого
//        ссылочного типа);
//         Написать метод, который преобразует массив в ArrayList;
//         Большая задача:
//        Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
//        Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
//        поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//        Для хранения фруктов внутри коробки можете использовать ArrayList;
//        Сделать метод getWeight() который высчитывает вес коробки, зная кол-во фруктов и вес одного
//        фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
//        Внутри класса корока сделать метод compare, который позволяет сравнить текущую коробку с той,
//        которую подадут в compare в качестве параметра, true - если их веса равны, false в противной
//        случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
//        Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним
//        про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей
//        коробке фруктов не остается, а в другую перекидываются объ

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        Box<Apple> ba = new Box<>(new Apple(), new Apple(), new Apple());
        Box<Orange> bo = new Box<>(new Orange(), new Orange());

        System.out.println(ba.compare(bo));

        Box<Apple> ba2 = new Box<>(new Apple(), new Apple(), new Apple());
        System.out.println(ba.getWeight());
        ba2.transfer(ba);
        System.out.println(ba.getWeight());
        System.out.println(ba2.getWeight());
    }

    public static <T> void swap(T[] arr, int a, int b) {
        T obj = arr[b];
        arr[b] = arr[a];
        arr[a] = obj;
    }

    public static <T> ArrayList<T> getAL(T[] arr) {
        ArrayList<T> al = new ArrayList<T>();
        for (T o : arr)
            al.add(o);
        return al;
    }
}
