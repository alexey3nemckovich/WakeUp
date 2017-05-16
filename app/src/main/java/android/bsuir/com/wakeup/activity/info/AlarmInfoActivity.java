package android.bsuir.com.wakeup.activity.info;

import android.bsuir.com.wakeup.alarm.Alarm;
import android.bsuir.com.wakeup.alarm.AlarmSong;
import android.bsuir.com.wakeup.activity.main.MainActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bsuir.com.wakeup.R;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TimePicker;

public class AlarmInfoActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_info);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        songSpinnerAdapter = new SongSpinnerAdapter(this);
        spinner.setAdapter(songSpinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selectedSongPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        Intent intent = getIntent();
        alarm = intent.getParcelableExtra(MainActivity.alarmIntentExtra);

        spinner.setSelection(songSpinnerAdapter.getPosition(alarm.getSong()));

        timePicker.setCurrentHour(alarm.getDate().getHour());
        timePicker.setCurrentMinute(alarm.getDate().getMinute());
    }

    @Override
    public void onBackPressed(){
        returnResult();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                returnResult();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void returnResult(){
        Intent intent = new Intent();

        setSong();
        setDate();

        intent.putExtra(MainActivity.alarmIntentExtra, alarm);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setSong(){
        AlarmSong alarmSong = songSpinnerAdapter.getAlarmSong(selectedSongPosition);
        alarm.setSong(alarmSong);
    }

    private void setDate(){
        try{
            int hours = timePicker.getCurrentHour();
            int minutes = timePicker.getCurrentMinute();
            alarm.getDate().setTime(hours, minutes);
        }catch (Exception e){
            //log
        }
    }

    private Alarm alarm;
    private TimePicker timePicker;
    private int selectedSongPosition;
    private SongSpinnerAdapter songSpinnerAdapter;
}
