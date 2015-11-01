package com.smartbinapp.smartbin;

/**
 * Created by Shilpa_choudhary on 10/21/2015.
 */

import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;

import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class customFregmentTab  extends Fragment implements View.OnClickListener {

    private final static String TAG = "customFregmant";
    EditText text1;
    EditText text2;
    Button button1;
    Button button2;
    private int year, month, day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day =  c.get(Calendar.DAY_OF_MONTH);
        View rootView = inflater.inflate(R.layout.customfregmentlayout, container, false);

        RelativeLayout rl = (RelativeLayout)rootView.findViewById(R.id.loclayout);
        if(Double.parseDouble(BinDetailsActivity1.filltxt)>80){
            rl.setBackgroundResource(R.drawable.red_button_border);

        }
        else if(Double.parseDouble(BinDetailsActivity1.filltxt) <=80 && Double.parseDouble(BinDetailsActivity1.filltxt)>50 ){
            rl.setBackgroundResource(R.drawable.orange_button_border);
        }else{
            rl.setBackgroundResource(R.drawable.green_button_border);
        }

        TextView ltxtView = (TextView) rootView.findViewById(R.id.ltxtView);
        ltxtView.setText(BinDetailsActivity1.loctxt);

        TextView rtxtView = (TextView) rootView.findViewById(R.id.rtxtView);
        rtxtView.setText(BinDetailsActivity1.filltxt +"%");

        button1 = (Button) rootView.findViewById(R.id.button1);// get button id
        button1.setOnClickListener(this); //on button appay click listner

        button2 = (Button) rootView.findViewById(R.id.button2);// get button id from
        button2.setOnClickListener(this); //on button appay click listner


        text1 = (EditText) rootView.findViewById(R.id.editText1);// id get from
        text1.setText(day + " / " + (month + 1) + " / " + year);
        text2 = (EditText) rootView.findViewById(R.id.editText2);
        text2.setText("Today");     //set text

        Log.v(TAG, "shilpa in mainactivity actionbar custom activity3");
        return rootView;
    }


    public void onClick(View v)//on button click that called
    {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);

        switch (v.getId())// on run time get id what button os click and get id
        {
            case R.id.button1:        // it mean if button1 click then this work
                date.setCallBack(ondateForFrom); //
                date.show(getActivity().getFragmentManager(), "datePickerForFromButton");
                break;

            case R.id.button2:        // it mean if button1 click then this work
                date.setCallBack(ondateForTo);
                date.show(getActivity().getFragmentManager(), "datePickerforToButton");
                break;

            case R.id.button3:        // it mean if button3 click then this work
                //show custom graph
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                customGraphView graph = new customGraphView();
                fragmentTransaction.replace(R.id.binDetails, graph);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }

    //this is for set date for from button
    DatePickerDialog.OnDateSetListener ondateForFrom = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Date today = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.MONTH, -6); //subtract 6 month
            //view.setMinDate(c.getTime().getTime());
            text1.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);
        }
    };

    //This is to set date for To button
    DatePickerDialog.OnDateSetListener ondateForTo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            text2.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " +year);
        }
    };


}

