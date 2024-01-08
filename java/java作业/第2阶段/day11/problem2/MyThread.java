package com.chenhuiying.hello;

public class MyThread extends Thread{
    private String name;
    private Lottery lottery;

    public MyThread(Lottery lottery,String name){
        super(name);
        this.lottery = lottery;
    }

//    @Override
//    public void run(){
//        for(int i=0;i<6;i++){
//            synchronized (lottery){
//                lottery.solution1();
//            }
//        }
//    }
    @Override
    public void run(){
        for(int i=0;i<6;i++){
            synchronized (lottery){
                lottery.solution2();
            }
        }
        synchronized ("打印"){
            if(Thread.currentThread().getName().equals("抽奖箱1")){
                System.out.println("在此次抽奖过程中，抽奖箱1总共产生了6个奖项，分别为："+lottery.one.toString());
                System.out.println("最高奖项为："+lottery.one.stream().mapToInt(Integer::intValue).max().getAsInt()+"元");
                System.out.println("总计额为："+lottery.one.stream().mapToInt(Integer::intValue).sum()+"元");
            }else{
                System.out.println("在此次抽奖过程中，抽奖箱2总共产生了6个奖项，分别为："+lottery.two.toString());
                System.out.println("最高奖项为："+lottery.two.stream().mapToInt(Integer::intValue).max().getAsInt()+"元");
                System.out.println("总计额为："+lottery.two.stream().mapToInt(Integer::intValue).sum()+"元");
            }
        }

    }

}
