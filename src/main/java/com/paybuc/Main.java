package com.paybuc;

import com.paybuc.database.DatabaseConnector;
import com.paybuc.enums.FileDirectory;
import com.paybuc.utils.CSVDataReader;
import com.paybuc.utils.FileDownloader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

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

            System.out.println(CSVDataReader.toLotto(FileDirectory.Lottory.toString()).size());
            System.out.println(CSVDataReader.toLottoPlusOne(FileDirectory.LottoryPlusOne.toString()).size());
            System.out.println(CSVDataReader.toLottoPlusTwo(FileDirectory.LottoryPlusTwo.toString()).size());
            System.out.println(CSVDataReader.toPowerball(FileDirectory.Powerball.toString()).size());
            System.out.println(CSVDataReader.toPowerballPlus(FileDirectory.PowerballPlus.toString()).size());
            System.out.println(CSVDataReader.toDailyLotto(FileDirectory.DailyLotto.toString()).size());

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
}