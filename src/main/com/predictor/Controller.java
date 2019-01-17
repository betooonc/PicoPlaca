package com.predictor;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller{
    private int lastDigit;
    private Date inputTime, morningFrom, morningTo, afternoonFrom, afternoonTo, inputDate;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
    private boolean verifyTime, verifyDate;

    public void setParameters(String plateNum, String date, String time) {
        //Take the last digit of the plate number
        try {
            lastDigit = Integer.parseInt(plateNum.substring(plateNum.length() - 1));
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Please, check again your Plate Number");
            return;
        }

        //Date is converted and availability is checked
        try {
            inputDate = format.parse(date);
            verifyDate = checkDay(inputDate.getDay(),lastDigit);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Date must be in dd/MM/yy format");
            e.printStackTrace();
        }

        //Time is converted and availability is checked
        try {
            inputTime = timeFormat.parse(time);
            verifyTime = checkTime(inputTime);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Time must be in hh:mm format");
            return;
        }

        //Determined if the vehicle can be driven or not
        if (verifyDate == true){
            JOptionPane.showMessageDialog(null, "You can drive");
        }else if(verifyTime == true){
            JOptionPane.showMessageDialog(null, "You can drive");
        }else {
            JOptionPane.showMessageDialog(null, "You can´t drive");
        }
    }

    /**
     * Method to determine the availability of the date according to the day of the week and the last digit of the plate
     * @param day Day of the week to check
     * @param plateNum Last digit of Plate Number
     * @return Availability of the date  (True/False)
     */
    public boolean checkDay(int day, int plateNum){
        boolean availableDay = true;
        switch (day){
            case 1:
                if (plateNum == 1 || plateNum == 2){
                    availableDay = false;
                }
                break;
            case 2:
                if (plateNum == 3 || plateNum == 4){
                    availableDay = false;
                }
                break;
            case 3:
                if (plateNum == 5 || plateNum == 6){
                    availableDay = false;
                }
                break;
            case 4:
                if (plateNum == 7 || plateNum == 8){
                    availableDay = false;
                }
                break;
            case 5:
                if (plateNum == 9 || plateNum == 0){
                    availableDay = false;
                }
                break;
        }
        return availableDay;
    }

    /**
     * Method to determine the availability of the time according to the established schedule
     * @param time Time to check
     * @return Availability of the time  (True/False)
     */
    public boolean checkTime(Date time) {
        boolean availableTime = true;
        try {
            //Schedule established by the AMT
            morningFrom = timeFormat.parse("07:00");
            morningTo = timeFormat.parse("09:30");
            afternoonFrom = timeFormat.parse("16:00");
            afternoonTo = timeFormat.parse("19:30");

            if ((morningFrom.getTime() <= time.getTime()) && morningTo.getTime() >= time.getTime()){
                availableTime = false;
            }
            if ((afternoonFrom.getTime() <= time.getTime()) && afternoonTo.getTime() >= time.getTime()){
                availableTime = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return availableTime;
    }
}
