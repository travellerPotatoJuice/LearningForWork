package com.chenhuiying.hello;

public class Breeder {
    public void breed(Animal a){
        a.drink();
        a.eat();
        if(a instanceof Dog){
            ((Dog) a).swimming();
        }
        else if(a instanceof Frog){
            ((Frog) a).swimming();
        }
    }

}
