package com.chenhuiying.hello;


public class Entry {

    public static void main(String[] args) {
        Game game = new Game();
        Generator generator = new Generator(game);
        Guessor guessor = new Guessor(game);
        guessor.start();
        generator.start();

    }
}