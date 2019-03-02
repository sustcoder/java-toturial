package com.sustcoder.auth;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MACUtils
{
    public static String getLocalMAC()
            throws SocketException, UnknownHostException
    {
        byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < mac.length; ++i) {
            if (i != 0) {
                buff.append("-");
            }

            int temp = mac[i] & 0xFF;
            String str = Integer.toHexString(temp);
            if (str.length() == 1)
                buff.append("0" + str);
            else
                buff.append(str);
        }

        return buff.toString();
    }
}
