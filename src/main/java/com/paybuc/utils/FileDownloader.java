/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paybuc.utils;

import com.paybuc.enums.FileLinks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Brandon
 */
public class FileDownloader {
    
    private static File file;
    private static String FILE_NAME;

    private FileDownloader(String FILE_NAME, String FILE_URL) {
        while(!Download(FILE_NAME, FILE_URL)){
            try {
                System.out.println("Failed to Download, process restarting...");
                Thread.sleep(2500L);
            } catch (InterruptedException ex) {
                Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void directory(){
        File binDir = new File("bin");
        File backupDir = new File("bin\\backup");

        if(!binDir.exists()){
            backupDir.mkdirs();
        }

    }

    public static void getDefaultFiles(){
        directory();
        Download("sa_daily_lotto.csv", FileLinks.DailyLotto.toString());
        Download("lotto.csv", FileLinks.Lottory.toString());
        Download("lotto_plus_one.csv", FileLinks.LottoryPlusOne.toString());
        Download("lotto_plus_two.csv", FileLinks.LottoryPlusTwo.toString());
        Download("powerball.csv", FileLinks.Powerball.toString());
        Download("powerball_plus.csv", FileLinks.PowerballPlus.toString());
    }
    
    public static boolean Download(String FILE_NAME, String FILE_URL){
        try {
            URL fetchWebsite = new URL(FILE_URL);
            System.setProperty("http.agent", "Chrome");
            ReadableByteChannel readableByteChannel = Channels.newChannel(fetchWebsite.openStream());
            
            file = new File(new File("").getAbsolutePath() + "\\bin\\"+FILE_NAME);

            System.out.println(file.getAbsolutePath());

            if(file.exists()){
                MoveFile(file.getAbsolutePath(), System.getProperty("user.dir")+"\\bin\\backup\\"+file.getName());
                System.out.println("Backup successfully moved.");
            }
             
            try (FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile())) {
                fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                System.out.println("File download complete.");
                return true;
            } catch (IOException ex) {
                return false;
            }
        } catch (MalformedURLException ex) { 
            return false;
        } catch (IOException ex) {
            return false; 
        }
    }
    
    public static boolean MoveFile(String From, String To) {
        try {
            // ======= CHECK IF toFILE EXISTS =======
            File toFile = new File(To);
            if (toFile.exists()) {
                toFile.delete();
            }
            // =====================================

            Path temp = Files.move(Paths.get(From),
                        Paths.get(To));

            if (temp != null) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            return false;
        }
    }
    
    private FileDownloader(String FILE_NAME, String FILE_URL, boolean k) {
        try {
            URL fetchWebsite = new URL(FILE_URL);
            System.setProperty("http.agent", "Chrome");
            ReadableByteChannel readableByteChannel = Channels.newChannel(fetchWebsite.openStream());
            try (FileOutputStream fos = new FileOutputStream(new File("").getAbsolutePath() + "\\"+FILE_NAME)) {
                fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static File getFile(){
        return file;
    } 
}
