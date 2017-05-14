package android.bsuir.com.wakeup.activity.info;

import android.bsuir.com.wakeup.R;
import android.bsuir.com.wakeup.binding.MusicBinding;
import android.bsuir.com.wakeup.alarm.AlarmSong;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

class SongSpinnerAdapter extends BaseAdapter
        implements SpinnerAdapter{

    SongSpinnerAdapter(Context context){
        alarmSongs = MusicBinding.getMusicBindings();
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return alarmSongs.size();
    }

    @Override
    public Object getItem(int position) {
        return alarmSongs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getAlarmSong(position).resourceId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView textView = (TextView) view;
        if(textView == null){
            textView = (TextView) layoutInflater.inflate(R.layout.song_spinner_item, null);
        }
        textView.setText(getAlarmSong(position).name);
        textView.setTag(position);
        return textView;
    }

    AlarmSong getAlarmSong(int position){
        return (AlarmSong) getItem(position);
    }

    int getPosition(AlarmSong alarmSong){
        for(int i = 0; i < alarmSongs.size(); i++){
            if(alarmSongs.get(i).resourceId == alarmSong.resourceId){
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    private Context context;
    private LayoutInflater layoutInflater;

    private List<AlarmSong> alarmSongs;
}
