package com.android.meet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Meetsyncalpha1 extends Activity {
	
    private static final int ACTIVITY_CREATE=0;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onConfirmClicked(View v) {
    	// XXX: must talk to backend or external service
    	// and verify authentication
        Intent i = new Intent(this, EventList.class);
        startActivityForResult(i, ACTIVITY_CREATE);
        
    }
    
    public void onLogoClicked(View v) {
    	Toast.makeText(this, "MEET Sync Alpha 1", Toast.LENGTH_SHORT).show();
    }
    
    public void onRemCheckboxClicked(View v) {
        // XXX: do something
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	// XXX: do something here
        super.onActivityResult(requestCode, resultCode, intent);
    }

}