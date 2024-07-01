/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paybuc.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import com.paybuc.enums.InternetStatus;

/**
 *
 * @author Brandon
 */
public class InternetConnection {

    private InternetStatus status;

    public InternetConnection(){
        checkStatus();
    }
    
    public void checkStatus() {
        try {
            URL url = new URL("http://www.google.com");
            System.setProperty("http.agent", "Chrome");
            URLConnection connection = url.openConnection();
            connection.connect();
            status = InternetStatus.ONLINE;
        } catch (MalformedURLException e) {
            status = InternetStatus.OFFLINE;
        } catch (IOException e) {
            status = InternetStatus.OFFLINE;
        }
    }
    
    public InternetStatus getStatus(){
        return status;
    }
}
