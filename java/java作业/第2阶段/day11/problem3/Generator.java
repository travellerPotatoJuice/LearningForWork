package com.chenhuiying.hello;

import java.util.Random;

public class Generator extends Thread {

    private Game game;

    public Generator(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        synchronized (game) {
            System.out.println("随机数已准备就绪");
            game.generatorTargetNum();
            game.setFlag(false);
            game.notifyAll();
            try {
                game.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {

            synchronized (game) {
                if (!game.getStatus().equals("猜对了")) {
                    game.check();
                    game.notifyAll();
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("A:恭喜你猜对了");
                    break;
                }
            }

        }

    }
}


