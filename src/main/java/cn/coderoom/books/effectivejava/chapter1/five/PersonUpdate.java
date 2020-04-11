package cn.coderoom.books.effectivejava.chapter1.five;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PersonUpdate {

    private final Date birthdayDate;

    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
        BOOM_END = gmtCal.getTime();
    }

    public PersonUpdate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public boolean isBabyBommer(){

        return birthdayDate.compareTo(BOOM_START) >= 0 &&
                birthdayDate.compareTo(BOOM_END) < 0 ;

    }

}
