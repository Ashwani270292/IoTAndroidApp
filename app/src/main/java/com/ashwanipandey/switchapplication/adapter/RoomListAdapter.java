package com.ashwanipandey.switchapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.ashwanipandey.switchapplication.R;
import com.ashwanipandey.switchapplication.RoomDetailActivity;
import com.ashwanipandey.switchapplication.beans.RoomBean;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder>{
    private List<RoomBean> roomBeanList;
    private Context context;

    public RoomListAdapter(Context context, List<RoomBean> roomBeans){
        this.context = context;
        this.roomBeanList = roomBeans;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_room,  viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final RoomBean roomBean = roomBeanList.get(i);
        viewHolder.tvRoomName.setText(roomBean.getRoomName());
        if(roomBean.isStatus())
            viewHolder.toggleSwitch.setChecked(true);
        else
            viewHolder.toggleSwitch.setChecked(false);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RoomDetailActivity.class);
                intent.putExtra("roomData",roomBean.toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(roomBeanList != null)
        return roomBeanList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRoomName;
        Switch toggleSwitch;
        public ViewHolder(View itemView){
            super(itemView);
            tvRoomName = (TextView)itemView.findViewById(R.id.tvRoomName);
            toggleSwitch = (Switch)itemView.findViewById(R.id.toggleSwitch);
        }

    }
}
