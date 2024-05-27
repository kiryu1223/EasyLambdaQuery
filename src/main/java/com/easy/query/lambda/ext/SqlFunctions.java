package com.easy.query.lambda.ext;


import com.easy.query.lambda.util.SqlTypes;
import com.easy.query.lambda.db.DbType;
import com.easy.query.lambda.error.SqlFunctionInvokeException;

import java.lang.annotation.*;
import java.time.*;
import java.time.temporal.TemporalAmount;

public class SqlFunctions
{
    // region [扩展注解]

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Exts
    {
        Ext[] value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Repeatable(Exts.class)
    public @interface Ext
    {
        /**
         * 目标数据库
         */
        DbType dbType();

        /**
         * 实际函数展开
         */
        String function();
    }

    // endregion

    // region [聚合函数]

    @Ext(dbType = DbType.MySQL, function = "COUNT()")
    public static long count()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "COUNT({})")
    public static <T> long count(T t)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUM({})")
    public static <T> T sum(T t)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "AVG({})")
    public static <T extends Number> T AVG(T t)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MIN({})")
    public static <T> T min(T t)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MAX({})")
    public static <T> T max(T t)
    {
        throw new SqlFunctionInvokeException();
    }

    // endregion

    // region [时间]

    @Ext(dbType = DbType.MySQL, function = "NOW()")
    public static LocalDateTime now()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "UTC_TIMESTAMP()")
    public static LocalDateTime utcNow()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LOCALTIME()")
    public static LocalDateTime localNow()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SYSDATE()")
    public static LocalDateTime sysNow()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CURDATE()")
    public static LocalDate nowDate()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CURTIME()")
    public static LocalTime nowTime()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "UTC_DATE()")
    public static LocalDate utcNowDate()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "UTC_TIME()")
    public static LocalTime utcNowTime()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDDATE({},{})")
    public static <TimeLong extends TemporalAmount> LocalDateTime addDate(LocalDateTime time, TimeLong timeLong)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDDATE({},{})")
    public static <TimeLong extends TemporalAmount> LocalDate addDate(LocalDate time, TimeLong timeLong)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDDATE({},{})")
    public static LocalDateTime addDate(LocalDateTime time, int days)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDDATE({},{})")
    public static LocalDate addDate(LocalDate time, int days)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDDATE({},{})")
    public static LocalDateTime addDate(LocalDateTime time, LocalDateTime addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDDATE({},{})")
    public static LocalDate addDate(LocalDate time, LocalDate addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDTIME({},{})")
    public static LocalDateTime addTime(LocalDateTime time, LocalTime addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDTIME({},{})")
    public static LocalDateTime addTime(LocalDateTime time, String addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDTIME({},{})")
    public static LocalTime addTime(LocalTime time, LocalTime addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDTIME({},{})")
    public static LocalDateTime addTime(String time, LocalTime addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ADDTIME({},{})")
    public static LocalDateTime addTime(String time, String addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATE({})")
    public static LocalDate getDate(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATE({})")
    public static LocalDate getDate(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEDIFF({},{})")
    public static int dateDiff(LocalDateTime t1, LocalDateTime t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEDIFF({},{})")
    public static int dateDiff(LocalDateTime t1, LocalDate t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEDIFF({},{})")
    public static int dateDiff(LocalDateTime t1, String t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEDIFF({},{})")
    public static int dateDiff(LocalDate t1, LocalDate t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEDIFF({},{})")
    public static int dateDiff(LocalDate t1, LocalDateTime t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEDIFF({},{})")
    public static int dateDiff(LocalDate t1, String t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEDIFF({},{})")
    public static int dateDiff(String t1, String t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEFORMAT({},{})")
    public static int dateFormat(LocalDateTime time, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEFORMAT({},{})")
    public static int dateFormat(LocalDate time, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DATEFORMAT({},{})")
    public static int dateFormat(String time, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAY({})")
    public static int getDay(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAY({})")
    public static int getDay(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAY({})")
    public static int getDay(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYNAME({})")
    public static String getDayName(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYNAME({})")
    public static String getDayName(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYNAME({})")
    public static String getDayName(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYOFWEEK({})")
    public static int getDayOfWeek(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYOFWEEK({})")
    public static int getDayOfWeek(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYOFWEEK({})")
    public static int getDayOfWeek(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYOFYEAR({})")
    public static int getDayOfYear(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYOFYEAR({})")
    public static int getDayOfYear(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DAYOFYEAR({})")
    public static int getDayOfYear(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SEC_TO_TIME({})")
    public static LocalTime toTime(int second)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "STR_TO_DATE({})")
    public static LocalDate toDate(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "STR_TO_DATE({},{})")
    public static LocalDate toDate(String time, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "STR_TO_DATE({},{})")
    public static LocalDateTime toDateTime(String time, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "FROM_DAYS({})")
    public static LocalDate toDate(long days)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TO_DAYS({})")
    public static long toDays(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TO_DAYS({})")
    public static long toDays(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TO_DAYS({})")
    public static long toDays(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME_TO_SEC({})")
    public static int toSecond(LocalTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME_TO_SEC({})")
    public static int toSecond(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME_TO_SEC({})")
    public static int toSecond(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "HOUR({})")
    public static int getHour(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "HOUR({})")
    public static int getHour(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "HOUR({})")
    public static int getHour(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LAST_DAY({})")
    public static int getLastDay(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LAST_DAY({})")
    public static int getLastDay(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LAST_DAY({})")
    public static int getLastDay(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MAKEDATE({},{})")
    public static LocalDate createDate(int year, int days)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MAKETIME({},{},{})")
    public static LocalTime createTime(int hour, int minute, int second)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MICROSECOND({})")
    public static int getMicroSecond(LocalTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MICROSECOND({})")
    public static int getMicroSecond(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MICROSECOND({})")
    public static int getMicroSecond(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MINUTE({})")
    public static int getMinute(LocalTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MINUTE({})")
    public static int getMinute(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MINUTE({})")
    public static int getMinute(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MONTH({})")
    public static int getMonth(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MONTH({})")
    public static int getMonth(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MONTH({})")
    public static int getMonth(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MONTHNAME({})")
    public static String getMonthName(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MONTHNAME({})")
    public static String getMonthName(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MONTHNAME({})")
    public static String getMonthName(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "QUARTER({})")
    public static int getQuarter(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "QUARTER({})")
    public static int getQuarter(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "QUARTER({})")
    public static int getQuarter(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SECOND({})")
    public static int getSecond(LocalTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SECOND({})")
    public static int getSecond(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SECOND({})")
    public static int getSecond(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBDATE({},{})")
    public static <TimeLong extends TemporalAmount> LocalDateTime subDate(LocalDateTime time, TimeLong timeLong)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBDATE({},{})")
    public static <TimeLong extends TemporalAmount> LocalDate subDate(LocalDate time, TimeLong timeLong)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBDATE({},{})")
    public static LocalDateTime subDate(LocalDateTime time, int days)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBDATE({},{})")
    public static LocalDate subDate(LocalDate time, int days)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBDATE({},{})")
    public static LocalDateTime subDate(LocalDateTime time, LocalDateTime addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBDATE({},{})")
    public static LocalDate subDate(LocalDate time, LocalDate addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBTIME({},{})")
    public static LocalDateTime subTime(LocalDateTime time, LocalTime addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBTIME({},{})")
    public static LocalDateTime subTime(LocalDateTime time, String addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBTIME({},{})")
    public static LocalTime subTime(LocalTime time, LocalTime addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBTIME({},{})")
    public static LocalDateTime subTime(String time, LocalTime addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBTIME({},{})")
    public static LocalDateTime subTime(String time, String addtime)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME({})")
    public static int getTime(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME({})")
    public static int getTime(LocalTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME({})")
    public static int getTime(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME_FORMAT({},{})")
    public static String timeFormat(LocalTime time, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME_FORMAT({},{})")
    public static String timeFormat(LocalDateTime time, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIME_FORMAT({},{})")
    public static String timeFormat(String time, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIMEDIFF({},{})")
    public static LocalTime timeDiff(LocalDateTime t1, LocalDateTime t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIMEDIFF({},{})")
    public static LocalTime timeDiff(LocalDateTime t1, LocalTime t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIMEDIFF({},{})")
    public static LocalTime timeDiff(LocalDateTime t1, String t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIMEDIFF({},{})")
    public static LocalTime timeDiff(LocalTime t1, LocalTime t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIMEDIFF({},{})")
    public static LocalTime timeDiff(LocalTime t1, LocalDateTime t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIMEDIFF({},{})")
    public static LocalTime timeDiff(LocalTime t1, String t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TIMEDIFF({},{})")
    public static LocalTime timeDiff(String t1, String t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEK({})")
    public static int getWeek(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEK({},{})")
    public static int getWeek(LocalDate time, int firstDayofweek)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEK({})")
    public static int getWeek(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEK({},{})")
    public static int getWeek(LocalDateTime time, int firstDayofweek)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEK({})")
    public static int getWeek(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEK({},{})")
    public static int getWeek(String time, int firstDayofweek)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEKDAY({})")
    public static int getWeekDay(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEKDAY({})")
    public static int getWeekDay(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEKDAY({})")
    public static int getWeekDay(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEKOFYEAR({})")
    public static int getWeekOfYear(LocalDate time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEKOFYEAR({})")
    public static int getWeekOfYear(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "WEEKOFYEAR({})")
    public static int getWeekOfYear(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "YEAR({})")
    @Ext(dbType = DbType.SqlServer, function = "DATEPART(YEAR,{})")
    public static int getYear(LocalDateTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "YEAR({})")
    @Ext(dbType = DbType.SqlServer, function = "DATEPART(YEAR,{})")
    public static int getYear(LocalTime time)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "YEAR({})")
    @Ext(dbType = DbType.SqlServer, function = "DATEPART(YEAR,{})")
    public static int getYear(String time)
    {
        throw new SqlFunctionInvokeException();
    }

    // endregion

    // region [数值]

    @Ext(dbType = DbType.MySQL, function = "ABS({})")
    public static <T extends Number> T abs(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "COS({})")
    public static <T extends Number> double cos(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SIN({})")
    public static <T extends Number> double sin(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TAN({})")
    public static <T extends Number> double tan(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ACOS({})")
    public static <T extends Number> double acos(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ASIN({})")
    public static <T extends Number> double asin(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ATAN({})")
    public static <T extends Number> double atan(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ATAN2({},{})")
    public static <T extends Number> double atan2(T a, T b)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CEIL({})")
    public static <T extends Number> long ceil(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "COT({})")
    public static <T extends Number> double cot(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "DEGREES({})")
    public static <T extends Number> double degrees(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "EXP({})")
    public static <T extends Number> double exp(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "FLOOR({})")
    public static <T extends Number> long floor(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @SafeVarargs
    @Ext(dbType = DbType.MySQL, function = "GREATEST({},{})")
    public static <T extends Number> T big(T a, T b, T... cs)
    {
        throw new SqlFunctionInvokeException();
    }

    @SafeVarargs
    @Ext(dbType = DbType.MySQL, function = "LEAST({},{})")
    public static <T extends Number> T small(T a, T b, T... cs)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LN({})")
    public static <T extends Number> double ln(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LOG({})")
    public static <T extends Number> double log(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LOG2({})")
    public static <T extends Number> double log2(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LOG10({})")
    public static <T extends Number> double log10(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "MOD({},{})")
    public static <T extends Number> T mod(T a, T b)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "PI()")
    public static double pi()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "POW({},{})")
    public static <T extends Number> double pow(T a, T b)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "RADIANS({})")
    public static <T extends Number> double radians(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "RAND()")
    public static double random()
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "RAND({})")
    public static double random(int a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ROUND({})")
    public static <T extends Number> T round(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "ROUND({},{})")
    public static <T extends Number> T round(T a, int b)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SIGN({})")
    public static <T extends Number> int sign(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SQRT({})")
    public static <T extends Number> double sqrt(T a)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TRUNCATE({},{})")
    public static <T extends Number> long truncate(T a, int b)
    {
        throw new SqlFunctionInvokeException();
    }

    // endregion

    // region [字符串]

    @Ext(dbType = DbType.MySQL, function = "ASCII({})")
    public static int ascii(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CHAR_LENGTH({})")
    public static int length(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CONCAT({},{})")
    public static String concat(String s1, String s2, String... ss)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CONCAT_WS({},{},{})")
    public static String join(String separator, String s1, String s2, String... ss)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CHAR({})")
    public static <T extends Number> String toStr(T t)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "FORMAT({},{})")
    public static <T extends Number> String format(T t, String format)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "HEX({})")
    public static <T extends Number> String hex(T t)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "HEX({})")
    public static String hex(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "INSERT({},{},{},{})")
    public static String insert(String str, int pos, int length, String newStr)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "INSTR({},{})")
    public static int instr(String s1, String s2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LOWER({})")
    public static String toLower(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LEFT({},{})")
    public static String left(String string, int length)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LENGTH({})")
    public static int byteLength(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LPAD({},{},{})")
    public static String leftPad(String string, int length, String lpadString)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LTRIM({})")
    public static String trimStart(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LOCATE({},{})")
    public static int locate(String subString, String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "LOCATE({},{},{})")
    public static int locate(String subString, String string, int offset)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "REPEAT({},{})")
    public static String repeat(String string, int number)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "REPLACE({},{},{})")
    public static String replace(String cur, String subs, String news)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "REVERSE({})")
    public static String reverse(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "RIGHT({},{})")
    public static String right(String string, int length)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "RPAD({},{},{})")
    public static String rightPad(String string, int length, String rpadString)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "RTRIM({})")
    public static String trimEnd(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SPACE({})")
    public static String space(int number)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "STRCMP({},{})")
    public static int compare(String s1, String s2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBSTR({},{})")
    public static String subStr(String string, int pos)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBSTR({},{},{})")
    public static String subStr(String string, int pos, int length)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "SUBSTRING_INDEX({},{},{})")
    public static String subStrIndex(String string, String delimiter, int length)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "TRIM({})")
    public static String trim(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "UPPER({})")
    public static String toUpper(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "UNHEX({})")
    public static byte[] unHex(String string)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "GROUP_CONCAT({})")
    public static byte[] groupConcat(String property)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "GROUP_CONCAT({property} SEPARATOR {delimiter})")
    public static <T> String groupJoin(String delimiter, T property)
    {
        throw new SqlFunctionInvokeException();
    }

    @SafeVarargs
    @Ext(dbType = DbType.MySQL, function = "GROUP_CONCAT({properties} SEPARATOR {delimiter})")
    public static <T> String groupJoin(String delimiter, T... properties)
    {
        throw new SqlFunctionInvokeException();
    }

    // endregion

    // region [控制流程]

    @Ext(dbType = DbType.MySQL, function = "IF({},{},{})")
    public static <T> T If(boolean condition, T truePart, T falsePart)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "IFNULL({},{})")
    public static <T> T ifNull(T ifNotNull, T ifNull)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "NULLIF({},{})")
    public static <T> T nullIf(T ifNotEq, T t2)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CAST({} AS {})")
    public static <T> T cast(Object value, SqlTypes.SqlType<T> targetType)
    {
        throw new SqlFunctionInvokeException();
    }

    @Ext(dbType = DbType.MySQL, function = "CONVERT({targetType},{value})")
    public static <T> T convert(Object value, SqlTypes.SqlType<T> targetType)
    {
        throw new SqlFunctionInvokeException();
    }

    // endregion
}
