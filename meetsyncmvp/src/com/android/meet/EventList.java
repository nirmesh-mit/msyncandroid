package com.android.meet;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class EventList extends ListActivity {//ExpandableListActivity {
    private static final int ADD_ID = Menu.FIRST;
    private static final int CV_ID = Menu.FIRST + 1;
    private static final int REFRESH_ID = Menu.FIRST + 2;
    private static final int SETTINGS_ID = Menu.FIRST + 3;
    private static final int HELP_ID = Menu.FIRST + 4;
    
    private static final int JOIN_ID = Menu.FIRST + 5;
    
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_VIEWEVENT=1;
	
    private int numEvents;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);
        
        registerForContextMenu(getListView());
        
        numEvents = 0;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        
        // TODO(nirmesh): change hard coded string later
        menu.add(0, ADD_ID, 0, "New Event");
        menu.add(0, CV_ID, 0, "Calendar View");
        menu.add(0, REFRESH_ID, 0, "Refresh");
        menu.add(0, SETTINGS_ID, 0, "Settings");
        menu.add(0, HELP_ID, 0, "Help");
        
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case ADD_ID:
            	Intent i = new Intent(this, NewEvent.class);
                startActivityForResult(i, ACTIVITY_CREATE);
                return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i = new Intent(this, ViewEvent.class);
        
        // XXX: pass the appropriate identifier to the 
        // the activity being called
        // to be added along with backend code 
        // i.putExtra();
        startActivityForResult(i, ACTIVITY_VIEWEVENT);
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
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        
        numEvents++;
        
        switch(requestCode) {
            case ACTIVITY_CREATE:            	
            	/*MatrixCursor cursor = new MatrixCursor(new String[]{"EventName"});
            	
            	for (int i = 0; i < numEvents; i++) {
            		cursor.addRow(new String[]{"Event " + (i+1)});
            	}
            	
            	
                Cursor eventsCursor = cursor; 
                startManagingCursor(eventsCursor);

                String[] from = new String[]{"EventName"};

                // and an array of the fields we want to bind those fields to (in this case just text1)
                int[] to = new int[]{R.id.eventrowtext};

                // Now create a simple cursor adapter and set it to display
                SimpleCursorAdapter eventsList = 
                    new SimpleCursorAdapter(this, R.layout.event_row, eventsCursor, from, to);
             //   setListAdapter(eventsList);
              */
            	// XXX: actually need to fetch data from backend at this point
            	setListAdapter(new ArrayAdapter<String>(this, R.layout.event_row, new String[]{"Event1", "Event2"}));
                break;
            case ACTIVITY_VIEWEVENT:    
            	break;
        }
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, JOIN_ID, 0, "Join");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case JOIN_ID:
            	// Actually join the event 
            	// XXX: need to talk to the backend            	
            	Toast.makeText(this, "Successfully joined event", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

}
