package com.ashwanipandey.switchapplication.beans;

import java.io.Serializable;

public class Devices implements Serializable{

    private String deviceName;
    private String deviceType;
    private String status;
    private boolean switchStatus;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if(status.equalsIgnoreCase("off"))
            this.switchStatus = false;
        else
            this.switchStatus = true;
    }

    public boolean isSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(boolean switchStatus) {
        this.switchStatus = switchStatus;
    }
}
