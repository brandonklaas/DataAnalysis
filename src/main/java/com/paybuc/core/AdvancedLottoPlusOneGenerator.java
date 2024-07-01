/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paybuc.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.paybuc.enums.DayOfTheWeek;
import com.paybuc.enums.FileLinks;
import com.paybuc.enums.InternetStatus;
import com.paybuc.gui.Statistics;
import com.paybuc.utils.FileDownloader;
import com.paybuc.utils.InternetConnection;

/**
 * @author Brandon
 */
public class AdvancedLottoPlusOneGenerator {
    private Map<String, String[]> DrawData = new HashMap();
    private Map<String, String[]> PlusDrawData = new HashMap();
    
    private ArrayList<String[]> DrawDataArray = new ArrayList();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy"); 
    
    private Map<String, String> GeneratedData = new HashMap();
    private Map<String, Integer> GeneratedProbability = new HashMap();
    
    private int [][] fullNumberStat      = new int[52][2];
    private int odd = 0;
    private int even = 0;
    
    private int [][] filteredNumberStat  = new int[52][2];
    private int filteredOdd = 0;
    private int filteredEven = 0;

    private int Odd0_Even5 = 0;
    private int Odd1_Even4 = 0;
    private int Odd2_Even3 = 0;
    private int Odd3_Even2 = 0;
    private int Odd4_Even1 = 0;
    private int Odd5_Even0 = 0;
    private Statistics allStats;
    
    private String[] latestDraw;

    private String path;
    private int numberOfDraws = 0;
    private int[] generated_pb;
    
    private String FILE_NAME = "lotto_plus_one.csv";
    
    private InternetStatus internetStatus;
    private File file;
    private FileDownloader fileDownloader;
    
    public AdvancedLottoPlusOneGenerator() {
        internetStatus = new InternetConnection().getStatus();
        
        if(internetStatus == InternetStatus.ONLINE){
            System.out.println("Internet Status : " + internetStatus);
            fileDownloader = new FileDownloader(FILE_NAME, FileLinks.LottoryPlusOne.toString());
            file = fileDownloader.getFile();
        } else {
            System.out.println("Internet Status : " + internetStatus);
            file = new File("bin\\"+FILE_NAME);
        }
        
        defaults();
        this.path = file.getAbsolutePath();
        
        String line = "";
        String splitBy = ",";
        
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);    
                // use comma as separator  
                String sequence = numberStats(numbers[2].trim()) + " " + numberStats(numbers[3].trim()) + " " + numberStats(numbers[4].trim()) + " " + numberStats(numbers[5].trim()) + " " + numberStats(numbers[6].trim()) + " " + numbers[7].trim();
                if(!DrawData.containsKey(sequence)){
                    DrawDataArray.add(new String[]{numbers[2].trim() , numbers[3].trim() , numbers[4].trim() , numbers[5].trim() , numbers[6].trim() , numbers[7].trim(), numbers[8].trim(), numbers[1]});
                    DrawData.put(sequence, new String[]{numbers[2].trim() , numbers[3].trim() , numbers[4].trim() , numbers[5].trim() , numbers[6].trim() , numbers[7].trim(), numbers[8].trim(), numbers[1]});
                    latestDraw = new String[]{numbers[2].trim(), numbers[3].trim(), numbers[4].trim(), numbers[5].trim(), numbers[6].trim(), numbers[7].trim(), numbers[8].trim(), numbers[1]};

                    int tempEven = 0, tempOdd = 0;
                    for (int i = 2; i < 8; i++) {
                        if ((Integer.parseInt(numbers[i].trim())) % 2 == 0) {
                            tempEven++;
                        } else {
                            tempOdd++;
                        }
                    }
                    
                    calculateStats(tempEven, tempOdd);
                } else {
                    if (!numbers[1].equals(DrawData.get(sequence)[7])) {
                        System.out.println("Duplicate : " + sequence + "   Days Repeated " + DrawData.get(sequence)[7] + " - [" + numbers[1] + "]");
                    }
                }
            }
            
            allStats = new Statistics("All Draws", String.valueOf(Odd0_Even5), String.valueOf(Odd1_Even4), String.valueOf(Odd2_Even3),
                       String.valueOf(Odd3_Even2), String.valueOf(Odd4_Even1), String.valueOf(Odd5_Even0));
            
            lastStats(25);
            
//            String sequence;
//            for (int i = 0; i < 10000000; i++) {
//                if (!checkReappearances(sequence = generatePowerBall())) {
//                    if(GeneratedData.containsKey(sequence)){
//                        if(GeneratedProbability.containsKey(sequence)){
//                            GeneratedProbability.put(sequence, GeneratedProbability.get(sequence) + 1);
//                        } else {
//                            GeneratedProbability.put(sequence, 2);
//                        }
//                    } else {
//                        GeneratedData.put(sequence, sequence);
//                    }
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public AdvancedLottoPlusOneGenerator(boolean b) {
        internetStatus = new InternetConnection().getStatus();
        
        if(internetStatus == InternetStatus.ONLINE){
            System.out.println("Internet Status : " + internetStatus);
            
            fileDownloader = new FileDownloader(FILE_NAME, FileLinks.Powerball.toString());
            file = fileDownloader.getFile();
        } else {
            System.out.println("Internet Status : " + internetStatus);
            file = new File("bin\\"+FILE_NAME);
        }
        
        defaults();
        this.path = file.getAbsolutePath();
        getPattern("Wednesday");
        getPattern("Saturday");
        
        String line = "";
        String splitBy = ",";
        
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);    // use comma as separator  
                String sequence = numberStats(numbers[2].trim()) + " " + numberStats(numbers[3].trim()) + " " + numberStats(numbers[4].trim()) + " " + numberStats(numbers[5].trim()) + " " + numberStats(numbers[6].trim()) + " " + numberStats(numbers[7].trim());
                
                if (!DrawData.containsKey(sequence)) {
//                    if (getDayOfTheWeek(numbers[1]).equals("Friday")) {
                        DrawDataArray.add(new String[]{numbers[2].trim(), numbers[3].trim(), numbers[4].trim(), numbers[5].trim(), numbers[6].trim(), numbers[7].trim(), numbers[8].trim(), "[" + numbers[1] + "]"});
                        DrawData.put(sequence, new String[]{numbers[2].trim(), numbers[3].trim(), numbers[4].trim(), numbers[5].trim(), numbers[6].trim(), numbers[7].trim(), numbers[8].trim(), "[" + numbers[1] + "]"});
                        
                        int tempEven = 0, tempOdd = 0;
                        
                        for (int i = 2; i < 7; i++) {
                            if ((Integer.parseInt(numbers[i].trim())) % 2 == 0) {
                                tempEven++;
                            } else {
                                tempOdd++;
                            }
                        }
                        calculateStats(tempEven, tempOdd);
//                    }
                } else {
                    if (!numbers[1].equals(DrawData.get(sequence)[7])) {
                        System.out.println("Duplicate : " + sequence + "   Days Repeated " + DrawData.get(sequence)[7] + " - [" + numbers[1] + "]");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Map<String, String> generate(int num2gen) {
        String sequence;
        for (int i = 0; i < num2gen; i++) {
            if (!checkReappearances(sequence = generateLotto())) {
                if (GeneratedData.containsKey(sequence)) {
                    if (GeneratedProbability.containsKey(sequence)) {
                        GeneratedProbability.put(sequence, GeneratedProbability.get(sequence) + 1);
                    } else {
                        GeneratedProbability.put(sequence, 2);
                    }
                } else {
                    GeneratedData.put(sequence, sequence);
                }
            }
        }
        return GeneratedData;
    }
    
    public Statistics getAllStats(){
        return allStats;
    }
    
    public String[] getLatestDraw(){
        return latestDraw;
    }
    
    public Statistics getPattern(String day) {
        String line = "";
        String splitBy = ",";

        Odd0_Even5 = 0;
        Odd1_Even4 = 0;
        Odd2_Even3 = 0;
        Odd3_Even2 = 0;
        Odd4_Even1 = 0;
        Odd5_Even0 = 0;

        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(path));
       
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);
                
                if (getDayOfTheWeek(numbers[1]).equals(day)) {
                    int tempEven = 0, tempOdd = 0;
                    for (int i = 2; i < 7; i++) {
                        if ((Integer.parseInt(numbers[i].trim())) % 2 == 0) {
                            tempEven++;
                        } else {
                            tempOdd++;
                        }
                    }
                    calculateStats(tempEven, tempOdd);
                }
            }
            
            return new Statistics(day, String.valueOf(Odd0_Even5), String.valueOf(Odd1_Even4), String.valueOf(Odd2_Even3),
                       String.valueOf(Odd3_Even2), String.valueOf(Odd4_Even1), String.valueOf(Odd5_Even0));
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Map<String, String[]> getDrawData(){
        return DrawData;
    }
    
    public void calculateStats(int Even, int Odd) {
        numberOfDraws++;
        switch(Odd + "-" + Even) {
            case "0-5":
                Odd0_Even5++;
                break;
            case "1-4":
                Odd1_Even4++;
                break;
            case "2-3":
                Odd2_Even3++;
                break;
            case "3-2":
                Odd3_Even2++;
                break;
            case "4-1":
                Odd4_Even1++;
                break; 
            case "5-0":
                Odd5_Even0++;
                break; 
        }
    }
    
    public String getDayOfTheWeek(String strDate) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormatter.parse(strDate));
            return DayOfTheWeek.fromId(cal.get(Calendar.DAY_OF_WEEK)).toString();
        } catch (ParseException ex) {
            return null;
        }
    }
 
    public int[][] lastStats(int numberOfDraws) {
        filteredEven = 0;
        filteredOdd = 0;
        filteredNumberStat = new int[52][2];
        filteredDefaults();

        for (int i = DrawDataArray.size() - numberOfDraws; i < DrawDataArray.size(); i++) {
            filteredNumberStats(DrawDataArray.get(i)[0]);
            filteredNumberStats(DrawDataArray.get(i)[1]);
            filteredNumberStats(DrawDataArray.get(i)[2]);
            filteredNumberStats(DrawDataArray.get(i)[3]);
            filteredNumberStats(DrawDataArray.get(i)[4]);
        }
        showFilteredNumStats();
        return filteredNumberStat;
    }
    
    public void defaults() {
        for (int n = 0; n < fullNumberStat.length; n++) {
            fullNumberStat[n][0] = n + 1;
        }
    }
    
    public void filteredDefaults() {
        for(int n = 0; n < filteredNumberStat.length; n++) {
            filteredNumberStat[n][0] = n+1;
        }
    }
    
    public int filteredNumberStats(String num) {
        if((Integer.parseInt(num) % 2) == 0){
            filteredEven++;
            filteredNumberStat[Integer.parseInt(num)-1][1]++;
        } else {
            filteredOdd++;
            filteredNumberStat[Integer.parseInt(num)-1][1]++;
        }
        return Integer.parseInt(num);
    }
    
    public int[][] getFullNumberStat(){
        return fullNumberStat;
    }
    
    public int numberStats(String num){
        if((Integer.parseInt(num) % 2) == 0){
            even++;
            fullNumberStat[Integer.parseInt(num)-1][1]++;
        } else {
            odd++;
            fullNumberStat[Integer.parseInt(num)-1][1]++;
        }
        return Integer.parseInt(num);
    }
    
    public void showFilteredNumStats(){
        Arrays.sort(filteredNumberStat, Comparator.comparingDouble(o -> o[1]));
//        for(int i = 0; i < 50; i++) {
//            System.out.println(filteredNumberStat[i][0] + " : " +filteredNumberStat[i][1]+ " times");
//        }
//        System.out.println("========================================");
//        System.out.println("Odd : " + filteredOdd + "\nEven : " + filteredEven);
    }
    
    public void showNumStats() {
        Arrays.sort(fullNumberStat, Comparator.comparingDouble(o -> o[1]));
//        for(int i = 0; i < 50; i++) {
//            System.out.println(fullNumberStat[i][0] + " : " +fullNumberStat[i][1]+ " times");
//        }
//        System.out.println("========================================");
//        System.out.println("Odd : " + odd + "\n" + "Even : " + even);
    }
    
    public boolean checkReappearances(String sequence) {
        return (DrawData.containsKey(sequence)) ? true : false;
    }
    
    public String generateLotto(){
        generated_pb = new int[]{random(52),random(52),random(52),random(52),random(52), random(52)};
        Arrays.sort(generated_pb);
        return String.valueOf(generated_pb[0]+" "+generated_pb[1]+" "+generated_pb[2]+" "+generated_pb[3]+" "+generated_pb[4]+" "+generated_pb[5]);
    }
    
    public int[] getGenerated(){
        return generated_pb;
    }
    
    public int random(int max) {
        Random rand = new Random();
        return rand.nextInt(max) + 1;
    }
    
    public static void main(String [] args) {
        new AdvancedLottoPlusOneGenerator(); 
    }
}
