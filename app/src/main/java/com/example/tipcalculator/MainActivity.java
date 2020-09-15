/*
@author Amber Sibel
@date 5/25/20
Homework01
 */
package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText value;
    TextView tip;
    TextView total;
    double tipAmount;
    double totalAmount;
    double seekValue;
    double valueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = (EditText)findViewById(R.id.billValue);
        tip = (TextView)findViewById(R.id.tipTotal);
        total = (TextView)findViewById(R.id.total_with_tip);

        findViewById(R.id.tip10).setOnClickListener(this);
        findViewById(R.id.tip15).setOnClickListener(this);
        findViewById(R.id.tip18).setOnClickListener(this);
        findViewById(R.id.exitButton).setOnClickListener(this);

        SeekBar seek = (SeekBar)findViewById(R.id.seekBar);
        final TextView seekBarValue = (TextView)findViewById(R.id.seekbarText);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(String.valueOf(progress + "%"));
                setSeekValue(progress * 0.01);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                try {
                    if(Double.parseDouble(value.getText().toString()) == 0){
                        value.setError("Enter Bill Total");
                    }
                    tipAmount = Double.parseDouble(value.getText().toString()) * seekValue;
                    tip.setText(String.valueOf(tipAmount));
                    totalAmount = Double.parseDouble(value.getText().toString()) + tipAmount;
                    total.setText(String.valueOf(totalAmount));
                } catch(Exception e) {
                    value.setError("Enter Bill Total");
                }
            }
        });
    }
    public void setSeekValue(double progress) {
        seekValue = progress;
    }
    @Override
    public void onClick(View v) {
        SeekBar seek = (SeekBar)findViewById(R.id.seekBar);

        try {
            if(Double.parseDouble(value.getText().toString()) == 0){
                value.setError("Enter Bill Total");
            }
            switch (v.getId()) {
                case R.id.exitButton:
                    finish();
                    break;
                case R.id.tip10:
                    tipAmount = Double.parseDouble(value.getText().toString()) * 0.10;
                    tip.setText(String.valueOf(tipAmount));
                    totalAmount = Double.parseDouble(value.getText().toString()) + tipAmount;
                    total.setText(String.valueOf(totalAmount));
                    seek.setProgress(10);
                    break;
                case R.id.tip15:
                    tipAmount = Double.parseDouble(value.getText().toString()) * 0.15;
                    tip.setText(String.valueOf(tipAmount));
                    totalAmount = Double.parseDouble(value.getText().toString()) + tipAmount;
                    total.setText(String.valueOf(totalAmount));
                    seek.setProgress(15);
                    break;
                case R.id.tip18:
                    tipAmount = Double.parseDouble(value.getText().toString()) * 0.18;
                    tip.setText(String.valueOf(tipAmount));
                    totalAmount = Double.parseDouble(value.getText().toString()) + tipAmount;
                    total.setText(String.valueOf(totalAmount));
                    seek.setProgress(18);
                    break;
            }
        } catch(Exception e) {
            if (v.getId() == R.id.exitButton) {
                finish();
            } else {
                value.setError("Enter Bill Total");
            }
        }
    }
}
/*
I changed the progress bar to match the percentages because I thought it looked funny if you
clicked on 15% and the custom bar still said 41%
 */