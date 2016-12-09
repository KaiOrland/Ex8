package com.example.madaim.ex8;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.EditText;

/**
 * Created by Madaim on 09/12/2016.
 */

public class MyEditText extends EditText {


    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static class MyMenuInfo implements ContextMenu.ContextMenuInfo{
        public EditText ed;

        public MyMenuInfo(EditText ed) {
            this.ed = ed;
        }
    }

    @Override
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return new MyMenuInfo(this);
    }
}
