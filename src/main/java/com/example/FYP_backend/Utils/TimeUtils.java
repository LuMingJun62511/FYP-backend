package com.example.FYP_backend.Utils;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TimeUtils {
//这是一开始的，能动态用的，我为了省的天天改数据库，就整了个定值版本的，因为演示就是在5月初，所以以5月10为Dday
//    Instant now = Instant.now();// 获取当前时间
//    ZoneId zone = ZoneId.systemDefault(); // 获取当前时间所在的时区
//    LocalDateTime localDateTime = LocalDateTime.ofInstant(now, zone);// 将当前时间转换为所在时区的本地日期时间
//    DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
//    LocalDateTime startOfWeek = localDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));// 获取本周一的日期时间
//    Instant startOfWeekInstant = startOfWeek.atZone(zone).toInstant();// 将本周一的日期时间转换为Instant类型
////    逻辑是这样，默认结算日为周一，现在直到下一个结算日为不完整的一周，不过实时监控吗，这周也得算
////    然后从上个结算日往前推7整周，为我需要的时间，
//    Instant eightSettlementDateAgo = startOfWeekInstant.minus(Duration.ofDays(49));
//    private static final Instant Dday = Instant.from(LocalDate.of(2023, 5, 10));
    private static final Instant Dday = LocalDate.of(2023, 5, 10).atStartOfDay(ZoneId.systemDefault()).toInstant();
    private static final ZoneId zone = ZoneId.systemDefault();
    private static final LocalDateTime localDateTime = LocalDateTime.ofInstant(Dday, zone);
//    private static final DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
    private static final LocalDateTime startOfWeek = localDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    private static final Instant startOfWeekInstant = startOfWeek.atZone(zone).toInstant();
    private static final Instant eightSettlementDateAgo = startOfWeekInstant.minus(Duration.ofDays(49));

    public static Instant getStartOfWeekInstant() {
        return startOfWeekInstant;
    }

    public static Instant getEightSettlementDateAgo(){
        return eightSettlementDateAgo;
    }

    public static Instant getDday(){
        return Dday;
    }

    public static Instant aMonthDdayAgo(){
        return Dday.minus(Duration.ofDays(30));
    }


    public static int countWeeksBeforeDDay(Instant a) {
        Instant now = Instant.now();
        long daysBetween = ChronoUnit.DAYS.between(a.atZone(ZoneId.systemDefault()), Dday.atZone(ZoneId.systemDefault()));
        int weeks = (int) (daysBetween / 7);
        return weeks;
    }
}
