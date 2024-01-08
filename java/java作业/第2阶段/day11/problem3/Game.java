package com.chenhuiying.hello;

import java.util.Random;

public class Game {
    private int targetNum=0;
    private int guessNum=0;
    private int max = 100;
    private String status="";
    boolean flag = true;


    public int getTargetNum() {
        return targetNum;
    }

    public String getStatus(){
        return status;
    }

    public int getGuessNum() {
        return guessNum;
    }

    public boolean getFlag(){return flag;}

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGuessNum(int guessNum) {
        this.guessNum = guessNum;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int generateNum(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }

    public void generatorTargetNum(){

            Random random = new Random();
            this.targetNum = random.nextInt(100);
            System.out.println("生成的随机数是" + this.getTargetNum());

    }

    public void generateGuessNum(){
        if(this.getStatus().equals("猜大了")){
            max = guessNum;
            guessNum = generateNum(max);
        }else if(this.getStatus().equals("猜小了")){
            guessNum = guessNum+generateNum(max-guessNum);
        }
    }

    public void check() {
            if (this.getGuessNum() > this.getTargetNum()){
                this.setStatus("猜大了");
                System.out.println("A:猜大了");
            } else if (this.getGuessNum() < this.getTargetNum()){
                this.setStatus("猜小了");
                System.out.println("B:猜小了");
            }else{
                this.setStatus("猜对了");
            }

    }

    public void guess(){
        if(this.getTargetNum()!=-1){
            generateGuessNum();
            System.out.println("B:我猜数字是"+this.getGuessNum());
        }
    }

}
