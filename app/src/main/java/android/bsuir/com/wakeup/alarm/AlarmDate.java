package android.bsuir.com.wakeup.alarm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AlarmDate extends Date {

    AlarmDate() {
        date = new Date();
        date.setSeconds(0);
        long currMinuteTime = date.getTime();
        date.setTime(currMinuteTime + dayMilliseconds);
    }

    public AlarmDate(Date date){
        this.date = date;
    }

    AlarmDate(String storeFormatString) throws ParseException{
        this.date = storeFormat.parse(storeFormatString);
    }

    @Override
    public String toString(){
        return renderFormat.format(date);
    }

    String toStoreString(){
        return storeFormat.format(date);
    }

    public int getHour(){
        return date.getHours();
    }

    public int getMinute(){
        return date.getMinutes();
    }

    public void setTime(int hours, int minutes){
        date.setTime(calcMilliseconds(hours, minutes));
    }

    @Override
    public long getTime(){
        return date.getTime();
    }

    private long calcMilliseconds(int hours, int minutes){
        Calendar cal = Calendar.getInstance();
        int alarmYear  = cal.get(Calendar.YEAR);
        int alarmMonth = cal.get(Calendar.MONTH);
        int alarmDay  = cal.get(Calendar.DATE);
        cal.set(alarmYear, alarmMonth, alarmDay, hours, minutes, 0);
        if(cal.getTimeInMillis() <= System.currentTimeMillis()){
            return cal.getTimeInMillis() + dayMilliseconds;
        }
        return cal.getTimeInMillis();
    }

    private static int dayMilliseconds = 86400 * 1000;

    private Date date;
    private DateFormat renderFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private DateFormat storeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
}
