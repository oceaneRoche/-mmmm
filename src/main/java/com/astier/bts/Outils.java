package com.astier.bts;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Outils {
    public ArrayList<Ip_v4>getSystemIp(){
        ArrayList<Ip_v4> ip = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()){
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isUp() && !networkInterface.isLoopback() && !networkInterface.isVirtual()){
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    while(inetAddresses.hasMoreElements()){
                        InetAddress inetAddress = inetAddresses.nextElement();
                        if (inetAddress.getHostName().contains(".") && !inetAddress.isLoopbackAddress()){
                            ip.add(new Ip_v4(networkInterface.getDisplayName(), inetAddress.getHostName(), inetAddress.getHostAddress()));
                        }
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        return ip;
    }
}
