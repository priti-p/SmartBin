package com.smartbinapp.smartbin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Priti_Parate on 9/24/2015.
 */
public class MapFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        return inflater.inflate(R.layout.map, container, false);


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
//        ImageView ivMap = (ImageView) getActivity().findViewById(R.id.mapActivity);
//        ivMap.setSelected(true);



    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        //((BaseContainerFragment)getParentFragment()).replaceFragment(new MapFragment(), true);
    }
}
