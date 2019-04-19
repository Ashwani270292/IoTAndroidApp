package com.ashwanipandey.switchapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwanipandey.switchapplication.adapter.SwitchListAdapter;
import com.ashwanipandey.switchapplication.beans.Devices;
import com.ashwanipandey.switchapplication.beans.RoomBean;

import java.util.ArrayList;

public class RoomDetailActivity extends AppCompatActivity {

    private RoomBean roomBean;
    private TextView roomName;
    private RecyclerView switchListRecycler;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Devices> devicesArrayList = new ArrayList<>();
    private SwitchListAdapter switchListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        try {
            roomBean = (RoomBean) getIntent().getExtras().get("roomData");
        }catch (Exception e){
            e.printStackTrace();
        }

        roomName = (TextView)findViewById(R.id.tvRoomName);
        switchListRecycler = (RecyclerView)findViewById(R.id.switchList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        switchListAdapter = new SwitchListAdapter(this, devicesArrayList);

        switchListRecycler.setLayoutManager(linearLayoutManager);
        switchListRecycler.setAdapter(switchListAdapter);

        if(roomBean != null){
            setRoomData();
        }else{
            Toast.makeText(this,"Failed to get Data" , Toast.LENGTH_SHORT).show();
        }
    }

    private void setRoomData(){
        try {

            getSupportActionBar().setTitle(roomBean.getRoomName());
        }catch (Exception e){
            e.printStackTrace();
        }
        roomName.setText(roomBean.getRoomName());
        //hiding roomName since it is getting showed on title bar
        roomName.setVisibility(View.GONE);
        if(roomBean.getDevicesList() != null){
            devicesArrayList.addAll(roomBean.getDevicesList());
        }
        switchListAdapter.notifyDataSetChanged();
    }
}
