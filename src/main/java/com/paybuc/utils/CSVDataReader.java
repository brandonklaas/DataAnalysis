package com.paybuc.utils;

import com.paybuc.pojos.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CSVDataReader {

    private static String line = "";
    private static String splitBy = ",";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static HashMap<String,DailyLottoDto> toDailyLotto(String path) {
        HashMap<String,DailyLottoDto> map = null;
        try {
            map = new HashMap<String, DailyLottoDto>();
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);
                DailyLottoDto dailyLottoDto = new DailyLottoDto();
                dailyLottoDto.setOne(Integer.parseInt(numbers[2].trim()));
                dailyLottoDto.setTwo(Integer.parseInt(numbers[3].trim()));
                dailyLottoDto.setThree(Integer.parseInt(numbers[4].trim()));
                dailyLottoDto.setFour(Integer.parseInt(numbers[5].trim()));
                dailyLottoDto.setBonus(Integer.parseInt(numbers[6].trim()));
                dailyLottoDto.setDate(dateFormat.parse(numbers[1].trim()));
                map.put(dailyLottoDto.toString(), dailyLottoDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static HashMap<String, PowerballDto> toPowerball(String path) {
        HashMap<String,PowerballDto> map = null;
        try {
            map = new HashMap<String, PowerballDto>();
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);
                PowerballDto powerballDto = new PowerballDto();
                powerballDto.setOne(Integer.parseInt(numbers[2].trim()));
                powerballDto.setTwo(Integer.parseInt(numbers[3].trim()));
                powerballDto.setThree(Integer.parseInt(numbers[4].trim()));
                powerballDto.setFour(Integer.parseInt(numbers[5].trim()));
                powerballDto.setFive(Integer.parseInt(numbers[6].trim()));
                powerballDto.setBonus(Integer.parseInt(numbers[7].trim()));
                powerballDto.setDate(dateFormat.parse(numbers[1].trim()));
                map.put(powerballDto.toString(), powerballDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static HashMap<String, PowerballPlusDto> toPowerballPlus(String path) {
        HashMap<String,PowerballPlusDto> map = null;
        try {
            map = new HashMap<String, PowerballPlusDto>();
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);
                PowerballPlusDto powerballPlusDto = new PowerballPlusDto();
                powerballPlusDto.setOne(Integer.parseInt(numbers[2].trim()));
                powerballPlusDto.setTwo(Integer.parseInt(numbers[3].trim()));
                powerballPlusDto.setThree(Integer.parseInt(numbers[4].trim()));
                powerballPlusDto.setFour(Integer.parseInt(numbers[5].trim()));
                powerballPlusDto.setFive(Integer.parseInt(numbers[6].trim()));
                powerballPlusDto.setBonus(Integer.parseInt(numbers[7].trim()));
                powerballPlusDto.setDate(dateFormat.parse(numbers[1].trim()));
                map.put(powerballPlusDto.toString(), powerballPlusDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static HashMap<String, LottoDto> toLotto(String path) {
        HashMap<String,LottoDto> map = null;
        try {
            map = new HashMap<String, LottoDto>();
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);
                LottoDto lottoDto = new LottoDto();
                lottoDto.setOne(Integer.parseInt(numbers[2].trim()));
                lottoDto.setTwo(Integer.parseInt(numbers[3].trim()));
                lottoDto.setThree(Integer.parseInt(numbers[4].trim()));
                lottoDto.setFour(Integer.parseInt(numbers[5].trim()));
                lottoDto.setFive(Integer.parseInt(numbers[6].trim()));
                lottoDto.setSix(Integer.parseInt(numbers[7].trim()));
                lottoDto.setBonus(Integer.parseInt(numbers[8].trim()));
                lottoDto.setDate(dateFormat.parse(numbers[1].trim()));
                map.put(lottoDto.toString(), lottoDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static HashMap<String, LottoPlusOneDto> toLottoPlusOne(String path) {
        HashMap<String,LottoPlusOneDto> map = null;
        try {
            map = new HashMap<String, LottoPlusOneDto>();
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);
                LottoPlusOneDto lottoPlusOneDto = new LottoPlusOneDto();
                lottoPlusOneDto.setOne(Integer.parseInt(numbers[2].trim()));
                lottoPlusOneDto.setTwo(Integer.parseInt(numbers[3].trim()));
                lottoPlusOneDto.setThree(Integer.parseInt(numbers[4].trim()));
                lottoPlusOneDto.setFour(Integer.parseInt(numbers[5].trim()));
                lottoPlusOneDto.setFive(Integer.parseInt(numbers[6].trim()));
                lottoPlusOneDto.setSix(Integer.parseInt(numbers[7].trim()));
                lottoPlusOneDto.setBonus(Integer.parseInt(numbers[8].trim()));
                map.put(lottoPlusOneDto.toString(), lottoPlusOneDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static HashMap<String, LottoPlusTwoDto> toLottoPlusTwo(String path) {
        HashMap<String,LottoPlusTwoDto> map = null;
        try {
            map = new HashMap<String, LottoPlusTwoDto>();
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(splitBy);
                LottoPlusTwoDto lottoPlusTwoDto = new LottoPlusTwoDto();
                lottoPlusTwoDto.setOne(Integer.parseInt(numbers[2].trim()));
                lottoPlusTwoDto.setTwo(Integer.parseInt(numbers[3].trim()));
                lottoPlusTwoDto.setThree(Integer.parseInt(numbers[4].trim()));
                lottoPlusTwoDto.setFour(Integer.parseInt(numbers[5].trim()));
                lottoPlusTwoDto.setFive(Integer.parseInt(numbers[6].trim()));
                lottoPlusTwoDto.setSix(Integer.parseInt(numbers[7].trim()));
                lottoPlusTwoDto.setBonus(Integer.parseInt(numbers[8].trim()));
                map.put(lottoPlusTwoDto.toString(), lottoPlusTwoDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
