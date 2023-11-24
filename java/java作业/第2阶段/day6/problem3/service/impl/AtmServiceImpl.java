package com.chenhuiying.hello.entry.function.entry.entry.service.impl;



import com.chenhuiying.hello.entry.function.entry.entry.service.AtmService;
import com.chenhuiying.hello.entry.function.entry.entry.service.IllegalInput;
import com.chenhuiying.hello.entry.function.entry.entry.service.NoMoney;

public class AtmServiceImpl implements AtmService {          // atm机业务处理类

    // 定义总金额
    private double totalMoney = 20000 ;

    // 取钱的方法
    @Override
    public double drawMoney(double money) {
        if(money<0)throw new IllegalInput();
        if(money>totalMoney){
            throw new NoMoney();
        }else{
            return totalMoney - money ;
        }

    }

}
