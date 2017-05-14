package android.bsuir.com.wakeup.activity.alarm;

import android.bsuir.com.wakeup.alarm.Alarm;
import android.bsuir.com.wakeup.activity.main.MainActivity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bsuir.com.wakeup.R;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class AlarmActivity extends AppCompatActivity
    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        ImageButton buttonOk = (ImageButton) findViewById(R.id.button);
        buttonOk.setOnClickListener(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        Alarm alarm = getIntent().getParcelableExtra(MainActivity.alarmIntentExtra);
        mediaPlayer = MediaPlayer.create(this, alarm.getSong().resourceId);
        mediaPlayer.start();
    }

    @Override
    public void onClick(View view){
        mediaPlayer.stop();
        finish();
    }

    private MediaPlayer mediaPlayer;
}
