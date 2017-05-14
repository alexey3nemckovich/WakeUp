package android.bsuir.com.wakeup.activity.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bsuir.com.wakeup.R;
import android.bsuir.com.wakeup.activity.info.AlarmInfoActivity;
import android.bsuir.com.wakeup.notification.AlarmNotificationReceiver;
import android.bsuir.com.wakeup.alarm.Alarm;
import android.bsuir.com.wakeup.alarm.DefaultAlarm;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements MenuItem.OnMenuItemClickListener, OnAlarmClickListener, OnAlarmStateChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView alarmsList = (ListView) findViewById(R.id.alarms_list);
        alarmsListAdapter = new AlarmListAdapter(this, null, this, this);

        alarmsList.setAdapter(alarmsListAdapter);
        alarmsList.setItemsCanFocus(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem addAlarmButton =  menu.findItem(R.id.action_add_alarm);
        addAlarmButton.setOnMenuItemClickListener(this);

        return true;
    }

    @Override
    public void onAlarmClick(Alarm alarm, int position){
        Intent intent = new Intent(this, AlarmInfoActivity.class);
        intent.putExtra(alarmIntentExtra, alarm);
        startActivityForResult(intent, setAlarmInfoRequestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == setAlarmInfoRequestCode){
            if(resultCode == RESULT_OK){
                Alarm response = data.getParcelableExtra(alarmIntentExtra);

                alarmsListAdapter.getAlarmWithId(response.getId()).setDate(response.getDate());
                alarmsListAdapter.getAlarmWithId(response.getId()).setSong(response.getSong());

                alarmsListAdapter.notifyDataSetChanged();

                if(response.isOn()){
                    onAlarmStateChanged(response);
                }
            }
        }
    }

    @Override
    public void onAlarmStateChanged(Alarm alarm){
        AlarmManager alarmManager =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmNotificationReceiver.class);
        intent.putExtra(alarmIntentExtra, alarm);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarm.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if(alarm.isOn()){
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.getDate().getTime(), pendingIntent);
        }else{
            alarmManager.cancel(pendingIntent);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem){
        alarmsListAdapter.addAlarm(new DefaultAlarm());
        alarmsListAdapter.notifyDataSetChanged();

        return true;
    }

    public static int setAlarmInfoRequestCode = 1;
    public static final String alarmIntentExtra = "alarm";

    private AlarmListAdapter alarmsListAdapter;
}
