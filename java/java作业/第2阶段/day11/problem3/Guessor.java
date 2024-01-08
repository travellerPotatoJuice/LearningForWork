package com.chenhuiying.hello;

import java.util.Random;

public class Guessor extends Thread {

    private Game game;
    private int bound = 100;

    public Guessor(Game game) {
        this.game = game;
    }


    @Override
    public void run() {
        synchronized (game) {
            while (game.getFlag()) {
                try {
                    game.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.setGuessNum(game.generateNum(bound));
                System.out.println("B:我猜数字是" + game.getGuessNum());
                game.notifyAll();
                try {
                    game.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while (true) {
            synchronized (game) {
                if (game.getGuessNum() != game.getTargetNum()) {
                    game.guess();
                    game.notifyAll();
                    try {
                        game.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    game.notifyAll();
                    break;
                }
            }
        }
    }
}

