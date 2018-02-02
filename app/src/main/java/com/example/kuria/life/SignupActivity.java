package com.example.kuria.life;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    EditText date;
    DatePickerDialog datePickerDialog;
    EditText f_name,l_name,e_mail,aadhar,mob_no,password,c_password,blood_grp;
    Button signup;
    String fn,ln,eml,db,auid,mob,pswd,c_pswd,bg;
    String url;
    ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        f_name=(EditText)findViewById(R.id.f_name);
        l_name=(EditText)findViewById(R.id.l_name);
        e_mail=(EditText)findViewById(R.id.e_mail);
        aadhar=(EditText)findViewById(R.id.aadhar);
        mob_no=(EditText)findViewById(R.id.mob_no);
        password=(EditText)findViewById(R.id.password);
        c_password=(EditText)findViewById(R.id.c_password);
        blood_grp=(EditText) findViewById(R.id.blood_grp);

        signup=(Button)findViewById(R.id.signup);
        // initiate the date picker and a button
        date = (EditText) findViewById(R.id.dob);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SignupActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        TextView login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(SignupActivity.this);
                progressDialog.setMessage("Registering User...");
                progressDialog.show();
                // Retrieving data from fields
                fn=f_name.getText().toString();
                ln=l_name.getText().toString();
                eml=e_mail.getText().toString();
                db=date.getText().toString();
                auid=aadhar.getText().toString();
                mob=mob_no.getText().toString();
                pswd=password.getText().toString();
                c_pswd=c_password.getText().toString();
                bg=blood_grp.getText().toString();
                // validation
                if(fn.equalsIgnoreCase(""))
                {

                    f_name.setError("Missing First Name");
                    f_name.setFocusable(true);

                }
                else if (ln.equalsIgnoreCase(""))
                {
                    l_name.setError("Missing Last Name");
                    l_name.setFocusable(true);

                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(eml).matches())
                {
                    e_mail.setError("Invalid E-mail");
                    e_mail.setFocusable(true);
                }

                //Date check is required to be run
                else if(auid.equalsIgnoreCase(""))
                {
                    aadhar.setError("Invalid Aadhar");
                    aadhar.setFocusable(true);

                }
                else if(!Patterns.PHONE.matcher(mob).matches()||(mob).length()!=10)
                {
                    mob_no.setError("Invalid Phone Number");
                    mob_no.setFocusable(true);


                }
                else if(pswd.equalsIgnoreCase(""))
                {
                    password.setError("Invalid Password");
                    password.setFocusable(true);
                }
                else if(!c_pswd.equalsIgnoreCase(pswd))
                {
                    c_password.setError("Invalid Password");
                    c_password.setFocusable(true);

                }
                else
                {
                    url = "Registerjson.php?fname=" + fn + "&lname=" + ln + "&email=" + eml + "&dob=" + db + "&aadhar=" + auid +"&mob=" + mob + "&bloodgrp=" + bg + "&password=" + pswd;
                    url = url.replace(" ", "%20");
                    new Loads1().execute();

                }
            }


        });
    }
    private class Loads1 extends AsyncTask<String, Void, String> {
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
                Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_LONG).show();
                Intent userHome = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(userHome);
            } else {
                // error in json act
                progressDialog.dismiss();

                Toast.makeText(getApplicationContext(),"Error! Please try again",Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getData() {
        String ret = "na";
        JsonAct ja = new JsonAct();
        String s = ja.setJsonVal(url);
        try {
            Log.v("Exception", "********" + s); // Doubt?
            if (!s.equalsIgnoreCase("na")) {
                ret="ok";
            } else {
                ret = "na";
            }
        } catch (Exception e) {
            ret = e.toString();
        }
        return ret;
    }



}
