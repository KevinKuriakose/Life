package com.example.kuria.life;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText uname,pswd;
    String username,password;
    String url;
    ProgressDialog progressDialog;
    public static String userid,first,last,email,blood,phno,aadhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=(EditText)findViewById(R.id.username);
        pswd=(EditText)findViewById(R.id.password);
        TextView signup=(TextView)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Authenticating");
                progressDialog.show();
                username = uname.getText().toString();
                password = pswd.getText().toString();
                url = "loginjson.php?username=" + username + "&password=" + password;
                url = url.replace(" ", "%20");
                new Loads().execute();
            }
        });

    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

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
                progressDialog.dismiss();
                Intent userHome = new Intent(getApplicationContext(), MainNavigation.class);
                startActivity(userHome);
            } else {
                progressDialog.dismiss();

                Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getData() {
        String ret = "na";
        JsonAct ja = new JsonAct();
        String result = ja.setJsonVal(url);
        try {
            JSONArray arr = new JSONArray(result);
            String s = arr.getString(0).trim();
            if (!s.equalsIgnoreCase("na")) {
                JSONObject ob = arr.getJSONObject(0);
                userid = ob.getString("user_id");
                email=ob.getString("email_id");
                first=ob.getString("firstname");
                last=ob.getString("lastname");
                blood=ob.getString("blood_grp");
                phno=ob.getString("phone_no");
                aadhar=ob.getString("aadhar_no");

                //Log.v("Result:", "***logined" );


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
