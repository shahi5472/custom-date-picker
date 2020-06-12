package com.s.m.shahi.custombottomsheetdatepicker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class DatePicker extends BottomSheetDialogFragment implements View.OnClickListener {
    private View view;
    private TextView showDateTv, showMonthTv, showYearTv;
    private NumberPicker dateTv, monthTv, yearTv;
    private String dayValue, monthValue, yearValue;
    private DatePickerListener bottomSheetCallBack;
    private boolean isLeapYear = false;

    private String cancelButtonText;
    private int cancelButtonTextColor;
    private boolean cancelButtonTextAllCaps = true;
    private Drawable cancelButtonBackground;

    private String confirmButtonText;
    private int confirmButtonTextColor;
    private boolean confirmButtonTextAllCaps = true;
    private Drawable confirmButtonBackground;

    public DatePicker() {
    }

    public void setDatePickerCancelButton(String cancelButtonText, int cancelButtonTextColor, boolean cancelButtonTextAllCaps, Drawable cancelButtonBackground) {
        this.cancelButtonText = cancelButtonText;
        this.cancelButtonTextColor = cancelButtonTextColor;
        this.cancelButtonTextAllCaps = cancelButtonTextAllCaps;
        this.cancelButtonBackground = cancelButtonBackground;
    }

    public void setDatePickerConfirmButton(String confirmButtonText, int confirmButtonTextColor, boolean confirmButtonTextAllCaps, Drawable confirmButtonBackground) {
        this.confirmButtonText = confirmButtonText;
        this.confirmButtonTextColor = confirmButtonTextColor;
        this.confirmButtonTextAllCaps = confirmButtonTextAllCaps;
        this.confirmButtonBackground = confirmButtonBackground;
    }

    public void setCancelButtonText(String cancelButtonText) {
        this.cancelButtonText = cancelButtonText;
    }

    public void setCancelButtonTextColor(int cancelButtonTextColor) {
        this.cancelButtonTextColor = cancelButtonTextColor;
    }

    public void setCancelButtonTextAllCaps(boolean cancelButtonTextAllCaps) {
        this.cancelButtonTextAllCaps = cancelButtonTextAllCaps;
    }

    public void setCancelButtonBackground(Drawable cancelButtonBackground) {
        this.cancelButtonBackground = cancelButtonBackground;
    }

    public void setConfirmButtonText(String confirmButtonText) {
        this.confirmButtonText = confirmButtonText;
    }

    public void setConfirmButtonTextColor(int confirmButtonTextColor) {
        this.confirmButtonTextColor = confirmButtonTextColor;
    }

    public void setConfirmButtonTextAllCaps(boolean confirmButtonTextAllCaps) {
        this.confirmButtonTextAllCaps = confirmButtonTextAllCaps;
    }

    public void setConfirmButtonBackground(Drawable confirmButtonBackground) {
        this.confirmButtonBackground = confirmButtonBackground;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bottomSheetCallBack = (DatePickerListener) getContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.custom_date_picker_view, container, false);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        final String[] months = new String[]{
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };

        dateTv = view.findViewById(R.id.dateTv);
        monthTv = view.findViewById(R.id.monthTv);
        yearTv = view.findViewById(R.id.yearTv);

        showDateTv = view.findViewById(R.id.showDateTv);
        showMonthTv = view.findViewById(R.id.showMonthTv);
        showYearTv = view.findViewById(R.id.showYearTv);

        dateTv.setMinValue(1);
        dateTv.setMaxValue(31);
        dateTv.setWrapSelectorWheel(true);

        monthTv.setDisplayedValues(months);
        monthTv.setMinValue(0);
        monthTv.setMaxValue(11);
        monthTv.setWrapSelectorWheel(true);

        yearTv.setMinValue(1750);
        yearTv.setMaxValue(year);
        yearTv.setWrapSelectorWheel(true);

        dateTv.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                dayValue = String.valueOf("" + newVal);
                showDateTv.setText("" + newVal);
            }
        });

        monthTv.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setLastDay(newVal);
                if (yearValue != null) leapYearCheck(Integer.parseInt(yearValue));
                monthValue = String.valueOf("" + months[newVal] + ",");
                showMonthTv.setText("" + months[newVal] + ",");
            }
        });

        yearTv.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                leapYearCheck(newVal);
                yearValue = String.valueOf("" + newVal);
                showYearTv.setText("" + newVal);
            }
        });

        Button cancelBtn = view.findViewById(R.id.cancelBtn);
        Button confirmBtn = view.findViewById(R.id.confirmBtn);

        setCancelButtonDesign(cancelBtn);
        setConfirmButtonDesign(confirmBtn);

        confirmBtn.setOnClickListener(this);

        cancelBtn.setOnClickListener(this);

        return view;
    }

    private void setConfirmButtonDesign(Button confirmBtn) {
        //For Cancel Button
        confirmBtn.setAllCaps(this.confirmButtonTextAllCaps);

        if (confirmButtonText != null) {
            confirmBtn.setText(confirmButtonText);
        }

        if (confirmButtonTextColor != 0) {
            confirmBtn.setTextColor(confirmButtonTextColor);
        }

        if (confirmButtonBackground != null) {
            confirmBtn.setBackground(confirmButtonBackground);
        }
    }

    private void setCancelButtonDesign(Button cancelBtn) {
        //For Cancel Button
        cancelBtn.setAllCaps(this.cancelButtonTextAllCaps);

        if (cancelButtonText != null) {
            cancelBtn.setText(cancelButtonText);
        }

        if (cancelButtonTextColor != 0) {
            cancelBtn.setTextColor(cancelButtonTextColor);
        }

        if (cancelButtonBackground != null) {
            cancelBtn.setBackground(cancelButtonBackground);
        }
    }

    private void setLastDay(int newVal) {
        switch (newVal) {
            case 1:
                if (isLeapYear) {
                    setFebruaryValue(29);
                } else {
                    setFebruaryValue(28);
                }
                break;
            case 3:
                setMaxValueMonth();
                break;
            case 5:
                setMaxValueMonth();
                break;
            case 8:
                setMaxValueMonth();
                break;
            case 10:
                setMaxValueMonth();
                break;
            default:
                dateTv.setMaxValue(31);
                break;
        }
    }

    private void setFebruaryValue(int value) {
        dateTv.setMaxValue(value);
        if (dayValue != null) {
            if (Integer.parseInt(dayValue) == value + 1) {
                dateTv.setValue(value);
            }
        }
    }

    private void setMaxValueMonth() {
        dateTv.setMaxValue(30);
        if (dayValue == null) return;
        if (Integer.parseInt(dayValue) == 31) {
            dateTv.setValue(30);
        }
    }

    private void leapYearCheck(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    isLeapYear = true;
                    if (monthValue != null)
                        if (monthValue.equals("February")) setFebruaryValue(29);
                } else {
                    isLeapYear = false;
                    if (monthValue != null)
                        if (monthValue.equals("February")) setFebruaryValue(28);
                }
            } else {
                isLeapYear = true;
                if (monthValue != null)
                    if (monthValue.equals("February")) setFebruaryValue(29);
            }
        } else {
            isLeapYear = false;
            if (monthValue != null)
                if (monthValue.equals("February")) setFebruaryValue(28);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancelBtn) {
            try {
                dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.confirmBtn) {
            try {
                dismiss();
                bottomSheetCallBack.onPickupDate("" + dayValue + " " + monthValue + " " + yearValue);
                bottomSheetCallBack.onDay("" + dayValue);
                bottomSheetCallBack.onMonth(" " + monthValue);
                bottomSheetCallBack.onYear(" " + yearValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
