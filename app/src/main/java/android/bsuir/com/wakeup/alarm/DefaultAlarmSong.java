package android.bsuir.com.wakeup.alarm;

import android.bsuir.com.wakeup.binding.MusicBinding;

public class DefaultAlarmSong extends AlarmSong {

    public DefaultAlarmSong(){
        super(MusicBinding.getMusicBindings().get(0));
    }
}
