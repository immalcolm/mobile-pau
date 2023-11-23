package com.pau.simpleapi;

import androidx.annotation.NonNull;

//Model Class for IP Address
public class IpAddress {

    private String ip;
    public String getIpAddress() {
        return ip;
    }

    public void setIpAddress(String ip) {
        this.ip = ip;
    }

    @NonNull
    @Override
    public String toString(){
        return "ip: " + ip;
    }

}
