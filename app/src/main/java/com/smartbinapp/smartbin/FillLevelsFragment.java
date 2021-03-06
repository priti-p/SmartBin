package com.smartbinapp.smartbin;

/**
 * Created by Priti_Parate on 9/24/2015.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

//import com.smartbinapp.smartbin.BinInfo;
import com.smartbinapp.smartbin.MyLinkedMap;
import com.smartbinapp.smartbin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FillLevelsFragment extends Fragment {

    public static com.smartbinapp.smartbin.MyLinkedMap<String, Double> fillLevelMap = null;

    //BinInfo allBinsInfo = BinInfo.getInstance();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Bins = "bins";
    private static final String TAG_ID = "Id";
    private static final String TAG_Fill = "Fill";
    private static final String TAG_Loc = "Location";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.filllevel, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }




    @Override
    public void onStart () {

        super.onStart();
        ((LaunchActivity) getActivity()).getSupportActionBar().setTitle(R.string.Fill_Levels_Fragment);

        LinearLayout llFillLevels = (LinearLayout) getActivity().findViewById(R.id.fillLevelsActivity);
        ListView stringListView = (ListView) llFillLevels.findViewById(R.id.fillLevel_list);
        List<Entry<String, Double>> list=null;

        try {

            com.smartbinapp.smartbin.MyLinkedMap<String, Integer> tempMap = new MyLinkedMap<String, Integer>();
            com.smartbinapp.smartbin.MyLinkedMap<String, Double> binValues = new MyLinkedMap<String, Double>();

            for (int i = 0; LaunchActivity.bins!=null && i < LaunchActivity.bins.length(); i++) {

                JSONObject c = LaunchActivity.bins.getJSONObject(i);
                Log.d("object " + i, c.toString());
                String loc = c.getString(TAG_Loc);
                if(tempMap.get(loc)== null){
                    tempMap.put(c.getString(TAG_Loc), 1);
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

            Set<Entry<String, Double>> set = binValues.entrySet();

            list = new ArrayList<Entry<String, Double>>(set);
            Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });

            fillLevelMap = new MyLinkedMap<String, Double>();
            for (Map.Entry<String, Double> entry : list)
            {
                fillLevelMap.put( entry.getKey(), entry.getValue() );
            }
            stringListView.setAdapter(new FillLevelCustomAdapter(getActivity(), fillLevelMap));
            stringListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {

                        //Log.d("Module Item Trigger", "Module item was triggered");
                        //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), BinDetailsActivity1.class);
                        intent.putExtra("BinId", LaunchActivity.bins.getJSONObject(position).getString(TAG_ID));
                        Log.d("BinID", LaunchActivity.bins.getJSONObject(position).getString(TAG_ID));
                        intent.putExtra("lTxt", fillLevelMap.getKey(position));
                        Log.d("ltxt", fillLevelMap.getKey(position));
                        intent.putExtra("rTxt", Double.toString(fillLevelMap.getValue(position)));
                        Log.d("rtxt", Double.toString(fillLevelMap.getValue(position)));
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        if (LaunchActivity.data_for_search) {
            LaunchActivity.buildDialogAsSearched(getActivity()).show();
        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.d("Module Item Trigger", "Module item was triggered");
//        //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(getActivity(), BinDetailsActivity.class);
//        startActivity(intent);
//    }
}
