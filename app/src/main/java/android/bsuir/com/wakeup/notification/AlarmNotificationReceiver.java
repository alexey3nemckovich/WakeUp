package android.bsuir.com.wakeup.notification;

import android.bsuir.com.wakeup.activity.alarm.AlarmActivity;
import android.bsuir.com.wakeup.alarm.Alarm;
import android.bsuir.com.wakeup.activity.main.MainActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmNotificationReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Alarm alarm = intent.getParcelableExtra(MainActivity.alarmIntentExtra);
        Intent newIntent = new Intent(context, AlarmActivity.class);
        newIntent.putExtra(MainActivity.alarmIntentExtra, alarm);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }
}
