package com.paybuc;

import com.paybuc.utils.FileDownloader;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            InetAddress addres = InetAddress.getLocalHost();

            if(addres.isLoopbackAddress()){
                System.out.println(addres + "(OFFLINE)");
            } else {
                System.out.println(addres+"(ONLINE)");
                FileDownloader.getDefaultFiles();
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
}