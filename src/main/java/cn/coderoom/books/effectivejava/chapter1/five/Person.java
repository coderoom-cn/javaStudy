package cn.coderoom.books.effectivejava.chapter1.five;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Person {

    private final Date birthdayDate;

    public Person(Date date) {
        birthdayDate = date;
    }

    /**
     * 每次被调用就会创建一个Calendar对象，一个TimeZone，两个Date。
     * @return
     */
    public boolean isBabyBommer(){

        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
        Date boomerStart = gmtCal.getTime();
        gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
        Date boomerEnd = gmtCal.getTime();

        return birthdayDate.compareTo(boomerStart) >= 0 &&
                birthdayDate.compareTo(boomerEnd) < 0 ;

    }
}
