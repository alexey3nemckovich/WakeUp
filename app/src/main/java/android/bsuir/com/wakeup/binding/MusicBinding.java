package android.bsuir.com.wakeup.binding;

import android.bsuir.com.wakeup.R;
import android.bsuir.com.wakeup.alarm.AlarmSong;

import java.util.ArrayList;
import java.util.List;

public class MusicBinding {

    static {
        alarmSongs = new ArrayList<>();
        bind(R.raw.rise, "Skillet - Rise");
        bind(R.raw.sick_of_it, "Skillet - Sick of it");
        bind(R.raw.good_to_be_alive, "Skillet - Good to be alive");
    }

    public static List<AlarmSong> getMusicBindings(){
        return alarmSongs;
    }

    public static void bind(int resourceId, String renderName){
        alarmSongs.add(new AlarmSong(resourceId, renderName));
    }

    private static List<AlarmSong> alarmSongs;
}
