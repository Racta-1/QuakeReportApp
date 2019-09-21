package com.example.android.quakereport;

public class Earthquake {
    private double mMag;
    private String mLocation;
    private long mDate;

    public Earthquake(double Mag, String Location, long Date){
        this.mMag = Mag;
        this.mLocation = Location;
        this.mDate = Date;
    }

    public double getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }


    public long getmDate() {
        return mDate;
    }



}
