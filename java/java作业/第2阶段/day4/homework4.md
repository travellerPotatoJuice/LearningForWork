# 今日单词

1. 字符串拼接多个类的单词：append
2. 各个工具类的单词：Math, System, Runtime
3. 精确计算浮点数类的单词：BigDecimal
4. JDK 8之前的时间各个类单词：Date, SimpleDateFormat, Calendar
5. JDK 后之前的时间各个类单词：LocalDate，LocalTime，LocalDateTime，ZoneId, ZonedDateTime, Instant , DateTimeFormatter



# 简答题

1. **StringBuilder有何作用？StringBuilder和StringBuffer两个类有何区别？**

   答：

   + StringBuilder和String相比具有更好的可扩展性，它是一个可变字符串的容器，更适合做字符串的修改操作，效率更高
   + StringBuilder和StringBuffer的用法是一样的，唯一的区别在于StringBuilder不是线程安全的，StringBuffer是线程安全的

   

2. **为什么java中的时间零点是"1970-01-01 00:00:00"？那我们中国也是这个时间么？**

   答：和java的发明时间有关，为了方便起见被设置成了这个时间。这个时间是UTC时间，中国的时间是UTC+8。

   

3. **分别说出：Date、SimpleDateFormat、Calendar类的作用？**

   答：

   + Date：用于表示特定的时间点
   + SimpleDateFormat：将时间对象和时间毫秒值进行格式化
   + Calendar：用于操作日历字段

   

4. **分别说出：LocalDate、ZoneId、ZonedDateTIme、Instant、DateTimeFormatter、Period、Duration类的作用？**

   答：
   
   + localDate：表示本地日期（年，月，日，星期）
   + ZoneId：表示时区id
   + ZonedDateTime：表示带时区的时间
   + Instant：获取从1970-01-01 00:00:00开始走到此刻的总秒数+不够1秒的纳秒数
   + DateTimeFormatter：线程安全的时间格式化器
   + Period：计算两个LocalDate对象相差的年数，月数，天数
   + Duration：计算两个时间对象相差的天数，小时数，分数，毫秒数，纳秒数



# 排错题

1. 报错Unparseable date: "2022/1/22"；错误原因是String dateStr中日期的格式和格式化器中的pattern格式不一致，要使程序能正确运行，需要将dateStr改成“2022-1-22”或者将pattern改成“yyyy/MM/dd”
2. 和上题一样，MM默认月份是两个字符，需要写成03，如果要用不补全位数的月份，则可以将MM改成M。将date改成"2023-03-12"。