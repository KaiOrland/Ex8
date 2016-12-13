package com.example.madaim.ex8;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements TextWatcher {

    Button b1;
    EditText ed1;
    EditText ed2;
    RadioGroup rGroup;
    double var1;
    double var2;
    boolean radioCheck;
    static int menuId;

    public final static String ACTION_CHECK = "com.example.madaim.ex8.check";
    public final static String ACTION_CALC = "com.example.madaim.ex8.calc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.edFarenheit);
        ed2 = (EditText) findViewById(R.id.edCelsius);
        if(savedInstanceState != null) {
            int farColor = savedInstanceState.getInt("farClr");
            int celColor = savedInstanceState.getInt("celClr");
            ed1.setTextColor(farColor);
            ed1.setTextColor(celColor);
        }
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

        registerForContextMenu(ed1);
        registerForContextMenu(ed2);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
        int color = ((EditText)v).getCurrentTextColor();
        menuId = color==Color.BLUE?R.id.colorBlue:color==Color.GREEN?R.id.colorGreen:R.id.colorRed;
        menu.findItem(menuId).setChecked(true);
        int[] colors = new int[]{Color.RED, Color.rgb(0,153,0), Color.BLUE};
        for (int i = 0; i<colors.length;i++)
        {
            MenuItem item = menu.getItem(i);
            SpannableString s  = new SpannableString(item.getTitle());
            s.setSpan(new ForegroundColorSpan(colors[i]), 0,s.length(),0);
            item.setTitle(s);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        int farColor=ed1.getCurrentTextColor();
        int celColor=ed2.getCurrentTextColor();
        savedInstanceState.putInt("celClr", celColor);
        savedInstanceState.putInt("farClr", farColor);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_help:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://en.wikipedia.org/wiki/Conversion_of_units_of_temperature"));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        MyEditText.MyMenuInfo menuInfo = (MyEditText.MyMenuInfo) item.getMenuInfo();
        EditText ed = menuInfo.ed;
        switch(item.getItemId()){
            case R.id.colorRed:
                ed.setTextColor(Color.RED);
                break;
            case R.id.colorGreen:
                ed.setTextColor(Color.GREEN);
                break;
            case R.id.colorBlue:
                ed.setTextColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }

}
