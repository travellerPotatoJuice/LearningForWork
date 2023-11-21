package com.chenhuiying.hello;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws ParseException {
        BigDecimal phonePrice = new BigDecimal("3999");
        BigDecimal temperedFilmPrice = new BigDecimal("9.9");
        BigDecimal sum;
        DecimalFormat df = new DecimalFormat("#.##");
        sum = phonePrice.add(temperedFilmPrice.multiply(new BigDecimal("2")));
        System.out.println("商品总价：" + df.format(sum));
        int result = sum.subtract(new BigDecimal("4000")).compareTo(BigDecimal.ZERO);
        if(result>0){
            sum = sum.multiply(new BigDecimal("0.98"));
            System.out.println("折后价格：" + df.format(sum));
        }

    }
}

