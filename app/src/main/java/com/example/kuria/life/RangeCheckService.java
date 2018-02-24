package com.example.kuria.life;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
//import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import java.net.URI;
//import java.net.URISyntaxException;

//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

/**
 * Created by darsh on 27-01-2018.
 */

public class RangeCheckService extends Service {

    int datav=2;
    Handler hd=new Handler();
    String b,lati,logi,loc_check_url;
    int in_check,out_check;

    static String inst_lat,inst_long;

    public RangeCheckService() { }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        // Log.d(TAG, "FirstService started");
        hd.post(r);
//        Toast.makeText(getApplicationContext(), "inside service", Toast.LENGTH_LONG).show();
        this.stopSelf();
    }

    Runnable r=new Runnable() {
        @Override
        public void run() {
            check_value();
//        	Toast.makeText(getApplicationContext(), "hiiiii", Toast.LENGTH_LONG).show();
            hd.postDelayed(r, 5000);
        }
//	        if(ad_flag==1){
//	        	hd1.post(r_adds);
    };

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //  Log.d(TAG, "FirstService destroyed");
    }

    public void check_value() {

        //url
        lati = LocationService.lati;
        logi = LocationService.logi;
        String inst=QuickMaps.institution;
        // Toast.makeText(getApplicationContext(),"NOT NULL!!"+lati+"-"+logi,Toast.LENGTH_LONG).show();

        //startService(new Intent(getApplicationContext(),RangeCheckService.class));
        loc_check_url = "loc_check.php?lat=" + lati + "&long=" +logi+ "&inst=" +inst+"&user"+LoginActivity.userid;
        loc_check_url = loc_check_url.replace(" ", "%20");
        new Loads().execute();
//  	Toast.makeText(getApplicationContext(), "hi. inside check value",Toast.LENGTH_SHORT).show();
    }

    private class Loads extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String res = getData();
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equalsIgnoreCase("ok")) {
                // Bluetooth
                out_check=0;
                in_check++;
                if(in_check>1) {
                    Log.v("action:", "Trigger activated");
                    Toast.makeText(getApplicationContext(), "Trigger activated!!!", Toast.LENGTH_SHORT).show();
//try
//{
//                    final OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url("http://lifemace.xyz/traffic.php?i=1")
//                            .get()
//                            .addHeader("Content-Type", "application/json")
//                            .addHeader("Authorization", "Your Token")
//                            .addHeader("cache-control", "no-cache")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String serverResponse = response.body().string();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
                }

            } else {
                in_check=0;
                out_check++;
                if(out_check>1) {
                    Log.v("action:","Back to Normal Lighting");
                    Toast.makeText(getApplicationContext(), "OUT..BACK NORMAL..!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private String getData() {
        String ret = "na";
        JsonAct ja2 = new JsonAct();
        String result2 = ja2.setJsonVal(loc_check_url);

        try {
            JSONArray array2 = new JSONArray(result2);
            JSONObject ob2=array2.getJSONObject(0);
            String reslt= (String) ob2.get("result");
            inst_lat= (String) ob2.get("latitude");
            inst_long= (String) ob2.get("longitude");
            Log.v("Result:", "***result:" + reslt+inst_lat+inst_long);


            if (!reslt.equalsIgnoreCase("na")) {
                //JSONObject ob = arr.getJSONObject(0);
                ret = "ok";
            } else {
                ret = "na";
            }
        } catch (JSONException e) {
            ret = e.toString();

        }
        return ret;
    }


}
