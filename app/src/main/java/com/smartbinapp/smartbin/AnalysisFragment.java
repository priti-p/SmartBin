package com.smartbinapp.smartbin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Priti_Parate on 9/24/2015.
 */
public class AnalysisFragment extends Fragment implements View.OnClickListener{

//    List<BinInfo> finalList = LaunchActivity.BinList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //final LinearLayout llFillLevels = (LinearLayout) inflater.inflate(R.layout.analysis, container, false);
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
}
