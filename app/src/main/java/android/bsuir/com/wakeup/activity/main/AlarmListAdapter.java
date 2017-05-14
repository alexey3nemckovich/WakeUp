package android.bsuir.com.wakeup.activity.main;

import android.bsuir.com.wakeup.R;
import android.bsuir.com.wakeup.alarm.Alarm;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;


class AlarmListAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{

    AlarmListAdapter(
            Context context,
            ArrayList<Alarm> alarms,
            OnAlarmClickListener onAlarmClickListener,
            OnAlarmStateChangedListener onAlarmStateChangedListener
    ){
        this.onAlarmClickListener = onAlarmClickListener;
        this.onAlarmStateChangedListener = onAlarmStateChangedListener;
        this.context = context;
        if(alarms == null){
            this.alarms = new ArrayList<>();
        }else{
            this.alarms = alarms;
        }
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return alarms.size();
    }

    @Override
    public Object getItem(int position){
        return alarms.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.alarm_item, parent, false);
        }
        view.setOnClickListener(this);
        view.setTag(position);

        Alarm alarm = getAlarm(position);

        ((TextView)view.findViewById(R.id.alarm_song_name)).setText(alarm.getSong().name);
        ((TextView)view.findViewById(R.id.alarm_date)).setText(alarm.getDate().toString());
        Switch alarmSwitch = (Switch) view.findViewById(R.id.alarm_state);
        alarmSwitch.setChecked(alarm.isOn());
        alarmSwitch.setOnCheckedChangeListener(this);
        alarmSwitch.setTag(position);

        return view;
    }

    @Override
    public void onClick(View view){
        Integer position = (Integer) view.getTag();
        onAlarmClickListener.onAlarmClick(getAlarm(position), position);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        Alarm alarm = getAlarm((Integer) buttonView.getTag());
        alarm.setState(isChecked);
        onAlarmStateChangedListener.onAlarmStateChanged(alarm);
    }

    public void addAlarm(Alarm alarm){
        alarms.add(alarm);
    }

    public Alarm getAlarmWithId(int id){
        for (Alarm alarm: alarms) {
            if(alarm.getId() == id){
                return alarm;
            }
        }
        return null;
    }

    public Alarm getAlarm(int position){
        return (Alarm) getItem(position);
    }

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Alarm> alarms;
    private OnAlarmClickListener onAlarmClickListener;
    private OnAlarmStateChangedListener onAlarmStateChangedListener;
}
