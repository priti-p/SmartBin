package com.smartbinapp.smartbin;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Priti_Parate on 9/22/2015.
 */
public class FillLevelsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        final LinearLayout llFillLevels = (LinearLayout) inflater.inflate(R.layout.filllevel, container, false);
//        final RelativeLayout params = (RelativeLayout) inflater.inflate(R.layout.filllevel_button, container,false);
//
//        setview((LinearLayout)llFillLevels.findViewById(R.id.fillLevelsActivity), params);

        String[] stringArray = new String[10];
        for(int i=0; i < stringArray.length; i++){
            stringArray[i] = "String " + i;
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.filllevel_item, stringArray );
        ListView stringListView = (ListView) llFillLevels.findViewById(R.id.fillLevel_list);
        stringListView.setAdapter(arrayAdapter);

        return inflater.inflate(R.layout.filllevel, container, false);


    }

    private void setListView() {
        String[] stringArray = new String[10];
        for(int i=0; i < stringArray.length; i++){
            stringArray[i] = "String " + i;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
        ListView stringListView = (ListView) getActivity().findViewById(R.id.fillLevel_list);
        stringListView.setAdapter(arrayAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setListView();
        //LinearLayout llFillLevels = (LinearLayout) getActivity().findViewById(R.id.fillLevelsActivity);
//        RelativeLayout params = (RelativeLayout) getActivity().findViewById(R.id.flbtnlayout);
//        setview(llFillLevels, params);
    }

//    @Override
//    public void onClick(View arg0) {
//        // TODO Auto-generated method stub
//        //((BaseContainerFragment)getParentFragment()).replaceFragment(new MapFragment(), true);
//    }

    private void setview(LinearLayout llFillLevels, RelativeLayout params){
        //button will be displayed in this layout


        //Create four
        for(int j=0;j<=10;j++) {
//            // Create LinearLayout
//            LinearLayout llLayout = new LinearLayout(this);
//            llLayout.setOrientation(LinearLayout.VERTICAL);

            // Create Button
            final Button btn = new Button(getActivity().getApplicationContext() );

            // Give button an ID
            btn.setId(j+1);
            if(j<2){
                btn.setBackgroundResource(R.drawable.red_button_border);
            }
            else if(j<6){
                btn.setBackgroundResource(R.drawable.orange_button_border);
            }
            else{
                btn.setBackgroundResource(R.drawable.green_button_border);
            }
            btn.setText("Bin " + j + 1);
            // set the layoutParams on the button

//            TextView ltxtView = (TextView) params.findViewById(R.id.ltxtView);
//            ltxtView.setText("Loc1, Bin1");



            //inal android.widget.RelativeLayout llbtn = (android.widget.RelativeLayout) getResources().getLayout(R.layout.filllevel_button);


            //btn.setLayoutParams(llFillLevels.getLayoutParams());
//            btn.setMinimumWidth(launch.getMeasuredWidth());

            final int index = j;
            // Set click listener for button
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    android.util.Log.i("location button", "index :" + index);

                    //Toast.makeText(getApplicationContext(), "Clicked Button Index :" + index, Toast.LENGTH_LONG).show();

                }
            });

            //Add button to LinearLayout
            llFillLevels.addView(btn);
            //Add button to LinearLayout defined in XML
//            launch.addView(llLayout);
        }

    }

}
