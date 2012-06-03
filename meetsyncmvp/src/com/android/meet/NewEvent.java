package com.android.meet;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class NewEvent extends Activity {
    private TextView mDateDisplay;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;
	
    private TextView mTimeDisplay;
    private Button mPickTime;

    private int mHour;
    private int mMinute;

    static final int TIME_DIALOG_ID = 1;
    
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDateDisplay();
                }
            };
    
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = 
    		new TimePickerDialog.OnTimeSetListener() {
				
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					mHour = hourOfDay;
					mMinute = minute;
					
					updateTimeDisplay();					
				}
			};
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        
        // capture our View elements
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);

        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        
        // capture View elements
        mTimeDisplay = (TextView) findViewById(R.id.timeDisplay);
        mPickTime = (Button) findViewById(R.id.pickTime);
        
        // listener for time chooser
        mPickTime.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);				
			}
		});
        
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
        updateDateDisplay();
        updateTimeDisplay();
        
        Button syncitButton = (Button) findViewById(R.id.syncit);
        
        syncitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	//XXX: must talk to backend and store data
                setResult(RESULT_OK);//, mIntent);
                finish();
            }

        });
    }
    
	private void updateDateDisplay() {
	        mDateDisplay.setText(
	            new StringBuilder()
	                    // Month is 0 based so add 1
	                    .append(mMonth + 1).append("-")
	                    .append(mDay).append("-")
	                    .append(mYear).append(" "));
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, false);
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    mYear, mMonth, mDay);
		}
		
		return null;
	}



	private void updateTimeDisplay() {
		mTimeDisplay.setText(
				new StringBuilder()
				.append(pad(mHour)).append(":")
				.append(pad(mMinute))
				);
	}

	private static String pad(int mHour2) {
		if (mHour2 < 10) {
			return "0" + String.valueOf(mHour2);
		} else {
			return String.valueOf(mHour2);
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
}
