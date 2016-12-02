package com.example.madaim.ex8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    double farenheit;
    double celsius;
    TextView tv;
    String message;
    boolean isCelsius;
    Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btnReturn = (Button)findViewById(R.id.button2);
        final Intent intent = getIntent();
        Bundle b1 = intent.getExtras();
        if(b1.containsKey("checkFar")) {
            farenheit = intent.getDoubleExtra("checkFar", 0);
            isCelsius = false;
        }
        if(b1.containsKey("checkCel")) {
            celsius = intent.getDoubleExtra("checkCel", 0);
            isCelsius = true;
        }
        if(intent.getAction().equals(MainActivity.ACTION_CHECK)){
            if(celsius==(farenheit-32)*(5.0/9))
                message = getResources().getString(R.string.success, celsius, farenheit);
            else
                message = getResources().getString(R.string.fail);

        }
        else{
            if(isCelsius==true){
                farenheit = celsius*(9.0/5)+32;
                message = getResources().getString(R.string.calculateFar, farenheit, celsius);
            }
            else{
                celsius = (farenheit-32)*(5.0/9);
                message = getResources().getString(R.string.calculateCel, celsius, farenheit);
            }

        }


        tv = (TextView)findViewById(R.id.message);
        tv.setText(message);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(Result.this, MainActivity.class);
                startActivity(intent2);
            }
            });
    }



    public void text(View v){

        if((farenheit!=0)&&(celsius!=0)){
            if(celsius==(farenheit-32)*(5/9)){
                String success = getResources().getString(R.string.success);
               // tv.setText(success, Double.toString();
            }
        }



    }
}
