package com.example.admin.corneronahorizontalbar;

import android.text.format.Time;

import com.orm.SugarRecord;

public class Contact extends SugarRecord{

    //String sTime;
    String sChronometer;

    //обязательно создайте пустой конструктор
    public Contact() {
    }

    public Contact(String sChronometer) {
        //this.sTime = sTime;
        this.sChronometer = sChronometer;
    }

    @Override
    public String toString() {
        return sChronometer;
    }
}