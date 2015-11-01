package com.smartbinapp.smartbin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PieChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class AnalysisGraph extends AppCompatActivity {

    private final static String TAG = "WeekFregmant";
    private GraphicalView mChart;
    private XYSeries bin1Series ;
    private XYSeries bin2Series ;
    private XYMultipleSeriesDataset dataset;
    private XYSeriesRenderer bin1Renderer;
    private XYSeriesRenderer bin2Renderer;
    private XYMultipleSeriesRenderer multiRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_graph);
        int z[]={0,1,2,3,4,5,6};
        int x[]={2,4,8,10,12,14,10};
        int w[]={0,1,2,3,4,5,6};
        int y[]={5,4,8,10,12,14,10};
        double range[] = {0.0,0.1,10.0};

        Log.d("grapg analysis", "Inside graph analysis");

        // Creating an  XYSeries for Temperature
        bin1Series = new XYSeries("Bin1 ");


        // Creating an  XYSeries for Humidity
        bin2Series = new XYSeries("Bin2");

        //  Adding data to the X Series.
        for(int i=0;i<z.length;i++)
        {
            bin1Series.add(z[i],x[i]);

        }

        //  Adding data to the X Series.
        for(int i=0;i<w.length;i++)
        {
            bin2Series.add(w[i],y[i]);

        }
        // Creating a dataset to hold each series
        dataset = new XYMultipleSeriesDataset();

        // Adding Visits Series to the dataset
        dataset.addSeries(bin1Series);
        dataset.addSeries(bin2Series);

        // Creating XYSeriesRenderer to customize incomeSeries
        bin1Renderer = new XYSeriesRenderer();
        bin1Renderer.setColor(Color.WHITE);
        bin1Renderer.setPointStyle(PointStyle.CIRCLE);
        bin1Renderer.setFillPoints(true);
        bin1Renderer.setLineWidth(2);
        bin1Renderer.setDisplayChartValues(true);
        bin1Renderer.setFillBelowLine(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        bin2Renderer = new XYSeriesRenderer();
        bin2Renderer.setColor(Color.YELLOW);
        bin2Renderer.setPointStyle(PointStyle.CIRCLE);
        bin2Renderer.setFillPoints(true);
        bin2Renderer.setLineWidth(2);
        bin2Renderer.setDisplayChartValues(true);
        bin2Renderer.setFillBelowLine(true);

//        // Creating XYSeriesRenderer to customize visitsSeries
//        bin1Renderer = new XYSeriesRenderer();
//        bin1Renderer.setColor(Color.BLUE);
//        bin1Renderer.setPointStyle(PointStyle.CIRCLE);
//        bin1Renderer.setFillPoints(true);
//        bin1Renderer.setLineWidth(6);
//        bin1Renderer.setDisplayChartValues(true);
//        bin1Renderer.setChartValuesTextAlign(Paint.Align.CENTER);

//        // Creating XYSeriesRenderer to customize visitsSeries
//        bin2Renderer = new XYSeriesRenderer();
//        bin2Renderer.setColor(Color.GREEN);
//        bin2Renderer.setPointStyle(PointStyle.CIRCLE);
//        bin2Renderer.setFillPoints(true);
//        bin2Renderer.setLineWidth(6);
//        bin2Renderer.setDisplayChartValues(true);
        // humidityRenderer.setChartValuesTextAlign(Paint.Align.RIGHT);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        multiRenderer = new XYMultipleSeriesRenderer();

        multiRenderer.setChartTitle("Bin Temperature and Humidity Reading Chart");
        multiRenderer.setXTitle("days");
        multiRenderer.setYTitle("Reading");
        multiRenderer.setChartTitle("FillTrend");
        multiRenderer.setZoomButtonsVisible(true);
        multiRenderer.setXAxisMin(0);
        multiRenderer.setXAxisMax(7);
        multiRenderer.setZoomEnabled(false);
        multiRenderer.setYAxisMin(0);
        multiRenderer.setBackgroundColor(0X0000);
        //multiRenderer.setYAxisMax(5);
        //multiRenderer.setInitialRange(range,2);
//        multiRenderer.setBarSpacing(4);

        // Adding visitsRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(bin1Renderer);
        multiRenderer.addSeriesRenderer(bin2Renderer);

        // Getting a reference to LinearLayout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.analysisgraph_activity);

        // remove any views before u paint the chart
        chartContainer.removeAllViews();
        // drawing bar chart
        mChart = ChartFactory.getLineChartView(AnalysisGraph.this, dataset, multiRenderer);
        // adding the view to the linearlayout
        chartContainer.addView(mChart);

//        //Log.v(TAG, "shilpa in mainactivity actionbar custom activity");
//        // Specifying chart types to be drawn in the graph
//        // Number of data series and number of types should be same
//        // Order of data series and chart type will be same
//        String[] types = new String[] { PieChart.class, PieChart.class };
//
//        Log.v(TAG, "shilpa in mainactivity actionbar custom activity3");
//
//        try {
//            mChart = (GraphicalView) ChartFactory.getCombinedXYChartView(getActivity().getBaseContext(), dataset, multiRenderer, types);
//        }
//        catch(Exception e){
//
//            Log.v(TAG,"hehehe "+ e.toString());
//        }
//
//        Log.v(TAG, "shilpa in mainactivity actionbar custom activity1");
//        // Adding the Line Chart to the LinearLayout
//        chartContainer.addView(mChart);



        Log.v(TAG, "hehehe in mainactivity actionbar week activity");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_analysis_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_export){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
