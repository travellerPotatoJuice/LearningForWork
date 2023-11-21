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
        double a = 0.01;
        double b = 0.05;
        BigDecimal num1 = BigDecimal.valueOf(a);
        BigDecimal num2 = BigDecimal.valueOf(b);
        BigDecimal result = num1.divide(num2);
        System.out.println(result);


    }
}

