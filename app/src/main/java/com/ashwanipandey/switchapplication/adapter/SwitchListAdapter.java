package com.ashwanipandey.switchapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ashwanipandey.switchapplication.R;
import com.ashwanipandey.switchapplication.beans.Devices;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SwitchListAdapter extends RecyclerView.Adapter<SwitchListAdapter.ViewHolder>{

    private ArrayList<Devices> devices;
    private Context context;

    public SwitchListAdapter(Context context, ArrayList<Devices> devices){
        this.context = context;
        this.devices = devices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_room_switch, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Devices device = devices.get(i);
        viewHolder.deviceName.setText(device.getDeviceName());
        viewHolder.deviceType.setText(device.getDeviceType());
        if(device.isSwitchStatus()){
            viewHolder.aSwitch.setChecked(true);
        }else{
            viewHolder.aSwitch.setChecked(false);
        }

        viewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //if checked
                if(b){
                    device.setStatus("on");
                }else{
                    device.setStatus("off");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(devices != null)
            return devices.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView deviceName, deviceType;
        Switch aSwitch;
        public ViewHolder(View itemView){
            super(itemView);

            deviceName = (TextView)itemView.findViewById(R.id.tvDeviceName);
            deviceType = (TextView)itemView.findViewById(R.id.tvDeviceType);
            aSwitch = (Switch)itemView.findViewById(R.id.toggleSwitch);
        }

    }
}
