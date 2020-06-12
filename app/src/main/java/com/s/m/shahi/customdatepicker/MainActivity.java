package com.s.m.shahi.customdatepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.s.m.shahi.custombottomsheetdatepicker.DatePickerListener;
import com.s.m.shahi.custombottomsheetdatepicker.DatePicker;

public class MainActivity extends AppCompatActivity implements DatePickerListener {

    private DatePicker datePicker;

    private TextView dateView;
    private Button showDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = new DatePicker();

        dateView = findViewById(R.id.dateView);
        showDate = findViewById(R.id.showDate);

        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), datePicker.getTag());
            }
        });
    }

    @Override
    public void onPickupDate(String value) {
        dateView.setText(value);
    }

    @Override
    public void onDay(String day) {

    }

    @Override
    public void onMonth(String month) {

    }

    @Override
    public void onYear(String onYear) {

    }
}