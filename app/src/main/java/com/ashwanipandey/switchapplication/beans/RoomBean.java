package com.ashwanipandey.switchapplication.beans;

import java.util.ArrayList;
import java.util.List;

public class RoomBean {

    private String roomName;
    private ArrayList<Devices> devicesList;
    private boolean status;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ArrayList<Devices> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(ArrayList<Devices> devicesList) {
        this.devicesList = devicesList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
