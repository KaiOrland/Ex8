package com.example.madaim.ex8;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Madaim on 16/12/2016.
 */

public class MyDialog  extends DialogFragment {

    private int requestCode;
    public final static int EXIT_DIALOG = 1;
    public final static int PRECISION_DIALOG = 2;
    private ResultsListener listener;

    public static MyDialog newInstance(int requestCode) {
        Bundle args = new Bundle();
        MyDialog fragment = new MyDialog();
        args.putInt("rc", requestCode);
        fragment.setArguments(args);
        return fragment;
    }

    private AlertDialog.Builder buildExitDialog(){
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.exitTitle)
                .setMessage(R.string.sure)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onFinishedDialog(requestCode, "ok");

                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
    }
    private AlertDialog.Builder buildPrecisionDialog(){
        View view = getActivity().getLayoutInflater().inflate(R.layout.precision, null);
        final SeekBar sk;
        final TextView t1;
        final int curentPrecision = ((MainActivity)getActivity()).getCurrentPrecision();
        t1 = (TextView)view.findViewById(R.id.amount);

        String str = "%."+curentPrecision+"f";
        t1.setText(String.format(str, 123.0));

        sk = (SeekBar)view.findViewById(R.id.seekBar2);
        sk.setProgress(curentPrecision);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String str = "%."+progress+"f";
                t1.setText(String.format(str, 123.0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return new AlertDialog.Builder(getActivity())
                .setTitle("set the numbers precision")
                .setView(view)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onFinishedDialog(requestCode, sk.getProgress());
                        ;

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        this.requestCode = getArguments().getInt("rc");
        if(requestCode == EXIT_DIALOG){
            return buildExitDialog().create();
        }
        else
            return buildPrecisionDialog().create();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            this.listener = (ResultsListener)activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException("Hosting activity must implement the interface ResultsListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface ResultsListener{
        public void onFinishedDialog(int requestCode, Object results);
    }
}
