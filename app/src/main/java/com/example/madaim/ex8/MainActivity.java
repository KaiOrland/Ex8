package com.example.madaim.ex8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText ed1;
    EditText ed2;
    RadioGroup rGroup;
    double var1;
    double var2;

    public final String ACTION_CHECK = getPackageName() +".check";
    public final String ACTION_CALC = getPackageName() +".calc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.edFarenheit);
        ed2 = (EditText)findViewById(R.id.edCelsius);
        rGroup = (RadioGroup)findViewById(R.id.radioGroup);
        String StrVar1 = ed1.getText().toString();
        var1 = Double.parseDouble(StrVar1);
        String StrVar2 = ed2.getText().toString();
        var2 = Double.parseDouble(StrVar2);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                ed1.setEnabled(true);
                ed2.setEnabled(true);
                b1.setEnabled(true);
                switch(checkedId){
                    case R.id.checkRd:
                        break;
                    case R.id.calcRd:
                        if(!ed1.getText().toString().equals("")){
                            ed2.setEnabled(false);
                        }
                        if(!ed2.getText().toString().equals("")){
                            ed1.setEnabled(false);
                        }
                        break;
                }

            }
        });
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Result.class);
                if (rGroup.getCheckedRadioButtonId() == R.id.checkRd){
                    intent.putExtra("checkFar", var1 );
                    intent.putExtra("checkCel", var2 );
                    intent.setAction(ACTION_CHECK);
                    startActivity(intent);
                }
                else{

                }

            }
        });
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
