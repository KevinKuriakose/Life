package com.example.kuria.life;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class QuickMaps extends AppCompatActivity {

    WebView qk_currloc;
    public static TextView hsp,plc,bbank;
    public static String institution;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_maps);

        qk_currloc=(WebView)findViewById(R.id.curr_loc);
        hsp=(TextView)findViewById(R.id.n_hsp);
        plc=(TextView)findViewById(R.id.n_plc);
        //fire=(TextView)findViewById(R.id.n_fire);
        bbank=(TextView)findViewById(R.id.n_bbank);

        Log.v("Initial Loc:", "lat: "+ LocationService.lati+ "  longi:"+LocationService.logi);

        qk_currloc.getSettings().setJavaScriptEnabled(true);
        qk_currloc.setWebViewClient(new WebViewClient());

        //if(!LocationService.lati.equals("") && !LocationService.logi.equals(""))
        qk_currloc.loadUrl("http://maps.google.com/?q="+LocationService.lati+","+LocationService.logi);

        // else
        //qk_currloc.loadUrl("http://maps.google.com/");

        Toast.makeText(getApplicationContext(),LocationService.lati+", "+LocationService.logi, Toast.LENGTH_LONG).show();

        Log.v("Initial Loc:", "lat: "+ LocationService.lati+ "  longi:"+LocationService.logi);
        hsp.setText("Mar Baselious Medical Mission Hospital");
        hsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                institution="Mar Baselious Medical Mission Hospital";
                Log.v("plc onclick:", "!!!!START RANGE CHECK!!!!");
                Intent i= new Intent(getApplicationContext(),RangeCheckService.class);
                startService(i);

                // String url="http://google.com/maps?saddr="+LocationService.lati+","+LocationService.logi+"&&daddr="+"10.052303"
                //         +","+"76.618868";
                if(RangeCheckService.inst_long!=null) {
                    String url = "http://google.com/maps?saddr=" + LocationService.lati + "," + LocationService.logi + "&&daddr=" +"Mar Baselious Medical Mission Hospital";
                    Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
                    Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(j);
                }
            }
        });

        plc.setText("Kothamangalam Police Station");
        plc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                institution=plc.getText().toString();
                Log.v("plc onclick:", "!!!!START RANGE CHECK!!!!");
                Intent i= new Intent(getApplicationContext(),RangeCheckService.class);
                startService(i);

                // String url="http://google.com/maps?saddr="+LocationService.lati+","+LocationService.logi+"&&daddr="+"10.052303"
                //         +","+"76.618868";
                if(RangeCheckService.inst_long!=null) {
                    String url = "http://google.com/maps?saddr=" + LocationService.lati + "," + LocationService.logi + "&&daddr=" +"Kothamangalam Police Station";
                    Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
                    Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(j);
                }
            }
        });

        bbank.setText("Blood Bank Kothamangalam");
        bbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                institution=bbank.getText().toString();
                Log.v("bbank onclick:", "!!!!START RANGE CHECK!!!!");
                Intent i= new Intent(getApplicationContext(),RangeCheckService.class);
                startService(i);

                // String url="http://google.com/maps?saddr="+LocationService.lati+","+LocationService.logi+"&&daddr="+"10.052303"
                //         +","+"76.618868";
                if(RangeCheckService.inst_long!=null) {
                    String url = "http://google.com/maps?saddr=" + LocationService.lati + "," + LocationService.logi + "&&daddr=" + "Blood Bank.Aluva - Munnar Road";
                    Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
                    Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(j);
                }
            }
        });




    }
}
