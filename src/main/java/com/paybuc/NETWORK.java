/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paybuc;

import com.paybuc.utils.FileDownloader;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandon
 */
public class NETWORK {
    public static void main(String[] args){
        try {
            InetAddress addres = InetAddress.getLocalHost();
            
            if(addres.isLoopbackAddress()){
                System.out.println(addres + "(OFFLINE)");
            } else {
                System.out.println(addres+"(ONLINE)");
                FileDownloader.getDefaultFiles();
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(NETWORK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
