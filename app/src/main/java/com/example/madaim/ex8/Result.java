package com.example.madaim.ex8;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Result extends Activity {

    double farenheit;
    double celsius;
    TextView tv;
    String message;
    boolean isCelsius;
    Button btnReturn;
    int curentPrecision;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btnReturn = (Button) findViewById(R.id.button2);
        final Intent intent = getIntent();
        Bundle b1 = intent.getExtras();
        curentPrecision = intent.getIntExtra("precision", 0);
        if (b1.containsKey("checkFar")) {
            farenheit = intent.getDoubleExtra("checkFar", 0);
            isCelsius = false;
        }
        if (b1.containsKey("checkCel")) {
            celsius = intent.getDoubleExtra("checkCel", 0);
            isCelsius = true;
        }

        if (intent.getAction().equals(MainActivity.ACTION_CHECK)) {
            if (celsius == (farenheit - 32) * (5.0 / 9))
                message = getResources().getString(R.string.success, celsius, farenheit);
            else
                message = getResources().getString(R.string.fail);

        } else {
            if (isCelsius == true) {
                farenheit = celsius * (9.0 / 5) + 32;
                farenheit = round(farenheit, curentPrecision);
                message = getResources().getString(R.string.calculateFar, farenheit, celsius);
            } else {
                celsius = (farenheit - 32) * (5.0 / 9);
                celsius = round(celsius, curentPrecision);
                message = getResources().getString(R.string.calculateCel, celsius, farenheit);
            }

        }



        tv = (TextView) findViewById(R.id.message);
        tv.setText(message);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(Result.this, MainActivity.class);
                startActivity(intent2);
            }
        });

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void text(View v) {

        if ((farenheit != 0) && (celsius != 0)) {
            if (celsius == (farenheit - 32) * (5 / 9)) {
                String success = getResources().getString(R.string.success);
                // tv.setText(success, Double.toString();
            }
        }


    }


}
