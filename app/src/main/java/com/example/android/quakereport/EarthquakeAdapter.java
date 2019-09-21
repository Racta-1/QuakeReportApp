package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null ){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake,parent,false);
        }

        Earthquake currentQuake = getItem(position);

        TextView magTextView = (TextView) listView.findViewById(R.id.magnitude);
        Double mag = new Double(currentQuake.getmMag());
        magTextView.setText(formatDecimal(mag));
        //String formattedMag = formatDecimal(currentQuake.getMag())
        //magTextView.setText(formattedData)

        TextView locationTextView = (TextView) listView.findViewById(R.id.location);
        locationTextView.setText(currentQuake.getmLocation());

        TextView dateTextView = (TextView) listView.findViewById(R.id.date);
        Date date = new Date(currentQuake.getmDate());
        dateTextView.setText(formatDate(date));

        TextView timeTextView = (TextView) listView.findViewById(R.id.time);
        Date time = new Date(currentQuake.getmDate());
        timeTextView.setText(formatTime(time));

        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentQuake.getmMag());
        magnitudeCircle.setColor(magnitudeColor);

        return listView;
    }

    private String formatDate(Date dateObject){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLL dd,yyyy");
        return simpleDateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject){
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("h:mm a");
        return simpleTimeFormat.format(dateObject);
    }

    private String formatDecimal(double magnitude){
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
                default:
                    magnitudeColorResourceId = R.color.magnitude10plus;
                    break;

        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
