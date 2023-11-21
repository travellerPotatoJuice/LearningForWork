package com.chenhuiying.hello.task;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimeTask extends TimerTask {

    // 定义秒杀开始时间
    private long startTime ;

    // 构造方法，对秒杀开始时间进行初始化
    public TimeTask() {
        Calendar calendar  = Calendar.getInstance();
        Date now = calendar.getTime();//获取当前时间
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        Date start  = calendar.getTime(); //获取秒杀开始时间
        startTime = start.getTime();
    }

    @Override
    public void run() {                                 // 每一秒执行一次该方法

        // 补全代码
        Date nowDay = new Date();
        long now = nowDay.getTime();
        long diffInMillies = startTime - now;
        long hours = TimeUnit.MILLISECONDS.toHours(diffInMillies);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillies) % 60;

        System.out.println("即将开始，距开始："+ hours + "小时"+ minutes + "分钟"
                + seconds + "秒");
    }

}
