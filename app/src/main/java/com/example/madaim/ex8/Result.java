package com.example.madaim.ex8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    double farenheit;
    double celsius;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        Bundle b1 = intent.getExtras();
        if(b1.containsKey("checkFar"))
            farenheit = intent.getDoubleExtra("checkFar",0);
        if(b1.containsKey("checkCel"))
            celsius = intent.getDoubleExtra("checkCel", 0);

        tv = (TextView)findViewById(R.id.message);
    }



    public void text(View v){

        if((farenheit!=0)&&(celsius!=0)){
            if(celsius==(farenheit-32)*(5/9)){
                String success = getResources().getString(R.string.success);
               // tv.setText(success, Double.toString();
            }
        }


       //
    }
}
