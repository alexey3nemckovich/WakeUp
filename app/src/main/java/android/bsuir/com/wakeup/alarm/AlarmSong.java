package android.bsuir.com.wakeup.alarm;

public class AlarmSong {
    public String name;
    public int resourceId;

    public AlarmSong(AlarmSong song){
        this.name = song.name;
        this.resourceId = song.resourceId;
    }

    public AlarmSong(int resourceId, String name){
        this.name = name;
        this.resourceId = resourceId;
    }
}
