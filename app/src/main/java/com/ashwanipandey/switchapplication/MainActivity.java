package com.ashwanipandey.switchapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ashwanipandey.switchapplication.adapter.RoomListAdapter;
import com.ashwanipandey.switchapplication.beans.Devices;
import com.ashwanipandey.switchapplication.beans.RoomBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView roomList;
    private LinearLayoutManager linearLayoutManager;
    private RoomListAdapter roomListAdapter;
    private List<RoomBean> roomBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomList = (RecyclerView)findViewById(R.id.roomList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        roomBeanList = new ArrayList<>();
        roomListAdapter = new RoomListAdapter(this, roomBeanList);
        roomList.setLayoutManager(linearLayoutManager);
        roomList.setAdapter(roomListAdapter);
        populateRooms(loadJSONFromAsset());
        roomListAdapter.notifyDataSetChanged();
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("rooms.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    private void populateRooms(String rooms){

        //parsing json object and storing them in array list
        //you can also do parsing using GSON, it is easier and faster. But this is the basic parsing

        try{
            JSONArray jsonArray = new JSONArray(rooms);

            //NOTE: In this case you have a JSON Array, ideally it should be coming in a JSONObject
            // but this is only to parse JSON Array in case of object uncomment the below lines

//            JSONObject jsonObject = new JSONObject(rooms);
//            JSONArray jsonArray1 = jsonObject.getJSONArray("yourArrayName");

            for(int i = 0; i < jsonArray.length(); i++){
                RoomBean roomBean = new RoomBean();
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    roomBean.setRoomName(jsonObject.getString("room"));
                    JSONArray devicesList = jsonObject.getJSONArray("device");
                    ArrayList<Devices> devices = new ArrayList<>();
                    for(int j= 0 ; j < devicesList.length(); j++){
                        JSONObject deviceJSON = devicesList.getJSONObject(j);
                        Devices device = new Devices();
                        device.setDeviceName(deviceJSON.getString("name"));
                        device.setDeviceType(deviceJSON.getString("deviceType"));
                        device.setStatus(deviceJSON.getString("status"));
                        devices.add(device);
                    }
                    roomBean.setDevicesList(devices);
                }catch (Exception e){
                    e.printStackTrace();
                }

                roomBeanList.add(roomBean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
