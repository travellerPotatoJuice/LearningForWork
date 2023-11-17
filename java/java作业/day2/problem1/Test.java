package com.chenhuiying.hello;

public class Test {
    public static void main(String[] args) {
        Breeder breeder = new Breeder();
        Animal dog = new Dog();
        Animal sheep = new Sheep();
        Animal frog = new Frog();
        breeder.breed(dog);
        breeder.breed(frog);
        breeder.breed(sheep);
    }
}