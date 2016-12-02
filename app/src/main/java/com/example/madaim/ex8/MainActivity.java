package com.example.madaim.ex8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    Button b1;
    EditText ed1;
    EditText ed2;
    RadioGroup rGroup;
    double var1;
    double var2;
    boolean radioCheck;

    public final static String ACTION_CHECK = "com.example.madaim.ex8.check";
    public final static String ACTION_CALC = "com.example.madaim.ex8.calc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.edFarenheit);
        ed2 = (EditText) findViewById(R.id.edCelsius);
        rGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ed1.addTextChangedListener(this);
        ed2.addTextChangedListener(this);

            rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    ed1.setEnabled(true);
                    ed2.setEnabled(true);

                    switch (checkedId) {
                        case R.id.checkRd:
                            radioCheck = true;
                            break;
                        case R.id.calcRd:
                            radioCheck = false;

                            break;
                    }

                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String StrVar1 = ed1.getText().toString();
                    if (StrVar1 != null && !StrVar1.equals("")) {
                        var1 = Double.parseDouble(StrVar1);
                    }
                    String StrVar2 = ed2.getText().toString();
                    if (StrVar2 != null && !StrVar2.equals(""))
                        var2 = Double.parseDouble(StrVar2);

                    Intent intent = new Intent(MainActivity.this, Result.class);
                    if (rGroup.getCheckedRadioButtonId() == R.id.checkRd) {
                        intent.putExtra("checkFar", var1);
                        intent.putExtra("checkCel", var2);
                        intent.setAction(ACTION_CHECK);
                        startActivity(intent);
                    } else {

                        if (!ed1.getText().toString().equals("")) {
                            intent.putExtra("checkFar", var1);
                            intent.setAction(ACTION_CALC);
                            startActivity(intent);
                        } else {
                            intent.putExtra("checkCel", var2);
                            intent.setAction(ACTION_CALC);
                            startActivity(intent);
                        }
                    }

                }
            });
        }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(ed1.getText().toString().equals("")&&ed2.getText().toString().equals(""))
            b1.setEnabled(false);
        else {
            b1.setEnabled(true);
            if (radioCheck == false)
            {
                    if (!ed1.getText().toString().equals("")) {
                        ed2.setEnabled(false);
                    }
                    if (!ed2.getText().toString().equals("")) {
                        ed1.setEnabled(false);
                    }
                }
            else {
                if(!ed1.getText().toString().equals("")&&!ed2.getText().toString().equals(""))
                    b1.setEnabled(true);
                else
                    b1.setEnabled(false);
            }
            }
    }


   /*public void action(View v){
        String StrVar1 = ed1.getText().toString();
        var1 = Double.parseDouble(StrVar1);
        String StrVar2 = ed2.getText().toString();
        var2 = Double.parseDouble(StrVar2);

        switch(v.getId()) {
            case R.id.checkRd:
                b1.setEnabled(true);
                if (R.id.button) {
                    Intent intent = new Intent(this, Result.class);
                    intent.putExtra("checkVar", );
                    startActivityForResult(intent, );
                }
                break;
            case R.id.calcRd:
                if (R.id.button) {
                    b1.setEnabled(true);
                    Intent intent = new Intent(this, Result.class);
                    startActivityForResult(intent, );
                }
                break;
        }
        }
        */
    }
