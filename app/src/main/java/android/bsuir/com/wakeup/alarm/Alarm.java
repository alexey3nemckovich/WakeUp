package android.bsuir.com.wakeup.alarm;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;

public class Alarm implements Parcelable{

    public Alarm(AlarmSong song, AlarmDate date, boolean state){
        this.date = date;
        this.state = state;
        this.song = song;
        this.id = hashCode();
    }

    public Alarm(Parcel parcel) throws ParseException{
        //parse id
        id = parcel.readInt();
        //parse song
        int songResourceId = parcel.readInt();
        String songName = parcel.readString();
        song = new AlarmSong(songResourceId, songName);
        //parse date
        this.date = new AlarmDate(parcel.readString());
        //parse state
        boolean boolArray[] = new boolean[1];
        parcel.readBooleanArray(boolArray);
        this.state = boolArray[0];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        //write id
        dest.writeInt(id);
        //write song
        dest.writeInt(song.resourceId);
        dest.writeString(song.name);
        //write state
        dest.writeString(date.toStoreString());
        boolean boolArray[] = new boolean[]{state};
        dest.writeBooleanArray(boolArray);
    }

    @Override
    public int describeContents(){return 0;}

    public void setState(boolean on){
        this.state = on;
    }

    public void setDate(AlarmDate date){
        this.date = date;
    }

    public void setSong(AlarmSong song){
        this.song = song;
    }

    public int getId(){
        return id;
    }

    public boolean isOn(){
        return state;
    }

    public AlarmDate getDate(){
        return date;
    }

    public AlarmSong getSong(){
        return song;
    }

    public static Creator<Alarm> CREATOR = new Creator<Alarm>() {

        @Override
        public Alarm createFromParcel(Parcel source){
            try {
                return new Alarm(source);
            }catch (Exception e){
                return null;
            }
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    private int id;
    private AlarmSong song;
    private AlarmDate date;
    private boolean state;
}
