package com.example.kwiatekjakub.ipcalculator;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by KwiatekJakub on 2017-07-25.
 */

public class IP {
    private int ipAddress;
    private int mask = -1;

    public IP(String ip, String mmask, Context context) {
        try {
            for (int i = 3; i > -1; i--) {
                if (i != 0) {
                    ipAddress += Integer.parseInt(ip.substring(0, ip.indexOf("."))) << i * 8;
                    ip = ip.substring(ip.indexOf(".") + 1);
                } else {
                    ipAddress += Integer.parseInt(ip) << i * 8;
                }
            }
            mask = mask << (32 - Integer.parseInt(mmask));
        }catch (NumberFormatException e){
            Toast.makeText(context, R.string.wrong_data_alert, Toast.LENGTH_LONG).show();
        }

        }

    public IP(int ipAddress) {
        this.ipAddress = ipAddress;
    }

    public IP getMask() {
        return new IP(mask);
    }

    public int getIpAddress() {
        return ipAddress;
    }

    public String getIpAddressString() {
        return this.toString();
    }

    public IP netAddress() {
        return new IP(ipAddress & mask);
    }

    public IP broadcast() {
        return new IP(netAddress().getIpAddress() | ~mask);
    }

    public IP minAddress() {
        return new IP(netAddress().getIpAddress() + 1);
    }

    public IP maxAddress() {
        return new IP(broadcast().getIpAddress() - 1);
    }

    public int numberOfHosts() {
        return maxAddress().getIpAddress() - netAddress().getIpAddress();
    }

    @Override
    public String toString() {
        return String.valueOf((ipAddress >> 24) & 255) + "."
                + String.valueOf((ipAddress >> 16) & 255) + "."
                + String.valueOf((ipAddress >> 8) & 255) + "."
                + String.valueOf(ipAddress & 255);
    }
}