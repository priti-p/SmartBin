package com.smartbinapp.smartbin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Priti_Parate on 9/24/2015.
 */
public class AnalysisFragment extends Fragment implements View.OnClickListener{

    public static com.smartbinapp.smartbin.MyLinkedMap<String, Double> analysisMap = null;

    //BinInfo allBinsInfo = BinInfo.getInstance();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Bins = "bins";
    private static final String TAG_ID = "Id";
    private static final String TAG_Fill = "Fill";
    private static final String TAG_Loc = "Location";

    EditText fromText;
    EditText toText;
    private int year, month, day;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        return inflater.inflate(R.layout.analysis, container, false);


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        ((BaseContainerFragment)getParentFragment()).replaceFragment(new AnalysisFragment(), true);
    }

    @Override
    public void onStart () {

        super.onStart();

        ((LaunchActivity) getActivity()).getSupportActionBar().setTitle(R.string.Analysis_Fragment);

        LinearLayout llFillLevels = (LinearLayout) getActivity().findViewById(R.id.analysisActivity);
        ListView stringListView = (ListView) llFillLevels.findViewById(R.id.compareBinsList);
        List<Map.Entry<String, Double>> list=null;

        try {
            com.smartbinapp.smartbin.MyLinkedMap<String, Integer> tempMap = new MyLinkedMap<String, Integer>();
            com.smartbinapp.smartbin.MyLinkedMap<String, Double> binValues = new MyLinkedMap<String, Double>();

            for (int i = 0; LaunchActivity.bins!=null && i < LaunchActivity.bins.length(); i++) {

                JSONObject c = LaunchActivity.bins.getJSONObject(i);
                Log.d("object " + i, c.toString());
                String loc = c.getString(TAG_Loc);
                if(tempMap.get(loc)== null){
                    tempMap.put(loc, 1);
                }
                else{
                    int temp = tempMap.get(loc) +1;
                    tempMap.put(loc, temp);

                }
                //binValues.put(c.getString(TAG_Loc)+", Bin"+c.getString(TAG_PID), Double.parseDouble(c.getString(TAG_Fill)));
                if(tempMap.get(loc)==1)
                    binValues.put(c.getString(TAG_Loc), Double.parseDouble(c.getString(TAG_Fill)));
                else if (tempMap.get(loc)==2){
                    binValues.put(loc+", Bin1", binValues.get(loc));
                    //fillLevelMap.put(c.getString(TAG_Loc), Double.parseDouble(c.getString(TAG_Fill)));
                    binValues.remove(loc);
                    binValues.put(loc + ", Bin2", Double.parseDouble(c.getString(TAG_Fill)));
                }
                else
                {
                    binValues.put(loc+", Bin"+Integer.toString(tempMap.get(loc)), Double.parseDouble(c.getString(TAG_Fill)));
                }
            }

            Set<Map.Entry<String, Double>> set = binValues.entrySet();

            list = new ArrayList<Map.Entry<String, Double>>(set);
            Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });

            analysisMap = new MyLinkedMap<String, Double>();
            for (Map.Entry<String, Double> entry : list)
            {
                analysisMap.put( entry.getKey(), entry.getValue() );
            }

//                JSONObject c = LaunchActivity.bins.getJSONObject(i);
//                //binValues.put(c.getString(TAG_Loc)+", Bin"+c.getString(TAG_ID), Double.parseDouble(c.getString(TAG_Fill)));
//                analysisMap.put(c.getString(TAG_Loc) + ", Bin" + c.getString(TAG_ID), Double.parseDouble(c.getString(TAG_Fill)));
//            }

            stringListView.setAdapter(new AnalysisCustomAdapter(getActivity(), analysisMap));

            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day =  c.get(Calendar.DAY_OF_MONTH);
            fromText = (EditText) llFillLevels.findViewById(R.id.FromDate);// id get from example_3_1 xml file
            fromText.setText(day + " / " + (month + 1) + " / " + year);
            toText = (EditText) llFillLevels.findViewById(R.id.ToDate);
            toText.setText("Today");     //set text

            fromText.setInputType(InputType.TYPE_NULL);
            fromText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePicker(v);
                }
            });
            fromText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        showDatePicker(v);
                    }
                }
            });

//            toText.setInputType(InputType.TYPE_NULL);
//            toText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showDatePicker(v);
//                }
//            });
//            toText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if (hasFocus) {
//                        showDatePicker(v);
//                    }
//                }
//            });

            RadioGroup radioGroup = (RadioGroup) llFillLevels.findViewById(R.id.analysis_trend);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // find which radio button is selected
                    if (checkedId == R.id.radio_fill_trend) {
                        Log.d("Analysis", "checked fill trend");
                        Intent intent = new Intent(getActivity(), AnalysisGraph.class);
                        startActivity(intent);
                    } else if (checkedId == R.id.radio_temp_trend) {
                        Log.d("Analysis", "checked temp trend");

                    } else if (checkedId == R.id.radio_humadity_trend) {
                        Log.d("Analysis", "checked humidity trend");

                    }
                }
            });



//            Set<Map.Entry<String, Double>> set = binValues.entrySet();
//
//            list = new ArrayList<Map.Entry<String, Double>>(set);
//            Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
//                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
//                    return (o2.getValue()).compareTo(o1.getValue());
//                }
//            });
//
//            analysisMap = new MyLinkedMap<String, Double>();
//            for (Map.Entry<String, Double> entry : list)
//            {
//                analysisMap.put( entry.getKey(), entry.getValue() );
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

//        stringListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("Module Item Trigger", "Module item was triggered");
//                //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), BinDetailsActivity1.class);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (LaunchActivity.data_for_search) {
            LaunchActivity.buildDialogAsSearched(getActivity()).show();
        }
    }

    public void showDatePicker (View v){
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
            case R.id.FromDate:        // it mean if button1 click then this work
                date.setCallBack(ondateForFrom); //
                date.show(getActivity().getFragmentManager(), "datePickerForFromButton");
                break;

//            case R.id.button2:        // it mean if button1 click then this work
//                date.setCallBack(ondateForTo);
//                date.show(getActivity().getFragmentManager(), "datePickerforToButton");
//                break;
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
            fromText.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);
        }
    };

    //This is to set date for To button
    DatePickerDialog.OnDateSetListener ondateForTo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            toText.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);
        }
    };


//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        Log.d("AnalysisFrg", "Inside radio button");
//        boolean checked = ((android.support.v7.widget.AppCompatRadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radio_fill_trend:
//                if (checked)
//
//                startActivity(intent);
//                    break;
//            case R.id.radio_temp_trend:
//                if (checked)
//                    // Ninjas rule
//                    break;
//
//            case R.id.radio_humadity_trend:
//                if (checked)
//                    // Ninjas rule
//                    break;
//
//        }
//    }
}
