package com.smartbinapp.smartbin;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.ActionBar.LayoutParams;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

//import com.couchbase.lite.Database;
//import com.couchbase.lite.LiveQuery;
//import com.couchbase.lite.Manager;
//import com.couchbase.lite.util.*;


public class LaunchActivity extends AppCompatActivity {
    public static final String SYNC_URL = "http://127.0.0.1:5984/";
    public static String TAG = "BinApp";

    private static final String TAB_FillLevels_TAG = "tab_FillLevels";
    private static final String TAB_Map_TAG = "tab_Map";
    private static final String TAB_Analaysis_TAG = "tab_Analysis";
    private FragmentTabHost mTabHost;

//    //    couch internals
//    protected static Manager manager;
//    private Database database;
//    private LiveQuery liveQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        //all child activity should have this arrow
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        //button will be displayed in this layout
//        final LinearLayout llFillLevels = (LinearLayout) findViewById(R.id.fillLevelsActivity);
//
//        //Create four
//        for(int j=0;j<=10;j++) {
////            // Create LinearLayout
////            LinearLayout llLayout = new LinearLayout(this);
////            llLayout.setOrientation(LinearLayout.VERTICAL);
//
//            // Create Button
//            final Button btn = new Button(this);
//
//            // Give button an ID
//            btn.setId(j+1);
//            btn.setText("Bin " + j + 1);
//            // set the layoutParams on the button
//            btn.setLayoutParams(llFillLevels.getLayoutParams());
////            btn.setMinimumWidth(launch.getMeasuredWidth());
//
//            final int index = j;
//            // Set click listener for button
//            btn.setOnClickListener(new OnClickListener() {
//                public void onClick(View v) {
//
//                    android.util.Log.i("TAG", "index :" + index);
//
//                    Toast.makeText(getApplicationContext(), "Clicked Button Index :" + index, Toast.LENGTH_LONG).show();
//
//                }
//            });
//
//            //Add button to LinearLayout
//            //Add button to LinearLayout
//            llFillLevels.addView(btn);
//            //Add button to LinearLayout defined in XML
////            launch.addView(llLayout);
//        }


//        //to start couch db
//        try {
//            startCBLite();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Error Initializing CBLIte, see logs for details", Toast.LENGTH_LONG).show();
//            com.couchbase.lite.util.Log.e(TAG, "Error initializing CBLite", e);
//        }

        // addlistener for image button
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_fill_levels));
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_map));
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_analysis));

        /// add tab view
        InitView();
    }

    private void InitView() {
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_FillLevels_TAG),
                R.drawable.filllevel_tab_indicator), FillLevelContainer.class, null);
        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_Map_TAG),
                R.drawable.map_tab_indicator), MapContainer.class, null);
        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_Analaysis_TAG),
                R.drawable.analysis_tab_indicator), AnalysisContainer.class, null);


    }

    private TabHost.TabSpec setIndicator(Context ctx, TabHost.TabSpec spec, int resid) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.tab_item, null);
        ImageView img = (ImageView)v.findViewById(R.id.img_tab);
        img.setBackgroundResource(resid);
        return spec.setIndicator(v);
    }

    @Override
    public void onBackPressed() {
        boolean isPopFragment=false;
        String currentTabTag = mTabHost.getCurrentTabTag();

        if(currentTabTag.equals(TAB_FillLevels_TAG)){
            isPopFragment=((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_FillLevels_TAG)).popFragment();
        }
        else if(currentTabTag.equals(TAB_Map_TAG)){
            isPopFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_Map_TAG)).popFragment();

        }
        else if (currentTabTag.equals(TAB_Analaysis_TAG)) {
        isPopFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_Analaysis_TAG)).popFragment();
        }

        if (!isPopFragment) {
            finish();
        }

    }



//    public void addListenerOnImageButton(ImageButton imageButton) {
//
//        imageButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getApplicationContext(), "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
//                v.findViewById(R.id.btn_analysis).setBackgroundResource(R.drawable.button_analysis_white);
//
//            }
//        });
//
//
//
//        imageButton.bringToFront();
//        imageButton.setFocusable(true);
//        imageButton.setFocusableInTouchMode(true);
//        return;
//    }

//    protected void startCBLite() throws Exception {
//
//        Manager.enableLogging(TAG, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_SYNC_ASYNC_TASK, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_SYNC, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_QUERY, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_VIEW, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_DATABASE, com.couchbase.lite.util.Log.VERBOSE);
//
//        manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
//
//        //install a view definition needed by the application
//        database = manager.getDatabase(DATABASE_NAME);
//        com.couchbase.lite.View viewItemsByDate = database.getView(String.format("%s/%s", designDocName, byDateViewName));
//        viewItemsByDate.setMap(new Mapper() {
//            @Override
//            public void map(Map<String, Object> document, Emitter emitter) {
//                Object createdAt = document.get("created_at");
//                if (createdAt != null) {
//                    emitter.emit(createdAt.toString(), null);
//                }
//            }
//        }, "1.0");
//
//        initItemListAdapter();
//
//        startLiveQuery(viewItemsByDate);
//
//        startSync();
//
//    }
//
//    private void startSync() {
//
//        URL syncUrl;
//        try {
//            syncUrl = new URL(SYNC_URL);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//
//        Replication pullReplication = database.createPullReplication(syncUrl);
//        pullReplication.setContinuous(true);
//
//        Replication pushReplication = database.createPushReplication(syncUrl);
//        pushReplication.setContinuous(true);
//
//        pullReplication.start();
//        pushReplication.start();
//
//        pullReplication.addChangeListener(this);
//        pushReplication.addChangeListener(this);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_refresh:
                doRefresh();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void doRefresh(){

    }

    private void openSettings(){

    }
}
