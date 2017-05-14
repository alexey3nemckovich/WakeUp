package android.bsuir.com.wakeup.alarm;

public class DefaultAlarm extends Alarm {

    public DefaultAlarm(){
        super(new DefaultAlarmSong(), new AlarmDate(), false);
    }
}
